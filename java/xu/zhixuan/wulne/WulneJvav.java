package xu.zhixuan.wulne;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import xu.zhixuan.core.betterfps.BetterFpsClient;
import xu.zhixuan.core.events.Client.EventClientInit;
import xu.zhixuan.core.events.Client.EventClientStart;
import xu.zhixuan.core.events.Client.EventClientStop;
import xu.zhixuan.core.events.Client.EventTick;
import xu.zhixuan.core.memoryfix.UpdateChecker;
import xu.zhixuan.core.mousetweaks.Constants;
import xu.zhixuan.core.mousetweaks.Main;
import xu.zhixuan.core.mousetweaks.OnTickMethod;
import xu.zhixuan.wulne.Command.CommandManager;
import xu.zhixuan.wulne.Event.EventCategory;
import xu.zhixuan.wulne.Event.EventManager;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.ModuleManager;
import xu.zhixuan.wulne.UI.Account.AltManager;
import xu.zhixuan.wulne.UI.ClickUI.ClickGuiScreenUI;
import xu.zhixuan.wulne.UI.loading.SplashProgress;
import xu.zhixuan.wulne.config.ClickGui.Drag;
import xu.zhixuan.wulne.config.FileManager;
import java.io.File;

public class WulneJvav {
    public static String CLIENT_NAME = "WulneClient";
    public static String CLIENT_VERSION = "Release 7.1 Minecraft 1.8.9";
    public static WulneJvav INSTANCE = new WulneJvav();
    public static File file = new File("WulneClient");
    public static File Settingfile = new File("WulneClient/Settings");
    public static File Modulefile = new File("WulneClient/Module");
    public static File ClickGuifile = new File("WulneClient/ClickGUi");
    public static File Plugin = new File("WulneClient/Plugins");
    public static int flag = -666;
    public static boolean needReload = false;
    private IChatComponent updateMessage;
    private int messageDelay = 0;
    private static final Logger getLogger = LogManager.getLogger();
    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public AltManager altManager;
    public ClickGuiScreenUI click;
    public Drag drag;

    public void onInit() {
        if (!file.exists()) {
            file.mkdirs();
        }

        if (!Settingfile.exists()) {
            Settingfile.mkdirs();
        }

        if (!Modulefile.exists()) {
            Modulefile.mkdirs();
        }

        if (!ClickGuifile.exists()) {
            ClickGuifile.mkdirs();
        }

        if (!Plugin.exists()) {
            Plugin.mkdirs();
        }
        Display.setTitle(CLIENT_NAME + " " + CLIENT_VERSION + " || by Frish2021");
        getLogger.info("WulneClient Initing");
        SplashProgress.setProgress(1,"Client - onInitFunc");
        EventManager.call(new EventClientInit());
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        drag = new Drag();
    }
    public void onStart() {
        FileManager.init();
        Display.setTitle(CLIENT_NAME + " " + CLIENT_VERSION);
        EventManager.register(this);
        EventManager.call(new EventClientStart());
        getLogger.info("WulneClient Starting");
        Main.initialize(Constants.EntryPoint.FORGE);
        BetterFpsClient.start(Minecraft.getMinecraft());
        AltManager.init();
        moduleManager.readSettings();
        moduleManager.load();
        drag.load();
        commandManager.load();
    }

    public void start() {
        Wulne.pluginManager.onClientStart(this);
    }
    public void onStop() {
        EventManager.unregister(this);
        EventManager.call(new EventClientStop());
        getLogger.info("WulneClient Stopping");
        moduleManager.saveSettings();
        drag.save();
        Wulne.pluginManager.onClientStop(this);
    }

    @EventTarget
    public void onTick(EventTick e) {
        if (Main.onTickMethod == OnTickMethod.FORGE && e.eventCategory == EventCategory.PRE) {
            Main.onUpdateInGame();
        }
        if (updateMessage != null && Minecraft.getMinecraft().thePlayer != null) {
            if (++messageDelay == 80) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(updateMessage);
                updateMessage = null;
            }
        }
    }

    @EventTarget
    public void Start(EventClientStart event) {
        String updateUrl = System.getProperty("memoryfix.updateurl", "%%UPDATE_URL%%");
        UpdateChecker updater = new UpdateChecker(updateUrl, res -> updateMessage = res.getUpdateMessage());
        updater.start();
    }

    public AltManager getAltManager() {
        return altManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static WulneJvav getINSTANCE() {
        return INSTANCE;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
