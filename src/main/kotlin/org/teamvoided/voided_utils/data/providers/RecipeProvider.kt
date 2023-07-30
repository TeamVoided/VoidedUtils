package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.family.BlockFamily
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.blocks.AbstractToggleableButtonBlock
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.VUItems
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generateRecipes(c: Consumer<RecipeJsonProvider>) {

        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, VUBlocks.REDSTONE_LANTERN)
            .pattern("###")
            .pattern("#$#")
            .pattern("###")
            .ingredient('#', Items.IRON_NUGGET)
            .ingredient('$', Blocks.REDSTONE_TORCH)
            .criterion(hasItem(Items.REDSTONE_TORCH), conditionsFromItem(Items.REDSTONE_TORCH))
            .criterion(hasItem(VUBlocks.REDSTONE_LANTERN), conditionsFromItem(VUBlocks.REDSTONE_LANTERN))
            .offerTo(c, getId(VUBlocks.REDSTONE_LANTERN))

        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, VUBlocks.IRON_COATED_TRAPDOOR)
            .pattern(" # ")
            .pattern("#$#")
            .pattern(" # ")
            .ingredient('#', Items.IRON_INGOT)
            .ingredient('$', ItemTags.WOODEN_TRAPDOORS)
            .criterion("has_trapdoor", conditionsFromItemTag(ItemTags.WOODEN_TRAPDOORS))
            .criterion(hasItem(VUBlocks.IRON_COATED_TRAPDOOR), conditionsFromItem(VUBlocks.IRON_COATED_TRAPDOOR))
            .offerTo(c, getId(VUBlocks.IRON_COATED_TRAPDOOR))

        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, VUBlocks.IRON_COATED_DOOR)
            .pattern(" # ")
            .pattern("#$#")
            .pattern(" # ")
            .ingredient('#', Items.IRON_INGOT)
            .ingredient('$', ItemTags.WOODEN_DOORS)
            .criterion("has_door", conditionsFromItemTag(ItemTags.WOODEN_DOORS))
            .criterion(hasItem(VUBlocks.IRON_COATED_DOOR), conditionsFromItem(VUBlocks.IRON_COATED_DOOR))
            .offerTo(c, getId(VUBlocks.IRON_COATED_DOOR))

        genWoodSet(
            c, WoodTypes(
                VUBlocks.CHARRED_PLANKS,
                ItemTagProvider.CHARRED_LOGS,
                VUBlocks.CHARRED_LOG,
                VUBlocks.CHARRED_WOOD,
                VUBlocks.STRIPPED_CHARRED_LOG,
                VUBlocks.STRIPPED_CHARRED_WOOD,
                VUItems.CHARRED_SIGN,
                VUItems.CHARRED_HANGING_SIGN,
                VUBlocks.CHARRED_FAMILY
            )
        )


        VUBlocks.TOGGLEABLE_BUTTONS.forEach {
            val base = (it as AbstractToggleableButtonBlock).buttonBlock.asItem()

            LOGGER.info(base.toString())
            ShapelessRecipeJsonFactory.create(RecipeCategory.REDSTONE, it)
                .ingredient(base)
                .ingredient(Items.LEVER)
                .criterion(hasItem(base), conditionsFromItem(base))
                .criterion(hasItem(Items.LEVER), conditionsFromItem(Items.LEVER))
                .criterion(hasItem(it), conditionsFromItem(it))
                .offerTo(c, getId(it))
        }


    }

    private fun genWoodSet(c: Consumer<RecipeJsonProvider>, wood: WoodTypes) {
        generateFamily(c, wood.family)
        offerPlanksRecipe2(c, wood.planks, wood.logs, 4)
        offerBarkBlockRecipe(c, wood.wood, wood.log)
        offerBarkBlockRecipe(c, wood.strippedWood, wood.strippedLog)
        offerHangingSignRecipe(c, wood.hangingSign, wood.strippedLog)
//        offerBoatRecipe(c, Items.ACACIA_BOAT, planks)

    }

    data class WoodTypes(
        val planks: ItemConvertible,
        val logs: TagKey<Item>,
        val log: ItemConvertible,
        val wood: ItemConvertible,
        val strippedLog: ItemConvertible,
        val strippedWood: ItemConvertible,
        val sign: ItemConvertible,
        val hangingSign: ItemConvertible,
        val family: BlockFamily,
    )

}