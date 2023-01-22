package xu.zhixuan.wulne.Command;

import xu.zhixuan.core.commands.BindCommand;
import xu.zhixuan.core.commands.HelpCommand;
import xu.zhixuan.core.commands.toggleCommand;
import xu.zhixuan.wulne.Wulne;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    public static List<Command> commands = new ArrayList<>();
    public static Map<Command, Object> pluginCommands = new HashMap<>();

    public void load() {
        addCommand(new HelpCommand());
        addCommand(new toggleCommand());
        addCommand(new BindCommand());

        Wulne.pluginManager.onCommandManagerLoad(this);
    }

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public static Command getCommandByName(String name) {
        for (Command cmd : commands) {
            if (cmd == null) {
                continue;
            }
            String[] names = cmd.getNames();
            for (String myName : names) {
                if (myName.equalsIgnoreCase(name)) {
                    return cmd;
                }
            }
        }
        return null;
    }
}
