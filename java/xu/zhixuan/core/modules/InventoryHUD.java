package xu.zhixuan.core.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.Chunk;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Util.Fonts.ChromaText;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.DrawUtil;
import xu.zhixuan.wulne.WulneJvav;

import java.awt.*;

public class InventoryHUD extends Module {
    public InventoryHUD() {
        super("客户端信息显示",30,10, ModuleType.Render,true);
    }

    @Override
    public void onEnabled() {
        super.onEnabled();
    }

    @Override
    public void renderItem(int mouseX, int mouseY) {
        super.renderItem(mouseX, mouseY);
        DrawUtil.drawRect(this.getX(),this.getY(),this.getWidth(),getHeight(),new Color(24, 24, 24, 160).getRGB());
        FontLoaders.msFont16.drawString(WulneJvav.CLIENT_NAME + " " + WulneJvav.CLIENT_VERSION,getX() + 2,getY() + 2,-1);
        FontLoaders.msFont16.drawString("帧数：" + Minecraft.getDebugFPS(),getX() + 2,getY() + 11,-1);
        FontLoaders.msFont16.drawString("玩家名字：" + Minecraft.getMinecraft().thePlayer.getName(),getX() + 2,getY() + 20,-1);
        FontLoaders.msFont16.drawString("正在玩什么服务器：" + this.playing(),getX() + 2,getY() + 29,-1);
        FontLoaders.msFont16.drawString("你所在什么群系：" + EnumChatFormatting.DARK_GREEN + this.getBiome(),getX() + 2,getY() + 38,-1);
        FontLoaders.msFont16.drawString("X坐标：" + this.getPosX(),getX() + 2,getY() + 47,-1);
        FontLoaders.msFont16.drawString("Y坐标：" + this.getPosY(),getX() + 2,getY() + 56,-1);
        FontLoaders.msFont16.drawString("Z坐标：" + this.getPosZ(),getX() + 2,getY() + 65,-1);
        Entity entity = this.mc.getRenderViewEntity();
        EnumFacing enumfacing = entity.getHorizontalFacing();
        FontLoaders.msFont16.drawString("方向: " + EnumChatFormatting.YELLOW + enumfacing.getName(),getX() + 2,getY() + 74,-1);
        this.drawHollowRect(getX(),getY(),getWidth(),getHeight(), ChromaText.drawChroma(10));
    }

