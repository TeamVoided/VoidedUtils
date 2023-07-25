package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import org.teamvoided.voided_utils.VoidedUtils.id

object VUITabs {

    private val SHIPPOST_TAB = register("shippost_tab")

    fun init() {
        Registry.register(
            Registries.ITEM_GROUP,
            SHIPPOST_TAB,
            FabricItemGroup.builder()
                .icon { VUItems.TEST.defaultStack }
                .name(Text.translatable("Voided Utils"))
                .entries { _, entries -> entries.addAll(VUItems.ITEM_LIST)
                }.build()
        )
    }

    private fun register(name: String): RegistryKey<ItemGroup> = RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))

}