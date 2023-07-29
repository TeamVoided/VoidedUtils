package org.teamvoided.voided_utils.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.LanternBlock
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.BlockView
import net.minecraft.world.World
@Suppress("OVERRIDE_DEPRECATION")
class RedstoneLanternBlock(settings: FabricBlockSettings) : LanternBlock(settings) {
    init {
        defaultState = stateManager.defaultState.with(LIT, true)
    }

    override fun onBlockAdded(state: BlockState?, world: World, pos: BlockPos, oldState: BlockState?, notify: Boolean) {
        for (direction in Direction.entries) {
            world.updateNeighborsAlways(pos.offset(direction), this)
        }
    }

    override fun onStateReplaced(
        state: BlockState?, world: World, pos: BlockPos, newState: BlockState?, moved: Boolean,
    ) {
        if (!moved) {
            for (direction in Direction.entries) {
                world.updateNeighborsAlways(pos.offset(direction), this)
            }
        }
    }

    override fun neighborUpdate(
        state: BlockState,
        world: World,
        pos: BlockPos,
        block: Block?,
        fromPos: BlockPos?,
        notify: Boolean,
    ) {
        if (state.get(LIT) == this.shouldUnpower(world, pos, state)
            && !world.blockTickScheduler.willTick(pos, this)
        ) {
            world.scheduleBlockTick(pos, this, 2)
        }
    }

    override fun getWeakRedstonePower(state: BlockState, world: BlockView?, pos: BlockPos?, direction: Direction): Int {
        if (state.get(HANGING)) {
            return if (state.get(LIT) && Direction.DOWN != direction) 15 else 0
        }
        return if (state.get(LIT) && Direction.UP != direction) 15 else 0
    }

    override fun getStrongRedstonePower(
        state: BlockState, world: BlockView?, pos: BlockPos?, direction: Direction,
    ): Int {

        if (state.get(HANGING)) {
            return if (direction == Direction.UP) state.getWeakRedstonePower(world, pos, direction) else 0
        }
        return if (direction == Direction.DOWN) state.getWeakRedstonePower(world, pos, direction) else 0
    }

    private fun shouldUnpower(world: World, pos: BlockPos, state: BlockState): Boolean {
        if (state.get(HANGING)) {
            return world.isEmittingRedstonePower(pos.up(), Direction.UP)
        }
        return world.isEmittingRedstonePower(pos.down(), Direction.DOWN)
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos?, random: RandomGenerator?) {
        world.setBlockState(
            pos,
            state.with(LIT, !(state.get(LIT) && shouldUnpower(world, pos!!, state))) as BlockState,
            3
        )
    }

    override fun isRedstonePowerSource(state: BlockState?): Boolean {
        return true
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(LIT)
    }

    companion object {
        val LIT: BooleanProperty = Properties.LIT
        val HANGING: BooleanProperty = Properties.HANGING
    }
}