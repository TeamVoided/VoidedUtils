package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.registries.VUBlocks;

import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(method = "getStrippedState", at = @At("HEAD"), cancellable = true)
    private void appendCustomStrip(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
        if (state.getBlock() == VUBlocks.CHARRED_LOG) {
            cir.setReturnValue(Optional.of(VUBlocks.STRIPPED_CHARRED_LOG.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))));
            cir.cancel();
        }
    }
}