    public String getBiome() {
        String biomeName = "";
        BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);
        Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(blockpos);
        if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Plains") {
            biomeName = "平原";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Desert") {
            biomeName = "沙漠";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Extreme Hills") {
            biomeName = "极限山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Forest") {
            biomeName = "森林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Taiga") {
            biomeName = "针叶林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Swampland") {
            biomeName = "沼泽";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "River") {
            biomeName = "河流";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Hell") {
            biomeName = "地狱";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "The End") {
            biomeName = "末地";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "FrozenOcean") {
            biomeName = "冰冻的海洋";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "FrozenRiver") {
            biomeName = "冰冻的河流";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Ice Plains") {
            biomeName = "冰原";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Ice Mountains") {
            biomeName = "冰山";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "MushroomIsland") {
            biomeName = "蘑菇岛";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "MushroomIslandShore") {
            biomeName = "蘑菇岛岸";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Beach") {
            biomeName = "沙滩";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "DesertHills") {
            biomeName = "沙漠山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "ForestHills") {
            biomeName = "森林山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "TaigaHills") {
            biomeName = "针叶林山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Extreme Hills Edge") {
            biomeName = "极限山丘边缘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Jungle") {
            biomeName = "丛林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "JungleHills") {
            biomeName = "丛林山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "JungleEdge") {
            biomeName = "丛林边缘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Deep Ocean") {
            biomeName = "深海洋";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Stone Beach") {
            biomeName = "浅海洋";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Cold Beach") {
            biomeName = "冷海洋";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Birch Forest") {
            biomeName = "白桦林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Birch Forest Hills") {
            biomeName = "白桦林山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Roofed Forest") {
            biomeName = "黑森林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Cold Taiga") {
            biomeName = "冷的针叶林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Cold Taiga Hills") {
            biomeName = "冷的针叶林山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Mega Taiga") {
            biomeName = "巨型针叶林";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Mega Taiga Hills") {
            biomeName = "巨型针叶林山丘";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Extreme Hills+") {
            biomeName = "极限山丘+";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Savanna") {
            biomeName = "热带草原";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Savanna Plateau") {
            biomeName = "热带高原";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Mesa") {
            biomeName = "平顶山";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Mesa Plateau F") {
            biomeName = "平顶山高原F";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Mesa Plateau") {
            biomeName = "平顶山高原";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Sunflower Plains") {
            biomeName = "向日葵平原";
        } else if (chunk.getBiome(blockpos,this.mc.theWorld.getWorldChunkManager()).biomeName == "Ocean") {
            biomeName = "海洋";
        }
        return biomeName;
    }

    public double getPosX() {
        double PosX = Math.round(Minecraft.getMinecraft().thePlayer.posX);
        return PosX;
    }

    public double getPosY() {
        double PosY = Math.round(Minecraft.getMinecraft().thePlayer.posY);
        return PosY;
    }

    public double getPosZ() {
        double PosZ = Math.round(Minecraft.getMinecraft().thePlayer.posZ);
        return PosZ;
    }



    public String playing() {
        String serverIp = "单人游戏";
        if (mc.theWorld.isRemote) {
            final ServerData serverData = mc.getCurrentServerData();
            if (serverData != null) {
                serverIp = serverData.serverIP;
            }
        }
        return serverIp;
    }

    @Override
    public void draw() {
        super.draw();
        DrawUtil.drawRect(this.getX(),this.getY(),this.getWidth(),this.getHeight(),new Color(24, 24, 24, 160).getRGB());
        FontLoaders.msFont16.drawString(WulneJvav.CLIENT_NAME + " " + WulneJvav.CLIENT_VERSION,getX() + 2,getY() + 2,-1);
        FontLoaders.msFont16.drawString("帧数：" + Minecraft.getDebugFPS(),getX() + 2,getY() + 11,-1);
        FontLoaders.msFont16.drawString("玩家名字：" + Minecraft.getMinecraft().thePlayer.getName(),getX() + 2,getY() + 20,-1);
        FontLoaders.msFont16.drawString("正在玩什么服务器：" + this.playing(),getX() + 2,getY() + 29,-1);
        FontLoaders.msFont16.drawString("你所在什么群系：" + EnumChatFormatting.DARK_GREEN + this.getBiome(),getX() + 2,getY() + 38,-1);
        FontLoaders.msFont16.drawString("X坐标：" + this.getPosX(),getX() + 2,getY() + 47,-1);
        FontLoaders.msFont16.drawString("Y坐标：" + this.getPosY(),getX() + 2,getY() + 56,-1);
        FontLoaders.msFont16.drawString("Z坐标：" + this.getPosZ(),getX() + 2,getY() + 65,-1);
        Entity entity = this.mc.getRenderViewEntity();
        EnumFacing enumfacing = entity.getHorizontalFacing();
        FontLoaders.msFont16.drawString("方向: " + EnumChatFormatting.YELLOW + enumfacing.getName(),getX() + 2,getY() + 74,-1);
        this.drawHollowRect(getX(),getY(),getWidth(),getHeight(), ChromaText.drawChroma(10));
    }
    private void drawHollowRect(int x, int y, int w, int h, int color) {
        Gui.drawHorizontalLine(x, x + w, y, color);
        Gui.drawHorizontalLine(x, x + w, y + h, color);
        Gui.drawVerticalLine(x, y + h, y, color);
        Gui.drawVerticalLine(x + w, y + h, y, color);
    }

    @Override
    public int getWidth() {
        return 160;
    }

    @Override
    public int getHeight() {
        return 90;
    }
}
