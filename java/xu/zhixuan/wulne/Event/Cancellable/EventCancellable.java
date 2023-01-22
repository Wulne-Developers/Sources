package xu.zhixuan.wulne.Event.Cancellable;

import xu.zhixuan.wulne.Event.EventBase;

public abstract class EventCancellable extends EventBase implements Cancellable{
    private boolean cancelled;

    protected EventCancellable() {
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean state) {
        this.cancelled = state;
    }
}
