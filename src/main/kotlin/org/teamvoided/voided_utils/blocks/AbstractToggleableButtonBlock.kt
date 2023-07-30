package org.teamvoided.voided_utils.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AbstractButtonBlock
import net.minecraft.block.BlockSetType
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent

@Suppress("OVERRIDE_DEPRECATION")
class AbstractToggleableButtonBlock(
    val buttonBlock: AbstractButtonBlock, blockSetType: BlockSetType, activatedByProjectile: Boolean,
) :
    AbstractButtonBlock(FabricBlockSettings.copyOf(buttonBlock), blockSetType, 0, activatedByProjectile) {


    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult,
    ): ActionResult? {
        return if (state.get(POWERED)) {
            ActionResult.CONSUME
        } else {
            if (state.get(POWERED)) {
                powerOn(state, world, pos, false)
                playClickSound(player, world, pos, false)
                world.emitGameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos)
                ActionResult.success(world.isClient)
            } else {
                powerOn(state, world, pos, true)
                playClickSound(player, world, pos, true)
                world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos)
                ActionResult.success(world.isClient)
            }
        }
    }


    fun powerOn(state: BlockState, world: World, pos: BlockPos, value: Boolean) {
        world.setBlockState(pos, state.with(POWERED, value) as BlockState, 3)
        updateNeighbors(state, world, pos)
    }

    private fun updateNeighbors(state: BlockState, world: World, pos: BlockPos) {
        world.updateNeighborsAlways(pos, this)
        world.updateNeighborsAlways(pos.offset(getDirection(state).opposite), this)
    }


}