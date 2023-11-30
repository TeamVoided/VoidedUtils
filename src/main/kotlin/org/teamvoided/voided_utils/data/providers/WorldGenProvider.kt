package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import java.util.concurrent.CompletableFuture

@Suppress("UnstableApiUsage")
class WorldGenProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun getName(): String =  "World Generation"

    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        entries.addAll(registries.getLookupOrThrow(RegistryKeys.CONFIGURED_FEATURE))
    }
}