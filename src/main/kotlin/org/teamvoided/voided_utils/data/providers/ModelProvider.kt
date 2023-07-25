package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Models
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUBlocks

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        gen.registerSimpleCubeAll(VUBlocks.CHARRED_PLANKS)
        gen.registerAxisRotated(VUBlocks.CHARRED_LOG, id("charred_log"))
        gen.registerAxisRotated(VUBlocks.CHARRED_WOOD, id("charred_wood"))
        gen.registerAxisRotated(VUBlocks.STRIPPED_CHARRED_LOG, id("stripped_charred_log"))
    }

    override fun generateItemModels(gen: ItemModelGenerator) {

        gen.register(VUBlocks.CHARRED_PLANKS.asItem(), Models.CUBE_ALL)
    }
}