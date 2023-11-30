@file:Suppress("DEPRECATION")

package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.util.Identifier
import org.apache.commons.lang3.text.WordUtils
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.registries.VUBlocks.BLOCK_LIST
import org.teamvoided.voided_utils.registries.VUItems.ITEM_LIST
import org.teamvoided.voidlib.core.gId

class EnglishTranslationProvider(output: FabricDataOutput) : FabricLanguageProvider(output, "en_us") {
    override fun generateTranslations(build: TranslationBuilder) {
        ITEM_LIST.forEach { build.add(it.translationKey, genLang(it.gId)) }

        BLOCK_LIST.forEach {
            val x = genLang(it.gId)
//            LOGGER.info("{} - {}", x, it.toString())
            try {
                build.add(it.translationKey, x)
            } catch (e: Exception) {
                VoidedUtils.LOGGER.error("Error {}", e.toString())
            }
        }

    }

    private fun genLang(identifier: Identifier): String =
        WordUtils.capitalize(identifier.path.replace("_", " "))
}