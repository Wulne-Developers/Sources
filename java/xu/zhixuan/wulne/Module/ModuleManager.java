package xu.zhixuan.wulne.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import org.lwjgl.input.Keyboard;
import xu.zhixuan.core.events.Client.EventDraggableHUD;
import xu.zhixuan.core.events.Client.EventKey;
import xu.zhixuan.core.events.Render.EventRender2D;
import xu.zhixuan.core.modules.*;
import xu.zhixuan.core.modules.Gloabl.*;
import xu.zhixuan.wulne.Event.EventManager;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Value.Mode;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;
import xu.zhixuan.wulne.Util.Fonts.ChromaText;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.HUDRenderUtil;
import xu.zhixuan.wulne.Wulne;
import xu.zhixuan.wulne.WulneJvav;
import xu.zhixuan.wulne.config.FileManager;

import java.io.File;
import java.util.*;

public class ModuleManager {
    public List<Module> modules = new ArrayList<>();
    public static Map<Module, Object> pluginModsList = new HashMap<>();
    public static Map<Module, Object> disabledPluginList = new HashMap<>();
    protected boolean hovered;

    public void load() {
        modules.add(new Keystroke());
        modules.add(new ClickGui());
        modules.add(new Sprint());
        modules.add(new Chat());
        modules.add(new Wings());
        modules.add(new ModAmrror());
        modules.add(new CPSMod());
        modules.add(new InventoryHUD());
        modules.add(new noCommand());
        modules.add(new Notification());
        modules.add(new BlockAni());
        modules.add(new FullBright());
        modules.add(new PositionStatu());
        modules.add(new MoreParticle());
        modules.add(new CheckAnimation());
        modules.add(new Zoom());
        modules.add(new ItemPichyec());
        modules.add(new Perspective());
        modules.add(new ContainerAnimations());
        modules.add(new CustomScoreboard());
        modules.add(new BlockOverlay());
        modules.add(new AutoGG());
        modules.add(new WorldTime());
        modules.add(new MemoryManager());
        modules.add(new TNT());

        Wulne.pluginManager.onModuleManagerLoad(this);
        this.sortModules();
        this.readSettings();
        EventManager.register(this);
    }

