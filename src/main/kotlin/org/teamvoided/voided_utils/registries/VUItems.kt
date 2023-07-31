package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.voided_utils.VoidedUtils.getConfig
import org.teamvoided.voided_utils.VoidedUtils.id
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object VUItems {

    val ITEM_LIST = LinkedList<Item>()
    val ALL_ITEM_LIST = LinkedList<Item>()

    private val settings: FabricItemSettings = FabricItemSettings().maxCount(64)

    val CHARRED_SIGN: Item = SignItem(Item.Settings().maxCount(16), VUBlocks.CHARRED_SIGN, VUBlocks.CHARRED_WALL_SIGN)
    val CHARRED_HANGING_SIGN: Item =
        HangingSignItem(VUBlocks.CHARRED_HANGING_SIGN, VUBlocks.CHARRED_WALL_HANGING_SIGN, Item.Settings().maxCount(16))

    fun init() {
        val c = getConfig()
        if (c.enableCharredWoodSet) {
            register("charred_sign", CHARRED_SIGN)
            register("charred_hanging_sign", CHARRED_HANGING_SIGN)
        }
    }

    private fun register(id: String, item: Item): Item {
        ITEM_LIST.add(item)
        ALL_ITEM_LIST.add(item)
        return Registry.register(Registries.ITEM, id(id), item)
    }

}