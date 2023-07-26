package org.teamvoided.voided_utils

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_utils.VoidedUtils.LOGGER
import org.teamvoided.voided_utils.data.providers.*
import java.util.concurrent.CompletableFuture

class VoidedUtilsData : DataGeneratorEntrypoint {
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
        pack.addProvider { output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider> ->
            BlockTagProvider(output, registriesFuture)
        }
    }
}