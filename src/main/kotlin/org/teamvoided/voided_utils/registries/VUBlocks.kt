package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
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
import org.teamvoided.voided_utils.blocks.*
import org.teamvoided.voided_utils.registries.VUItems.ALL_ITEM_LIST
import java.util.*


object VUBlocks {

    val BLOCK_LIST = LinkedList<Block>()
    val BLOCK_ITEM_LIST = LinkedList<Item>()

    val CHARRED_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeRegistry.registerWood(id("charred"))
    val CHARRED_WOOD_TYPE: SignType = WoodTypeRegistry.register(id("charred"), CHARRED_BLOCK_SET_TYPE)

    @JvmField
    val CHARRED_LOG: Block = registerWithItem("charred_log", createPillarBlock(MapColor.STONE))

    @JvmField
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
            CHARRED_BLOCK_SET_TYPE
        )
    )

    val CHARRED_BUTTON: Block = registerWithItem("charred_button", createButtonBlock(CHARRED_BLOCK_SET_TYPE))


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
            CHARRED_BLOCK_SET_TYPE
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
            CHARRED_BLOCK_SET_TYPE
        )
    )


    val CHARRED_FENCE_GATE: Block = registerWithItem(
        "charred_fence_gate",
        FenceGateBlock(
            AbstractBlock.Settings.create().mapColor(CHARRED_PLANKS.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f).lavaIgnitable(),
            CHARRED_WOOD_TYPE
        )
    )

    val CHARRED_SIGN_ID = id("entity/signs/charred")
    val CHARRED_HANGING_SIGN_ID = id("entity/signs/hanging/charred")

    val CHARRED_SIGN: Block = register(
        "charred_sign",
        CustomSignBlock(
            CHARRED_SIGN_ID,
            FabricBlockSettings.copyOf(Blocks.OAK_SIGN),
            SignType.OAK
        )
    )
    val CHARRED_WALL_SIGN: Block = register(
        "charred_wall_sign",
        CustomWallSignBlock(
            CHARRED_SIGN_ID,
            FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN),
            SignType.OAK
        )
    )

    val CHARRED_HANGING_SIGN: Block = register(
        "charred_hanging_sign",
        CustomHangingSignBlock(
            CHARRED_HANGING_SIGN_ID,
            AbstractBlock.Settings.create().mapColor(CHARRED_LOG.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
            CHARRED_WOOD_TYPE
        )
    )

    val CHARRED_WALL_HANGING_SIGN: Block = register(
        "charred_wall_hanging_sign",
        CustomWallHangingSignBlock(
            CHARRED_HANGING_SIGN_ID,
            AbstractBlock.Settings.create()
                .mapColor(CHARRED_LOG.defaultMapColor)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0f)
                .lavaIgnitable()
                .dropsLike(CHARRED_HANGING_SIGN),
            CHARRED_WOOD_TYPE
        )
    )


    val REDSTONE_LANTERN: Block = registerWithItem("redstone_lantern", RedstoneLanternBlock(
        FabricBlockSettings.copyOf(Blocks.LANTERN)
            .luminance { if (it.get(RedstoneLanternBlock.LIT)) 7 else 0 }
    ))

    fun init() {
        StrippableBlockRegistry.register(CHARRED_LOG, STRIPPED_CHARRED_LOG)
        StrippableBlockRegistry.register(CHARRED_WOOD, STRIPPED_CHARRED_WOOD)
    }

    private fun registerWithItem(id: String, block: Block): Block {
        val item = Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        BLOCK_ITEM_LIST.add(item)
        ALL_ITEM_LIST.add(item)
        return register(id, block)
    }

    fun register(id: String, block: Block): Block {
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
