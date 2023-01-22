package xu.zhixuan.core.events.Client;

import xu.zhixuan.wulne.Event.Cancellable.EventCancellable;

public class EventChat extends EventCancellable {
    public String msg;
    public boolean cancelled;

    public EventChat(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }

    public boolean getCancelled(){
        return this.cancelled;
    }
}
