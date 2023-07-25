package org.teamvoided.voided_utils

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.VUITabs
import org.teamvoided.voided_utils.registries.VUItems

@Suppress("unused")
object VoidedUtils {
    const val MODID = "voided_utils"
    val LOGGER = LoggerFactory.getLogger(VoidedUtils::class.java)
    fun id(path: String): Identifier = Identifier(MODID, path)
    fun getId(item: Item): Identifier = Registries.ITEM.getId(item)
    fun getId(block: Block): Identifier = Registries.BLOCK.getId(block)

    fun commonInit() {
        LOGGER.info("Hello from Common")
        VUItems.init()
        VUBlocks.init()
        VUITabs.init()
    }

    fun clientInit() {
        LOGGER.info("Hello from clientInit")
    }

//    fun dataInit(gen: FabricDataGenerator) {
//
//    }
}