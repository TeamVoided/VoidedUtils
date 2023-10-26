package org.teamvoided.voided_utils.registries.modules

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.PressurePlateBlock.ActivationRule
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.voided_utils.VoidedUtils.getConfig
import org.teamvoided.voided_utils.blocks.AbstractToggleableButtonBlock
import org.teamvoided.voided_utils.blocks.ObsidianWallBlock
import org.teamvoided.voided_utils.blocks.voided.*
import org.teamvoided.voided_utils.registries.VUBlocks
import org.teamvoided.voided_utils.registries.VUBlocks.regFullSquare
import org.teamvoided.voided_utils.registries.VUBlocks.registerWithItem
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object ConsistentStones {

    val INFESTED_LIST = LinkedList<InfestedBlock>()
    val SLAB_LIST = LinkedList<VoidedSlabBlock>()
    val STAIR_LIST = LinkedList<VoidedStairBlock>()
    val WALL_LIST = LinkedList<VoidedWallBlock>()
    val BTN_LIST = LinkedList<VoidedButtonBlock>()
    val PLATE_LIST = LinkedList<VoidedPressurePlateBlock>()

    val CRACKED_RED_NETHER_BRICKS = Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS))


    private val deepSet = FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE).sounds(BlockSoundGroup.DEEPSLATE)

    val INFESTED_MOSSY_COBBLESTONE =
        InfestedBlock(Blocks.MOSSY_COBBLESTONE, FabricBlockSettings.create().mapColor(MapColor.CLAY))
    val INFESTED_COBBLED_DEEPSLATE: InfestedBlock =
        InfestedBlock(Blocks.COBBLED_DEEPSLATE, FabricBlockSettings.copyOf(deepSet))
    val INFESTED_DEEPSLATE_BRICKS: InfestedBlock =
        InfestedBlock(Blocks.DEEPSLATE_BRICKS, FabricBlockSettings.copyOf(deepSet))
    val INFESTED_CRACKED_DEEPSLATE_BRICKS: InfestedBlock =
        InfestedBlock(Blocks.CRACKED_DEEPSLATE_BRICKS, FabricBlockSettings.copyOf(deepSet))
    val INFESTED_DEEPSLATE_TILES: InfestedBlock =
        InfestedBlock(Blocks.DEEPSLATE_TILES, FabricBlockSettings.copyOf(deepSet))
    val INFESTED_CRACKED_DEEPSLATE_TILES: InfestedBlock =
        InfestedBlock(Blocks.CRACKED_DEEPSLATE_TILES, FabricBlockSettings.copyOf(deepSet))
    val INFESTED_POLISHED_DEEPSLATE: InfestedBlock =
        InfestedBlock(Blocks.POLISHED_DEEPSLATE, FabricBlockSettings.copyOf(deepSet))

    val SMOOTH_STONE_STAIR = VoidedStairBlock(Blocks.SMOOTH_STONE)
    val CRACKED_STONE_BRICKS_STAIR = VoidedStairBlock(Blocks.CRACKED_STONE_BRICKS)
    val TUFF_STAIR = VoidedStairBlock(Blocks.TUFF)
    val CALCITE_STAIR = VoidedStairBlock(Blocks.CALCITE)
    val PACKED_MUD_STAIR = VoidedStairBlock(Blocks.PACKED_MUD)
    val DRIPSTONE_STAIR = VoidedStairBlock(Blocks.DRIPSTONE_BLOCK)
    val CUT_SANDSTONE_STAIR = VoidedStairBlock(Blocks.CUT_SANDSTONE)
    val CUT_RED_SANDSTONE_STAIR = VoidedStairBlock(Blocks.CUT_RED_SANDSTONE)
    val NETHERRACK_STAIR = VoidedStairBlock(Blocks.NETHERRACK)
    val CRACKED_NETHER_BRICKS_STAIR = VoidedStairBlock(Blocks.CRACKED_NETHER_BRICKS)
    val CRACKED_RED_NETHER_BRICKS_STAIR = VoidedStairBlock(CRACKED_RED_NETHER_BRICKS)
    val CRACKED_DEEPSLATE_TILES_STAIR = VoidedStairBlock(Blocks.CRACKED_DEEPSLATE_TILES)
    val CRACKED_DEEPSLATE_BRICKS_STAIR = VoidedStairBlock(Blocks.CRACKED_DEEPSLATE_BRICKS)
    val SMOOTH_BASALT_STAIR = VoidedStairBlock(Blocks.SMOOTH_BASALT)
    val CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIR = VoidedStairBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
    val END_STONE_STAIR = VoidedStairBlock(Blocks.END_STONE)
    val QUARTZ_BRICKS_STAIR = VoidedStairBlock(Blocks.QUARTZ_BRICKS)
    val SNOW_STAIR = VoidedStairBlock(Blocks.SNOW_BLOCK)
    val OBSIDIAN_STAIR = VoidedStairBlock(
        Blocks.OBSIDIAN,
        FabricBlockSettings.copyOf(Blocks.OBSIDIAN).pistonBehavior(PistonBehavior.BLOCK)
    )

    val CRACKED_STONE_BRICKS_SLAB = VoidedSlabBlock(Blocks.CRACKED_STONE_BRICKS)
    val CRACKED_DEEPSLATE_BRICKS_SLAB = VoidedSlabBlock(Blocks.CRACKED_DEEPSLATE_BRICKS)
    val CRACKED_DEEPSLATE_TILES_SLAB = VoidedSlabBlock(Blocks.CRACKED_DEEPSLATE_TILES)
    val TUFF_SLAB = VoidedSlabBlock(Blocks.TUFF)
    val CALCITE_SLAB = VoidedSlabBlock(Blocks.CALCITE)
    val PACKED_MUD_SLAB = VoidedSlabBlock(Blocks.PACKED_MUD)
    val DRIPSTONE_SLAB = VoidedSlabBlock(Blocks.DRIPSTONE_BLOCK)
    val NETHERRACK_SLAB = VoidedSlabBlock(Blocks.NETHERRACK)
    val CRACKED_NETHER_BRICKS_SLAB = VoidedSlabBlock(Blocks.CRACKED_NETHER_BRICKS)
    val CRACKED_RED_NETHER_BRICKS_SLAB = VoidedSlabBlock(CRACKED_RED_NETHER_BRICKS)
    val SMOOTH_BASALT_SLAB = VoidedSlabBlock(Blocks.SMOOTH_BASALT)
    val CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB = VoidedSlabBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
    val END_STONE_SLAB = VoidedSlabBlock(Blocks.END_STONE)
    val QUARTZ_BRICKS_SLAB = VoidedSlabBlock(Blocks.QUARTZ_BRICKS)
    val OBSIDIAN_SLAB = VoidedSlabBlock(
        Blocks.OBSIDIAN,
        FabricBlockSettings.copyOf(Blocks.OBSIDIAN).pistonBehavior(PistonBehavior.BLOCK)
    )

    val SMOOTH_STONE_WALL = VoidedWallBlock(Blocks.SMOOTH_STONE)
    val STONE_WALL = VoidedWallBlock(Blocks.STONE)
    val CRACKED_STONE_BRICKS_WALL = VoidedWallBlock(Blocks.CRACKED_STONE_BRICKS)
    val POLISHED_GRANITE_WALL = VoidedWallBlock(Blocks.POLISHED_GRANITE)
    val POLISHED_DIORITE_WALL = VoidedWallBlock(Blocks.POLISHED_DIORITE)
    val POLISHED_ANDESITE_WALL = VoidedWallBlock(Blocks.POLISHED_ANDESITE)
    val CRACKED_DEEPSLATE_BRICKS_WALL = VoidedWallBlock(Blocks.CRACKED_DEEPSLATE_BRICKS)
    val CRACKED_DEEPSLATE_TILES_WALL = VoidedWallBlock(Blocks.CRACKED_DEEPSLATE_TILES)
    val TUFF_WALL = VoidedWallBlock(Blocks.TUFF)
    val CALCITE_WALL = VoidedWallBlock(Blocks.CALCITE)
    val PACKED_MUD_WALL = VoidedWallBlock(Blocks.PACKED_MUD)
    val DRIPSTONE_WALL = VoidedWallBlock(Blocks.DRIPSTONE_BLOCK)
    val CUT_SANDSTONE_WALL = VoidedWallBlock(Blocks.CUT_SANDSTONE)
    val SMOOTH_SANDSTONE_WALL = VoidedWallBlock(Blocks.SMOOTH_SANDSTONE)
    val CUT_RED_SANDSTONE_WALL = VoidedWallBlock(Blocks.CUT_RED_SANDSTONE)
    val SMOOTH_RED_SANDSTONE_WALL = VoidedWallBlock(Blocks.SMOOTH_RED_SANDSTONE)
    val PRISMARINE_BRICKS_WALL = VoidedWallBlock(Blocks.PRISMARINE_BRICKS)
    val DARK_PRISMARINE_WALL = VoidedWallBlock(Blocks.DARK_PRISMARINE)
    val NETHERRACK_WALL = VoidedWallBlock(Blocks.NETHERRACK)
    val CRACKED_NETHER_BRICKS_WALL = VoidedWallBlock(Blocks.CRACKED_NETHER_BRICKS)
    val CRACKED_RED_NETHER_BRICKS_WALL = VoidedWallBlock(CRACKED_RED_NETHER_BRICKS)
    val SMOOTH_BASALT_WALL = VoidedWallBlock(Blocks.SMOOTH_BASALT)
    val CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL = VoidedWallBlock(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
    val END_STONE_WALL = VoidedWallBlock(Blocks.END_STONE)
    val PURPUR_WALL = VoidedWallBlock(Blocks.PURPUR_BLOCK)
    val QUARTZ_WALL = VoidedWallBlock(Blocks.QUARTZ_BLOCK)
    val QUARTZ_BRICKS_WALL = VoidedWallBlock(Blocks.QUARTZ_BRICKS)
    val SMOOTH_QUARTZ_WALL = VoidedWallBlock(Blocks.SMOOTH_QUARTZ)
    val SNOW_WALL = VoidedWallBlock(Blocks.SNOW_BLOCK)
    val OBSIDIAN_WALL = ObsidianWallBlock()


    val SMOOTH_STONE_BUTTON = createStoneBtn(Blocks.SMOOTH_STONE)
    val DEEPSLATE_BUTTON = createStoneBtn(Blocks.DEEPSLATE)
    val POLISHED_DEEPSLATE_BUTTON = createStoneBtn(Blocks.POLISHED_DEEPSLATE)
    val BLACKSTONE_BUTTON = createStoneBtn(Blocks.BLACKSTONE)


    val SMOOTH_STONE_TOGGLEABLE_BUTTON = createToggleStoneBtn(SMOOTH_STONE_BUTTON)
    val DEEPSLATE_TOGGLEABLE_BUTTON = createToggleStoneBtn(DEEPSLATE_BUTTON)
    val POLISHED_DEEPSLATE_TOGGLEABLE_BUTTON = createToggleStoneBtn(POLISHED_DEEPSLATE_BUTTON)
    val BLACKSTONE_TOGGLEABLE_BUTTON = createToggleStoneBtn(BLACKSTONE_BUTTON)


    val SMOOTH_STONE_PRESSURE_PLATE = createStonePlate(Blocks.SMOOTH_STONE)
    val DEEPSLATE_PRESSURE_PLATE = createStonePlate(Blocks.DEEPSLATE)
    val POLISHED_DEEPSLATE_PRESSURE_PLATE = createStonePlate(Blocks.POLISHED_DEEPSLATE)
    val BLACKSTONE_PRESSURE_PLATE = createStonePlate(Blocks.BLACKSTONE)

    val RED_NETHER_BRICK_FENCE = FenceBlock(
        FabricBlockSettings.create().mapColor(MapColor.NETHER).strength(2.0f, 6.0f)
            .instrument(NoteBlockInstrument.BASEDRUM).requiresTool().sounds(BlockSoundGroup.NETHER_BRICKS)
    )


    fun init() {
        if (!getConfig().enableConsistentStones) return

        regFullSquare("cracked_red_nether_bricks", CRACKED_RED_NETHER_BRICKS)

        regInfested("infested_mossy_cobblestone", INFESTED_MOSSY_COBBLESTONE)
        regInfested("infested_cobbled_deepslate", INFESTED_COBBLED_DEEPSLATE)
        regInfested("infested_deepslate_bricks", INFESTED_DEEPSLATE_BRICKS)
        regInfested("infested_cracked_deepslate_bricks", INFESTED_CRACKED_DEEPSLATE_BRICKS)
        regInfested("infested_deepslate_tiles", INFESTED_DEEPSLATE_TILES)
        regInfested("infested_cracked_deepslate_tiles", INFESTED_CRACKED_DEEPSLATE_TILES)
        regInfested("infested_polished_deepslate", INFESTED_POLISHED_DEEPSLATE)

        regStair("smooth_stone_stair", SMOOTH_STONE_STAIR)
        regStair("cracked_stone_bricks_stair", CRACKED_STONE_BRICKS_STAIR)
        regStair("tuff_stair", TUFF_STAIR)
        regStair("calcite_stair", CALCITE_STAIR)
        regStair("packed_mud_stair", PACKED_MUD_STAIR)
        regStair("dripstone_stair", DRIPSTONE_STAIR)
        regStair("cut_sandstone_stair", CUT_SANDSTONE_STAIR)
        regStair("cut_red_sandstone_stair", CUT_RED_SANDSTONE_STAIR)
        regStair("netherrack_stair", NETHERRACK_STAIR)
        regStair("cracked_nether_bricks_stair", CRACKED_NETHER_BRICKS_STAIR)
        regStair("cracked_red_nether_bricks_stair", CRACKED_RED_NETHER_BRICKS_STAIR)
        regStair("cracked_deepslate_tiles_stair", CRACKED_DEEPSLATE_TILES_STAIR)
        regStair("cracked_deepslate_bricks_stair", CRACKED_DEEPSLATE_BRICKS_STAIR)
        regStair("smooth_basalt_stair", SMOOTH_BASALT_STAIR)
        regStair("cracked_polished_blackstone_bricks_stair", CRACKED_POLISHED_BLACKSTONE_BRICKS_STAIR)
        regStair("end_stone_stair", END_STONE_STAIR)
        regStair("quartz_bricks_stair", QUARTZ_BRICKS_STAIR)
        regStair("snow_stair", SNOW_STAIR)
        regStair("obsidian_stair", OBSIDIAN_STAIR)

        regSlab("cracked_stone_bricks_slab", CRACKED_STONE_BRICKS_SLAB)
        regSlab("cracked_deepslate_bricks_slab", CRACKED_DEEPSLATE_BRICKS_SLAB)
        regSlab("cracked_deepslate_tiles_slab", CRACKED_DEEPSLATE_TILES_SLAB)
        regSlab("tuff_slab", TUFF_SLAB)
        regSlab("calcite_slab", CALCITE_SLAB)
        regSlab("packed_mud_slab", PACKED_MUD_SLAB)
        regSlab("dripstone_slab", DRIPSTONE_SLAB)
        regSlab("netherrack_slab", NETHERRACK_SLAB)
        regSlab("cracked_nether_bricks_slab", CRACKED_NETHER_BRICKS_SLAB)
        regSlab("cracked_red_nether_bricks_slab", CRACKED_RED_NETHER_BRICKS_SLAB)
        regSlab("smooth_basalt_slab", SMOOTH_BASALT_SLAB)
        regSlab("cracked_polished_blackstone_bricks_slab", CRACKED_POLISHED_BLACKSTONE_BRICKS_SLAB)
        regSlab("end_stone_slab", END_STONE_SLAB)
        regSlab("quartz_bricks_slab", QUARTZ_BRICKS_SLAB)
        regSlab("obsidian_slab", OBSIDIAN_SLAB)

        regWall("smooth_stone_wall", SMOOTH_STONE_WALL)
        regWall("stone_wall", STONE_WALL)
        regWall("cracked_stone_bricks_wall", CRACKED_STONE_BRICKS_WALL)
        regWall("polished_granite_wall", POLISHED_GRANITE_WALL)
        regWall("polished_diorite_wall", POLISHED_DIORITE_WALL)
        regWall("polished_andesite_wall", POLISHED_ANDESITE_WALL)
        regWall("cracked_deepslate_bricks_wall", CRACKED_DEEPSLATE_BRICKS_WALL)
        regWall("cracked_deepslate_tiles_wall", CRACKED_DEEPSLATE_TILES_WALL)
        regWall("tuff_wall", TUFF_WALL)
        regWall("calcite_wall", CALCITE_WALL)
        regWall("packed_mud_wall", PACKED_MUD_WALL)
        regWall("dripstone_wall", DRIPSTONE_WALL)
        regWall("cut_sandstone_wall", CUT_SANDSTONE_WALL)
        regWall("smooth_sandstone_wall", SMOOTH_SANDSTONE_WALL)
        regWall("cut_red_sandstone_wall", CUT_RED_SANDSTONE_WALL)
        regWall("smooth_red_sandstone_wall", SMOOTH_RED_SANDSTONE_WALL)
        regWall("prismarine_bricks_wall", PRISMARINE_BRICKS_WALL)
        regWall("dark_prismarine_wall", DARK_PRISMARINE_WALL)
        regWall("netherrack_wall", NETHERRACK_WALL)
        regWall("cracked_nether_bricks_wall", CRACKED_NETHER_BRICKS_WALL)
        regWall("cracked_red_nether_bricks_wall", CRACKED_RED_NETHER_BRICKS_WALL)
        regWall("smooth_basalt_wall", SMOOTH_BASALT_WALL)
        regWall("cracked_polished_blackstone_bricks_wall", CRACKED_POLISHED_BLACKSTONE_BRICKS_WALL)
        regWall("end_stone_wall", END_STONE_WALL)
        regWall("purpur_wall", PURPUR_WALL)
        regWall("quartz_wall", QUARTZ_WALL)
        regWall("quartz_bricks_wall", QUARTZ_BRICKS_WALL)
        regWall("smooth_quartz_wall", SMOOTH_QUARTZ_WALL)
        regWall("snow_wall", SNOW_WALL)
        regWall("obsidian_wall", OBSIDIAN_WALL)

        regBtn("smooth_stone_button", SMOOTH_STONE_BUTTON)
        regBtn("deepslate_button", DEEPSLATE_BUTTON)
        regBtn("polished_deepslate_button", POLISHED_DEEPSLATE_BUTTON)
        regBtn("blackstone_button", BLACKSTONE_BUTTON)

        if (getConfig().enableToggleButtons) {
            regBtn("smooth_stone_toggleable_button", SMOOTH_STONE_TOGGLEABLE_BUTTON)
            regBtn("deepslate_toggleable_button", DEEPSLATE_TOGGLEABLE_BUTTON)
            regBtn("polished_deepslate_toggleable_button", POLISHED_DEEPSLATE_TOGGLEABLE_BUTTON)
            regBtn("blackstone_toggleable_button", BLACKSTONE_TOGGLEABLE_BUTTON)
        }

        regPlate("smooth_stone_pressure_plate", SMOOTH_STONE_PRESSURE_PLATE)
        regPlate("deepslate_pressure_plate", DEEPSLATE_PRESSURE_PLATE)
        regPlate("polished_deepslate_pressure_plate", POLISHED_DEEPSLATE_PRESSURE_PLATE)
        regPlate("blackstone_pressure_plate", BLACKSTONE_PRESSURE_PLATE)

        registerWithItem("red_nether_brick_fence", RED_NETHER_BRICK_FENCE)
    }

    fun regInfested(id: String, block: InfestedBlock) {
        INFESTED_LIST.add(block)
        registerWithItem(id, block)
    }

    fun regSlab(id: String, block: VoidedSlabBlock): Block {
        SLAB_LIST.add(block)
        return registerWithItem(id, block)
    }

    fun regStair(id: String, block: VoidedStairBlock): Block {
        STAIR_LIST.add(block)
        return registerWithItem(id, block)
    }

    fun regWall(id: String, block: VoidedWallBlock): Block {
        WALL_LIST.add(block)
        return registerWithItem(id, block)
    }

    fun regBtn(id: String, block: VoidedButtonBlock): Block {
        BTN_LIST.add(block)
        return registerWithItem(id, block)
    }

    fun regBtn(id: String, block: AbstractToggleableButtonBlock): Block {
        VUBlocks.TOGGLEABLE_BUTTONS.add(block)
        return registerWithItem(id, block)
    }

    fun regPlate(id: String, block: VoidedPressurePlateBlock): Block {
        PLATE_LIST.add(block)
        return registerWithItem(id, block)
    }

    private fun createStoneBtn(block: Block): VoidedButtonBlock = VoidedButtonBlock(
        block,
        FabricBlockSettings.create().noCollision().strength(0.5f).pistonBehavior(PistonBehavior.DESTROY),
        BlockSetType.STONE,
        20,
        false
    )


    private fun createToggleStoneBtn(block: AbstractButtonBlock): AbstractToggleableButtonBlock =
        AbstractToggleableButtonBlock(block, BlockSetType.STONE, false)


    private fun createStonePlate(block: Block): VoidedPressurePlateBlock {
        return VoidedPressurePlateBlock(
            block,
            ActivationRule.MOBS,
            FabricBlockSettings.create()
                .mapColor(block.defaultMapColor)
                .solid().requiresTool().noCollision().strength(0.5f)
                .pistonBehavior(PistonBehavior.DESTROY),
            BlockSetType.STONE
        )
    }

}