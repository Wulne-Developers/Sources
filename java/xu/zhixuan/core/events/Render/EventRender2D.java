package xu.zhixuan.core.events.Render;

import net.minecraft.client.gui.ScaledResolution;
import xu.zhixuan.wulne.Event.EventBase;

public class EventRender2D extends EventBase {
    private ScaledResolution sr;
    private float partialTicks;

    public EventRender2D(ScaledResolution sr) {
        this.sr = sr;
    }

    public EventRender2D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public EventRender2D(float partialTicks,ScaledResolution sr) {
        this.sr = sr;
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public ScaledResolution getSr() {
        return sr;
    }

    public void setSr(ScaledResolution sr) {
        this.sr = sr;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
