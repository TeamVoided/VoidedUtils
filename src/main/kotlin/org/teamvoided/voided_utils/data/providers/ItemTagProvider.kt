package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.data.tags.VUItemTags
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.VUItems
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {
        try {


            getOrCreateTagBuilder(ItemTags.FENCES)
                .add(VUBlocks.CHARRED_FENCE.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(VUBlocks.CHARRED_FENCE.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(VUBlocks.CHARRED_BUTTON.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(VUBlocks.CHARRED_DOOR.asItem())

            getOrCreateTagBuilder(ItemTags.DOORS)
                .add(VUBlocks.IRON_COATED_DOOR.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(VUBlocks.CHARRED_SLAB.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(VUBlocks.CHARRED_PRESSURE_PLATE.asItem())

            getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(VUBlocks.CHARRED_PLANKS.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(VUBlocks.CHARRED_STAIRS.asItem())

            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(VUBlocks.CHARRED_TRAPDOOR.asItem())

            getOrCreateTagBuilder(ItemTags.TRAPDOORS)
                .add(VUBlocks.IRON_COATED_TRAPDOOR.asItem())

            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(VUItemTags.CHARRED_LOGS)

            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(VUItems.CHARRED_HANGING_SIGN)

            getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(VUItems.CHARRED_SIGN)

            getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(VUBlocks.CHARRED_FENCE_GATE.asItem())


            VUBlocks.TOGGLEABLE_BUTTONS.forEach {
                getOrCreateTagBuilder(ItemTags.BUTTONS)
                    .add(it.asItem())
                getOrCreateTagBuilder(VUItemTags.TOGGLEABLE_BUTTONS)
                    .add(it.asItem())
            }




            getOrCreateTagBuilder(VUItemTags.CHARRED_LOGS)
                .add(VUBlocks.CHARRED_LOG.asItem())
                .add(VUBlocks.STRIPPED_CHARRED_LOG.asItem())
                .add(VUBlocks.CHARRED_WOOD.asItem())
                .add(VUBlocks.STRIPPED_CHARRED_WOOD.asItem())
        } catch (e: Exception) {
            VoidedUtils.LOGGER.error("Error {}", e.toString())
        }
    }


}