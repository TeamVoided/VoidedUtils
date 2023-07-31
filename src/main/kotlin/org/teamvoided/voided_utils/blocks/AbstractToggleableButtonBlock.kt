package org.teamvoided.voided_utils.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AbstractButtonBlock
import net.minecraft.block.BlockSetType
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent

@Suppress("OVERRIDE_DEPRECATION")
class AbstractToggleableButtonBlock(
    val btn: AbstractButtonBlock, blockSetType: BlockSetType, private val activatedByProjectile: Boolean,
) :
    AbstractButtonBlock(FabricBlockSettings.copyOf(btn), blockSetType, 0, activatedByProjectile) {


    override fun onUse(
        state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult,
    ): ActionResult {
        togglePow(state, world, pos, !state.get(POWERED), player)
        return ActionResult.success(world.isClient)
    }


    private fun togglePow(state: BlockState, world: World, pos: BlockPos, value: Boolean, entity: Entity) {
        world.setBlockState(pos, state.with(POWERED, value) as BlockState, 3)
        updateNeighbors(state, world, pos)
        playClickSound(null, world, pos, value)
        world.emitGameEvent(
            entity,
            if (value) GameEvent.BLOCK_ACTIVATE else GameEvent.BLOCK_DEACTIVATE,
            pos
        )
    }

    private fun updateNeighbors(state: BlockState, world: World, pos: BlockPos) {
        world.updateNeighborsAlways(pos, this)
        world.updateNeighborsAlways(pos.offset(getDirection(state).opposite), this)
    }

    override fun tryPowerWithProjectiles(state: BlockState, world: World, pos: BlockPos) {
        if (activatedByProjectile) {
            val pProjectileEntity: PersistentProjectileEntity? =
                world.getNonSpectatingEntities(
                    PersistentProjectileEntity::class.java, state.getOutlineShape(world, pos).boundingBox.offset(pos)
                )
                    .stream()
                    .findFirst()
                    .orElse(null)
            if (pProjectileEntity != null) togglePow(state, world, pos, state.get(POWERED), pProjectileEntity)
        }
    }


}