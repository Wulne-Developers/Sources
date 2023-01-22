package xu.zhixuan.core.modules

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.RenderTNTPrimed
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.entity.item.EntityTNTPrimed
import org.lwjgl.opengl.GL11
import xu.zhixuan.wulne.Module.Module
import xu.zhixuan.wulne.Module.ModuleType
import java.awt.Color
import java.text.DecimalFormat

class TNT : Module("TNT倒计时",0,0, ModuleType.Other,false) {
    fun TNTRender(tntRenderer : RenderTNTPrimed, tntPrimed : EntityTNTPrimed,x : Double,y : Double,z : Double,partialTicks : Float) {
        if (tntPrimed.fuse >= 1) {
            val d0 = tntPrimed.getDistanceSqToEntity(tntRenderer.renderManager.livingPlayer)
            if (d0 <= 4096.0) {
                val number = (tntPrimed.fuse.toFloat() - partialTicks) / 20.0f
                val str = DecimalFormat("0.00").format(number.toDouble())
                val fontrenderer = tntRenderer.fontRendererFromRenderManager
                val f = 1.6f
                val f1 = 0.016666668f * f
                GlStateManager.pushMatrix()
                GlStateManager.translate(x.toFloat() + 0.0f, y.toFloat() + tntPrimed.height + 0.5f, z.toFloat())
                GL11.glNormal3f(0.0f, 1.0f, 0.0f)
                tntRenderer.renderManager
                GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f)
                var xMultiplier = 1
                if (mc != null && mc.gameSettings != null && mc.gameSettings.thirdPersonView === 2) {
                    xMultiplier = -1
                }
                GlStateManager.rotate(tntRenderer.renderManager.playerViewX * xMultiplier.toFloat(), 1.0f, 0.0f, 0.0f)
                GlStateManager.scale(-f1, -f1, f1)
                GlStateManager.disableLighting()
                GlStateManager.depthMask(false)
                GlStateManager.disableDepth()
                GlStateManager.enableBlend()
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
                val tessellator = Tessellator.getInstance()
                val worldrenderer = tessellator.worldRenderer
                val i = 0
                val j = fontrenderer.getStringWidth(str) / 2
                val green = Math.min(tntPrimed.fuse.toFloat() / 80.0f, 1.0f)
                val color = Color(1.0f - green, green, 0.0f)
                GlStateManager.enableDepth()
                GlStateManager.depthMask(true)
                GlStateManager.disableTexture2D()
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR)
                worldrenderer.pos((-j - 1).toDouble(), (-1 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                worldrenderer.pos((-j - 1).toDouble(), (8 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                worldrenderer.pos((j + 1).toDouble(), (8 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                worldrenderer.pos((j + 1).toDouble(), (-1 + i).toDouble(), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                tessellator.draw()
                GlStateManager.enableTexture2D()
                fontrenderer.drawString(str, (-fontrenderer.getStringWidth(str) / 2).toFloat().toInt(), i, color.rgb)
                GlStateManager.enableLighting()
                GlStateManager.disableBlend()
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
                GlStateManager.popMatrix()
            }
        }
    }
}