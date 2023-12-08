package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags
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

            getOrCreateTagBuilder(VUBlockTags.AIR_PASSABLE)
                .forceAddTag(BlockTags.REPLACEABLE)
                .forceAddTag(BlockTags.ALL_SIGNS)
                .forceAddTag(BlockTags.FLOWER_POTS)
                .forceAddTag(BlockTags.BANNERS)
                .add(Blocks.TORCH)
                .add(Blocks.WALL_TORCH)
                .add(Blocks.SOUL_TORCH)
                .add(Blocks.SOUL_WALL_TORCH)
                .add(Blocks.LANTERN)
                .add(Blocks.SOUL_LANTERN)
                .add(Blocks.CHAIN)
                .forceAddTag(BlockTags.CAMPFIRES)
                .forceAddTag(BlockTags.CANDLES)
                .forceAddTag(BlockTags.BUTTONS)
                .forceAddTag(BlockTags.PRESSURE_PLATES)
                .add(Blocks.LEVER)
                .add(Blocks.TRIPWIRE)
                .add(Blocks.TRIPWIRE_HOOK)
                .forceAddTag(BlockTags.SAPLINGS)
                .forceAddTag(BlockTags.WOOL_CARPETS)
                .add(Blocks.MOSS_CARPET)
                .forceAddTag(BlockTags.TRAPDOORS)
                .add(Blocks.RED_MUSHROOM)
                .add(Blocks.BROWN_MUSHROOM)
                .forceAddTag(BlockTags.FENCES)
                .forceAddTag(BlockTags.FENCE_GATES)
                .add(Blocks.IRON_BARS)
                .forceAddTag(ConventionalBlockTags.GLASS_PANES)
                .forceAddTag(ConventionalBlockTags.BUDS)
                .forceAddTag(ConventionalBlockTags.CLUSTERS)
                .add(Blocks.REDSTONE_WIRE)
                .add(Blocks.REDSTONE_TORCH)
                .add(Blocks.REDSTONE_WALL_TORCH)
                .forceAddTag(BlockTags.CLIMBABLE)
                .forceAddTag(BlockTags.LEAVES)
                .forceAddTag(BlockTags.CORALS)
                .add(Blocks.LIGHTNING_ROD)
                .add(Blocks.COBWEB)
                .add(Blocks.END_ROD)
                .forceAddTag(BlockTags.WALLS)
                .add(Blocks.POINTED_DRIPSTONE)
                .add(Blocks.DECORATED_POT)
                .forceAddTag(BlockTags.RAILS)
                .add(Blocks.BELL)
                .add(Blocks.SEA_PICKLE)
                .add(Blocks.SPORE_BLOSSOM)
                .forceAddTag(BlockTags.FLOWERS)
                .add(Blocks.BAMBOO)
                .add(Blocks.BAMBOO_SAPLING)
                .add(Blocks.SUGAR_CANE)
                .add(Blocks.SWEET_BERRY_BUSH)
                .add(Blocks.CRIMSON_FUNGUS)
                .add(Blocks.WARPED_FUNGUS)
                .add(Blocks.KELP)
                .add(Blocks.KELP_PLANT)
                .add(Blocks.SCULK_SHRIEKER)
                .add(Blocks.BIG_DRIPLEAF_STEM)
                .add(Blocks.BIG_DRIPLEAF)
                .add(Blocks.SMALL_DRIPLEAF)
                .add(Blocks.GRINDSTONE)
                .add(Blocks.CONDUIT)
                .add(Blocks.REPEATER)
                .add(Blocks.COMPARATOR)

            getOrCreateTagBuilder(VUBlockTags.NETHER_PORTAL_BLOCK)
                .add(Blocks.OBSIDIAN)
                .add(ConsistentStones.OBSIDIAN_SLAB)
                .add(ConsistentStones.OBSIDIAN_STAIR)
                .add(ConsistentStones.OBSIDIAN_WALL)



        } catch (e: Exception) {
            LOGGER.error("Error {}", e.toString())
        }
    }

    companion object {
    }
}