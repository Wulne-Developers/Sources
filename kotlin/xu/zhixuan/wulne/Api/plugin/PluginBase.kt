package xu.zhixuan.wulne.Api.plugin

import xu.zhixuan.wulne.Command.CommandManager
import xu.zhixuan.wulne.Module.ModuleManager
import xu.zhixuan.wulne.WulneJvav

interface PluginBase {
    fun getModuleManager(modManager: ModuleManager)
    fun getCommandManager(cmdManager: CommandManager)
    fun onStart(wulne: WulneJvav)
    fun onStop(wulne: WulneJvav)
}