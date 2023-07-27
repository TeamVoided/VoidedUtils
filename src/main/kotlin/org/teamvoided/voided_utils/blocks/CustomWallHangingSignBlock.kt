package org.teamvoided.voided_utils.blocks

import net.minecraft.block.WallHangingSignBlock
import net.minecraft.util.Identifier
import net.minecraft.util.SignType

class CustomWallHangingSignBlock(override val texture: Identifier, settings: Settings, signType: SignType?) :
    WallHangingSignBlock(settings, signType), CustomSign
