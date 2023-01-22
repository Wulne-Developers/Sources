package xu.zhixuan.core.modules

import net.minecraft.util.EnumParticleTypes
import xu.zhixuan.core.events.Player.EventAttack
import xu.zhixuan.wulne.Event.EventTarget
import xu.zhixuan.wulne.Module.Module
import xu.zhixuan.wulne.Module.ModuleType
import xu.zhixuan.wulne.Module.Value.Numbers
import xu.zhixuan.wulne.Module.Value.Option

class MoreParticle : Module {
    var CrackSize: Numbers<Double> = Numbers("CrackSize", 2.0, 0.0, 10.0, 0.1)
    var Crit: Option<Boolean> = Option("CritParticle", true)
    var Normal: Option<Boolean> = Option("NormalParticle", true)

    constructor() : super("更多的打击粒子",0,0, ModuleType.Render,false) {
        addValues(CrackSize,Crit,Normal)
    }

    @EventTarget fun onAttack(e: EventAttack) {
        for (index in 0 until (CrackSize.getValue() as Double).toInt()) {
            if (Crit.getValue()) {
                mc.effectRenderer.emitParticleAtEntity(e.getTarget(), EnumParticleTypes.CRIT)
            }
            if (Normal.getValue()) {
                mc.effectRenderer.emitParticleAtEntity(e.getTarget(), EnumParticleTypes.CRIT_MAGIC)
            }
        }
    }
}