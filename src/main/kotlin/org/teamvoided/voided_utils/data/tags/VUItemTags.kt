package org.teamvoided.voided_utils.data.tags

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils

object VUItemTags {
    val CHARRED_LOGS: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, VoidedUtils.id("charred_logs"))
    val TOGGLEABLE_BUTTONS: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, VoidedUtils.id("toggleable_buttons"))
}