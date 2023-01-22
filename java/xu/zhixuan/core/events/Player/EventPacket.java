package xu.zhixuan.core.events.Player;

import net.minecraft.network.Packet;
import xu.zhixuan.wulne.Event.Cancellable.EventCancellable;

public class EventPacket extends EventCancellable {
    private Packet packet;
    public EventPacket(Packet e) {
        this.packet = e;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
