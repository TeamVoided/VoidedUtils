package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUItems.ITEM_LIST

object VUBlocks {

    val TEST_BLOCK: Block = registerWithItem("test_block", Block(FabricBlockSettings.create()))

    fun registerWithItem(id: String, block: Block): Block {
        ITEM_LIST.add(Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings())).defaultStack)
        return register(id, block)
    }

    private fun register(id: String, block: Block) = Registry.register(Registries.BLOCK, id(id), block)
}