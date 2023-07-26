package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.block.PressurePlateBlock.ActivationRule
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.SignType
import net.minecraft.util.math.Direction
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.mixin.SignTypeMixin
import org.teamvoided.voided_utils.registries.VUItems.ALL_ITEM_LIST
import java.util.*


object VUBlocks {

    val BLOCK_LIST = LinkedList<Block>()
    val BLOCK_ITEM_LIST = LinkedList<Item>()


    val CHARRED_LOG: Block = registerWithItem("charred_log", createPillarBlock(MapColor.STONE))
    val STRIPPED_CHARRED_LOG: Block = registerWithItem("stripped_charred_log", createPillarBlock(MapColor.STONE))
    val CHARRED_WOOD: Block = registerWithItem(
        "charred_wood",
        PillarBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    val STRIPPED_CHARRED_WOOD: Block = registerWithItem(
        "stripped_charred_wood",
        PillarBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    val CHARRED_PLANKS: Block = registerWithItem(
        "charred_planks",
        Block(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    val CHARRED_STAIRS: Block = registerWithItem(
        "charred_stairs",
        StairsBlock(CHARRED_PLANKS.defaultState, AbstractBlock.Settings.copy(CHARRED_PLANKS))
    )
    val CHARRED_SLAB: Block = registerWithItem(
        "charred_slab",
        SlabBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    val CHARRED_FENCE: Block = registerWithItem(
        "charred_fence",
        FenceBlock(
            AbstractBlock.Settings.create()
                .mapColor(CHARRED_PLANKS.defaultMapColor)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f)
                .sounds(BlockSoundGroup.WOOD)
                .lavaIgnitable()
        )
    )



    val CHARRED_PRESSURE_PLATE: Block = registerWithItem(
        "charred_pressure_plate",
        PressurePlateBlock(
            ActivationRule.EVERYTHING,
            AbstractBlock.Settings.create()
                .mapColor(CHARRED_PLANKS.defaultMapColor)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(0.5f)
                .lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
            BlockSetType.OAK
        )
    )

    val CHARRED_BUTTON: Block = registerWithItem("charred_button", createButtonBlock(BlockSetType.OAK))


    val CHARRED_TRAPDOOR: Block = registerWithItem(
        "charred_trapdoor",
        TrapdoorBlock(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0f)
                .nonOpaque()
                .allowsSpawning { _, _, _, _ -> false }
                .lavaIgnitable(),
            BlockSetType.OAK
        )
    )

    val CHARRED_DOOR: Block = registerWithItem(
        "charred_door",
        DoorBlock(
            AbstractBlock.Settings.create()
                .mapColor(CHARRED_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0f)
                .nonOpaque()
                .lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
            BlockSetType.OAK
        )
    )

    val CHARRED_SIGN_TYPE: SignType = SignTypeMixin.registerNew(SignTypeMixin.invokeInit("charred", BlockSetType.OAK))

    val CHARRED_FENCE_GATE: Block = registerWithItem(
        "charred_fence_gate",
        FenceGateBlock(
            AbstractBlock.Settings.create().mapColor(CHARRED_PLANKS.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f).lavaIgnitable(),
            CHARRED_SIGN_TYPE
        )
    )

    val CHARRED_SIGN: Block = register(
        "charred_sign",
        SignBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.STONE).solid().instrument(NoteBlockInstrument.BASS)
                .noCollision().strength(1.0f).lavaIgnitable(), CHARRED_SIGN_TYPE
        )
    )
    val CHARRED_WALL_SIGN: Block = register(
        "charred_wall_sign",
        WallSignBlock(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.STONE)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0f)
                .dropsLike(CHARRED_SIGN)
                .lavaIgnitable(),
            CHARRED_SIGN_TYPE
        )
    )

    val CHARRED_HANGING_SIGN: Block = register(
        "charred_hanging_sign",
        CeilingHangingSignBlock(
            AbstractBlock.Settings.create().mapColor(CHARRED_LOG.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
            CHARRED_SIGN_TYPE
        )
    )

    val CHARRED_WALL_HANGING_SIGN: Block = register(
        "charred_wall_hanging_sign",
        WallHangingSignBlock(
            AbstractBlock.Settings.create()
                .mapColor(CHARRED_LOG.defaultMapColor)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0f)
                .lavaIgnitable()
                .dropsLike(CHARRED_HANGING_SIGN),
            CHARRED_SIGN_TYPE
        )
    )

    fun init() {}
    private fun registerWithItem(id: String, block: Block): Block {
        val item = Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        BLOCK_ITEM_LIST.add(item)
        ALL_ITEM_LIST.add(item)
        return register(id, block)
    }

    private fun register(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCK_LIST.add(regBlock)
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

    private fun createButtonBlock(blockSetType: BlockSetType): AbstractButtonBlock = AbstractButtonBlock(
        AbstractBlock.Settings.create().noCollision().strength(0.5f).pistonBehavior(PistonBehavior.DESTROY),
        blockSetType,
        30,
        true
    )

    val CHARRED: BlockFamily = BlockFamilies.register(CHARRED_PLANKS)
        .button(CHARRED_BUTTON)
        .fence(CHARRED_FENCE)
        .fenceGate(CHARRED_FENCE_GATE)
        .pressurePlate(CHARRED_PRESSURE_PLATE)
        .sign(CHARRED_SIGN, CHARRED_WALL_SIGN)
        .slab(CHARRED_SLAB)
        .stairs(CHARRED_STAIRS)
        .door(CHARRED_DOOR)
        .trapdoor(CHARRED_TRAPDOOR)
        .group("wooden")
        .unlockCriterionName("has_planks")
        .build()

}
