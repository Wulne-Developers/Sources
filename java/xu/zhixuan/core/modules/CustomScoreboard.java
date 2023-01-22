package xu.zhixuan.core.modules;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;

public class CustomScoreboard extends Module {
    public static Option rednumbers = new Option("红色数字",true);
    public static Option bor = new Option("透明背景",true);
    public CustomScoreboard() {
        super("自定义计分板",0,0, ModuleType.Render,false);
        addValues(new Value[]{rednumbers,bor});
    }
}
