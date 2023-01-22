package xu.zhixuan.core.modules.Gloabl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import xu.zhixuan.core.events.Render.EventBlockOverlay;
import xu.zhixuan.core.modules.Gloabl.util.BlockOverlayEnum;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Mode;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;

import java.awt.*;

public class BlockOverlay extends Module {
    private Mode<Enum> mode;
    public static Option<Boolean> Chroma = new Option("Chroma", true);
    public static Numbers<Double> r = new Numbers("Red", 255.0, 0.0, 255.0, 1.0);
    public static Numbers<Double> g = new Numbers("Green", 255.0, 0.0, 255.0, 1.0);
    public static Numbers<Double> b = new Numbers("Blue", 255.0, 0.0, 255.0, 1.0);
    public static Numbers<Double> a = new Numbers("Alpha", 160.0, 0.0, 255.0, 1.0);
    public static Numbers<Double> speed = new Numbers("Speed", 3.0, 1.0, 10.0, 1.0);
    public static Numbers<Double> lineWidth = new Numbers("lineWidth", 2.0, 2.0, 5.0, 1.0);

    public BlockOverlay() {
        super("方块覆盖",0,0, ModuleType.Render,false);
        this.mode = new Mode("Mode", BlockOverlayEnum.values(), BlockOverlayEnum.Full);
        super.addValues(new Value[]{this.mode, Chroma, r, g, b, a, speed, lineWidth});
    }

    @EventTarget
    public void onRender3D(EventBlockOverlay event) {
        event.setCancelled(true);
        float red = ((Double)BlockOverlay.r.getValue()).floatValue() / 255.0F;
        float green = ((Double)BlockOverlay.g.getValue()).floatValue() / 255.0F;
        float blue = ((Double)BlockOverlay.b.getValue()).floatValue() / 255.0F;
        float alpha = ((Double)a.getValue()).floatValue() / 255.0F;
        if (this.mode.getValue() != BlockOverlayEnum.None) {
            GlStateManager.pushMatrix();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GL11.glLineWidth(((Double)lineWidth.getValue()).floatValue());
            if ((Boolean)Chroma.getValue()) {
                double millis = (double)((float)(System.currentTimeMillis() % (10000L / ((Double)speed.getValue()).longValue())) / (10000.0F / (float)((Double)speed.getValue()).longValue()));
                Color c = Color.getHSBColor((float)millis, 0.8F, 0.8F);
                float r = (float)c.getRed() / 255.0F;
                float g = (float)c.getGreen() / 255.0F;
                float b = (float)c.getBlue() / 255.0F;
                red = r;
                blue = b;
                green = g;
            }

            if (this.mode.getValue() == BlockOverlayEnum.Outline) {
                GlStateManager.color(red, green, blue, alpha);
                RenderGlobal.drawSelectionBoundingBox(event.getBB());
            }

            GlStateManager.color(red, green, blue, 1.0F);
            if (this.mode.getValue() == BlockOverlayEnum.Full) {
                RenderGlobal.drawSelectionBoundingBox(event.getBB());
                this.drawFilledBoundingBox(event.getBB(), red, green, blue, alpha);
            }

            if (this.mode.getValue() == BlockOverlayEnum.FullWithoutOutline) {
                this.drawFilledBoundingBox(event.getBB(), red, green, blue, alpha);
            }

            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            GlStateManager.popMatrix();
        }
    }

    private void drawFilledBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
    }
}
