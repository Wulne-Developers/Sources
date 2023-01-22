package xu.zhixuan.core.commands;

import xu.zhixuan.wulne.Command.Command;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Util.Client.PlayerUtil;
import xu.zhixuan.wulne.WulneJvav;

public class toggleCommand extends Command {
    public toggleCommand() {
        super("toggleCommand",new String[] {"t","toggle"});
    }

    @Override
    public void run(String[] args) {
        if(args.length == 0){
            PlayerUtil.sendMessage(".Toggle <功能名称>");
            return;
        }
        String moduleName = args[0];
        Module mod = WulneJvav.INSTANCE.moduleManager.getModuleByName(moduleName);
        if(mod == null){
            PlayerUtil.sendMessage("Module \"" + args[0] + "\" Not Found!");
            return;
        }
        mod.setEnabled(!mod.isEnabled());
        PlayerUtil.sendMessage(mod.getName() + " was toggled");
    }
}
