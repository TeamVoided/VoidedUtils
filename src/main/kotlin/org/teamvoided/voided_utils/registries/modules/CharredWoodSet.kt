package org.teamvoided.voided_utils.registries.modules

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.SignType
import org.teamvoided.scuffedlib.sign.block.VoidHangingSignBlock
import org.teamvoided.scuffedlib.sign.block.VoidSignBlock
import org.teamvoided.scuffedlib.sign.block.VoidWallHangingSignBlock
import org.teamvoided.scuffedlib.sign.block.VoidWallSignBlock
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.VoidedUtils.getConfig
import org.teamvoided.voided_utils.blocks.AbstractToggleableButtonBlock
import org.teamvoided.voided_utils.registries.VUBlocks

@Suppress("MemberVisibilityCanBePrivate")
object CharredWoodSet {
    var CHARRED_BLOCK_SET_TYPE: BlockSetType? = null
    var CHARRED_WOOD_TYPE: SignType? = null
    var CHARRED_SIGN_ID: Identifier? = null
    var CHARRED_HANGING_SIGN_ID: Identifier? = null
    var charredLike: AbstractBlock.Settings = FabricBlockSettings.create()
        .mapColor(MapColor.STONE)
        .instrument(NoteBlockInstrument.BASS)
        .strength(2.0f)
        .sounds(BlockSoundGroup.WOOD)
        .lavaIgnitable()
    var CHARRED_LOG: Block? = null
    var STRIPPED_CHARRED_LOG: Block? = null
    var CHARRED_WOOD: Block? = null
    var STRIPPED_CHARRED_WOOD: Block? = null
    var CHARRED_PLANKS: Block? = null
    var CHARRED_STAIRS: Block? = null
    var CHARRED_SLAB: Block? = null
    var CHARRED_FENCE: Block? = null
    var CHARRED_PRESSURE_PLATE: Block? = null
    var CHARRED_BUTTON: Block? = null
    var CHARRED_TRAPDOOR: Block? = null
    var CHARRED_DOOR: Block? = null
    var CHARRED_FENCE_GATE: Block? = null
    var CHARRED_SIGN: Block? = null
    var CHARRED_WALL_SIGN: Block? = null
    var CHARRED_HANGING_SIGN: Block? = null
    var CHARRED_WALL_HANGING_SIGN: Block? = null
    var TOGGLEABLE_CHARRED_BUTTON: AbstractToggleableButtonBlock? = null

