package voidstitcher.attentionore.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicOreRedstone;
import net.minecraft.core.block.material.Material;
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
}
