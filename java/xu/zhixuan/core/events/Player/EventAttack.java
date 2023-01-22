package xu.zhixuan.core.events.Player;

import net.minecraft.entity.Entity;
import xu.zhixuan.wulne.Event.EventBase;

public class EventAttack extends EventBase {
    private Entity target;
    public EventAttack(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
