package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.data.tags.VUBlockTags;
import org.teamvoided.voided_utils.misc.CustomAreaHelper;

import java.util.Optional;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {


    @Inject(method = "onBlockAdded", at = @At("RETURN"), cancellable = true)
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        Optional<CustomAreaHelper> optional;
        if (isOverworldOrNether(world) && (optional = CustomAreaHelper.Companion.getNewPortal(world, pos, Direction.Axis.X)).isPresent()) {
            optional.get().createPortal();
            ci.cancel();
        }
    }
    @Inject(at = @At("HEAD"), method = "shouldLightPortalAt", cancellable = true)
    private static void voidedUtils$shouldLightPortalAt(
            World world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (!isOverworldOrNether(world)) cir.setReturnValue(false);
        else {
            BlockPos.Mutable mutable = pos.mutableCopy();
            boolean bl = false;

            for (Direction direction2 : Direction.values()) {
                if (world.getBlockState(mutable.set(pos).move(direction2)).isIn(VUBlockTags.NETHER_PORTAL_BLOCK)) {
                    bl = true;
                    break;
                }
            }
            if (!bl) {
                cir.setReturnValue(false);
            } else {
                Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                cir.setReturnValue(CustomAreaHelper.Companion.getNewPortal(world, pos, axis).isPresent());
            }
        }
    }

    @Shadow
    private static boolean isOverworldOrNether(World world) {
        return false;
    }
}
