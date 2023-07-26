package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import org.teamvoided.voided_utils.registries.VUBlocks.BLOCK_LIST

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        BLOCK_LIST.forEach { addDrop(it) }
    }
}