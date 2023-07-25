package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.Direction
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.registries.VUItems.items
import java.util.*

object VUBlocks {

    val blocks = LinkedList<Block>()


    val CHARRED_LOG: Block = registerWithItem("charred_log", createPillarBlock(MapColor.STONE))
    val CHARRED_WOOD: Block = registerWithItem(
        "charred_wood",
        PillarBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    val STRIPPED_CHARRED_LOG: Block = registerWithItem("stripped_charred_log", createPillarBlock(MapColor.STONE))
    val CHARRED_PLANKS = registerWithItem(
        "charred_planks",
        Block(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    fun init() {}
    private fun registerWithItem(id: String, block: Block): Block {
        items.add(Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings())))
        return register(id, block)
    }

    private fun register(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        blocks.add(regBlock)
        return regBlock
    }

    private fun createPillarBlock(color: MapColor): PillarBlock = createPillarBlock(color, color)
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
