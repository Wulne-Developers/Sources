package xu.zhixuan.core.modules;

import xu.zhixuan.core.events.Render.EventRender2D;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.UI.Notification.NotificationManager;

public class Notification extends Module {
    public Notification() {
        super("客户端启用提示",0,0, ModuleType.Render,false);
    }

    @EventTarget
    public void onRender(EventRender2D e) {
        NotificationManager.INSTANCE.drawNotifications();
    }
}
