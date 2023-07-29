package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.registries.VUBlocks
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

    }
}