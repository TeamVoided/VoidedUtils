package org.teamvoided.voided_utils.registries

import net.minecraft.block.Blocks
import net.minecraft.registry.*
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.VerticalSurfaceType
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.data.features.CustomVegetationPatchFeature

@Suppress("SameParameterValue")
object VUWorldGen {
    val CUSTOM_VEGETATION_PATCH: Feature<VegetationPatchFeatureConfig> =
        regFeature("custom_vegetation_patch", CustomVegetationPatchFeature(VegetationPatchFeatureConfig.CODEC))
    @JvmField
    var CUSTOM_MOSS_PATCH_BONE_MEAL_KEY: RegistryKey<ConfiguredFeature<*, *>> = regCFeature("custom_moss_patch_bonemeal")


    fun init() {}

    fun configFeatures(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val holderProvider: HolderProvider<ConfiguredFeature<*, *>> = c.lookup(RegistryKeys.CONFIGURED_FEATURE)

        // Register the configured feature.
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CUSTOM_MOSS_PATCH_BONE_MEAL_KEY, CUSTOM_VEGETATION_PATCH,
            VegetationPatchFeatureConfig(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.of(Blocks.MOSS_BLOCK),
                PlacedFeatureUtil.placedInline(
                    holderProvider.getHolderOrThrow(UndergroundConfiguredFeatures.MOSS_VEGETATION),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                VerticalSurfaceType.FLOOR,
                ConstantIntProvider.create(1),
                0.0f,
                5,
                0.6f,
                UniformIntProvider.create(1, 2),
                0.75f
            )
        )
    }

    private fun <C : FeatureConfig, F : Feature<C>> regFeature(name: String, feature: F): F {
        return Registry.register(Registries.FEATURE, id(name), feature)
    }
    private fun regCFeature(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))

}