package xu.zhixuan.core.modules;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import xu.zhixuan.core.events.Render.EventRender2D;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Fonts.FontRenderer;
import xu.zhixuan.wulne.WulneJvav;

import java.util.Iterator;

public class PositionStatu extends Module {
    private FontRenderer font1;
    public static Option<Boolean> ICON = new Option( "图标", true);
    public static Option<Boolean> Text = new Option( "文本", true);
    public static Option<Boolean> Time = new Option("时间", true);
    public static int PotY = 0;

    public PositionStatu() {
        super("药水显示", 0,0, ModuleType.Render,false);
        this.font1 = FontLoaders.msFont18;
        super.addValues(ICON, Text, Time);
    }

    @EventTarget
    public void onRender2D(EventRender2D e) {
        if (WulneJvav.INSTANCE.moduleManager.getModByClass(PositionStatu.class).isEnabled()) {
            this.drawPotionStatus(e.getSr());
        }
    }

    public void onDisable() {
        PotY = 0;
    }

    private void drawPotionStatus(ScaledResolution sr) {
        int y = 0;
        if (mc.ingameGUI.getChatGUI().getChatOpen()) {
            y += 15;
        }

        Iterator var4 = mc.thePlayer.getActivePotionEffects().iterator();

        while(true) {
            while(var4.hasNext()) {
                PotionEffect effect = (PotionEffect)var4.next();
                int addY = 9;
                int addX = 0;

                Potion potion = Potion.potionTypes[effect.getPotionID()];
                String name = I18n.format(potion.getName(), new Object[0]);
                String level = " " + String.valueOf(effect.getAmplifier() + 1);
                int Duration = effect.getDuration();
                String timeString = Potion.getDurationString(effect);
                int color = potion.getLiquidColor();
                int Width = 0;
                if (level.trim().equals("1")) {
                    level = "";
                }

                if ((Boolean)ICON.getValue() && potion.hasStatusIcon()) {
                    Width += 24;
                    addX += 24;
                }

                if ((Boolean)Text.getValue()) {
                    Width += 4;
                    addX += 4;
                    Width += this.font1.getStringWidth(name + level);
                }

                if ((Boolean)Time.getValue()) {
                    Width += this.font1.getStringWidth(" - " + timeString);
                }

                if ((Boolean)ICON.getValue() && potion.hasStatusIcon()) {
                    GlStateManager.pushMatrix();
                    GL11.glDisable(2929);
                    GL11.glEnable(3042);
                    GL11.glDepthMask(false);
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                    int statusIconIndex = potion.getStatusIconIndex();
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    mc.ingameGUI.drawTexturedModalRect(sr.getScaledWidth() - 4 - Width, sr.getScaledHeight() - 4 - y - 20, 0 + statusIconIndex % 8 * 18, 198 + statusIconIndex / 8 * 18, 18, 18);
                    GL11.glDepthMask(true);
                    GL11.glDisable(3042);
                    GL11.glEnable(2929);
                    GlStateManager.popMatrix();
                }

                String ColorCode = "";
                if (Duration > 0) {
                    ColorCode = EnumChatFormatting.RED.toString();
                }

                if (Duration > 300) {
                    ColorCode = EnumChatFormatting.GOLD.toString();
                }

                if (Duration > 600) {
                    ColorCode = EnumChatFormatting.DARK_GRAY.toString();
                }

                if ((Boolean)Text.getValue()) {
                    this.font1.drawStringWithShadow(name + level, sr.getScaledWidth() - 4 - Width - 4 + addX, sr.getScaledHeight() - 4 - y - 4 - addY, color);
                    addX += this.font1.getStringWidth(name + level);
                }

                if ((Boolean)Time.getValue()) {
                    this.font1.drawStringWithShadow(ColorCode + " - " + timeString, sr.getScaledWidth() - 4 - Width - 4 + addX, sr.getScaledHeight() - 4 - y - 4 - addY, color);
                }

                y += 24;
            }

            PotY = y;
            return;
        }
    }
}
