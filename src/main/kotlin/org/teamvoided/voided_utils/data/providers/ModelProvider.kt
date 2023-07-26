package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Models
import net.minecraft.data.client.model.SimpleModelSupplier
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import net.minecraft.item.BlockItem
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_BUTTON
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_DOOR
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_FENCE
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_FENCE_GATE
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_HANGING_SIGN
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_LOG
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_PLANKS
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_PRESSURE_PLATE
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_SIGN
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_SLAB
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_STAIRS
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_TRAPDOOR
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_WALL_HANGING_SIGN
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_WALL_SIGN
import org.teamvoided.voided_utils.registries.VUBlocks.CHARRED_WOOD
import org.teamvoided.voided_utils.registries.VUBlocks.STRIPPED_CHARRED_LOG
import org.teamvoided.voided_utils.registries.VUBlocks.STRIPPED_CHARRED_WOOD
import org.teamvoided.voided_utils.registries.VUItems.items

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    private val logs = listOf(
        CHARRED_LOG,
        STRIPPED_CHARRED_LOG,
    )
    private val pillarBlocks = listOf(
        CHARRED_WOOD,
        STRIPPED_CHARRED_WOOD
    )

    private val CHARRED: BlockFamily = BlockFamilies.register(CHARRED_PLANKS)
        .button(CHARRED_BUTTON)
        .fence(CHARRED_FENCE)
        .fenceGate(CHARRED_FENCE_GATE)
        .pressurePlate(CHARRED_PRESSURE_PLATE)
        .sign(CHARRED_SIGN, CHARRED_WALL_SIGN)
        .slab(CHARRED_SLAB)
        .stairs(CHARRED_STAIRS)
        .door(CHARRED_DOOR)
        .trapdoor(CHARRED_TRAPDOOR)
        .group("wooden")
        .unlockCriterionName("has_planks")
        .build()

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {

        logs.forEach { gen.registerLog(it) }
        pillarBlocks.forEach { gen.registerLog(it) }

        gen.registerCubeAllModelTexturePool(CHARRED_PLANKS).family(CHARRED)
        gen.method_46190(STRIPPED_CHARRED_LOG, CHARRED_HANGING_SIGN, CHARRED_WALL_HANGING_SIGN)

//        blocks.forEach { block ->
//            try {
//                gen.registerSimpleCubeAll(block)
//            } catch (_: Exception) {
//            }
//        }
    }

    private val itemModelBlocks = listOf(
        CHARRED_SIGN,
        CHARRED_HANGING_SIGN,
        CHARRED_DOOR
    )

    override fun generateItemModels(gen: ItemModelGenerator) {

//        gen.register(items.find { if (it is BlockItem) return@find it.block == CHARRED_FENCE else false}, Models.FENCE_INVENTORY)

        items.forEach { item ->
            try {
                if (item !is BlockItem || itemModelBlocks.contains(item.block)) {
                    gen.register(item, Models.SINGLE_LAYER_ITEM)
                } else {
                    val path = getId(item).path
                    gen.writer.accept(id("item/$path"), SimpleModelSupplier(id("block/$path")))
                }
            } catch (_: Exception) {
            }
        }
    }
}