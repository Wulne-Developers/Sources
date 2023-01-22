package xu.zhixuan.wulne.Util.Chat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import xu.zhixuan.core.modules.Wings;

public class WingsRenderUtil extends ModelBase {
    private Minecraft mc = Minecraft.getMinecraft();
    private ResourceLocation location = new ResourceLocation("Wulne/wings.png");
    private ModelRenderer wing;
    private ModelRenderer wingTip;

    public WingsRenderUtil() {
        this.setTextureOffset("wing.bone", 0, 0);
        this.setTextureOffset("wing.skin", -10, 8);
        this.setTextureOffset("wingtip.bone", 0, 5);
        this.setTextureOffset("wingtip.skin", -10, 18);
        this.wing = new ModelRenderer(this, "wing");
        this.wing.setTextureSize(30, 30);
        this.wing.setRotationPoint(-2.0F, 0.0F, 0.0F);
        this.wing.addBox("bone", -10.0F, -1.0F, -1.0F, 10, 2, 2);
        this.wing.addBox("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10);
        this.wingTip = new ModelRenderer(this, "wingtip");
        this.wingTip.setTextureSize(30, 30);
        this.wingTip.setRotationPoint(-10.0F, 0.0F, 0.0F);
        this.wingTip.addBox("bone", -10.0F, -0.5F, -0.5F, 10, 1, 1);
        this.wingTip.addBox("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10);
        this.wing.addChild(this.wingTip);
    }

    public void renderWings(EntityPlayer player, float partialTicks) {
        if (this.mc.gameSettings.thirdPersonView != 0) {
            double scale = (Double) Wings.Scale.getValue() / 100.0;
            double rotate = (double)this.interpolate(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);
            GL11.glPushMatrix();
            GL11.glScaled(-scale, -scale, scale);
            GL11.glRotated(180.0 + rotate, 0.0, 1.0, 0.0);
            GL11.glTranslated(0.0, -1.25 / scale, 0.0);
            GL11.glTranslated(0.0, 0.0, 0.2 / scale);
            if (player.isSneaking()) {
                GL11.glTranslated(0.0, 0.125 / scale, 0.0);
            }

            float[] colors = new float[]{1.0F, 1.0F, 1.0F};
            GL11.glColor3f(colors[0], colors[1], colors[2]);
            this.mc.getTextureManager().bindTexture(this.location);

            for(int j = 0; j < 2; ++j) {
                GL11.glEnable(2884);
                float f11 = (float)(System.currentTimeMillis() % 1000L) / 1000.0F * 3.1415927F * 2.0F;
                this.wing.rotateAngleX = (float)Math.toRadians(-80.0) - (float)Math.cos((double)f11) * 0.2F;
                this.wing.rotateAngleY = (float)Math.toRadians(20.0) + (float)Math.sin((double)f11) * 0.4F;
                this.wing.rotateAngleZ = (float)Math.toRadians(20.0);
                this.wingTip.rotateAngleZ = -((float)(Math.sin((double)(f11 + 2.0F)) + 0.5)) * 0.75F;
                this.wing.render(0.0625F);
                GL11.glScalef(-1.0F, 1.0F, 1.0F);
                if (j == 0) {
                    GL11.glCullFace(1028);
                }
            }

            GL11.glCullFace(1029);
            GL11.glDisable(2884);
            GL11.glColor3f(255.0F, 255.0F, 255.0F);
            GL11.glPopMatrix();
        }
    }

    private float interpolate(float yaw1, float yaw2, float percent) {
        float f = (yaw1 + (yaw2 - yaw1) * percent) % 360.0F;
        if (f < 0.0F) {
            f += 360.0F;
        }

        return f;
    }
}
