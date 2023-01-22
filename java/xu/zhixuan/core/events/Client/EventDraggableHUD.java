package xu.zhixuan.core.events.Client;

import xu.zhixuan.wulne.Event.EventBase;

public class EventDraggableHUD extends EventBase {
    private int mouseX;
    private int mouseY;

    public EventDraggableHUD(int mouseX,int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }
}
