package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.Direction
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUItems.ITEM_LIST

object VUBlocks {

    val TEST_BLOCK: Block = registerWithItem("test_block", Block(FabricBlockSettings.create()))

    val CHARRED_LOG: Block = registerWithItem("charred_log", createPillarBlock(MapColor.STONE, MapColor.STONE))
    val STRIPPED_CHARRED_LOG: Block =
        registerWithItem("stripped_charred_log", createPillarBlock(MapColor.STONE, MapColor.STONE))
    val CHARRED_PLANKS = registerWithItem(
        "charred_planks",
        Block(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )

    private fun registerWithItem(id: String, block: Block): Block {
        ITEM_LIST.add(Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings())).defaultStack)
        return register(id, block)
    }

    private fun register(id: String, block: Block) = Registry.register(Registries.BLOCK, id(id), block)
    private fun createPillarBlock(topColor: MapColor, sideColor: MapColor): PillarBlock {
        return PillarBlock(
            AbstractBlock.Settings.create()
                .mapColor { state: BlockState ->
                    if (state.get(
                            PillarBlock.AXIS
                        ) === Direction.Axis.Y
                    ) topColor else sideColor
                }
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0f)
                .sounds(BlockSoundGroup.WOOD)
                .lavaIgnitable()
        )
    }
}
