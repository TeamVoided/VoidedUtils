package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.util.Identifier
import org.apache.commons.lang3.text.WordUtils
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.registries.VUBlocks.blocks
import org.teamvoided.voided_utils.registries.VUItems.items
@SuppressWarnings("deprecation")
class EnglishTranslationProvider(output: FabricDataOutput) : FabricLanguageProvider(output, "en_us") {
    override fun generateTranslations(build: TranslationBuilder) {
        blocks.forEach {
            try {
                build.add(it, genLang(getId(it)))
            } catch (_: Exception) {
            }
        }
        items.forEach {
            try {
                build.add(it, genLang(getId(it)))
            } catch (_: Exception) {
            }
        }
    }

    private fun genLang(identifier: Identifier): String? = WordUtils.capitalize(identifier.path.replace("_", " "))


}