package xu.zhixuan.wulne.UI.ClickUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import xu.zhixuan.core.modules.ClickGui;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Mode;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;
import xu.zhixuan.wulne.Util.Chat.AnimationUtils;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.ColorUtils;
import xu.zhixuan.wulne.Util.Render.RenderUtil;
import xu.zhixuan.wulne.Util.Render.RenderUtils;
import xu.zhixuan.wulne.Util.timer.TimerUtil;
import xu.zhixuan.wulne.WulneJvav;
import xu.zhixuan.wulne.config.FileManager;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ClickGuiScreenUI extends GuiScreen {
    static ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public static float width = 600;
    public static float height = 350;
    public static float windowX = sr.getScaledWidth() / 2f - width / 2, windowY = sr.getScaledHeight() / 2f - height / 2;
    public static float bgX = sr.getScaledWidth() / 2f, bgY = sr.getScaledHeight() / 2f;
    public static Module currentMod;
    public static float mRole, vRole;
    public TimerUtil timer = new TimerUtil();
    public float dragX, dragY;
    public static ModuleType modCategory = ModuleType.Render;
    public float bg;
    public float lastPercent;
    public float percent;
    public float percent2;
    public float lastPercent2;
    public float outro;
    public float lastOutro;
    public boolean yonghu = false;
    private boolean close = false;
    private boolean closed;
    public static AnimationUtils bgAnim = new AnimationUtils();

    public static float getWindowY() {
        return windowY;
    }

    public static float getWindowX() {
        return windowX;
    }

    public static void setWindowX(float windowX) {
        ClickGuiScreenUI.windowX = windowX;
    }

    public static void setWindowY(float windowY) {
        ClickGuiScreenUI.windowY = windowY;
    }

    @Override
    public void initGui() {
        super.initGui();
        percent = 1.33f;
        lastPercent = 1f;
        percent2 = 1.33f;
        lastPercent2 = 1f;
        outro = 1;
        lastOutro = 1;
        playPressSound(this.mc.getSoundHandler());
    }

    public void playPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }

    public void drawbg() {
        bg = bgAnim.animate(sr.getScaledWidth(), bg, 0.01f);
        RenderUtil.drawCircle(bgX, bgY, bg, ColorUtils.reAlpha(new Color(ClickGui.r.getValue().intValue(),ClickGui.g.getValue().intValue(),ClickGui.b.getValue().intValue()).getRGB(), 0.1f));
    }

    public float smoothTrans(double current, double last) {
        return (float) (current + (last - current) / (Minecraft.getDebugFPS() / 10));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawbg();

        ScaledResolution sr = new ScaledResolution(mc);


        float outro = smoothTrans(this.outro, lastOutro);
        if (mc.currentScreen == null) {
            GlStateManager.translate(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);
            GlStateManager.scale(outro, outro, 0);
            GlStateManager.translate(-sr.getScaledWidth() / 2, -sr.getScaledHeight() / 2, 0);
        }


        //animation
        percent = smoothTrans(this.percent, lastPercent);
        percent2 = smoothTrans(this.percent2, lastPercent2);


        if (percent > 0.98) {
            GlStateManager.translate(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);
            GlStateManager.scale(percent, percent, 0);
            GlStateManager.translate(-sr.getScaledWidth() / 2, -sr.getScaledHeight() / 2, 0);
        } else {
            if (percent2 <= 1) {
                GlStateManager.translate(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, 0);
                GlStateManager.scale(percent2, percent2, 0);
                GlStateManager.translate(-sr.getScaledWidth() / 2, -sr.getScaledHeight() / 2, 0);
            }
        }


        if (percent <= 1.5 && close) {
            percent = smoothTrans(this.percent, 2);
            percent2 = smoothTrans(this.percent2, 2);
        }

        if (percent >= 1.4 && close) {
            percent = 1.5f;
            closed = true;
            mc.currentScreen = null;
        }

        if (isHovered(windowX, windowY, windowX + width, windowY + 40, mouseX, mouseY) && Mouse.isButtonDown(0)) {
            if (dragX == 0 && dragY == 0) {
                dragX = mouseX - windowX;
                dragY = mouseY - windowY;
            } else {
                windowX = mouseX - dragX;
                windowY = mouseY - dragY;
            }
        } else if (dragX != 0 || dragY != 0) {
            dragX = 0;
            dragY = 0;
        }

        //滚动
        int dWheel = Mouse.getDWheel();
        if (isHovered(windowX + 200, windowY + 20, windowX + width - 2, windowY + height, mouseX, mouseY)) {
            if (dWheel < 0 && Math.abs(vRole) + 170 < (currentMod.values.size() * 25)) {
                vRole -= 6;
            }
            if (dWheel > 0 && vRole < 0) {
                vRole += 6;
            }
        }

        if (isHovered(windowX + 10, windowY + 20, windowX + 190, windowY + height, mouseX, mouseY)) {
            if (dWheel < 0 && (Math.abs(mRole) + 200) < (WulneJvav.INSTANCE.moduleManager.getModsByCategory(modCategory).size() * 30)) {
                mRole -= 6;
            }
            if (dWheel > 0 && mRole < 0) {
                mRole += 6;
            }
        }

        RenderUtils.drawRoundRect(windowX, windowY, windowX + width, windowY + height, -1);
        RenderUtils.drawRoundRect(windowX, windowY, windowX + width, windowY + 40, new Color(245, 245, 245).getRGB());
        FontLoaders.ClickFont.drawString("Wulne", windowX + 8, windowY + 20, new Color(94, 115, 255).getRGB());

        //模块分类
        float cx = windowX + 100;
        float px = windowX + width - 35;
        if (yonghu == false) {
            for (ModuleType mc : ModuleType.values()) {
                FontLoaders.msFont22.drawString(mc.name(), cx, windowY + 20, modCategory == mc ? new Color(94, 115, 255).getRGB() : new Color(100, 100, 100).getRGB());
                if (isHovered(cx, windowY + 20, cx + 60, windowY + 30, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
                    modCategory = mc;
                    mRole = 0;
                    vRole = 0;
                    timer.reset();
                }
                RenderUtils.drawImage((int) px, (int) (windowY + 15), 16, 16, new ResourceLocation("Wulne/NewClickGui/yonghu.png"));

                if (isHovered((int) px, (int) (windowY + 15), px + 60, windowY + 30, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
                    currentMod = null;
                    yonghu = true;
                    timer.reset();
                }
                cx += (width + -520) / (ModuleType.values().length - 2);
            }
        }

        //模块启用
        float mY = windowY + 50 + mRole;
        if (yonghu == false) {
            for (Module m : WulneJvav.INSTANCE.moduleManager.getModsByCategory(modCategory)) {
                if (((mY + 30) < (windowY + 70))) {
                    mY += 30;
                }
                if (!((mY + 30) < (windowY + 70)) && !((mY + 30) > (windowY + height))) {
                    if (m.isEnabled()) {
                        RenderUtils.drawRoundRect(windowX + 10, mY, windowX + 190, mY + 25, new Color(94, 115, 255).getRGB());
                    } else {
                        RenderUtils.drawRoundRect(windowX + 10, mY, windowX + 190, mY + 25, new Color(0, 0, 0, 50).getRGB());
                    }

                    if (isHovered(windowX + 10, mY, windowX + 190, mY + 25, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
                        m.setEnabled(!m.isEnabled());
                        timer.reset();
                    } else if (isHovered(windowX + 10, mY, windowX + 190, mY + 25, mouseX, mouseY) && Mouse.isButtonDown(1) && timer.delay(300)) {
                        currentMod = m;
                        timer.reset();
                    }

                    FontLoaders.msFont18.drawString(m.getName(), windowX + 15, mY + 5, -1);
                    mY += 30;
                }
            }
        }

        if (yonghu == true) {
            drawYonghuUI(mouseX, mouseY, partialTicks);
        }

        //分割线
        if (yonghu == false) {
            RenderUtil.drawRect(windowX + 200, windowY + 50, windowX + 201, windowY + height - 10, new Color(150, 150, 150, 100).getRGB());
        }

        //客户端Value设置
        float vY = windowY + vRole + 70;
        if (currentMod != null) {
            RenderUtil.drawImage(new ResourceLocation("Wulne/NewClickGui/back.png"), (int) (windowX + 210), (int) (windowY + 50), 16, 16);
            String keyName = Keyboard.getKeyName(currentMod.getKey());
            FontLoaders.msFont18.drawString(currentMod.name + "(" + keyName + "键)", windowX + 230, windowY + 55, new Color(94, 115, 255).getRGB());
            if (isHovered(windowX + 210, windowY + 50, windowX + width - 370, windowY + 70, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
                currentMod = null;
                timer.reset();
            }
            for (Value v : currentMod.values) {
                if (((vY + 30) < (windowY + 50))) {
                    vY += 25;
                }
                if (!((vY + 30) < (windowY + 90)) && !((vY + 30) > (windowY + height))) {
                    if (v instanceof Option) {
                        FontLoaders.msFont18.drawString(v.getName(), windowX + 210, vY, new Color(94, 115, 255).getRGB());
                        if ((boolean) v.getValue()) {
                            RenderUtil.drawImage(new ResourceLocation("Wulne/NewClickGUi/enabled.png"), (int) (windowX + width - 20), (int) (vY - 4), 16, 16);
                        } else if (!(boolean) v.getValue()) {
                            RenderUtil.drawImage(new ResourceLocation("Wulne/NewClickGUi/disable.png"), (int) (windowX + width - 20), (int) (vY - 4), 16, 16);
                        }
                        if (isHovered(windowX + width - 20, vY - 4, windowX + width + 6, vY + 8, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
                            v.setValue(!((Boolean) v.getValue()));
                            timer.reset();
                        }
                    }

                    if (v instanceof Mode) {
                        RenderUtils.drawRoundRect(windowX + width - 395, vY - 5, windowX + width - 10, vY + 10, new Color(231, 231, 231, 200).getRGB());
                        FontLoaders.msFont18.drawString(v.getName() + "：", windowX + 210, vY - 2, new Color(94, 115, 255).getRGB());
                        FontLoaders.msFont18.drawString(((Mode<?>) v).getModeAsString(), windowX + width - 220, vY - 2, new Color(94, 115, 255).getRGB());
                        FontLoaders.msFont18.drawString(">", windowX + width - 20, vY - 2, new Color(94, 115, 255).getRGB());
                        if (isHovered(windowX + width - 395, vY - 5, windowX + width - 10, vY + 10, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
                            if (Arrays.binarySearch(((Mode<?>) v).getModes(), (v.getValue()))
                                    + 1 < ((Mode<?>) v).getModes().length) {
                                v.setValue(((Mode<?>) v)
                                        .getModes()[Arrays.binarySearch(((Mode<?>) v).getModes(), (v.getValue())) + 1]);
                            } else {
                                v.setValue(((Mode<?>) v).getModes()[0]);
                            }
                            timer.reset();
                        }
                    }

                    if (v instanceof Numbers) {
                        FontLoaders.msFont18.drawString(v.getName() + ": " + Math.round(Float.parseFloat(v.getValue().toString())), windowX + 210, vY, new Color(94, 115, 255).getRGB());

                        float present = (windowX + width - 10 - windowX - 210) * (((Number) v.getValue()).floatValue() - ((Numbers) v).getMinimum().floatValue())
                                / (((Numbers) v).getMaximum().floatValue() - ((Numbers) v).getMinimum().floatValue());

                        FontLoaders.msFont18.drawString(v.getValue().toString(), windowX + width - FontLoaders.msFont18.getStringWidth(v.getValue().toString()) - 5, vY, -1);

                        v.animX1 = present;
                        if (v.animX != v.animX1) {
                            v.animX += (v.animX1 - v.animX) / 30;
                        }

                        RenderUtils.drawRect(windowX + 210, vY + 9, windowX + width - 10, vY + 10, new Color(120, 120, 120).getRGB());
                        RenderUtils.drawRect(windowX + 210,
                                vY + 9,
                                windowX + 210 + v.animX1, vY + 10, new Color(94, 115, 255).getRGB());

                        if (isHovered(windowX + 210, vY + 7, windowX + width - 10, vY + 12, mouseX, mouseY) && Mouse.isButtonDown(0)) {
                            v.drag = true;
                            float render2 = ((Numbers) v).getMinimum().floatValue();
                            double max = ((Numbers) v).getMaximum().doubleValue();
                            float inc = (((Numbers<?>) v).getIncrement()).floatValue();
                            double valAbs = (double) mouseX - windowX - 210;
                            double perc = valAbs / (((windowX + width - 10) - (windowX + 210)));
                            perc = Math.min(Math.max(0.0D, perc), 1.0D);
                            double valRel = (max - render2) * perc;
                            float val = (float) (render2 + valRel);
                            val = (float) (Math.round(val * (1.0D / inc)) / (1.0D / inc));
                            BigDecimal b = new BigDecimal(val);
                            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            ((Numbers) v).setValue(f1);

                            RenderUtil.smoothCircle(windowX + 210 + v.animX1, vY + 9, 4, new Color(94, 115, 255).getRGB());
                            RenderUtil.smoothCircle(windowX + 210 + v.animX1, vY + 9, 3, -1);
                        } else {
                            RenderUtil.smoothCircle(windowX + 210 + v.animX1, vY + 9, 4, new Color(94, 115, 255).getRGB());
                            RenderUtil.smoothCircle(windowX + 210 + v.animX1, vY + 9, 3, -1);
                        }
                    }
                    vY += 20;
                }
            }
        }
    }

    public void drawYonghuUI(int mouseX, int mouseY, float partialTicks) {
        RenderUtils.drawImage((int) (windowX + 2),(int) (windowY + 40 + 2),16,16,new ResourceLocation("Wulne/NewClickGui/back.png"));
        if (isHovered(windowX + 2, windowY + 40 + 2, windowX + 20, windowY + 70, mouseX, mouseY) && Mouse.isButtonDown(0) && timer.delay(300)) {
            yonghu = false;
            timer.reset();
        }
        FontLoaders.msFont18.drawString("用户信息",windowX + 18,windowY + 40 + 7,new Color(94, 115, 255).getRGB());
        FontLoaders.msFont18.drawString("Player：" + Minecraft.getMinecraft().thePlayer.getName(),windowX + 18,windowY + 40 + 7 + 10,new Color(94, 115, 255).getRGB());
        FontLoaders.msFont18.drawString("ClickGui版本：FrGui v3.0",windowX + 18,windowY + 40 + 7 + 10 + 10,new Color(94, 115, 255).getRGB());
        FontLoaders.msFont18.drawString("Wulne版本：Release 7",windowX + 18,windowY + 40 + 7 + 10 + 10 + 10,new Color(94, 115, 255).getRGB());
    }

    public static boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (!closed && keyCode == Keyboard.KEY_ESCAPE) {
            close = true;
            mc.mouseHelper.grabMouseCursor();
            mc.inGameHasFocus = true;
            return;
        }

        if (close) {
            this.mc.displayGuiScreen((GuiScreen) null);
        }
    }

    @Override
    public void onGuiClosed() {
    }
}
