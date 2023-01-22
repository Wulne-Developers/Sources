package xu.zhixuan.core.modules.Gloabl;

import xu.zhixuan.core.events.Render.EventChunkRender;
import xu.zhixuan.core.modules.Gloabl.util.AnimationHandler;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.WulneJvav;

public class CheckAnimation extends Module {
    public AnimationHandler animation = new AnimationHandler();
    public CheckAnimation() {
        super("区块动画",0,0, ModuleType.Render,false);
    }

    @EventTarget
    public void onChunkRender(EventChunkRender eventChunkRender) {
        CheckAnimation m = (CheckAnimation) WulneJvav.INSTANCE.getModuleManager().getModByClass(CheckAnimation.class);
        if (m.isEnabled()) {
            m.animation.preRender(eventChunkRender.getRenderChunkIn());
        }
    }
}
