package org.teamvoided.voided_utils.config

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import net.fabricmc.loader.api.FabricLoader
import org.teamvoided.voided_utils.VoidedUtils.LOGGER
import org.teamvoided.voided_utils.VoidedUtils.MODID
import java.io.File
import java.io.FileReader
import java.io.FileWriter



object Config {
    private val defaultConfig = ConfigData(
        1,
        enableRedstoneLantern = true,
        enableCharredWoodSet = true,
        enableButtonLevers = true,
        enableIronTrapdoor = true
    )
    private val configFile: File = FabricLoader.getInstance().configDir.resolve("$MODID.json").toFile()

    var config: ConfigData = defaultConfig

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json { prettyPrint = true; prettyPrintIndent = "\t" }

    fun load() {
        try {
            val stringData = FileReader(configFile).use { it.readText() }
            config = json.decodeFromString(stringData)
        } catch (e: Exception) {
            LOGGER.info("No config Found! Or is Broken! Making a new one.")
            LOGGER.warn(e.toString())
            save(defaultConfig)
        }
    }
    private fun save(config: ConfigData) = FileWriter(configFile).use { it.write(json.encodeToString(config)) }
}


@Serializable
data class ConfigData(
    val version: Int,
    val enableRedstoneLantern: Boolean,
    val enableCharredWoodSet: Boolean,
    val enableButtonLevers: Boolean,
    val enableIronTrapdoor: Boolean
)