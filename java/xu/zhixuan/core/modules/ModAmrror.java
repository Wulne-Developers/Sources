package xu.zhixuan.core.modules;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.DrawUtil;
import xu.zhixuan.wulne.Util.Render.RenderUtil;
import java.awt.*;

public class ModAmrror extends Module {
    public ModAmrror() {
        super("盔甲显示",30,100, ModuleType.Render,true);
    }

    @Override
    public void renderItem(int mouseX, int mouseY) {
        DrawUtil.drawRect( getX(), getY(), getWidth(), getHeight(),new Color(0,0,0,100).getRGB());
        renderItemStack( 3, new ItemStack(Items.diamond_helmet));
        renderItemStack( 2, new ItemStack(Items.diamond_chestplate));
        renderItemStack( 1, new ItemStack(Items.diamond_leggings));
        renderItemStack( 0, new ItemStack(Items.diamond_boots));
        super.renderItem(mouseX,mouseY);
    }

    @Override
    public int getWidth() {
        return 63;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void draw() {
        DrawUtil.drawRect(getX(),getY(),getWidth(),getHeight(),new Color(0,0,0,100).getRGB());
        for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack itemstack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack( i, itemstack);
        }
        super.draw();
    }

    private void renderItemStack(final int i, final ItemStack is) {
        if (is == null) {
            return;
        }
        GL11.glPushMatrix();
        final int yAdd = -16 * i + 48;
        if (is.getItem().isDamageable()) {
            final double damage = (is.getMaxDamage() - is.getItemDamage()) / (double)is.getMaxDamage() * 100.0;
            FontLoaders.msFont16.drawString(String.format("%.2f%%", damage), this.drag.getxPosition() + 20, this.drag.getyPosition() + yAdd + 5, -1);
            if (this.mc.thePlayer != null && is != null) {
                if (is.getItem().isDamageable()) {
                    final double damage2 = (is.getMaxDamage() - is.getItemDamage()) / (double)is.getMaxDamage() * 100.0;
                    FontLoaders.msFont16.drawString(String.format("%.2f%%", damage), this.drag.getxPosition() + 20, this.drag.getyPosition() + yAdd + 5, -1);
                }
                if (is.isStackable() && this.mc.thePlayer.getHeldItem().stackSize != 1) {
                    FontLoaders.msFont16.drawString(Integer.toString(this.mc.thePlayer.getHeldItem().stackSize), this.drag.getxPosition() + 20, this.drag.getyPosition() + yAdd + 5, -1);
                }
            }
            RenderHelper.enableGUIStandardItemLighting();
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(is, this.drag.getxPosition(), this.drag.getyPosition() + yAdd);
            GL11.glPopMatrix();
        }
    }
}
