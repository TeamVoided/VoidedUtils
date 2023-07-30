@file:Suppress("DEPRECATION")

package org.teamvoided.voided_utils.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.WallHangingSignBlock
import net.minecraft.util.Identifier
import org.apache.commons.lang3.text.WordUtils
import org.teamvoided.voided_utils.VoidedUtils.getId
import org.teamvoided.voided_utils.registries.VUBlocks.BLOCK_LIST
import org.teamvoided.voided_utils.registries.VUItems.ITEM_LIST

class EnglishTranslationProvider(output: FabricDataOutput) : FabricLanguageProvider(output, "en_us") {
    override fun generateTranslations(build: TranslationBuilder) {
        ITEM_LIST.forEach {
            try {
                build.add(it.translationKey, genLang(getId(it)))
            } catch (_: Exception) {
            }
        }
        BLOCK_LIST.forEach {
            try {
                if (it !is WallHangingSignBlock) build.add(it.translationKey, genLang(getId(it)))
            } catch (_: Exception) {
            }
        }
    }


    private fun genLang(identifier: Identifier): String = WordUtils.capitalize(identifier.path.replace("_", " "))


}