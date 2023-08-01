package org.teamvoided.voided_utils.blocks.voided

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*

open class VoidedButtonBlock(val block: Block, settings: FabricBlockSettings, blockSetType: BlockSetType, onTicks: Int, activatedByProjectile: Boolean) :
    AbstractButtonBlock(settings, blockSetType, onTicks, activatedByProjectile)
