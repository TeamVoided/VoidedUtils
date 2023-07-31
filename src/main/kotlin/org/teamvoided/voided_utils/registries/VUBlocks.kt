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
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import net.minecraft.util.SignType
import net.minecraft.util.math.Direction
import org.teamvoided.scuffedlib.sign.block.VoidHangingSignBlock
import org.teamvoided.scuffedlib.sign.block.VoidSignBlock
import org.teamvoided.scuffedlib.sign.block.VoidWallHangingSignBlock
import org.teamvoided.scuffedlib.sign.block.VoidWallSignBlock
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.blocks.AbstractToggleableButtonBlock
import org.teamvoided.voided_utils.blocks.RedstoneLanternBlock
import org.teamvoided.voided_utils.registries.VUItems.ALL_ITEM_LIST
import java.util.*


@Suppress("MemberVisibilityCanBePrivate")
object VUBlocks {

    val BLOCK_LIST = LinkedList<Block>()
    val BLOCK_ITEM_LIST = LinkedList<Block>()
    val TOGGLEABLE_BUTTONS = LinkedList<AbstractToggleableButtonBlock>()


    private val CHARRED_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeRegistry.registerWood(id("charred"))
    private val CHARRED_WOOD_TYPE: SignType = WoodTypeRegistry.register(id("charred"), CHARRED_BLOCK_SET_TYPE)


    private val IRON_LIKE_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeRegistry.register(
        id("iron"), true,
        BlockSoundGroup.METAL,
        SoundEvents.BLOCK_IRON_DOOR_CLOSE,
        SoundEvents.BLOCK_IRON_DOOR_OPEN,
        SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE,
        SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN,
        SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF,
        SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON,
        SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,
        SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON
    )

    val CHARRED_SIGN_ID = id("entity/signs/charred")
    val CHARRED_HANGING_SIGN_ID = id("entity/signs/hanging/charred")

    private val charredLike: AbstractBlock.Settings = FabricBlockSettings.create()
        .mapColor(MapColor.STONE)
        .instrument(NoteBlockInstrument.BASS)
        .strength(2.0f)
        .sounds(BlockSoundGroup.WOOD)
        .lavaIgnitable()


    val CHARRED_LOG: Block = createPillarBlock(MapColor.DEEPSLATE, MapColor.STONE)
    val STRIPPED_CHARRED_LOG: Block = createPillarBlock(MapColor.DEEPSLATE, MapColor.STONE)
    val CHARRED_WOOD: Block = PillarBlock(charredLike)
    val STRIPPED_CHARRED_WOOD: Block = PillarBlock(charredLike)
    val CHARRED_PLANKS: Block = Block(FabricBlockSettings.copyOf(charredLike).strength(2.0f, 3.0f))
    val CHARRED_STAIRS: Block = StairsBlock(CHARRED_PLANKS.defaultState, FabricBlockSettings.copy(CHARRED_PLANKS))
    val CHARRED_SLAB: Block = SlabBlock(FabricBlockSettings.copy(CHARRED_PLANKS))
    val CHARRED_FENCE: Block = FenceBlock(FabricBlockSettings.copy(CHARRED_PLANKS))
    val CHARRED_PRESSURE_PLATE: Block = PressurePlateBlock(
        ActivationRule.EVERYTHING,
        FabricBlockSettings.copyOf(charredLike).strength(0.5f).pistonBehavior(PistonBehavior.DESTROY),
        CHARRED_BLOCK_SET_TYPE
    )
    val CHARRED_BUTTON: Block = createBtn(CHARRED_BLOCK_SET_TYPE)
    val CHARRED_TRAPDOOR: Block = TrapdoorBlock(
        FabricBlockSettings.copyOf(charredLike).strength(3.0f).nonOpaque().allowsSpawning { _, _, _, _ -> false },
        CHARRED_BLOCK_SET_TYPE
    )
    val CHARRED_DOOR: Block = DoorBlock(
        FabricBlockSettings.copyOf(charredLike).strength(3.0f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY),
        CHARRED_BLOCK_SET_TYPE
    )
    val CHARRED_FENCE_GATE: Block = FenceGateBlock(FabricBlockSettings.copy(CHARRED_PLANKS).solid(), CHARRED_WOOD_TYPE)
    val CHARRED_SIGN: Block = VoidSignBlock(
        CHARRED_SIGN_ID,
        FabricBlockSettings.copyOf(charredLike).strength(1.0f).noCollision().drops(id("blocks/charred_sign")),
        CHARRED_WOOD_TYPE
    )
    val CHARRED_WALL_SIGN: Block = VoidWallSignBlock(
        CHARRED_SIGN_ID,
        FabricBlockSettings.copyOf(CHARRED_SIGN).dropsLike(CHARRED_SIGN),
        CHARRED_WOOD_TYPE
    )
    val CHARRED_HANGING_SIGN: Block = VoidHangingSignBlock(
        CHARRED_HANGING_SIGN_ID,
        FabricBlockSettings.copyOf(CHARRED_SIGN).drops(id("blocks/charred_hanging_sign")),
        CHARRED_WOOD_TYPE
    )
    val CHARRED_WALL_HANGING_SIGN: Block = VoidWallHangingSignBlock(
        CHARRED_HANGING_SIGN_ID,
        FabricBlockSettings.copyOf(CHARRED_SIGN).dropsLike(CHARRED_HANGING_SIGN),
        CHARRED_WOOD_TYPE
    )

