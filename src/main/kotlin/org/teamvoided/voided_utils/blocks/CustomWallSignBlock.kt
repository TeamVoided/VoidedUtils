package org.teamvoided.voided_utils.blocks

import net.minecraft.block.WallSignBlock
import net.minecraft.util.Identifier
import net.minecraft.util.SignType

class CustomWallSignBlock(override val texture: Identifier, settings: Settings, signType: SignType) :
    WallSignBlock(settings, signType), CustomSign