    fun init() {
        CHARRED_BLOCK_SET_TYPE = BlockSetTypeRegistry.registerWood(VoidedUtils.id("charred"))
        CHARRED_WOOD_TYPE = WoodTypeRegistry.register(VoidedUtils.id("charred"), CHARRED_BLOCK_SET_TYPE)

        CHARRED_SIGN_ID = VoidedUtils.id("entity/signs/charred")
        CHARRED_HANGING_SIGN_ID = VoidedUtils.id("entity/signs/hanging/charred")

        CHARRED_LOG = VUBlocks.createPillarBlock(MapColor.DEEPSLATE, MapColor.STONE)
        STRIPPED_CHARRED_LOG = VUBlocks.createPillarBlock(MapColor.DEEPSLATE, MapColor.STONE)
        CHARRED_WOOD = PillarBlock(charredLike)
        STRIPPED_CHARRED_WOOD = PillarBlock(charredLike)

        CHARRED_PLANKS = Block(FabricBlockSettings.copyOf(charredLike).strength(2.0f, 3.0f))
        CHARRED_STAIRS = StairsBlock(CHARRED_PLANKS!!.defaultState, FabricBlockSettings.copy(CHARRED_PLANKS))
        CHARRED_SLAB = SlabBlock(FabricBlockSettings.copy(CHARRED_PLANKS))

        CHARRED_FENCE = FenceBlock(FabricBlockSettings.copy(CHARRED_PLANKS))
        CHARRED_PRESSURE_PLATE = PressurePlateBlock(
            PressurePlateBlock.ActivationRule.EVERYTHING,
            FabricBlockSettings.copyOf(charredLike).strength(0.5f).pistonBehavior(PistonBehavior.DESTROY),
            CHARRED_BLOCK_SET_TYPE
        )
        CHARRED_BUTTON = VUBlocks.createBtn(CHARRED_BLOCK_SET_TYPE!!)
        CHARRED_TRAPDOOR = TrapdoorBlock(
            FabricBlockSettings.copyOf(charredLike).strength(3.0f).nonOpaque().allowsSpawning { _, _, _, _ -> false },
            CHARRED_BLOCK_SET_TYPE
        )
        CHARRED_DOOR = DoorBlock(
            FabricBlockSettings.copyOf(charredLike).strength(3.0f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY),
            CHARRED_BLOCK_SET_TYPE
        )
        CHARRED_FENCE_GATE = FenceGateBlock(FabricBlockSettings.copy(CHARRED_PLANKS).solid(), CHARRED_WOOD_TYPE)
        CHARRED_SIGN = VoidSignBlock(
            CHARRED_SIGN_ID!!,
            FabricBlockSettings.copyOf(charredLike).strength(1.0f).noCollision()
                .drops(VoidedUtils.id("blocks/charred_sign")),
            CHARRED_WOOD_TYPE!!
        )
        CHARRED_WALL_SIGN = VoidWallSignBlock(
            CHARRED_SIGN_ID!!,
            FabricBlockSettings.copyOf(CHARRED_SIGN).dropsLike(CHARRED_SIGN),
            CHARRED_WOOD_TYPE!!
        )
        CHARRED_HANGING_SIGN = VoidHangingSignBlock(
            CHARRED_HANGING_SIGN_ID!!,
            FabricBlockSettings.copyOf(CHARRED_SIGN).drops(VoidedUtils.id("blocks/charred_hanging_sign")),
            CHARRED_WOOD_TYPE!!
        )
        CHARRED_WALL_HANGING_SIGN = VoidWallHangingSignBlock(
            CHARRED_HANGING_SIGN_ID!!,
            FabricBlockSettings.copyOf(CHARRED_SIGN).dropsLike(CHARRED_HANGING_SIGN),
            CHARRED_WOOD_TYPE!!
        )

        VUBlocks.registerWithItem("charred_log", CHARRED_LOG!!)
        VUBlocks.registerWithItem("stripped_charred_log", STRIPPED_CHARRED_LOG!!)
        VUBlocks.registerWithItem("charred_wood", CHARRED_WOOD!!)
        VUBlocks.registerWithItem("stripped_charred_wood", STRIPPED_CHARRED_WOOD!!)
        VUBlocks.registerWithItem("charred_planks", CHARRED_PLANKS!!)
        VUBlocks.registerWithItem("charred_stairs", CHARRED_STAIRS!!)
        VUBlocks.registerWithItem("charred_slab", CHARRED_SLAB!!)
        VUBlocks.registerWithItem("charred_fence", CHARRED_FENCE!!)
        VUBlocks.registerWithItem("charred_pressure_plate", CHARRED_PRESSURE_PLATE!!)
        VUBlocks.registerWithItem("charred_button", CHARRED_BUTTON!!)
        VUBlocks.registerWithItem("charred_trapdoor", CHARRED_TRAPDOOR!!)
        VUBlocks.registerWithItem("charred_door", CHARRED_DOOR!!)
        VUBlocks.registerWithItem("charred_fence_gate", CHARRED_FENCE_GATE!!)
        VUBlocks.register("charred_sign", CHARRED_SIGN!!)
        VUBlocks.register("charred_wall_sign", CHARRED_WALL_SIGN!!)
        VUBlocks.register("charred_hanging_sign", CHARRED_HANGING_SIGN!!)
        VUBlocks.register("charred_wall_hanging_sign", CHARRED_WALL_HANGING_SIGN!!)
        StrippableBlockRegistry.register(CHARRED_LOG, STRIPPED_CHARRED_LOG)
        StrippableBlockRegistry.register(CHARRED_WOOD, STRIPPED_CHARRED_WOOD)


        VUBlocks.CUTOUT_LIST.addAll(arrayListOf(CHARRED_DOOR, CHARRED_TRAPDOOR))

        if (getConfig().enableToggleButtons) {
            TOGGLEABLE_CHARRED_BUTTON = VUBlocks.createToggleBtn(CHARRED_BUTTON!!, CHARRED_BLOCK_SET_TYPE!!)
            VUBlocks.registerToggleBtn("toggleable_charred_button", TOGGLEABLE_CHARRED_BUTTON!!)
        }
    }

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