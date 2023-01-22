package xu.zhixuan.core.events.Client;

import xu.zhixuan.wulne.Event.EventBase;
import xu.zhixuan.wulne.Event.EventCategory;

public class EventTick extends EventBase {
    public EventCategory eventCategory;

    public EventTick(EventCategory c) {
        this.eventCategory = c;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
}
