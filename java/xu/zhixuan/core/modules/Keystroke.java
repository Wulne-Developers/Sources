package xu.zhixuan.core.modules;

import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Util.Fonts.ChromaText;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.DrawUtil;

import java.awt.Color;

public class Keystroke extends Module
{

    public Option bool = new Option<>("彩色文字",false);
    public Keystroke() {
        super("按键显示",90, 50, ModuleType.Render,true);
        this.addValues(bool);
    }

    @Override
    public void draw() {
        super.draw();
        //W
        DrawUtil.drawRect(getX() + 22, getY(), 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindForward.isKeyDown()) {
            DrawUtil.drawRect(getX() + 22, getY(), 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //S
        DrawUtil.drawRect(getX() + 22, getY() + 20 + 1, 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindBack.isKeyDown()) {
            DrawUtil.drawRect(getX() + 22, getY() + 20 + 1, 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //A
        DrawUtil.drawRect(getX() + 1, getY() + 20 + 1, 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindLeft.isKeyDown()) {
            DrawUtil.drawRect(getX() + 1, getY() + 20 + 1, 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //D
        DrawUtil.drawRect(getX() + 20 + 20 + 3, getY() + 20 + 1, 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindRight.isKeyDown()){
            DrawUtil.drawRect(getX() + 20 + 20 + 3, getY() + 20 + 1, 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //LMB
        DrawUtil.drawRect(getX() + 1, getY() + 20 + 20 + 2, 31, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindAttack.isKeyDown()){
            DrawUtil.drawRect(getX() + 1, getY() + 20 + 20 + 2, 31, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //RMB
        DrawUtil.drawRect(getX() + 1 + 32,getY() + 20 + 20 + 2,30,20,new Color(0,0,0,100).getRGB());
        if (mc.gameSettings.keyBindUseItem.isKeyDown()){
            DrawUtil.drawRect(getX() + 1 + 32,getY() + 20 + 20 + 2,30,20,new Color(225, 225, 225, 108).getRGB());
        }
        //Space
        DrawUtil.drawRect(getX() + 1,getY() + 20 + 20 + 20 + 3,62,20,new Color(0,0,0,100).getRGB());
        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            DrawUtil.drawRect(getX() + 1,getY() + 20 + 20 + 20 + 3,62,20,new Color(225, 225, 225, 108).getRGB());
        }
        if ((boolean)bool.getValue()) {
            FontLoaders.msFont18.drawString("W",getX() + 20 + 8,getY() + 5, ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("S",getX() + 20 + 8,getY() + 5 + 5 + 17,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("A",getX() + 20 - 12,getY() + 5 + 5 + 17,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("D",getX() + 20 + 8 + 20,getY() + 5 + 5 + 17,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("LMB",getX() + 8,getY() + 5 + 5 + 17 + 20,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("RMB",getX() + 8 + 28,getY() + 5 + 5 + 17 + 20,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("Space",getX() + 8 + 12,getY() + 5 + 5 + 17 + 20 + 22,ChromaText.drawChroma(10));
        } else if (!(boolean)bool.getValue()){
            FontLoaders.msFont18.drawString("W",getX() + 20 + 8,getY() + 5,-1);
            FontLoaders.msFont18.drawString("S",getX() + 20 + 8,getY() + 5 + 5 + 17,-1);
            FontLoaders.msFont18.drawString("A",getX() + 20 - 12,getY() + 5 + 5 + 17,-1);
            FontLoaders.msFont18.drawString("D",getX() + 20 + 8 + 20,getY() + 5 + 5 + 17,-1);
            FontLoaders.msFont18.drawString("LMB",getX() + 8,getY() + 5 + 5 + 17 + 20,-1);
            FontLoaders.msFont18.drawString("RMB",getX() + 8 + 28,getY() + 5 + 5 + 17 + 20,-1);
            FontLoaders.msFont18.drawString("Space",getX() + 8 + 12,getY() + 5 + 5 + 17 + 20 + 22,-1);
        }
    }

    @Override
    public void renderItem(final int mouseX, final int mouseY) {
        super.renderItem(mouseX, mouseY);
        //W
        DrawUtil.drawRect(getX() + 22, getY(), 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindForward.isKeyDown()) {
            DrawUtil.drawRect(getX() + 22, getY(), 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //S
        DrawUtil.drawRect(getX() + 22, getY() + 20 + 1, 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindBack.isKeyDown()) {
            DrawUtil.drawRect(getX() + 22, getY() + 20 + 1, 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //A
        DrawUtil.drawRect(getX() + 1, getY() + 20 + 1, 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindLeft.isKeyDown()) {
            DrawUtil.drawRect(getX() + 1, getY() + 20 + 1, 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //D
        DrawUtil.drawRect(getX() + 20 + 20 + 3, getY() + 20 + 1, 20, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindRight.isKeyDown()){
            DrawUtil.drawRect(getX() + 20 + 20 + 3, getY() + 20 + 1, 20, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //LMB
        DrawUtil.drawRect(getX() + 1, getY() + 20 + 20 + 2, 31, 20, new Color(0, 0, 0, 100).getRGB());
        if (mc.gameSettings.keyBindAttack.isKeyDown()){
            DrawUtil.drawRect(getX() + 1, getY() + 20 + 20 + 2, 31, 20, new Color(225, 225, 225, 108).getRGB());
        }
        //RMB
        DrawUtil.drawRect(getX() + 1 + 32,getY() + 20 + 20 + 2,30,20,new Color(0,0,0,100).getRGB());
        if (mc.gameSettings.keyBindUseItem.isKeyDown()){
            DrawUtil.drawRect(getX() + 1 + 32,getY() + 20 + 20 + 2,30,20,new Color(225, 225, 225, 108).getRGB());
        }
        //Space
        DrawUtil.drawRect(getX() + 1,getY() + 20 + 20 + 20 + 3,62,20,new Color(0,0,0,100).getRGB());
        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            DrawUtil.drawRect(getX() + 1,getY() + 20 + 20 + 20 + 3,62,20,new Color(225, 225, 225, 108).getRGB());
        }
        if ((boolean) bool.getValue()) {
            FontLoaders.msFont18.drawString("W",getX() + 20 + 8,getY() + 5, ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("S",getX() + 20 + 8,getY() + 5 + 5 + 17,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("A",getX() + 20 - 12,getY() + 5 + 5 + 17,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("D",getX() + 20 + 8 + 20,getY() + 5 + 5 + 17,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("LMB",getX() + 8,getY() + 5 + 5 + 17 + 20,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("RMB",getX() + 8 + 28,getY() + 5 + 5 + 17 + 20,ChromaText.drawChroma(10));
            FontLoaders.msFont18.drawString("Space",getX() + 8 + 12,getY() + 5 + 5 + 17 + 20 + 22,ChromaText.drawChroma(10));
        } else if (!(boolean) bool.getValue()){
            FontLoaders.msFont18.drawString("W",getX() + 20 + 8,getY() + 5,-1);
            FontLoaders.msFont18.drawString("S",getX() + 20 + 8,getY() + 5 + 5 + 17,-1);
            FontLoaders.msFont18.drawString("A",getX() + 20 - 12,getY() + 5 + 5 + 17,-1);
            FontLoaders.msFont18.drawString("D",getX() + 20 + 8 + 20,getY() + 5 + 5 + 17,-1);
            FontLoaders.msFont18.drawString("LMB",getX() + 8,getY() + 5 + 5 + 17 + 20,-1);
            FontLoaders.msFont18.drawString("RMB",getX() + 8 + 28,getY() + 5 + 5 + 17 + 20,-1);
            FontLoaders.msFont18.drawString("Space",getX() + 8 + 12,getY() + 5 + 5 + 17 + 20 + 22,-1);
        }
    }

    @Override
    public int getWidth() {
        return 63;
    }

    @Override
    public int getHeight() {
        return 83;
    }
}
