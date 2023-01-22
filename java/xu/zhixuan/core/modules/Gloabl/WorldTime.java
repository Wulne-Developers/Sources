package xu.zhixuan.core.modules.Gloabl;

import net.minecraft.network.play.server.S03PacketTimeUpdate;
import xu.zhixuan.core.events.Client.EventTick;
import xu.zhixuan.core.events.Player.EventPacket;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Numbers;
import xu.zhixuan.wulne.Module.Value.Option;
import xu.zhixuan.wulne.Module.Value.Value;

public class WorldTime extends Module {
    private Numbers<Double> Time = new Numbers("Time", 18000.0, 0.0, 24000.0, 0.1);
    private Numbers<Double> Change = new Numbers("TickValue", 500.0, 0.0, 10000.0, 0.1);
    public static Option<Boolean> TickChange = new Option("FastTime", true);
    public WorldTime() {
        super("世界时间",0,0, ModuleType.Other,false);
        super.addValues(new Value[]{this.Time, TickChange, this.Change});
    }

    @EventTarget
    public void EventPacketSend(EventPacket e) {
        if (e.getPacket() instanceof S03PacketTimeUpdate) {
            e.setCancelled(true);
        }
    }

    public void onEnabled() {
        super.onEnabled();
    }

    @EventTarget
    public void onTick(EventTick event) {
        if ((Boolean)TickChange.getValue()) {
            if (mc.theWorld.getWorldTime() + ((Double)this.Change.getValue()).longValue() > 24000L) {
                mc.theWorld.setWorldTime(mc.theWorld.getWorldTime() + ((Double)this.Change.getValue()).longValue() - 24000L);
            } else {
                mc.theWorld.setWorldTime(mc.theWorld.getWorldTime() + ((Double)this.Change.getValue()).longValue());
            }
        } else {
            mc.theWorld.setWorldTime(((Double)this.Time.getValue()).longValue());
        }
    }
}
