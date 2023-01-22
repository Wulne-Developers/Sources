package xu.zhixuan.wulne.Module;

import net.minecraft.client.Minecraft;
import xu.zhixuan.wulne.Event.EventManager;
import xu.zhixuan.wulne.Module.Value.NewMode;
import xu.zhixuan.wulne.Module.Value.Value;
import xu.zhixuan.wulne.UI.Notification.Notification;
import xu.zhixuan.wulne.UI.Notification.NotificationManager;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Fonts.FontRenderer;
import xu.zhixuan.wulne.Util.HUD.DraggableComponent;
import xu.zhixuan.wulne.WulneJvav;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Module {
    public Minecraft mc = Minecraft.getMinecraft();
    public String name;
    public boolean enabled;
    public boolean isString;
    public java.util.List<Value> values = new ArrayList<>();
    public List<NewMode> newModes = new ArrayList<>();
    public DraggableComponent drag;
    public ModuleType moduleType;
    public int x;
    public int y;
    public int k;

    public Module(String name, int x, int y,ModuleType moduleType,boolean isString) {
        this.name = name;
        this.isString = isString;
        this.x = x;
        this.y = y;
        this.moduleType = moduleType;
        this.enabled = false;

        drag = new DraggableComponent(this.x,this.y, this.getWidth(), this.getHeight(), new Color(0, 0, 0, 0).getRGB());
    }

    public void setY(int y) {
        this.drag.setyPosition(y);
    }

    public void setX(int x) {
        this.drag.setxPosition(x);
    }

    public int getWidth() {
        return 50;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getHeight() {
        return 50;
    }

    public void setString(boolean string) {
        isString = string;
    }

    public boolean isString() {
        return isString;
    }


    public int getKey() {
        return k;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return drag.getxPosition();
    }

    public int getY() {
        return drag.getyPosition();
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) {
            onEnabled();
            NotificationManager.sendClientMessage(name + " 以启用", Notification.Type.INFO);
        } else {
            onDisable();
            NotificationManager.sendClientMessage(name + " 以关闭", Notification.Type.ERROR);
        }
    }

    public void setKey(int k) {
        this.k = k;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void onEnabled() {
        EventManager.register(this);
    }

    public void onDisable() {
        EventManager.unregister(this);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void addValues(Value... values) {
        Value[] v1 = values;
        int vl = values.length;

        for (int i = 0; i < vl; ++i) {
            Value value = v1[i];
            this.values.add(value);
        }
    }

    public void addValues(NewMode... values) {
        NewMode[] v1 = values;
        int vl = values.length;

        for (int i = 0; i < vl; ++i) {
            NewMode value = v1[i];
            this.newModes.add(value);
        }
    }

    public List<NewMode> getNewModes() {
        return newModes;
    }

    public List<Value> getValues() {
        return values;
    }

    public void draw() {
    }

    public void renderItem(int mouseX, int mouseY) {
        drag.draw(mouseX, mouseY);
    }
}
