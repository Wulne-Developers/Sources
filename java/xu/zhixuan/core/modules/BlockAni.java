package xu.zhixuan.core.modules;

import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Mode;

public class BlockAni extends Module {
    public static Mode mod = new Mode<>("防砍模式",Block.values(),Block.Null);
    public BlockAni() {
        super("防砍动画",0,0, ModuleType.Render,false);
        addValues(mod);
    }

    public enum Block {
        Swing,Null
    }
}
