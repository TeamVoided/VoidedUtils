package org.teamvoided.voided_utils.blocks.voided

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock

class VoidedSlabBlock(val block: Block) : SlabBlock(FabricBlockSettings.copyOf(block))
