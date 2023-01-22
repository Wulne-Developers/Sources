package xu.zhixuan.core.commands;

import xu.zhixuan.wulne.Command.Command;
import xu.zhixuan.wulne.Util.Client.PlayerUtil;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("Help",new String[] {"help","h"});
    }

    @Override
    public void run(String[] args) {
        PlayerUtil.sendMessage("---------------Help---------------");
        PlayerUtil.sendMessage(".bind <Module> <Key>绑定按键");
        PlayerUtil.sendMessage(".toggle <Module> 启用模块");
        PlayerUtil.sendMessage(".help 客户端帮助菜单");
    }
}
