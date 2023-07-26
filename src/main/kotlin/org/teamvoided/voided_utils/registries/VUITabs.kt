package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUItems.ALL_ITEM_LIST
import java.util.*

object VUITabs {
    private val VOIDED_UTILS_TAB = register("voided_utils_tab")
    private val ITEM_STACKS = LinkedList<ItemStack>()
    fun init() {
        ALL_ITEM_LIST.forEach { ITEM_STACKS.add(it.defaultStack) }

        Registry.register(
            Registries.ITEM_GROUP,
            VOIDED_UTILS_TAB,
            FabricItemGroup.builder()
                .icon { VUItems.CHARRED_HANGING_SIGN.defaultStack }
                .name(Text.translatable("Voided Utils"))
                .entries { _, entries ->
                    entries.addStacks(ALL_ITEM_LIST.map { it.defaultStack })
                }.build()
        )
    }

    private fun register(name: String): RegistryKey<ItemGroup> = RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))

}