    val REDSTONE_LANTERN: Block = RedstoneLanternBlock(
        FabricBlockSettings.copyOf(Blocks.LANTERN).luminance { if (it.get(RedstoneLanternBlock.LIT)) 7 else 0 })

    val IRON_COATED_TRAPDOOR: Block =
        TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR), IRON_LIKE_BLOCK_SET_TYPE)
    val IRON_COATED_DOOR: Block = DoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_DOOR), IRON_LIKE_BLOCK_SET_TYPE)

    val TOGGLEABLE_STONE_BUTTON: AbstractToggleableButtonBlock = createToggleStoneBtn(Blocks.STONE_BUTTON)
    val TOGGLEABLE_POLISHED_BLACKSTONE_BUTTON: AbstractToggleableButtonBlock =
        createToggleStoneBtn(Blocks.POLISHED_BLACKSTONE_BUTTON)
    val TOGGLEABLE_OAK_BUTTON: AbstractToggleableButtonBlock = createToggleBtn(Blocks.OAK_BUTTON, BlockSetType.OAK)
    val TOGGLEABLE_SPRUCE_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.SPRUCE_BUTTON, BlockSetType.SPRUCE)
    val TOGGLEABLE_BIRCH_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.BIRCH_BUTTON, BlockSetType.BIRCH)
    val TOGGLEABLE_JUNGLE_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.JUNGLE_BUTTON, BlockSetType.JUNGLE)
    val TOGGLEABLE_ACACIA_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.ACACIA_BUTTON, BlockSetType.ACACIA)
    val TOGGLEABLE_DARK_OAK_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.DARK_OAK_BUTTON, BlockSetType.DARK_OAK)
    val TOGGLEABLE_MANGROVE_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.MANGROVE_BUTTON, BlockSetType.MANGROVE)
    val TOGGLEABLE_CHERRY_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.CHERRY_BUTTON, BlockSetType.CHERRY)
    val TOGGLEABLE_BAMBOO_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.BAMBOO_BUTTON, BlockSetType.BAMBOO)
    val TOGGLEABLE_CRIMSON_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.CRIMSON_BUTTON, BlockSetType.CRIMSON)
    val TOGGLEABLE_WARPED_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(Blocks.WARPED_BUTTON, BlockSetType.WARPED)
    val TOGGLEABLE_CHARRED_BUTTON: AbstractToggleableButtonBlock =
        createToggleBtn(CHARRED_BUTTON, CHARRED_BLOCK_SET_TYPE)


    fun init() {
        val c = VoidedUtils.getConfig()

        if (c.enableRedstoneLantern) registerWithItem("redstone_lantern", REDSTONE_LANTERN)

        if (c.enableCharredWoodSet) {
            registerWithItem("charred_log", CHARRED_LOG)
            registerWithItem("stripped_charred_log", STRIPPED_CHARRED_LOG)
            registerWithItem("charred_wood", CHARRED_WOOD)
            registerWithItem("stripped_charred_wood", STRIPPED_CHARRED_WOOD)
            registerWithItem("charred_planks", CHARRED_PLANKS)
            registerWithItem("charred_stairs", CHARRED_STAIRS)
            registerWithItem("charred_slab", CHARRED_SLAB)
            registerWithItem("charred_fence", CHARRED_FENCE)
            registerWithItem("charred_pressure_plate", CHARRED_PRESSURE_PLATE)
            registerWithItem("charred_button", CHARRED_BUTTON)
            registerWithItem("charred_trapdoor", CHARRED_TRAPDOOR)
            registerWithItem("charred_door", CHARRED_DOOR)
            registerWithItem("charred_fence_gate", CHARRED_FENCE_GATE)
            register("charred_sign", CHARRED_SIGN)
            register("charred_wall_sign", CHARRED_WALL_SIGN)
            register("charred_hanging_sign", CHARRED_HANGING_SIGN)
            register("charred_wall_hanging_sign", CHARRED_WALL_HANGING_SIGN)
            StrippableBlockRegistry.register(CHARRED_LOG, STRIPPED_CHARRED_LOG)
            StrippableBlockRegistry.register(CHARRED_WOOD, STRIPPED_CHARRED_WOOD)
        }
        if (c.enableIronCoatedBlocks) {
            registerWithItem("iron_coated_trapdoor", IRON_COATED_TRAPDOOR)
            registerWithItem("iron_coated_door", IRON_COATED_DOOR)
        }
        if (c.enableToggleButtons) {
            registerToggleBtn("toggleable_stone_button", TOGGLEABLE_STONE_BUTTON)
            registerToggleBtn("toggleable_polished_blackstone_button", TOGGLEABLE_POLISHED_BLACKSTONE_BUTTON)
            registerToggleBtn("toggleable_oak_button", TOGGLEABLE_OAK_BUTTON)
            registerToggleBtn("toggleable_spruce_button", TOGGLEABLE_SPRUCE_BUTTON)
            registerToggleBtn("toggleable_birch_button", TOGGLEABLE_BIRCH_BUTTON)
            registerToggleBtn("toggleable_jungle_button", TOGGLEABLE_JUNGLE_BUTTON)
            registerToggleBtn("toggleable_acacia_button", TOGGLEABLE_ACACIA_BUTTON)
            registerToggleBtn("toggleable_dark_oak_button", TOGGLEABLE_DARK_OAK_BUTTON)
            registerToggleBtn("toggleable_mangrove_button", TOGGLEABLE_MANGROVE_BUTTON)
            registerToggleBtn("toggleable_cherry_button", TOGGLEABLE_CHERRY_BUTTON)
            registerToggleBtn("toggleable_bamboo_button", TOGGLEABLE_BAMBOO_BUTTON)
            registerToggleBtn("toggleable_crimson_button", TOGGLEABLE_CRIMSON_BUTTON)
            registerToggleBtn("toggleable_warped_button", TOGGLEABLE_WARPED_BUTTON)
            if (c.enableCharredWoodSet) registerToggleBtn("toggleable_charred_button", TOGGLEABLE_CHARRED_BUTTON)
        }
    }

    val CUTOUT_LIST: List<Block> =
        listOf(CHARRED_DOOR, CHARRED_TRAPDOOR, REDSTONE_LANTERN, IRON_COATED_TRAPDOOR, IRON_COATED_DOOR)


    private fun registerWithItem(id: String, block: Block): Block {
        val item = Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        ALL_ITEM_LIST.add(item)
        BLOCK_ITEM_LIST.add(block)
        return register(id, block)
    }

    private fun registerToggleBtn(id: String, block: AbstractToggleableButtonBlock): Block {
        TOGGLEABLE_BUTTONS.add(block)
        return registerWithItem(id, block)
    }

    private fun register(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCK_LIST.add(regBlock)
        return regBlock
    }

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

    private fun createBtn(blockSetType: BlockSetType): AbstractButtonBlock = AbstractButtonBlock(
        AbstractBlock.Settings.create().noCollision().strength(0.5f).pistonBehavior(PistonBehavior.DESTROY),
        blockSetType,
        30,
        true
    )

    private fun createToggleBtn(button: Block, blockSetType: BlockSetType): AbstractToggleableButtonBlock =
        AbstractToggleableButtonBlock(button as AbstractButtonBlock, blockSetType, true)

    private fun createToggleStoneBtn(button: Block): AbstractToggleableButtonBlock =
        AbstractToggleableButtonBlock(button as AbstractButtonBlock, BlockSetType.STONE, false)


    val CHARRED_FAMILY: BlockFamily = BlockFamilies.register(CHARRED_PLANKS)
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
