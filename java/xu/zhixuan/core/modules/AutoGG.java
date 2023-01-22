package xu.zhixuan.core.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;

import java.util.Arrays;

public class AutoGG extends Module {
    public static final AutoGG INSTANCE = new AutoGG();
    private long lastTrigger;
    public AutoGG() {
        super("自动GG",0,0, ModuleType.Other,false);
    }

    public void onChat(IChatComponent message) {
        if (Minecraft.getMinecraft().getCurrentServerData() != null && Minecraft.getMinecraft().getCurrentServerData().serverIP != null && System.currentTimeMillis() > this.lastTrigger + 1000L && Arrays.asList(getHypixelTrigger().split("\n")).stream().anyMatch((trigger) -> {
            return message.getUnformattedText().contains(trigger);
        })) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("gg");
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages("gg");
            this.lastTrigger = System.currentTimeMillis();
        }

    }

    public static String getHypixelTrigger() {
        return "? TheBridge ? Match Recap - Games Played: \n Total Points: \n Kills: \n Deaths: \n Victories: \n";
    }
}
