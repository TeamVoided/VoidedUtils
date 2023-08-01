package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.VoidedUtils.mc
import org.teamvoided.voided_utils.blocks.voided.*
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.modules.ConsistentStones

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
                VUBlocks.STRIPPED_CHARRED_LOG, VUBlocks.CHARRED_HANGING_SIGN, VUBlocks.CHARRED_WALL_HANGING_SIGN
            )

            registerTrapdoor(VUBlocks.IRON_COATED_TRAPDOOR, mc("block/iron_trapdoor"), gen)
            registerDoor(VUBlocks.IRON_COATED_DOOR, Blocks.IRON_DOOR, mc("item/iron_door"), gen)

            VUBlocks.TOGGLEABLE_BUTTONS.forEach { inheritBtn(it, getId(it.btn), gen) }
            ConsistentStones.INFESTED_LIST.forEach { gen.registerInfested(it.regularBlock, it) }

            stairs(ConsistentStones.SNOW_STAIR, Blocks.SNOW, gen)
            stairs(ConsistentStones.CUT_SANDSTONE_STAIR, mc("sandstone_top"), getId(Blocks.CUT_SANDSTONE), gen)
            stairs(
                ConsistentStones.CUT_RED_SANDSTONE_STAIR, mc("red_sandstone_top"), getId(Blocks.CUT_RED_SANDSTONE), gen
            )
            ConsistentStones.STAIR_LIST
                .filter {
                    it != ConsistentStones.SNOW_STAIR
                            && it != ConsistentStones.CUT_SANDSTONE_STAIR
                            && it != ConsistentStones.CUT_RED_SANDSTONE_STAIR
                }.forEach { stairs(it, gen) }

            ConsistentStones.SLAB_LIST.forEach { slab(it, gen) }
            ConsistentStones.WALL_LIST.filter {
                it != ConsistentStones.SNOW_WALL
                        && it != ConsistentStones.SMOOTH_SANDSTONE_WALL
                        && it != ConsistentStones.SMOOTH_RED_SANDSTONE_WALL
                        && it != ConsistentStones.SMOOTH_QUARTZ_WALL
                        && it != ConsistentStones.QUARTZ_WALL
            }.forEach { wall(it, gen) }

            listOf(
                Pair(ConsistentStones.SNOW_WALL, mc("snow")),
                Pair(ConsistentStones.SMOOTH_SANDSTONE_WALL, mc("sandstone_top")),
                Pair(ConsistentStones.SMOOTH_RED_SANDSTONE_WALL, mc("red_sandstone_top")),
                Pair(ConsistentStones.SMOOTH_QUARTZ_WALL, mc("quartz_block_bottom")),
                Pair(ConsistentStones.QUARTZ_WALL, mc("quartz_block_side"))
            ).forEach { wall(it.first, it.second, gen) }

            ConsistentStones.BTN_LIST.forEach { btn(it, gen) }
            ConsistentStones.PLATE_LIST.forEach {
                when (it) {
                    ConsistentStones.DEEPSLATE_PRESSURE_PLATE -> pressurePlate(it, mc("deepslate_top"), gen)
                    ConsistentStones.BLACKSTONE_PRESSURE_PLATE -> pressurePlate(it, mc("blackstone_top"), gen)
                    else -> pressurePlate(it, gen)

                }

            }

            fence(ConsistentStones.RED_NETHER_BRICK_FENCE, getId(Blocks.RED_NETHER_BRICKS).withPrefix("block/"), gen)

            VUBlocks.FULL_SQUARE_LIST.forEach { gen.registerSimpleCubeAll(it) }
        } catch (e: Exception) {
            VoidedUtils.LOGGER.error("Error {}", e.toString())
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

    private fun stairs(block: VoidedStairBlock, gen: BlockStateModelGenerator) =
        stairs(block, block.block, block.block, block.block, block.block, gen)

    private fun stairs(block: VoidedStairBlock, src: Block, gen: BlockStateModelGenerator) =
        stairs(block, src, src, src, src, gen)

    private fun stairs(
        block: VoidedStairBlock, src: Block, bottom: Block, side: Block, top: Block, gen: BlockStateModelGenerator,
    ) = stairs(block, src, getId(bottom), getId(side), getId(top), gen)

    private fun stairs(block: VoidedStairBlock, ends: Identifier, side: Identifier, gen: BlockStateModelGenerator) =
        stairs(block, block.block, ends, side, ends, gen)

    private fun stairs(
        block: VoidedStairBlock,
        src: Block, bottom: Identifier, side: Identifier, top: Identifier, gen: BlockStateModelGenerator,
    ) {
        val texture: Texture = Texture.texture(src)
            .put(TextureKey.BOTTOM, bottom.withPrefix("block/"))
            .put(TextureKey.SIDE, side.withPrefix("block/"))
            .put(TextureKey.TOP, top.withPrefix("block/"))

        val id: Identifier = Models.INNER_STAIRS.upload(block, texture, gen.modelCollector)
        val id2: Identifier = Models.STAIRS.upload(block, texture, gen.modelCollector)
        val id3: Identifier = Models.OUTER_STAIRS.upload(block, texture, gen.modelCollector)

        gen.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(block, id, id2, id3))
        gen.registerParentedItemModel(block, id2)
    }


    private fun slab(block: VoidedSlabBlock, gen: BlockStateModelGenerator) = slab(block, block.block, gen)
    private fun slab(block: VoidedSlabBlock, src: Block, gen: BlockStateModelGenerator) =
        slab(block, src, src, src, gen)

    private fun slab(block: VoidedSlabBlock, bottom: Block, side: Block, top: Block, gen: BlockStateModelGenerator) =
        slab(
            block, Texture.texture(block.block)
                .put(TextureKey.BOTTOM, getId(bottom).withPrefix("block/"))
                .put(TextureKey.SIDE, getId(side).withPrefix("block/"))
                .put(TextureKey.TOP, getId(top).withPrefix("block/")), gen
        )

    fun slab(block: VoidedSlabBlock, texture: Texture, gen: BlockStateModelGenerator) {
        val id = Models.SLAB.upload(block, texture, gen.modelCollector)
        val id2 = Models.SLAB_TOP.upload(block, texture, gen.modelCollector)
        val id3 = getId(block.block).withPrefix("block/")
        gen.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(block, id, id2, id3))
        gen.registerParentedItemModel(block, id)
    }

    fun wall(block: VoidedWallBlock, gen: BlockStateModelGenerator) = wall(block, getId(block.block), gen)

    fun wall(wallBlock: VoidedWallBlock, inId: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(wallBlock.block).put(TextureKey.WALL, inId.withPrefix("block/"))

        val id = Models.TEMPLATE_WALL_POST.upload(wallBlock, texture, gen.modelCollector)
        val id2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, texture, gen.modelCollector)
        val id3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, texture, gen.modelCollector)
        gen.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, id, id2, id3))
        gen.registerParentedItemModel(wallBlock, Models.WALL_INVENTORY.upload(wallBlock, texture, gen.modelCollector))
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

    private fun inheritBtn(btn: Block, p: Identifier, gen: BlockStateModelGenerator) {
        gen.blockStateCollector.accept(
            BlockStateModelGenerator.createButtonBlockState(
                btn, id(p.namespace, "block/${p.path}"), id(p.namespace, "block/${p.path}_pressed")
            )
        )
        gen.registerParentedItemModel(btn, id(p.namespace, "block/${p.path}_inventory"))
    }

    private fun btn(btn: VoidedButtonBlock, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(btn.block)
        val id = Models.BUTTON.upload(btn, texture, gen.modelCollector)
        val id2 = Models.BUTTON_PRESSED.upload(btn, texture, gen.modelCollector)
        gen.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(btn, id, id2))
        val id3 = Models.BUTTON_INVENTORY.upload(btn, texture, gen.modelCollector)
        gen.registerParentedItemModel(btn, id3)
    }

    private fun pressurePlate(plate: VoidedPressurePlateBlock, gen: BlockStateModelGenerator) =
        pressurePlate(plate, getId(plate.block), gen)

    private fun pressurePlate(plate: VoidedPressurePlateBlock, inId: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(inId.withPrefix("block/"))
        val id = Models.PRESSURE_PLATE_UP.upload(plate, texture, gen.modelCollector)
        val id2 = Models.PRESSURE_PLATE_DOWN.upload(plate, texture, gen.modelCollector)
        gen.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(plate, id, id2))
        gen.registerParentedItemModel(plate, id)
    }

    private fun fence(fenceBlock: Block, inId: Identifier, gen: BlockStateModelGenerator) {
        val texture = Texture.texture(inId)
        val id = Models.FENCE_POST.upload(fenceBlock, texture, gen.modelCollector)
        val id2 = Models.FENCE_SIDE.upload(fenceBlock, texture, gen.modelCollector)
        val id3 = Models.FENCE_INVENTORY.upload(fenceBlock, texture, gen.modelCollector)
        gen.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, id, id2))
        gen.registerParentedItemModel(fenceBlock, id3)
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