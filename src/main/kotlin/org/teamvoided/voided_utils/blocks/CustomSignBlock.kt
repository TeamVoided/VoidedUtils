package org.teamvoided.voided_utils.blocks

import net.minecraft.block.SignBlock
import net.minecraft.util.Identifier
import net.minecraft.util.SignType

class CustomSignBlock(override val texture: Identifier, settings: Settings, signType: SignType) :
    SignBlock(settings, signType), CustomSign
