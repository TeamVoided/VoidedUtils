package org.teamvoided.voided_utils

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.voided_utils.data.providers.BlockLootTableProvider
import org.teamvoided.voided_utils.data.providers.ModelProvider
import org.teamvoided.voided_utils.data.providers.RecipeProvider
import org.teamvoided.voided_utils.registries.VUITabs

@Suppress("unused")
object VoidedUtils {
    const val MODID = "voided_utils"
    val LOGGER = LoggerFactory.getLogger(VoidedUtils::class.java)
    fun id(path: String): Identifier = Identifier(MODID, path)

    fun commonInit() {
        LOGGER.info("Hello from registries")
        VUITabs.init()
    }

    fun clientInit() {
        LOGGER.info("Hello from clientInit")
    }

    fun dataInit(gen: FabricDataGenerator) {

        LOGGER.info("Hello from DataInit")
        val pack: FabricDataGenerator.Pack = gen.createPack()

        pack.addProvider { output: FabricDataOutput -> RecipeProvider(output) }
        pack.addProvider { output: FabricDataOutput -> ModelProvider(output) }
        pack.addProvider { output: FabricDataOutput -> BlockLootTableProvider(output) }
    }
}