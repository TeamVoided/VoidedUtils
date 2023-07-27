package org.teamvoided.voided_utils.blocks

import net.minecraft.block.CeilingHangingSignBlock
import net.minecraft.util.Identifier
import net.minecraft.util.SignType

class CustomHangingSignBlock(override val texture: Identifier, settings: Settings, signType: SignType?) :
    CeilingHangingSignBlock(settings, signType), CustomSign
