package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.voided_utils.VoidedUtils.id
import java.util.*

object VUItems {

    val items = LinkedList<Item>()
    private val settings: FabricItemSettings = FabricItemSettings().maxCount(64)

    val TEST: Item = register("test", Item(settings))
    val TES2T: Item = register("tes2t", Item(settings))
    fun init(){}
    private fun register(id: String, item: Item): Item {
        items.add(item)
        return Registry.register(Registries.ITEM, id(id), item)
    }

}