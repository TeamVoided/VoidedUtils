package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUBlocks
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {


    override fun configure(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(VUBlocks.IRON_COATED_DOOR)
            .add(VUBlocks.IRON_COATED_TRAPDOOR)





        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(VUBlocks.CHARRED_FENCE)

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
            .addTag(CHARRED_LOGS)

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
            getOrCreateTagBuilder(TOGGLEABLE_BUTTONS)
                .add(it)
        }




        getOrCreateTagBuilder(CHARRED_LOGS)
            .add(VUBlocks.CHARRED_LOG)
            .add(VUBlocks.STRIPPED_CHARRED_LOG)
            .add(VUBlocks.CHARRED_WOOD)
            .add(VUBlocks.STRIPPED_CHARRED_WOOD)

    }

    companion object{
        val CHARRED_LOGS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("charred_logs"))
        val TOGGLEABLE_BUTTONS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("toggleable_buttons"))
    }
}