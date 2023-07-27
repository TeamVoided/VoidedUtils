package org.teamvoided.voided_utils

import net.minecraft.client.resource.Material
import java.util.*

object MaterialRegistry {
    private val identifiers: MutableList<Material>

    init {
        identifiers = ArrayList()
    }

    fun addIdentifier(sprite: Material) = identifiers.add(sprite)
    fun getIdentifiers(): Collection<Material> = Collections.unmodifiableList(identifiers)
}