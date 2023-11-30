package org.teamvoided.voided_utils.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils.id

object VUBlockTags {
    val CHARRED_LOGS: TagKey<Block> = reg("charred_logs")
    val TOGGLEABLE_BUTTONS: TagKey<Block> = reg("toggleable_buttons")
    @JvmField
    val SHEARS_MINEABLE_FAST: TagKey<Block> = reg("mineable/shears_fast")
    @JvmField
    val NETHER_PORTAL_BLOCK: TagKey<Block> = reg("nether_portal_block")
    @JvmField
    val AIR_PASSABLE: TagKey<Block> = reg("air_passable")


    private fun reg(id: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}