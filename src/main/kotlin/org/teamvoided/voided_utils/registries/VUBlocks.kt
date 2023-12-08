package org.teamvoided.voided_utils.registries

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeRegistry
import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import org.teamvoided.voided_utils.VoidedUtils
import org.teamvoided.voided_utils.VoidedUtils.id
import org.teamvoided.voided_utils.blocks.AbstractToggleableButtonBlock
import org.teamvoided.voided_utils.blocks.RedstoneLanternBlock
import org.teamvoided.voided_utils.mixin.AbstractButtonBlockAccessor
import org.teamvoided.voided_utils.registries.VUItems.ALL_ITEM_LIST
import org.teamvoided.voided_utils.registries.modules.CharredWoodSet
import org.teamvoided.voided_utils.registries.modules.ConsistentStones
import org.teamvoided.voidlib.core.gId
import java.util.*
import kotlin.collections.ArrayList


@Suppress("MemberVisibilityCanBePrivate")
object VUBlocks {

    val BLOCK_LIST = LinkedList<Block>()
    val BLOCK_ITEM_LIST = LinkedList<Block>()
    val FULL_SQUARE_LIST = LinkedList<Block>()
    val TOGGLEABLE_BUTTONS = LinkedList<AbstractToggleableButtonBlock>()


    val IRON_LIKE_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeRegistry.register(
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


    val REDSTONE_LANTERN: Block? by lazy {
        if (VoidedUtils.getConfig().enableRedstoneLantern)
            RedstoneLanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).luminance { if (it.get(RedstoneLanternBlock.LIT)) 8 else 0 })
        else null
    }

    val IRON_COATED_TRAPDOOR: Block? by lazy {
        if (VoidedUtils.getConfig().enableIronCoatedBlocks)
            TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR), IRON_LIKE_BLOCK_SET_TYPE)
        else null
    }
    val IRON_COATED_DOOR: Block? by lazy {
        if (VoidedUtils.getConfig().enableIronCoatedBlocks)
            DoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_DOOR), IRON_LIKE_BLOCK_SET_TYPE)
        else null
    }

    val toggleButtons = mutableMapOf<Identifier, AbstractToggleableButtonBlock>()


    fun init() {
        val c = VoidedUtils.getConfig()

        if (c.enableRedstoneLantern) registerWithItem("redstone_lantern", REDSTONE_LANTERN!!)
        if (c.enableCharredWoodSet) CharredWoodSet.init()
        if (c.enableIronCoatedBlocks) {
            registerWithItem("iron_coated_trapdoor", IRON_COATED_TRAPDOOR!!)
            registerWithItem("iron_coated_door", IRON_COATED_DOOR!!)
        }
        if (c.enableToggleButtons) {
            Registries.BLOCK.forEach {
                if (it is AbstractButtonBlock) {
                    val newId = "toggleable_${it.gId.namespace}_${it.gId.path}"
                    val toggleButton = createToggleBtn(it, (it as AbstractButtonBlockAccessor).blockSetType)
                    toggleButtons += id(newId) to toggleButton
                    registerToggleBtn(newId, toggleButton)
                }
            }
        }
        if (c.enableConsistentStones) ConsistentStones.init()
    }

    val CUTOUT_LIST: ArrayList<Block?> = arrayListOf(
        REDSTONE_LANTERN,
        IRON_COATED_TRAPDOOR,
        IRON_COATED_DOOR
    )

    fun regFullSquare(id: String, block: Block): Block {
        FULL_SQUARE_LIST.add(block)
        return registerWithItem(id, block)
    }

    fun registerWithItem(id: String, block: Block): Block {
        val item = Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        ALL_ITEM_LIST.add(item)
        BLOCK_ITEM_LIST.add(block)
        return register(id, block)
    }

    fun registerToggleBtn(id: String, block: AbstractToggleableButtonBlock): Block {
        TOGGLEABLE_BUTTONS.add(block)
        return registerWithItem(id, block)
    }

    fun register(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCK_LIST.add(regBlock)
        return regBlock
    }

    fun createPillarBlock(topColor: MapColor, sideColor: MapColor): PillarBlock {
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

    fun createBtn(blockSetType: BlockSetType): AbstractButtonBlock = AbstractButtonBlock(
        AbstractBlock.Settings.create().noCollision().strength(0.5f).pistonBehavior(PistonBehavior.DESTROY),
        blockSetType,
        30,
        true
    )

    fun createToggleBtn(button: Block, blockSetType: BlockSetType): AbstractToggleableButtonBlock =
        AbstractToggleableButtonBlock(button as AbstractButtonBlock, blockSetType, true)

    fun createToggleStoneBtn(button: Block): AbstractToggleableButtonBlock =
        AbstractToggleableButtonBlock(button as AbstractButtonBlock, BlockSetType.STONE, false)

}
