package org.teamvoided.voided_utils

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.apache.http.config.RegistryBuilder
import org.teamvoided.voided_utils.VoidedUtils.LOGGER
import org.teamvoided.voided_utils.VoidedUtils.MODID
import org.teamvoided.voided_utils.data.providers.*
import org.teamvoided.voided_utils.registries.VUWorldGen


class VoidedUtilsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        LOGGER.info("Hello from DataInit")
        val pack: FabricDataGenerator.Pack = gen.createPack()
        pack.addProvider { o: FabricDataOutput -> RecipeProvider(o) }
        pack.addProvider { o: FabricDataOutput -> ModelProvider(o) }
        pack.addProvider { o: FabricDataOutput -> BlockLootTableProvider(o) }
        pack.addProvider { o: FabricDataOutput -> EnglishTranslationProvider(o) }
        pack.addProvider { o, r -> BlockTagProvider(o, r) }
        pack.addProvider { o, r -> ItemTagProvider(o, r) }
        pack.addProvider { o, r -> WorldGenProvider(o, r) }
    }


    /**
     * This method is called by the Fabric Datagen module to build registries.
     * @param registryBuilder a [RegistryBuilder] instance
     */
    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
        // Add all the registries we want to generate data for here.
        registryBuilder.add(RegistryKeys.CONFIGURED_FEATURE) { VUWorldGen.configFeatures(it) }
    }

    override fun getEffectiveModId(): String = MODID
}