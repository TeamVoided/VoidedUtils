package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.ModelIds
import net.minecraft.data.client.model.Models
import net.minecraft.data.client.model.Texture
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.VoidedUtils.mc
import org.teamvoided.voided_utils.blocks.AbstractToggleableButtonBlock
import org.teamvoided.voided_utils.registries.VUBlocks

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    private val logs = listOf(
        Log(VUBlocks.CHARRED_LOG, VUBlocks.CHARRED_WOOD),
        Log(VUBlocks.STRIPPED_CHARRED_LOG, VUBlocks.STRIPPED_CHARRED_WOOD)
    )


    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        try {
            logs.forEach { gen.registerLog(it.log).log(it.log).wood(it.wood) }

            gen.registerCubeAllModelTexturePool(VUBlocks.CHARRED_PLANKS).family(VUBlocks.CHARRED_FAMILY)
            gen.method_46190(
                VUBlocks.STRIPPED_CHARRED_LOG,
                VUBlocks.CHARRED_HANGING_SIGN,
                VUBlocks.CHARRED_WALL_HANGING_SIGN
            )
            registerTrapdoor(VUBlocks.IRON_COATED_TRAPDOOR, mc("block/iron_trapdoor"), gen)
            registerDoor(VUBlocks.IRON_COATED_DOOR, Blocks.IRON_DOOR, mc("item/iron_door"), gen)
            VUBlocks.TOGGLEABLE_BUTTONS.forEach { block ->
                val base = getId((block as AbstractToggleableButtonBlock).buttonBlock).path.removeSuffix("_button")
                LOGGER.info(base)
                button(block, mc("block/$base"), gen)
            }
        } catch (_: Exception) {
        }


    }

    override fun generateItemModels(gen: ItemModelGenerator) {}

    private fun registerTrapdoor(trapdoorBlock: Block, customTexture: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(customTexture)
        val id = Models.TEMPLATE_TRAPDOOR_TOP.upload(trapdoorBlock, texture, gen.modelCollector)
        val id2 = Models.TEMPLATE_TRAPDOOR_BOTTOM.upload(trapdoorBlock, texture, gen.modelCollector)
        val id3 = Models.TEMPLATE_TRAPDOOR_OPEN.upload(trapdoorBlock, texture, gen.modelCollector)
        gen.blockStateCollector.accept(BlockStateModelGenerator.createTrapdoorBlockState(trapdoorBlock, id, id2, id3))
        gen.registerParentedItemModel(trapdoorBlock, id2)
    }

    private fun registerDoor(doorBlock: Block, block: Block, customTexture: Identifier, gen: BlockStateModelGenerator) {
        val tex = Texture.topBottom(block)
        val id = Models.DOOR_BOTTOM_LEFT.upload(doorBlock, tex, gen.modelCollector)
        val id2 = Models.DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock, tex, gen.modelCollector)
        val id3 = Models.DOOR_BOTTOM_RIGHT.upload(doorBlock, tex, gen.modelCollector)
        val id4 = Models.DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock, tex, gen.modelCollector)
        val id5 = Models.DOOR_TOP_LEFT.upload(doorBlock, tex, gen.modelCollector)
        val id6 = Models.DOOR_TOP_LEFT_OPEN.upload(doorBlock, tex, gen.modelCollector)
        val id7 = Models.DOOR_TOP_RIGHT.upload(doorBlock, tex, gen.modelCollector)
        val id8 = Models.DOOR_TOP_RIGHT_OPEN.upload(doorBlock, tex, gen.modelCollector)
        registerItemModel(doorBlock.asItem(), customTexture, gen)
        gen.blockStateCollector.accept(
            BlockStateModelGenerator.method_25609(doorBlock, id, id2, id3, id4, id5, id6, id7, id8)
        )
    }

    private fun button(buttonBlock: Block, customTexture: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(customTexture)
        val id = Models.BUTTON.upload(buttonBlock, texture, gen.modelCollector)
        val id2 = Models.BUTTON_PRESSED.upload(buttonBlock, texture, gen.modelCollector)
        gen.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, id, id2))
        val id3 = Models.BUTTON_INVENTORY.upload(buttonBlock, texture, gen.modelCollector)
        gen.registerParentedItemModel(buttonBlock, id3)
    }

    private fun registerItemModel(item: Item, customTexture: Identifier, gen: BlockStateModelGenerator) {
        Models.SINGLE_LAYER_ITEM.upload(
            ModelIds.getItemModelId(item),
            Texture.layer0(customTexture),
            gen.modelCollector
        )
    }

    data class Log(val log: Block, val wood: Block)
}