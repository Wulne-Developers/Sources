package xu.zhixuan.core.events.Render;

import net.minecraft.util.AxisAlignedBB;
import xu.zhixuan.wulne.Event.Cancellable.EventCancellable;

public class EventBlockOverlay extends EventCancellable {
    AxisAlignedBB axisalignedbb;

    public EventBlockOverlay(AxisAlignedBB axisalignedbb) {
        this.axisalignedbb = axisalignedbb;
    }

    public AxisAlignedBB getBB() {
        return this.axisalignedbb;
    }

    public void setBB(AxisAlignedBB axisalignedbb) {
        this.axisalignedbb = axisalignedbb;
    }
}
