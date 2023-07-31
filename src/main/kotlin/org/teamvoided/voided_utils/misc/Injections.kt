package org.teamvoided.voided_utils.misc

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.block.Blocks
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import org.teamvoided.voided_utils.VoidedUtils.getConfig
import org.teamvoided.voided_utils.VoidedUtils.id
import java.util.function.Consumer

object Injections {
    private val CAKE_DROPS = id("injections/cake_drops")
    private val BARTER_UPGRADES = id("injections/barter_upgrades")
    fun init() {
        val c = getConfig()
        LootTableEvents.MODIFY.register { _, _, id, supplier, _ ->
            if (c.enableCakeDrops && id == Blocks.CAKE.lootTableId) injectCakeDrops(supplier::pool)
            if (c.enableBarterUpgrades && id == LootTables.PIGLIN_BARTERING_GAMEPLAY) injectBarterUpgrades(supplier::pool)
        }
    }

    private fun injectCakeDrops(addPool: Consumer<in LootPool.Builder>) =
        addPool.accept(LootPool.builder().with(LootTableEntry.builder(CAKE_DROPS)))

    private fun injectBarterUpgrades(addPool: Consumer<in LootPool.Builder>) =
        addPool.accept(LootPool.builder().with(LootTableEntry.builder(BARTER_UPGRADES)))
}