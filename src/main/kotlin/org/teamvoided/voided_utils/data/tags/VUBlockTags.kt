package org.teamvoided.voided_utils.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils

object VUBlockTags {
    val CHARRED_LOGS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, VoidedUtils.id("charred_logs"))
    val TOGGLEABLE_BUTTONS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, VoidedUtils.id("toggleable_buttons"))
    @JvmField
    val SHEARS_MINEABLE_FAST: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, VoidedUtils.id("mineable/shears/fast"))
    @JvmField
    val SHEARS_MINEABLE_SLOW: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, VoidedUtils.id("mineable/shears/slow"))
}