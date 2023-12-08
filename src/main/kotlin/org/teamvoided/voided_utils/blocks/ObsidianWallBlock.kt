package org.teamvoided.voided_utils.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.enums.WallShape
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.WorldView
import org.teamvoided.voided_utils.blocks.voided.VoidedWallBlock


class ObsidianWallBlock :
    VoidedWallBlock(Blocks.OBSIDIAN, FabricBlockSettings.copyOf(Blocks.OBSIDIAN).pistonBehavior(PistonBehavior.BLOCK)) {

    private fun shouldConnectTo(state: BlockState, faceFullSquare: Boolean, side: Direction): Boolean {
        val block = state.block
        val bl = (block is FenceGateBlock && FenceGateBlock.canWallConnect(state, side))
        return state.isIn(BlockTags.WALLS) || !cannotConnect(state) && faceFullSquare || block is PaneBlock || bl || state.isOf(Blocks.NETHER_PORTAL)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val worldView = ctx.world
        val blockPos = ctx.blockPos
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        val blockPos2 = blockPos.north()
        val blockPos3 = blockPos.east()
        val blockPos4 = blockPos.south()
        val blockPos5 = blockPos.west()
        val blockPos6 = blockPos.up()
        val blockState = worldView.getBlockState(blockPos2)
        val blockState2 = worldView.getBlockState(blockPos3)
        val blockState3 = worldView.getBlockState(blockPos4)
        val blockState4 = worldView.getBlockState(blockPos5)
        val blockState5 = worldView.getBlockState(blockPos6)
        val bl = this.shouldConnectTo(
            blockState,
            blockState.isSideSolidFullSquare(worldView, blockPos2, Direction.SOUTH),
            Direction.SOUTH
        )
        val bl2 = this.shouldConnectTo(
            blockState2,
            blockState2.isSideSolidFullSquare(worldView, blockPos3, Direction.WEST),
            Direction.WEST
        )
        val bl3 = this.shouldConnectTo(
            blockState3,
            blockState3.isSideSolidFullSquare(worldView, blockPos4, Direction.NORTH),
            Direction.NORTH
        )
        val bl4 = this.shouldConnectTo(
            blockState4,
            blockState4.isSideSolidFullSquare(worldView, blockPos5, Direction.EAST),
            Direction.EAST
        )
        val blockState6 = defaultState.with(WATERLOGGED, fluidState.fluid === Fluids.WATER) as BlockState
        return updateShape(worldView, blockState6, blockPos6, blockState5, bl, bl2, bl3, bl4)
    }

    private fun updateShape(
        world: WorldView,
        state: BlockState,
        pos: BlockPos,
        aboveState: BlockState,
        north: Boolean,
        east: Boolean,
        south: Boolean,
        west: Boolean
    ): BlockState {
        val voxelShape = aboveState.getCollisionShape(world, pos).getFace(Direction.DOWN)
        val blockState = updateSides(state, north, east, south, west, voxelShape)
        return blockState.with(UP, shouldRaisePost(blockState, aboveState, voxelShape)) as BlockState
    }

    private fun updateSides(
        state: BlockState,
        north: Boolean,
        east: Boolean,
        south: Boolean,
        west: Boolean,
        aboveShape: VoxelShape
    ): BlockState {
        return (((state.with(NORTH_SHAPE, getWallShape(north, aboveShape, TALL_NORTH_SHAPE)) as BlockState).with(
            EAST_SHAPE,
            getWallShape(east, aboveShape, TALL_EAST_SHAPE)
        ) as BlockState).with(SOUTH_SHAPE, getWallShape(south, aboveShape, TALL_SOUTH_SHAPE)) as BlockState).with(
            WEST_SHAPE,
            getWallShape(west, aboveShape, TALL_WEST_SHAPE)
        ) as BlockState
    }

    private fun shouldRaisePost(state: BlockState, aboveState: BlockState, aboveShape: VoxelShape): Boolean {
        val bl7: Boolean
        val bl6: Boolean
        val bl: Boolean = aboveState.block is WallBlock && aboveState.get(UP) != false
        if (bl) {
            return true
        }
        val wallShape = state.get(NORTH_SHAPE)
        val wallShape2 = state.get(SOUTH_SHAPE)
        val wallShape3 = state.get(EAST_SHAPE)
        val wallShape4 = state.get(WEST_SHAPE)
        val bl22 = wallShape2 == WallShape.NONE
        val bl3 = wallShape4 == WallShape.NONE
        val bl4 = wallShape3 == WallShape.NONE
        val bl5 = wallShape == WallShape.NONE
        bl6 = bl5 && bl22 && bl3 && bl4 || bl5 != bl22 || bl3 != bl4
        if (bl6) {
            return true
        }
        bl7 =
            wallShape == WallShape.TALL && wallShape2 == WallShape.TALL || wallShape3 == WallShape.TALL && wallShape4 == WallShape.TALL
        return if (bl7) {
            false
        } else aboveState.isIn(BlockTags.WALL_POST_OVERRIDE) || isCovered(aboveShape, TALL_POST_SHAPE)
    }

    private fun isCovered(aboveShape: VoxelShape, tallShape: VoxelShape): Boolean {
        return !VoxelShapes.matchesAnywhere(tallShape, aboveShape, BooleanBiFunction.ONLY_FIRST)
    }

    private fun getWallShape(connected: Boolean, aboveShape: VoxelShape, tallShape: VoxelShape): WallShape {
        return if (connected) {
            if (isCovered(aboveShape, tallShape)) {
                WallShape.TALL
            } else WallShape.LOW
        } else WallShape.NONE
    }

    companion object {
        private val TALL_POST_SHAPE = createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 9.0)
        private val TALL_NORTH_SHAPE = createCuboidShape(7.0, 0.0, 0.0, 9.0, 16.0, 9.0)
        private val TALL_SOUTH_SHAPE = createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 16.0)
        private val TALL_WEST_SHAPE = createCuboidShape(0.0, 0.0, 7.0, 9.0, 16.0, 9.0)
        private val TALL_EAST_SHAPE = createCuboidShape(7.0, 0.0, 7.0, 16.0, 16.0, 9.0)

    }
}