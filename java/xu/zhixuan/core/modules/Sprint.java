package xu.zhixuan.core.modules;

import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;

public class Sprint extends Module {
    public Sprint() {
        super("自动疾跑",0,0, ModuleType.Movement,false);
    }

    @Override
    public void onEnabled() {
        mc.gameSettings.keyBindSprint.Doing = true;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.keyBindSprint.Doing = false;
    }
}
