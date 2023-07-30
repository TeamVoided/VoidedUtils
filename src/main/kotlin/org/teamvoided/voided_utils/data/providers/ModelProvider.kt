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
            registerTrapdoor(VUBlocks.IRON_COATED_TRAPDOOR, Identifier("block/iron_trapdoor"), gen)
            registerDoor(VUBlocks.IRON_COATED_DOOR, Blocks.IRON_DOOR, Identifier("item/iron_door"), gen)
            VUBlocks.TOGGLEABLE_BUTTONS.forEach { block ->
                val base = getId((block as AbstractToggleableButtonBlock).buttonBlock).path.removeSuffix("_button")
                LOGGER.info(base)
                button(block, Identifier("block/$base"), gen) }
        } catch (_: Exception) {
        }


    }

    override fun generateItemModels(gen: ItemModelGenerator) {}

    private fun registerTrapdoor(trapdoorBlock: Block, customTexture: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(customTexture)
        val identifier = Models.TEMPLATE_TRAPDOOR_TOP.upload(trapdoorBlock, texture, gen.modelCollector)
        val identifier2 = Models.TEMPLATE_TRAPDOOR_BOTTOM.upload(trapdoorBlock, texture, gen.modelCollector)
        val identifier3 = Models.TEMPLATE_TRAPDOOR_OPEN.upload(trapdoorBlock, texture, gen.modelCollector)
        gen.blockStateCollector.accept(
            BlockStateModelGenerator.createTrapdoorBlockState(
                trapdoorBlock,
                identifier,
                identifier2,
                identifier3
            )
        )
        gen.registerParentedItemModel(trapdoorBlock, identifier2)
    }

    private fun registerDoor(doorBlock: Block, block: Block, customTexture: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.topBottom(block)
        val identifier = Models.DOOR_BOTTOM_LEFT.upload(doorBlock, texture, gen.modelCollector)
        val identifier2 = Models.DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock, texture, gen.modelCollector)
        val identifier3 = Models.DOOR_BOTTOM_RIGHT.upload(doorBlock, texture, gen.modelCollector)
        val identifier4 = Models.DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock, texture, gen.modelCollector)
        val identifier5 = Models.DOOR_TOP_LEFT.upload(doorBlock, texture, gen.modelCollector)
        val identifier6 = Models.DOOR_TOP_LEFT_OPEN.upload(doorBlock, texture, gen.modelCollector)
        val identifier7 = Models.DOOR_TOP_RIGHT.upload(doorBlock, texture, gen.modelCollector)
        val identifier8 = Models.DOOR_TOP_RIGHT_OPEN.upload(doorBlock, texture, gen.modelCollector)
        registerItemModel(doorBlock.asItem(), customTexture, gen)
        gen.blockStateCollector.accept(
            BlockStateModelGenerator.method_25609(
                doorBlock,
                identifier,
                identifier2,
                identifier3,
                identifier4,
                identifier5,
                identifier6,
                identifier7,
                identifier8
            )
        )
    }

    private fun button(buttonBlock: Block, customTexture: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(customTexture)
        val identifier = Models.BUTTON.upload(buttonBlock, texture, gen.modelCollector)
        val identifier2 =
            Models.BUTTON_PRESSED.upload(buttonBlock, texture, gen.modelCollector)
        gen.blockStateCollector.accept(
            BlockStateModelGenerator.createButtonBlockState(
                buttonBlock,
                identifier,
                identifier2
            )
        )
        val identifier3 =
            Models.BUTTON_INVENTORY.upload(buttonBlock, texture, gen.modelCollector)
        gen.registerParentedItemModel(buttonBlock, identifier3)
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