package xu.zhixuan.core.modules;

import xu.zhixuan.core.events.Render.EventRender3D;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.Util.Chat.WingsRenderUtil;

public class Wings extends Module {
    public Wings() {
        super("龙翼", 0, 0, ModuleType.Render,false);
        this.addValues(Scale);
    }

    public static Numbers<Double> Scale = new Numbers("规模", 100.0, 60.0, 140.0, 1.0);
    WingsRenderUtil wings = new WingsRenderUtil();

    @EventTarget
    public void onRenderPlayer(EventRender3D event) {
        if (!mc.thePlayer.isInvisible()) {
            this.wings.renderWings(mc.thePlayer, event.getPartialTicks());
        }
    }
}
