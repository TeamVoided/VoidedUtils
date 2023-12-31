package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.data.tags.VUBlockTags;

import static org.teamvoided.voided_utils.VoidedUtils.INSTANCE;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item {

    public ShearsItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "getMiningSpeedMultiplier", cancellable = true)
    public void voidedUtils$getMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        if (INSTANCE.getConfig().getEnableShearsMineableTag()) {
            if (state.isIn(VUBlockTags.SHEARS_MINEABLE_FAST)) {
                cir.setReturnValue(15.0F);
            }
        }
    }
}
