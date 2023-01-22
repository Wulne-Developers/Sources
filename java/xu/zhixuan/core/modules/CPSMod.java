package xu.zhixuan.core.modules;

import org.lwjgl.input.Mouse;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.DrawUtil;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CPSMod extends Module {
    private List<Long> clicks1 = new ArrayList<>();
    private List<Long> clicks2 = new ArrayList<>();
    private boolean wasPressed;
    private long lastPressed;
    private boolean wasPressed2;
    private long lastPressed2;

    public Option option = new Option("CPS只显示一个",false);
    public CPSMod() {
        super("CPS显示",5,10, ModuleType.Render,true);
        addValues(option);
    }

    private int getCPS() {
        final long time = System.currentTimeMillis();
        this.clicks1.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks1.size();
    }

    private int getCPS2() {
        final long time = System.currentTimeMillis();
        this.clicks2.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks2.size();
    }

    private int getCPS3() {
        final long time = System.currentTimeMillis();
        this.clicks2.removeIf(aLong -> aLong + 1000 < time);
        this.clicks1.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks1.size() + this.clicks2.size();
    }

    @Override
    public int getHeight() {
        return 22;
    }

    @Override
    public int getWidth() {
        return 20 + 30 + 10;
    }

    @Override
    public void draw() {
        boolean lpressed = Mouse.isButtonDown(0);
        boolean rpressed = Mouse.isButtonDown(1);
        if (lpressed != this.wasPressed) {
            this.lastPressed = System.currentTimeMillis() + 10L;
            this.wasPressed = lpressed;
            if (lpressed) {
                this.clicks1.add(this.lastPressed);
            }
        }

        if (rpressed != this.wasPressed2) {
            this.lastPressed2 = System.currentTimeMillis() + 10L;
            this.wasPressed2 = rpressed;
            if (rpressed) {
                this.clicks2.add(this.lastPressed2);
            }
        }
        if ((boolean) option.getValue()) {
            DrawUtil.drawRoundedRect(getX(), getY(), getX() + 20 + 20, getY() + 22,8,new Color(0, 0, 0, 50).getRGB());
            FontLoaders.msFont16.drawStringWithShadow("CPS: " + getCPS3(), getX() + 5, getY() + 8, -1);
        } else {
            DrawUtil.drawRoundedRect(getX(), getY(), getX() + 20 + 30 + 10, getY() + 22,8,new Color(0, 0, 0, 50).getRGB());
            FontLoaders.msFont16.drawStringWithShadow("CPS: " + getCPS() + " || " + getCPS2(), getX() + 5, getY() + 8, -1);
        }
        super.draw();
    }

    @Override
    public void renderItem(int mouseX, int mouseY) {
        boolean lpressed = Mouse.isButtonDown(0);
        boolean rpressed = Mouse.isButtonDown(1);
        if (lpressed != this.wasPressed) {
            this.lastPressed = System.currentTimeMillis() + 10L;
            this.wasPressed = lpressed;
            if (lpressed) {
                this.clicks1.add(this.lastPressed);
            }
        }

        if (rpressed != this.wasPressed2) {
            this.lastPressed2 = System.currentTimeMillis() + 10L;
            this.wasPressed2 = rpressed;
            if (rpressed) {
                this.clicks2.add(this.lastPressed2);
            }
        }
        if ((boolean) option.getValue()) {
            DrawUtil.drawRoundedRect(getX(), getY(), getX() + 20 + 20, getY() + 22,8,new Color(0, 0, 0, 50).getRGB());
            FontLoaders.msFont16.drawStringWithShadow("CPS: " + getCPS3(), getX() + 5, getY() + 8, -1);
        } else {
            DrawUtil.drawRoundedRect(getX(), getY(), getX() + 20 + 30 + 10, getY() + 22,8,new Color(0, 0, 0, 50).getRGB());
            FontLoaders.msFont16.drawStringWithShadow("CPS: " + getCPS() + " || " + getCPS2(), getX() + 5, getY() + 8, -1);
        }
        super.renderItem(mouseX, mouseY);
    }
}
