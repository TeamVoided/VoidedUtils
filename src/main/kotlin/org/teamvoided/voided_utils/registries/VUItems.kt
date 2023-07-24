package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.voided_utils.VoidedUtils.id
import java.util.*

object VUItems {

    val ITEM_LIST = LinkedList<ItemStack>()
    val set = FabricItemSettings().maxCount(64)

    val TEST: Item = register("test", Item(set))


    fun register(id: String, item: Item): Item {
        ITEM_LIST.add(item.defaultStack)
        return Registry.register(Registries.ITEM, id(id), item)
    }

}