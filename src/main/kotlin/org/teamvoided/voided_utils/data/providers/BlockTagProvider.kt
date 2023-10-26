package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.voided_utils.VoidedUtils.LOGGER
import org.teamvoided.voided_utils.data.tags.VUBlockTags
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.modules.CharredWoodSet
import org.teamvoided.voided_utils.registries.modules.ConsistentStones
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {


    override fun configure(arg: HolderLookup.Provider) {
        try {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(VUBlocks.IRON_COATED_DOOR)
                .add(VUBlocks.IRON_COATED_TRAPDOOR)
                .add(ConsistentStones.RED_NETHER_BRICK_FENCE)





            getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ConsistentStones.RED_NETHER_BRICK_FENCE)

            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(CharredWoodSet.CHARRED_FENCE)

            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(CharredWoodSet.CHARRED_BUTTON)

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(CharredWoodSet.CHARRED_DOOR)

            getOrCreateTagBuilder(BlockTags.DOORS)
                .add(VUBlocks.IRON_COATED_DOOR)

            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(CharredWoodSet.CHARRED_SLAB)

            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(CharredWoodSet.CHARRED_PRESSURE_PLATE)


            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(CharredWoodSet.CHARRED_STAIRS)

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(CharredWoodSet.CHARRED_TRAPDOOR)

            getOrCreateTagBuilder(BlockTags.TRAPDOORS)
                .add(VUBlocks.IRON_COATED_TRAPDOOR)

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(VUBlockTags.CHARRED_LOGS)

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(CharredWoodSet.CHARRED_SIGN)

            getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(CharredWoodSet.CHARRED_WALL_SIGN)

            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(CharredWoodSet.CHARRED_HANGING_SIGN)

            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(CharredWoodSet.CHARRED_WALL_HANGING_SIGN)

            getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(CharredWoodSet.CHARRED_PLANKS)

            getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(CharredWoodSet.CHARRED_FENCE_GATE)

            VUBlocks.TOGGLEABLE_BUTTONS.forEach {
                getOrCreateTagBuilder(BlockTags.BUTTONS)
                    .add(it)
                getOrCreateTagBuilder(VUBlockTags.TOGGLEABLE_BUTTONS)
                    .add(it)
            }

            ConsistentStones.INFESTED_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
            }

            ConsistentStones.STAIR_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
                getOrCreateTagBuilder(BlockTags.STAIRS)
                    .add(it)
            }

            ConsistentStones.SLAB_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
                getOrCreateTagBuilder(BlockTags.SLABS)
                    .add(it)
            }

            ConsistentStones.WALL_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
                getOrCreateTagBuilder(BlockTags.WALLS)
                    .add(it)
            }

            ConsistentStones.WALL_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
                getOrCreateTagBuilder(BlockTags.WALLS)
                    .add(it)
            }

            ConsistentStones.BTN_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
                getOrCreateTagBuilder(BlockTags.BUTTONS)
                    .add(it)
            }

            ConsistentStones.PLATE_LIST.forEach {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(it)
                getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES)
                    .add(it)
            }




            getOrCreateTagBuilder(VUBlockTags.CHARRED_LOGS)
                .add(CharredWoodSet.CHARRED_LOG)
                .add(CharredWoodSet.STRIPPED_CHARRED_LOG)
                .add(CharredWoodSet.CHARRED_WOOD)
                .add(CharredWoodSet.STRIPPED_CHARRED_WOOD)

            getOrCreateTagBuilder(VUBlockTags.SHEARS_MINEABLE_FAST)
                .add(Blocks.COBWEB)
                .forceAddTag(BlockTags.LEAVES)
        } catch (e: Exception) {
            LOGGER.error("Error {}", e.toString())
        }
    }

    companion object {
    }
}