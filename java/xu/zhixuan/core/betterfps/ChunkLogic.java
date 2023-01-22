package xu.zhixuan.core.betterfps;

import xu.zhixuan.core.betterfps.CopyMode;
import xu.zhixuan.core.betterfps.CopyMode.Mode;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Guilherme Chaguri
 */
public class ChunkLogic extends Chunk {

    @CopyMode(Mode.IGNORE) // Lets ignore the constructor, we don't want infinite loops
    private ChunkLogic(World worldIn, int x, int z) {
        super(worldIn, x, z);
    }

    public boolean shouldTick = true;

}
