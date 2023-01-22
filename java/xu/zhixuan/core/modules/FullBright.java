package xu.zhixuan.core.modules;

import xu.zhixuan.core.events.Client.EventTick;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;

public class FullBright extends Module {
    public FullBright() {
        super("地图高亮",0,0, ModuleType.Render,false);
    }

    @EventTarget

    public void onTick(EventTick event) {
        mc.gameSettings.gammaSetting = 100.0f;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.gammaSetting = 0f;
    }
}
