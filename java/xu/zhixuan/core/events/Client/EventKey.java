package xu.zhixuan.core.events.Client;

import xu.zhixuan.wulne.Event.EventBase;

public class EventKey extends EventBase {
    private int Key;
    public EventKey (int key) {
        this.Key = key;
    }

    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        Key = key;
    }
}
