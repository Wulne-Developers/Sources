package xu.zhixuan.core.commands;

import org.lwjgl.input.Keyboard;
import xu.zhixuan.wulne.Command.Command;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Util.Client.PlayerUtil;
import xu.zhixuan.wulne.WulneJvav;

public class BindCommand extends Command {
    public BindCommand() {
        super("BindCommand",new String[] {"b","bind"});
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            PlayerUtil.sendMessage(".bind <Module> <Key>");
            return;
        }

        Module mod = WulneJvav.INSTANCE.moduleManager.getModuleByName(args[0]);
        if (mod == null) {
            PlayerUtil.sendMessage("Module \"" + args[0] + "\" Not Found!");
            return;
        }

        int keyNum;
        mod.setKey(keyNum = Keyboard.getKeyIndex(args[1].toUpperCase()));
        args[1] = keyNum == 0 ? "None" : args[1].toUpperCase();
        PlayerUtil.sendMessage("Module \"" + mod.getName() + "\" Was Bound to " + args[1]);
    }
}
