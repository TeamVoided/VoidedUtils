package org.teamvoided.voided_utils

import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.block.Block
import net.minecraft.client.option.KeyBind
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.TexturedRenderLayers
import net.minecraft.client.resource.Material
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.lwjgl.glfw.GLFW
import org.slf4j.LoggerFactory
import org.teamvoided.voided_utils.registries.*

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
        VUSigns.init()
        VUITabs.init()

        ClientTickEvents.END_CLIENT_TICK.register(
            ClientTickEvents.EndTick
            {
                if (configKey.wasPressed()) {
                    LOGGER.info("pain")
                }
            })
    }


    private val configKey: KeyBind = KeyBindingHelper.registerKeyBinding(
        KeyBind(
            "key.block_beams.config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category.block_beams.generic"
        )
    )

    fun clientInit() {
        LOGGER.info("Hello from clientInit")
        BlockRenderLayerMap.INSTANCE.putBlock(VUBlocks.CHARRED_DOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(VUBlocks.CHARRED_TRAPDOOR, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(VUBlocks.REDSTONE_LANTERN, RenderLayer.getCutout())

        MaterialRegistry.addIdentifier(Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, VUBlocks.CHARRED_SIGN_ID))
        MaterialRegistry.addIdentifier(Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, VUBlocks.CHARRED_HANGING_SIGN_ID))

        MaterialRegistry.getIdentifiers().stream().forEach { LOGGER.info(it.toString()) }
    }
}