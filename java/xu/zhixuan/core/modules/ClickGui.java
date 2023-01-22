package xu.zhixuan.core.modules;

import org.lwjgl.input.Keyboard;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.UI.ClickUI.ClickGuiScreenUI;

public class ClickGui extends Module {
    public static Numbers<Number> r = new Numbers<>("背景R", 94, 0, 255, 1);
    public static Numbers<Number> g = new Numbers<>("背景G", 115, 0, 255, 1);
    public static Numbers<Number> b = new Numbers<>("背景B", 255, 0, 255, 1);
    public ClickGui() {
        super("设置界面",0,0, ModuleType.Other,false);
        setKey(Keyboard.KEY_RSHIFT);
        addValues(r,g,b);
    }

    @Override
    public void onEnabled() {
        setEnabled(false);
        mc.displayGuiScreen(new ClickGuiScreenUI());
        super.onEnabled();
    }
}
