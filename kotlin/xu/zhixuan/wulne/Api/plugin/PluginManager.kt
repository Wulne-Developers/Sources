package xu.zhixuan.wulne.Api.plugin

import com.alibaba.fastjson2.JSON
import xu.zhixuan.wulne.Command.CommandManager
import xu.zhixuan.wulne.Module.ModuleManager
import xu.zhixuan.wulne.Util.Client.FileUtil
import xu.zhixuan.wulne.WulneJvav
import java.io.File
import java.net.URL
import java.net.URLClassLoader

class PluginManager {
    val file = File("WulneClient/Plugins")
    val plugins : ArrayList<PluginBase> = ArrayList()
    var gameversion: String? = null
    var name: String? = null
    var version: String? = null
    var author: String? = null

    init {
        loadPlugin()

        if (plugins.isNotEmpty()) {
            for (p in plugins) {
                println("加载插件：" + name)
            }
        }
    }

    private fun loadPlugin() {
        file.mkdirs();
        if (file.listFiles().isNotEmpty()) {
            for (f in file.listFiles()) {
                if (f.name.substring(f.name.lastIndexOf(".") + 1) == "jar") {
                    val u = URLClassLoader(arrayOf<URL>(f.toURL()),Thread.currentThread().contextClassLoader)
                    val json = JSON.parseObject(FileUtil.readResource(u.getResourceAsStream("plugin.json")))
                    try {
                        gameversion = json["gameversion"].toString()
                        version = json["version"].toString()
                        author = json["author"].toString()
                        name = json["name"].toString()
                    } catch (e : NullPointerException) {
                        println("有个别插件加载失败,原因: 插件配置文件没有写完整")
                    }
                    if (gameversion == "1.8.9") {
                        var instaces : PluginBase = u.loadClass(json["mainClass"].toString()).newInstance() as PluginBase
                        plugins.add(instaces)
                    } else {
                        println("该插件游戏版本不是1.8.9")
                    }
                }
            }
        }
    }

    fun onModuleManagerLoad(modManager: ModuleManager) {
        for (plugin in plugins) {
            plugin.getModuleManager(modManager)
        }
    }

    fun onCommandManagerLoad(commandManager: CommandManager) {
        for (plugin in plugins) {
            plugin.getCommandManager(commandManager)
        }
    }

    fun onClientStart(wulneJvav: WulneJvav) {
        for (plugin in plugins) {
            plugin.onStart(wulneJvav)
        }
    }

    fun onClientStop(wulneJvav: WulneJvav) {
        for (plugin in plugins) {
            plugin.onStop(wulneJvav)
        }
    }
}