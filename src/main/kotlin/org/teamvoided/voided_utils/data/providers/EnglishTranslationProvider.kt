@file:Suppress("DEPRECATION")

package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.util.Identifier
import org.apache.commons.lang3.text.WordUtils
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.registries.VUBlocks.BLOCK_LIST
import org.teamvoided.voided_utils.registries.VUItems.ALL_ITEM_LIST
class EnglishTranslationProvider(output: FabricDataOutput) : FabricLanguageProvider(output, "en_us") {
    override fun generateTranslations(build: TranslationBuilder) {
        BLOCK_LIST.forEach {
            try {
                build.add(it, genLang(getId(it)))
            } catch (_: Exception) {
            }
        }
        ALL_ITEM_LIST.forEach {
            try {
                build.add(it, genLang(getId(it)))
            } catch (_: Exception) {
            }
        }
    }


    private fun genLang(identifier: Identifier): String? = WordUtils.capitalize(identifier.path.replace("_", " "))


}