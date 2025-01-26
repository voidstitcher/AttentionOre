package voidstitcher.attentionore.mixin;

import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value= BlockLogicOreRedstone.class, remap = false)
public abstract class OreRedstoneMixin extends BlockLogic {
	public OreRedstoneMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean isSignalSource() {
		return true;
	}


	private boolean isActiveRedstone(WorldSource world, int x, int y, int z) {
		Block<?> block = world.getBlock(x, y, z);
		if (block == null) return false;
		if (block.getLogic() instanceof BlockLogicWireRedstone) {
			return world.getBlockMetadata(x, y, z) != 0;
		}
		return false;
	}

	@Override
	public boolean getSignal(WorldSource worldSource, int x, int y, int z, Side side) {
		if (!isActiveRedstone(worldSource, x + 1, y, z) &&
			!isActiveRedstone(worldSource, x - 1, y, z) &&
			!isActiveRedstone(worldSource, x, y, z + 1) &&
			!isActiveRedstone(worldSource, x, y, z - 1)) {
			return false;
		}
		int dx = x - side.getOffsetX();
		int dy = y - side.getOffsetY();
		int dz = z - side.getOffsetZ();
		Block<?> block = worldSource.getBlock(dx, dy, dz);
		return block == null || !(block.getLogic() instanceof BlockLogicWireRedstone);
	}
}
