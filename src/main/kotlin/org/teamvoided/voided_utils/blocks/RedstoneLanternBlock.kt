package org.teamvoided.voided_utils.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.LanternBlock
import net.minecraft.block.RedstoneTorchBlock
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
class RedstoneLanternBlock(settings: FabricBlockSettings)  : LanternBlock(settings) {
   init {
       setDefaultState(stateManager.getDefaultState().with(LIT, false))
    }

//    override fun getWeakRedstonePower(state: BlockState, world: BlockView?, pos: BlockPos?, direction: Direction): Int {
//        return if (state.get(RedstoneTorchBlock.LIT) && Direction.UP != direction) 15 else 0
//    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(RedstoneTorchBlock.LIT)
    }

    companion object {
        val LIT: BooleanProperty = BooleanProperty.of("lit")
    }
}