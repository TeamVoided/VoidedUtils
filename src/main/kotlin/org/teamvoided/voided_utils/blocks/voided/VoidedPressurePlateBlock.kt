package org.teamvoided.voided_utils.blocks.voided

import net.minecraft.block.Block
import net.minecraft.block.BlockSetType
import net.minecraft.block.PressurePlateBlock

class VoidedPressurePlateBlock(
    val block: Block, activationRule: ActivationRule, settings: Settings, blockSetType: BlockSetType) :
    PressurePlateBlock(activationRule, settings, blockSetType)
