package org.teamvoided.voided_utils.blocks.voided

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.StairsBlock

class VoidedStairBlock(val block: Block) : StairsBlock( block.defaultState,FabricBlockSettings.copyOf(block))
