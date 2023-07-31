package org.teamvoided.voided_utils.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.teamvoided.voided_utils.VoidedUtils.INSTANCE;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {


    @Inject(at = @At("HEAD"), method = "applyFoodEffects")
    private void applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity, CallbackInfo ci) {
        if (INSTANCE.getId(stack.getItem()) == INSTANCE.getId(Items.GLOW_BERRIES)) {
            targetEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 120, 0));
        }
    }

}