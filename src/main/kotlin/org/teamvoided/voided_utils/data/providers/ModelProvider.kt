package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import org.teamvoided.voided_utils.registries.VUBlocks

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    private val logs = listOf(
        Log(VUBlocks.CHARRED_LOG, VUBlocks.CHARRED_WOOD),
        Log(VUBlocks.STRIPPED_CHARRED_LOG, VUBlocks.STRIPPED_CHARRED_WOOD)
    )


    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        try {


            logs.forEach { gen.registerLog(it.log).log(it.log).wood(it.wood) }

            gen.registerLantern(VUBlocks.REDSTONE_LANTERN)

            gen.registerCubeAllModelTexturePool(VUBlocks.CHARRED_PLANKS).family(VUBlocks.CHARRED)
            gen.method_46190(
                VUBlocks.STRIPPED_CHARRED_LOG,
                VUBlocks.CHARRED_HANGING_SIGN,
                VUBlocks.CHARRED_WALL_HANGING_SIGN
            )
        } catch (_: Exception) {
        }


    }

    override fun generateItemModels(gen: ItemModelGenerator) {

        /*
        ITEM_LIST.forEach {
            try {
                gen.register(it, Models.SINGLE_LAYER_ITEM)
            } catch (_: Exception) {
            }
        }
        */
    }

    data class Log(val log: Block, val wood: Block)
}