    public List<Module> getModsByCategory(ModuleType m) {
        List<Module> findList = new ArrayList<>();
        for (Module mod : modules) {
            if (mod.getModuleType() == m) {
                findList.add(mod);
            }
        }
        return findList;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public void sortModules() {
        modules.sort((m1, m2) -> {
            if (m1.getName().toCharArray()[0] > m2.getName().toCharArray()[0]) {
                return 1;
            }
            return -1;
        });
    }

    public void addPluginModule(Module mod, Object plugin) {
        pluginModsList.put(mod, plugin);
        modules.add(mod);
    }

    public void renderModules() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.draw();
            }
        }
    }

    public Module getModByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls) {
                continue;
            }
            return m;
        }
        return null;
    }

    @EventTarget
    public void onRender(EventRender2D eventRender2D) {
        if (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat)) {
            renderModules();
        }
    }

    @EventTarget
    public void onDraggable(EventDraggableHUD eventDraggable) {
        for (Module module : WulneJvav.INSTANCE.moduleManager.modules) {
            if (module.isString) {
                if (module.isEnabled()) {
                    module.renderItem(eventDraggable.getMouseX(), eventDraggable.getMouseY());
                    this.hovered = eventDraggable.getMouseX() >= module.getX() && eventDraggable.getMouseX() <= module.getX() + module.getWidth() && eventDraggable.getMouseY() >= module.getY() && eventDraggable.getMouseY() <= module.getY() + module.getHeight();
                    if (hovered) {
                        HUDRenderUtil.drawHollowRect(module.getX(), module.getY(), module.getWidth(), module.getHeight(), ChromaText.drawChroma(10));
                        FontLoaders.msFont14.drawString(module.name + " " + "x：" + module.getX() + " " + "y：" + module.getY(), module.getX(), module.getY() - FontLoaders.msFont14.FONT_HEIGHT, -1);
                        continue;
                    } else {
                        HUDRenderUtil.drawHollowRect(module.getX(), module.getY(), module.getWidth(), module.getHeight(), ChromaText.drawChroma(10));
                    }
                }
            }
        }
    }

    @EventTarget
    public void onKey(EventKey e) {
        for (Module module : modules) {
            if (module.getKey() == e.getKey()) {
                module.setEnabled(!module.isEnabled());
            }
        }
    }

    public Module getModuleByName(String name) {
        for (Module m : modules) {
            if (!m.getName().equalsIgnoreCase(name))
                continue;
            return m;
        }
        return null;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void readSettings() {
        List<String> enabled = FileManager.read("Module/Enabled.txt");
        Iterator var2 = enabled.iterator();

        while (var2.hasNext()) {
            String v = (String) var2.next();
            Module m = getModuleByName(v);
            if (m != null) {
                m.setEnabled(true);
            }
        }
        List<String> binds = FileManager.read("Module/Binds.txt");
        for (String v : binds) {
            String name = v.split(":")[0];
            String bind = v.split(":")[1];
            Module m = WulneJvav.INSTANCE.moduleManager.getModuleByName(name);
            if (m == null)
                continue;
            m.setKey(Keyboard.getKeyIndex(bind.toUpperCase()));
        }
        List<String> x = FileManager.read("Module/X.txt");
        for (String v : x) {
            String name = v.split(":")[0];
            String dragX = v.split(":")[1];
            Module m = WulneJvav.INSTANCE.moduleManager.getModuleByName(name);
            if (m == null)
                continue;
            m.setX(Integer.parseInt(dragX));
        }
        List<String> y = FileManager.read("Module/Y.txt");
        for (String v : y) {
            String name = v.split(":")[0];
            String dragY = v.split(":")[1];
            Module m = WulneJvav.INSTANCE.moduleManager.getModuleByName(name);
            if (m == null)
                continue;
            m.setY(Integer.parseInt(dragY));
        }
        List<String> vals = FileManager.read("Module/Values.txt");
        for (String v : vals) {
            String name = v.split(":")[0];
            String values = v.split(":")[1];
            Module m = WulneJvav.INSTANCE.moduleManager.getModuleByName(name);
            if (m == null)
                continue;
            for (Value value : m.getValues()) {
                if (!value.getName().equalsIgnoreCase(values))
                    continue;
                if (value instanceof Option) {
                    value.setValue(Boolean.parseBoolean(v.split(":")[2]));
                    continue;
                }
                if (value instanceof Numbers) {
                    value.setValue(Double.parseDouble(v.split(":")[2]));
                    continue;
                }
                ((Mode) value).setMode(v.split(":")[2]);
            }
        }
    }

    public void saveSettings() {
        String enable = "";
        Iterator var7 = WulneJvav.INSTANCE.moduleManager.modules.iterator();

        while (var7.hasNext()) {
            Module m = (Module) var7.next();
            if (m.isEnabled()) {
                enable = enable + String.format("%s%s", m.getName(), System.lineSeparator());
            }
        }

        FileManager.save("Module/Enabled.txt", enable, false);
        String x = "";
        StringBuilder content = new StringBuilder();

        for (Module m : modules) {
            content.append(
                    String.format("%s:%s%s", m.getName(), Keyboard.getKeyName(m.getKey()), System.lineSeparator()));
        }
        FileManager.save("Module/Binds.txt", content.toString(), false);
        String values = "";
        for (Module m : WulneJvav.INSTANCE.moduleManager.getModules()) {
            for (Value v : m.getValues()) {
                values = String.valueOf(values)
                        + String.format("%s:%s:%s%s", m.getName(), v.getName(), v.getValue(), System.lineSeparator());
            }
        }
        FileManager.save("Module/Values.txt", values, false);
        String init = "";
        for (Module m : WulneJvav.INSTANCE.moduleManager.modules) {
            init = String.valueOf(init)
                    + String.format("%s:%s%s", m.getName(), m.getX(), System.lineSeparator());
        }
        FileManager.save("Module/X.txt", init, false);
        String inits = "";
        for (Module m : WulneJvav.INSTANCE.moduleManager.modules) {
            inits = String.valueOf(inits)
                    + String.format("%s:%s%s", m.getName(), m.getY(), System.lineSeparator());
        }
        FileManager.save("Module/Y.txt", inits, false);
    }
}
