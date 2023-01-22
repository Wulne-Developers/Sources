package xu.zhixuan.wulne.UI.Notification;

import java.awt.Color;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import xu.zhixuan.wulne.Util.Client.Colors;
import xu.zhixuan.wulne.Util.Client.MathUtil;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.RenderUtil;
import xu.zhixuan.wulne.Util.Render.RenderUtils;

public class Notification {

    private String message;
    private TimeHelper timer;
    private double lastY, posY, width, height, animationX;
    private int color, imageWidth;
    private ResourceLocation image;
    private long stayTime;


    public Notification(String message, Type type) {
        this.message = message;
        timer = new TimeHelper();
        timer.reset();
        width = FontLoaders.msFont18.getStringWidth(message) + 40;
        height = 20;
        animationX = 0;
        stayTime = 1500;
        imageWidth = 16;
        posY = -1;
        image = new ResourceLocation("Wulne/notification/" + type.name().toUpperCase() + ".png");
        if (type.equals(Type.INFO))
            color = new Color(86, 248, 64).getRGB();
        else if (type.equals(Type.ERROR))
            color = new Color(255, 1, 1).getRGB();
        else if (type.equals(Type.SUCCESS))
            color = new Color(86, 248, 64).getRGB();
        else if (type.equals(Type.WARNING))
            color = new Color(255, 229, 61).getRGB();
    }

    public String getMessage() {
        return message;
    }

    public void drawBlack2(double getY, double lastY) {
        width = FontLoaders.msFont18.getStringWidth(message) + 40;
        height = 22;
        imageWidth = 11;

        if (animationX < width && !timer.isDelayComplete(stayTime)) {
            animationX += 240f / Minecraft.getDebugFPS();
        }
        if (timer.isDelayComplete(stayTime)) {
            animationX -= 240f / Minecraft.getDebugFPS();
        }
        if (posY == -1)
            posY = getY;
        else
            posY = getY;

        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int x1 = (int) (res.getScaledWidth() - animationX), x2 = (int) (res.getScaledWidth() + animationX), y1 = (int) posY - 25, y2 = (int) (y1 + height);
        GL11.glPushMatrix();
        RenderUtils.drawRoundRect(x1, y1, x2, y2, MathUtil.reAlpha(color, 0.8f));
        this.drawImage(image, (int) (x1 + (height - imageWidth) / 2F) - 1, y1 + (int) ((height - imageWidth) / 2F), imageWidth, imageWidth);
        y1 += 1;
        if (message.contains(" Enabled")) {
            FontLoaders.msFont16.drawString(message.replace(" Enabled", ""), (float) (x1 + 19), (float) (y1 + height / 4F), -1);
            FontLoaders.msFont16.drawString(" Enabled", (float) (x1 + 20 + FontLoaders.msFont16.getStringWidth(message.replace(" Enabled", ""))), (float) (y1 + height / 4F), Colors.GREY.c);
        } else if (message.contains(" Disabled")) {
            FontLoaders.msFont16.drawString(message.replace(" Disabled", ""), (float) (x1 + 19), (float) (y1 + height / 4F), -1);
            FontLoaders.msFont16.drawString(" Disabled", (float) (x1 + 20 + FontLoaders.msFont16.getStringWidth(message.replace(" Disabled", ""))), (float) (y1 + height / 4F), Colors.GREY.c);
        } else {
            FontLoaders.msFont16.drawString(message, (float) (x1 + 20), (float) (y1 + height / 4F), -1);
        }
        GL11.glColor3f(1, 1, 1);
        GL11.glPopMatrix();
    }

    public boolean shouldDelete() {
        //return false;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        return timer.isDelayComplete(stayTime) && animationX <= 0;
    }

    private boolean isFinished() {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        return timer.isDelayComplete(stayTime);
    }

    public double getHeight() {
        return height;
    }

    public enum Type {
        SUCCESS, INFO, WARNING, ERROR
    }

    public double getAnimationState(double animation, double finalState, double speed) {
        float add = (float) (RenderUtil.delta * speed);
        if (animation < finalState) {
            if (animation + add < finalState)
                animation += add;
            else
                animation = finalState;
        } else {
            if (animation - add > finalState)
                animation -= add;
            else
                animation = finalState;
        }
        return animation;
    }

    public void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void drawImage(ResourceLocation image, int x, int y, int width, int height, Color color) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtil.color(color.getRGB());
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
}
