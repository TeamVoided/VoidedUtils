package org.teamvoided.voided_utils.blocks.voided

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.WallBlock

open class VoidedWallBlock(val block: Block, settings: FabricBlockSettings) : WallBlock(settings) {
    constructor(block: Block) : this(block, FabricBlockSettings.copyOf(block))
}
