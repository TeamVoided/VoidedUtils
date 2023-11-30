package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.registries.VUWorldGen;


import static org.teamvoided.voided_utils.data.tags.VUBlockTags.AIR_PASSABLE;

@Mixin(MossBlock.class)
public class MossBlockMixin {


    @Inject(method = "isFertilizable", at = @At("HEAD"), cancellable = true)
    public void voidUtils$isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient, CallbackInfoReturnable<Boolean> cir) {
        System.out.printf("Blocks: %s\n", world.getBlockState(pos.up()).toString());
        cir.setReturnValue(world.getBlockState(pos.up()).isIn(AIR_PASSABLE));

    }

    @Inject(method = "fertilize", at = @At("HEAD"), cancellable = true)
    public void voidUtils$fertilize(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (true) {
            world.getRegistryManager()
                    .getOptional(RegistryKeys.CONFIGURED_FEATURE)
                    .flatMap(registry ->
                            registry.getHolder(VUWorldGen.CUSTOM_MOSS_PATCH_BONE_MEAL_KEY)
                    )
                    .ifPresent(reference ->
                            reference.value()
                                    .generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up())
                    );
            ci.cancel();
        }
    }


}
