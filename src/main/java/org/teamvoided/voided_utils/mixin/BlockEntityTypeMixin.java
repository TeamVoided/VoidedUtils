package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.CeilingHangingSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.blocks.CustomSign;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
	@Inject(method = "supports", at = @At("HEAD"), cancellable = true)
	private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
		Block block = state.getBlock();

		if (block instanceof CustomSign) {
			if (BlockEntityType.HANGING_SIGN.equals(this)) {
				if (!(block instanceof CeilingHangingSignBlock || block instanceof WallHangingSignBlock)) {
					return;
				}
			} else if (!BlockEntityType.SIGN.equals(this)) {
				return;
			}

			info.setReturnValue(true);
		}
	}
}