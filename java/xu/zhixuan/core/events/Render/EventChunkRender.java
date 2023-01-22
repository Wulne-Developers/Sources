package xu.zhixuan.core.events.Render;

import net.minecraft.client.renderer.chunk.RenderChunk;
import xu.zhixuan.wulne.Event.EventBase;

public class EventChunkRender extends EventBase {
    private RenderChunk renderChunkIn;

    public EventChunkRender (RenderChunk renderChunkIn) {
        this.renderChunkIn = renderChunkIn;
    }

    public RenderChunk getRenderChunkIn() {
        return renderChunkIn;
    }

    public void setRenderChunkIn(RenderChunk renderChunkIn) {
        this.renderChunkIn = renderChunkIn;
    }
}
