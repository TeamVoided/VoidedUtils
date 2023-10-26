package org.teamvoided.voided_utils.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.block.piston.PistonBehavior
import org.teamvoided.voided_utils.blocks.voided.VoidedWallBlock


class ObsidianWallBlock :
    VoidedWallBlock(Blocks.OBSIDIAN, FabricBlockSettings.copyOf(Blocks.OBSIDIAN).pistonBehavior(PistonBehavior.BLOCK)) {

//    override fun shouldConnectTo(state: BlockState?, faceFullSquare: Boolean, side: Direction?): Boolean {
//        return super.shouldConnectTo(state, faceFullSquare, side)
//    }
}