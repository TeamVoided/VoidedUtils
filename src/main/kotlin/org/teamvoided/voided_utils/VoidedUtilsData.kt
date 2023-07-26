package org.teamvoided.voided_utils

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import org.teamvoided.voided_utils.VoidedUtils.LOGGER
import org.teamvoided.voided_utils.data.providers.BlockLootTableProvider
import org.teamvoided.voided_utils.data.providers.EnglishTranslationProvider
import org.teamvoided.voided_utils.data.providers.ModelProvider
import org.teamvoided.voided_utils.data.providers.RecipeProvider

class VoidedUtilsData  : DataGeneratorEntrypoint{
    init {
        LOGGER.info("DataInit init")
    }
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        LOGGER.info("Hello from DataInit")
        val pack: FabricDataGenerator.Pack = gen.createPack()
        pack.addProvider { output: FabricDataOutput -> RecipeProvider(output) }
        pack.addProvider { output: FabricDataOutput -> ModelProvider(output) }
        pack.addProvider { output: FabricDataOutput -> BlockLootTableProvider(output) }
        pack.addProvider { output: FabricDataOutput -> EnglishTranslationProvider(output) }
    }
}