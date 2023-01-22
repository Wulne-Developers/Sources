package xu.zhixuan.core.modules.Gloabl;

import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Option;

public class ContainerAnimations extends Module {
	public static Option logout = new Option<>("显示左下角客户端名字",true);
	public static Option  ronqidonghua = new Option<>("容器动画",true);
	public ContainerAnimations() {
		super("容器显示设置", 0,0, ModuleType.Render,false);
		this.addValues(logout,ronqidonghua);
	}
}
