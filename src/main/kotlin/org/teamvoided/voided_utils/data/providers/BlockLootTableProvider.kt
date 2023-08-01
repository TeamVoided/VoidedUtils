package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.DoorBlock
import net.minecraft.block.InfestedBlock
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.VUBlocks.BLOCK_ITEM_LIST
import org.teamvoided.voided_utils.registries.VUItems
import org.teamvoided.voided_utils.registries.modules.ConsistentStones

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        try {
            addDrop(VUBlocks.CHARRED_SIGN) { VUItems.CHARRED_SIGN }
            addDrop(VUBlocks.CHARRED_HANGING_SIGN) { VUItems.CHARRED_HANGING_SIGN }
            add(VUBlocks.CHARRED_DOOR) { doorDrops(it) }
            add(VUBlocks.IRON_COATED_DOOR) { doorDrops(it) }

            ConsistentStones.INFESTED_LIST.forEach { addDropWithSilkTouch(it, it.regularBlock) }

            BLOCK_ITEM_LIST.forEach { if (it !is DoorBlock && it !is InfestedBlock) addDrop(it) }
        } catch (e: Exception) {
            VoidedUtils.LOGGER.error("Error {}", e.toString())
        }
    }
}