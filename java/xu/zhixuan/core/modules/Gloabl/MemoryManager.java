package xu.zhixuan.core.modules.Gloabl;

import net.minecraft.client.gui.ScaledResolution;
import xu.zhixuan.core.events.Client.EventTick;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.HUD.Timer;
import xu.zhixuan.wulne.Util.Render.DrawUtil;
import xu.zhixuan.wulne.Util.Render.RenderUtil;
import xu.zhixuan.wulne.Util.Render.RenderUtils;

import java.awt.*;

public class MemoryManager extends Module {
    private final Numbers<Double> delay = new Numbers("延迟", 120.0, 10.0, 600.0, 0.1);
    private final Numbers<Double> limit = new Numbers("限制", 80.0, 20.0, 95.0, 0.1);
    private Timer timer = new Timer();
    public Option shifou = new Option("内存修复", true);
    public Option shifou1 = new Option("内存显示", true);

    public MemoryManager() {
        super("内存资源管理器", 20, 30, ModuleType.Other,true);
        this.addValues(new Value[]{this.delay, this.limit, this.shifou, this.shifou1});
    }

    private static long bytesToMb(long bytes) {
        return bytes / 1024L / 1024L;
    }

    @Override
    public void draw() {
        if ((Boolean) this.shifou1.getValue()) {
            DrawUtil.drawRect(getX(),getY(),getWidth(),getHeight(),new Color(0,0,0,100).getRGB());
            ScaledResolution sr = new ScaledResolution(mc);
            long i = Runtime.getRuntime().maxMemory();
            long j = Runtime.getRuntime().totalMemory();
            long k = Runtime.getRuntime().freeMemory();
            long l = j - k;
            String allmemmory = String.format("Mem: % 2d%% %03d/%03dMB", l * 100L / i, bytesToMb(l), bytesToMb(i));
            FontLoaders.msFont18.drawString(allmemmory, getX() + 13, getY() + 5, (new Color(255, 255, 255, 255)).getRGB());
        }
    }

    @EventTarget
    public void onTick(EventTick event) {
        if ((Boolean) this.shifou.getValue()) {
            long maxMem = Runtime.getRuntime().maxMemory();
            long totalMem = Runtime.getRuntime().totalMemory();
            long freeMem = Runtime.getRuntime().freeMemory();
            long usedMem = totalMem - freeMem;
            float pct = (float) (usedMem * 100L / maxMem);
            if (this.timer.hasReached((Double) this.delay.getValue() * 1000.0) && (Double) this.limit.getValue() <= (double) pct) {
                Runtime.getRuntime().gc();
                this.timer.reset();
            }
        }
    }

    @Override
    public void renderItem(int mouseX, int mouseY) {
        super.renderItem(mouseX, mouseY);
        DrawUtil.drawRect(getX(),getY(),getWidth(),getHeight(),new Color(0,0,0,100).getRGB());
        ScaledResolution sr = new ScaledResolution(mc);
        long i = Runtime.getRuntime().maxMemory();
        long j = Runtime.getRuntime().totalMemory();
        long k = Runtime.getRuntime().freeMemory();
        long l = j - k;
        String allmemmory = String.format("Mem: % 2d%% %03d/%03dMB", l * 100L / i, bytesToMb(l), bytesToMb(i));
        FontLoaders.msFont18.drawString(allmemmory, getX() + 13, getY() + 5, (new Color(255, 255, 255, 255)).getRGB());
    }

    @Override
    public int getWidth() {
        return 130;
    }

    @Override
    public int getHeight() {
        return 20;
    }
}
