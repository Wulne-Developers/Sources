package xu.zhixuan.core.events.Render;

import net.optifine.shaders.Shaders;
import xu.zhixuan.wulne.Event.EventBase;

public class EventRender3D extends EventBase {
    public static float ticks;
    private boolean isUsingShaders;

    public EventRender3D() {
        this.isUsingShaders = Shaders.getShaderPackName() != null;
    }

    public EventRender3D(float ticks) {
        EventRender3D.ticks = ticks;
        this.isUsingShaders = Shaders.getShaderPackName() != null;
    }

    public float getPartialTicks() {
        return ticks;
    }

    public void setPartialTicks(float ticks) {
        EventRender3D.ticks = ticks;
    }

    public boolean isUsingShaders() {
        return this.isUsingShaders;
    }
}
