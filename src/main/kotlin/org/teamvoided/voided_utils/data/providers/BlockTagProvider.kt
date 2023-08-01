package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.voided_utils.VoidedUtils.LOGGER
import org.teamvoided.voided_utils.data.tags.VUBlockTags
import org.teamvoided.voided_utils.registries.VUBlocks
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
                .add(VUBlocks.CHARRED_FENCE)

            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(VUBlocks.CHARRED_BUTTON)

            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(VUBlocks.CHARRED_DOOR)

            getOrCreateTagBuilder(BlockTags.DOORS)
                .add(VUBlocks.IRON_COATED_DOOR)

            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(VUBlocks.CHARRED_SLAB)

            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(VUBlocks.CHARRED_PRESSURE_PLATE)


            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(VUBlocks.CHARRED_STAIRS)

            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(VUBlocks.CHARRED_TRAPDOOR)

            getOrCreateTagBuilder(BlockTags.TRAPDOORS)
                .add(VUBlocks.IRON_COATED_TRAPDOOR)

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(VUBlockTags.CHARRED_LOGS)

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(VUBlocks.CHARRED_SIGN)

            getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(VUBlocks.CHARRED_WALL_SIGN)

            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(VUBlocks.CHARRED_HANGING_SIGN)

            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(VUBlocks.CHARRED_WALL_HANGING_SIGN)

            getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(VUBlocks.CHARRED_PLANKS)

            getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(VUBlocks.CHARRED_FENCE_GATE)

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
                .add(VUBlocks.CHARRED_LOG)
                .add(VUBlocks.STRIPPED_CHARRED_LOG)
                .add(VUBlocks.CHARRED_WOOD)
                .add(VUBlocks.STRIPPED_CHARRED_WOOD)

            getOrCreateTagBuilder(VUBlockTags.SHEARS_MINEABLE_FAST)
                .add(Blocks.COBWEB)
                .forceAddTag(BlockTags.LEAVES)

            getOrCreateTagBuilder(VUBlockTags.SHEARS_MINEABLE_SLOW)
                .forceAddTag(BlockTags.WOOL)
        } catch (e: Exception) {
            LOGGER.error("Error {}", e.toString())
        }
    }

    companion object {
    }
}