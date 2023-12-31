package org.teamvoided.voided_utils

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.TexturedRenderLayers
import net.minecraft.client.resource.Material
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.scuffedlib.sign.MaterialRegistry
import org.teamvoided.voided_utils.config.Config
import org.teamvoided.voided_utils.config.ConfigData
import org.teamvoided.voided_utils.misc.Injections
import org.teamvoided.voided_utils.misc.Keybinds
import org.teamvoided.voided_utils.registries.*
import org.teamvoided.voided_utils.registries.modules.CharredWoodSet
import org.teamvoided.voidlib.config.ConfigManager

@Suppress("unused")
object VoidedUtils {
    const val MODID = "voided_utils"
    val LOGGER: Logger = LoggerFactory.getLogger(VoidedUtils::class.java)
    fun id(path: String): Identifier = Identifier(MODID, path)
    fun id(namespace: String, path: String): Identifier = Identifier(namespace, path)
    fun commonInit() {
        ConfigManager.registerConfig(Config)
        LOGGER.info("Utilizing your eRam and qRam")
        VUItems.init()
        VUBlocks.init()
        VUITabs.init()
        VUWorldGen.init()
        Keybinds.init()
        Injections.init()
    }

    fun clientInit() {
        VUBlocks.CUTOUT_LIST.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }

        val c = getConfig()
        if (c.enableCharredWoodSet) {
            MaterialRegistry.addId(Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, CharredWoodSet.CHARRED_SIGN_ID))
            MaterialRegistry.addId(
                Material(
                    TexturedRenderLayers.SIGNS_ATLAS_TEXTURE,
                    CharredWoodSet.CHARRED_HANGING_SIGN_ID
                )
            )
        }
    }

    fun getConfig(): ConfigData = Config.config
}