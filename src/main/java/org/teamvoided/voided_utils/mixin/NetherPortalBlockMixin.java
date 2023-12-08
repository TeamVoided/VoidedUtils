package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.misc.CustomAreaHelper;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin extends Block {
    @Final
    @Shadow
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;

    public NetherPortalBlockMixin(Settings settings) {super(settings);}

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"), cancellable = true)
    public void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        Direction.Axis axis = direction.getAxis();
        Direction.Axis axis2 = state.get(AXIS);
        boolean bl = axis2 != axis && axis.isHorizontal();
        if (bl || neighborState.isOf((NetherPortalBlock) (Object) this) || new CustomAreaHelper(world, pos, axis2).wasAlreadyValid()) {
            cir.setReturnValue(state);
        }
        cir.setReturnValue(Blocks.AIR.getDefaultState());
    }


    @Override
    public boolean isSideInvisible(BlockState state, @NotNull BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) return true;
        return super.isSideInvisible(state, stateFrom, direction);
    }
}
