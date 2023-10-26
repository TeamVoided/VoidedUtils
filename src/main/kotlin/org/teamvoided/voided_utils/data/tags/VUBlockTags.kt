package org.teamvoided.voided_utils.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils.id

object VUBlockTags {
    val CHARRED_LOGS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("charred_logs"))
    val TOGGLEABLE_BUTTONS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("toggleable_buttons"))
    @JvmField
    val SHEARS_MINEABLE_FAST: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("mineable/shears_fast"))
    @JvmField
    val NETHER_PORTAL_BLOCK: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("nether_portal_block"))
}