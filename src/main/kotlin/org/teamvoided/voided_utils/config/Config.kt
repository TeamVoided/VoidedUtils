package org.teamvoided.voided_utils.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voidlib.config.KotlinXVoidFig
import org.teamvoided.voidlib.config.Side
import org.teamvoided.voidlib.config.VoidFig



object Config: KotlinXVoidFig<ConfigData>(
    id("config"),
    Side.COMMON,
    ConfigData(
        1,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true
    ),
    Json { prettyPrint = true },
    ConfigData.serializer(),
    "json"
) {
    override fun matches(other: VoidFig<ConfigData>): Boolean {
         return matchesOther(other.config)
    }

    override fun matchesRawData(other: String): Boolean {
        val otherConfig: ConfigData = deserialize(other)

        return matchesOther(otherConfig)
    }

    private fun matchesOther(otherConfig: ConfigData): Boolean {
        return config.version == otherConfig.version &&
                config.enableRedstoneLantern == otherConfig.enableRedstoneLantern &&
                config.enableCharredWoodSet == otherConfig.enableCharredWoodSet &&
                config.enableIronCoatedBlocks == otherConfig.enableIronCoatedBlocks &&
                config.enableCakeDrops == otherConfig.enableCakeDrops &&
                config.enableBarterUpgrades == otherConfig.enableBarterUpgrades &&
                config.enableToggleButtons == otherConfig.enableToggleButtons &&
                config.enableShearsMineableTag == otherConfig.enableShearsMineableTag &&
                config.enableGlowBerriesGlow == otherConfig.enableGlowBerriesGlow &&
                config.enableConsistentStones == otherConfig.enableConsistentStones &&
                config.enableMossTag == otherConfig.enableMossTag &&
                config.enableX == otherConfig.enableX
    }
}


@Serializable
data class ConfigData(
    val version: Int,
    val enableRedstoneLantern: Boolean,
    val enableCharredWoodSet: Boolean,
    val enableIronCoatedBlocks: Boolean,
    val enableCakeDrops: Boolean,
    val enableBarterUpgrades: Boolean,
    val enableToggleButtons: Boolean,
    val enableShearsMineableTag: Boolean,
    val enableGlowBerriesGlow: Boolean,
    val enableConsistentStones: Boolean,
    val enableMossTag: Boolean,
    val enableX: Boolean
)