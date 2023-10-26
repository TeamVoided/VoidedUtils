package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.family.BlockFamily
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.data.tags.VUItemTags
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.VUItems
import org.teamvoided.voided_utils.registries.modules.CharredWoodSet
import org.teamvoided.voided_utils.registries.modules.ConsistentStones
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generateRecipes(c: Consumer<RecipeJsonProvider>) {

        try {

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
                    CharredWoodSet.CHARRED_PLANKS,
                    VUItemTags.CHARRED_LOGS,
                    CharredWoodSet.CHARRED_LOG,
                    CharredWoodSet.CHARRED_WOOD,
                    CharredWoodSet.STRIPPED_CHARRED_LOG,
                    CharredWoodSet.STRIPPED_CHARRED_WOOD,
                    VUItems.CHARRED_SIGN,
                    VUItems.CHARRED_HANGING_SIGN,
                    CharredWoodSet.CHARRED_FAMILY
                )
            )


            VUBlocks.TOGGLEABLE_BUTTONS.forEach {
                val base = it.btn.asItem()
                ShapelessRecipeJsonFactory.create(RecipeCategory.REDSTONE, it)
                    .ingredient(base)
                    .ingredient(Items.LEVER)
                    .group("toggleable_buttons")
                    .criterion(hasItem(base), conditionsFromItem(base))
                    .criterion(hasItem(Items.LEVER), conditionsFromItem(Items.LEVER))
                    .criterion(hasItem(it), conditionsFromItem(it))
                    .offerTo(c, getId(it))
            }

            CookingRecipeJsonFactory.createSmelting(
                Ingredient.ofItems(Blocks.RED_NETHER_BRICKS),
                RecipeCategory.BUILDING_BLOCKS,
                ConsistentStones.CRACKED_RED_NETHER_BRICKS.asItem(),
                0.1f,
                200
            )
                .criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS))
                .criterion(
                    hasItem(ConsistentStones.CRACKED_RED_NETHER_BRICKS),
                    conditionsFromItem(ConsistentStones.CRACKED_RED_NETHER_BRICKS)
                )
                .offerTo(c)

            ConsistentStones.STAIR_LIST.forEach {
                if (it != ConsistentStones.SNOW_STAIR) genFullStairs(it, it.block, c)
                else genStairs(it, it.block, c)
            }
            ConsistentStones.SLAB_LIST.forEach { genSlabs(it, it.block, c) }
            ConsistentStones.WALL_LIST.forEach {
                if (it != ConsistentStones.SNOW_WALL) genFullWalls(it, it.block, c)
                else genWalls(it, it.block, c)
            }
            ConsistentStones.BTN_LIST.forEach { genBtn(it, it.block, c) }
            ConsistentStones.PLATE_LIST.forEach { genPlate(it, it.block, c) }

            ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, ConsistentStones.RED_NETHER_BRICK_FENCE, 6)
                .pattern("#$#")
                .pattern("#$#")
                .ingredient('#', Items.RED_NETHER_BRICKS)
                .ingredient('$', Items.NETHER_BRICK)
                .criterion(hasItem(Items.NETHER_BRICK), conditionsFromItem(Items.NETHER_BRICK))
                .criterion(hasItem(Items.RED_NETHER_BRICKS), conditionsFromItem(Items.RED_NETHER_BRICKS))
                .offerTo(c, getId(ConsistentStones.RED_NETHER_BRICK_FENCE))

        } catch (e: Exception) {
            VoidedUtils.LOGGER.error("Error {}", e.toString())
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

    private fun genFullStairs(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        genStairs(out, inp, c)
        offerStonecuttingRecipe(c, RecipeCategory.BUILDING_BLOCKS, out, inp)
    }

    private fun genStairs(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        createStairsRecipe(out, Ingredient.ofItems(inp))
            .group("stairs")
            .criterion(hasItem(inp), conditionsFromItem(inp))
            .criterion(hasItem(out), conditionsFromItem(out))
            .offerTo(c, getId(out))
    }

    private fun genSlabs(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, out, Ingredient.ofItems(inp))
            .group("slabs")
            .criterion(hasItem(inp), conditionsFromItem(inp))
            .criterion(hasItem(out), conditionsFromItem(out))
            .offerTo(c, getId(out))
        offerStonecuttingRecipe(c, RecipeCategory.BUILDING_BLOCKS, out, inp, 2)
    }

    private fun genFullWalls(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        genStairs(out, inp, c)
        offerStonecuttingRecipe(c, RecipeCategory.BUILDING_BLOCKS, out, inp)
    }

    private fun genWalls(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        getWallRecipe(RecipeCategory.BUILDING_BLOCKS, out, Ingredient.ofItems(inp))
            .group("walls")
            .criterion(hasItem(inp), conditionsFromItem(inp))
            .criterion(hasItem(out), conditionsFromItem(out))
            .offerTo(c, getId(out))
    }

    private fun genBtn(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        createTransmutationRecipe(out, Ingredient.ofItems(inp))
            .group("buttons")
            .criterion(hasItem(inp), conditionsFromItem(inp))
            .criterion(hasItem(out), conditionsFromItem(out))
            .offerTo(c, getId(out))
    }

    private fun genPlate(out: Block, inp: ItemConvertible, c: Consumer<RecipeJsonProvider>) {
        createPressurePlateRecipe(RecipeCategory.REDSTONE, out, Ingredient.ofItems(inp))
            .group("pressure_plates")
            .criterion(hasItem(inp), conditionsFromItem(inp))
            .criterion(hasItem(out), conditionsFromItem(out))
            .offerTo(c, getId(out))
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