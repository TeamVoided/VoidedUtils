package org.teamvoided.voided_utils.misc

import net.minecraft.block.AbstractBlock.ContextPredicate
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.NetherPortalBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.Properties
import net.minecraft.util.math.*
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockLocating
import net.minecraft.world.BlockView
import net.minecraft.world.TeleportTarget
import net.minecraft.world.WorldAccess
import org.teamvoided.voided_utils.data.tags.VUBlockTags
import java.util.*
import java.util.function.Consumer
import java.util.function.Predicate
import kotlin.math.max

class CustomAreaHelper(private val world: WorldAccess, pos: BlockPos, private val axis: Direction.Axis) {
    private val negativeDir: Direction = if (axis === Direction.Axis.X) Direction.WEST else Direction.SOUTH
    private var foundPortalBlocks = 0
    private var lowerCorner: BlockPos?
    private var height = 0
    private var width = 0

    init {
        lowerCorner = getLowerCorner(pos)
        if (lowerCorner == null) {
            lowerCorner = pos
            width = 1
            height = 1
        } else {
            width = this.getWidth()
            if (width > 0) {
                height = getHeight()
            }
        }
    }

    private fun getLowerCorner(pos: BlockPos): BlockPos? {
        var pos = pos
        val i = max(world.bottomY.toDouble(), (pos.y - 21).toDouble()).toInt()
        while (pos.y > i && validStateInsidePortal(world.getBlockState(pos.down()))) {
            pos = pos.down()
        }
        val direction = negativeDir.opposite
        val j = this.getWidth(pos, direction) - 1
        return if (j < 0) {
            null
        } else pos.offset(direction, j)
    }

    private fun getWidth(): Int {
        val i = this.getWidth(lowerCorner, negativeDir)
        return if (i < 2 || i > 21) {
            0
        } else i
    }

    private fun getWidth(pos: BlockPos?, direction: Direction): Int {
        val mutable = BlockPos.Mutable()
        for (i in 0..21) {
            mutable.set(pos).move(direction, i)
            val blockState = world.getBlockState(mutable)
            if (!validStateInsidePortal(blockState)) {
                if (!IS_VALID_FRAME_BLOCK.test(blockState, world, mutable)) break
                return i
            }
            val blockState2 = world.getBlockState(mutable.move(Direction.DOWN))
            if (!IS_VALID_FRAME_BLOCK.test(blockState2, world, mutable)) break
        }
        return 0
    }

    private fun getHeight(): Int {
        val mutable = BlockPos.Mutable()
        val i = getPotentialHeight(mutable)
        return if (i < 3 || i > 21 || !hasTopFrame(mutable, i)) {
            0
        } else i
    }

    private fun hasTopFrame(pos: BlockPos.Mutable, height: Int): Boolean {
        for (i in 0 until width) {
            val mutable = pos.set(lowerCorner).move(Direction.UP, height).move(negativeDir, i)
            if (IS_VALID_FRAME_BLOCK.test(world.getBlockState(mutable), world, mutable)) continue
            return false
        }
        return true
    }

    private fun getPotentialHeight(pos: BlockPos.Mutable): Int {
        for (i in 0..20) {
            pos.set(lowerCorner).move(Direction.UP, i).move(negativeDir, -1)
            if (!IS_VALID_FRAME_BLOCK.test(world.getBlockState(pos), world, pos)) {
                return i
            }
            pos.set(lowerCorner).move(Direction.UP, i).move(negativeDir, width)
            if (!IS_VALID_FRAME_BLOCK.test(world.getBlockState(pos), world, pos)) {
                return i
            }
            for (j in 0 until width) {
                pos.set(lowerCorner).move(Direction.UP, i).move(negativeDir, j)
                val blockState = world.getBlockState(pos)
                if (!validStateInsidePortal(blockState)) {
                    return i
                }
                if (!blockState.isOf(Blocks.NETHER_PORTAL)) continue
                ++foundPortalBlocks
            }
        }
        return 21
    }

    val isValid: Boolean
        get() = lowerCorner != null && width >= 2 && width <= 21 && height >= 3 && height <= 21

    fun createPortal() {
        val blockState = Blocks.NETHER_PORTAL.defaultState.with(NetherPortalBlock.AXIS, axis) as BlockState
        BlockPos.iterate(
            lowerCorner, lowerCorner!!.offset(Direction.UP, height - 1).offset(
                negativeDir, width - 1
            )
        ).forEach(
            Consumer { pos: BlockPos? ->
                world.setBlockState(
                    pos,
                    blockState,
                    Block.NOTIFY_LISTENERS or Block.FORCE_STATE
                )
            })
    }

    fun wasAlreadyValid(): Boolean {
        return isValid && foundPortalBlocks == width * height
    }

    companion object {
        private const val MIN_WIDTH = 2
        const val MAX_WIDTH = 21
        private const val MIN_HEIGHT = 3
        const val MAX_HEIGHT = 21
        private val IS_VALID_FRAME_BLOCK = ContextPredicate { s, _, _ -> s.isIn(VUBlockTags.NETHER_PORTAL_BLOCK) }
        private const val MAX_SAFE_ENTITY_XY = 4.0f
        private const val MAX_VERTICAL_TRAVEL_DELTA = 1.0
        fun getNewPortal(world: WorldAccess, pos: BlockPos, axis: Direction.Axis): Optional<CustomAreaHelper> {
            return getOrEmpty(
                world,
                pos,
                { helper: CustomAreaHelper -> helper.isValid && helper.foundPortalBlocks == 0 },
                axis
            )
        }

        fun getOrEmpty(
            world: WorldAccess, pos: BlockPos, predicate: Predicate<CustomAreaHelper>, axis: Direction.Axis
        ): Optional<CustomAreaHelper> {
            val optional = Optional.of(CustomAreaHelper(world, pos, axis)).filter(predicate)
            if (optional.isPresent)  return optional
            val axis2 = if (axis === Direction.Axis.X) Direction.Axis.Z else Direction.Axis.X
            return Optional.of(CustomAreaHelper(world, pos, axis2)).filter(predicate)
        }

        private fun validStateInsidePortal(state: BlockState): Boolean {
            return state.isAir || state.isIn(BlockTags.FIRE) || state.isOf(Blocks.NETHER_PORTAL)
        }

        fun entityPosInPortal(
            portalRect: BlockLocating.Rectangle, portalAxis: Direction.Axis,
            entityPos: Vec3d, entityDimensions: EntityDimensions
        ): Vec3d {
            val h: Double
            var axis: Direction.Axis
            val g: Double
            val d = portalRect.width.toDouble() - entityDimensions.width.toDouble()
            val e = portalRect.height.toDouble() - entityDimensions.height.toDouble()
            val blockPos = portalRect.lowerLeft
            g = if (d > 0.0) {
                val f = blockPos.getComponentAlongAxis(portalAxis).toFloat() + entityDimensions.width / 2.0f
                MathHelper.clamp(
                    MathHelper.inverseLerp(entityPos.getComponentAlongAxis(portalAxis) - f.toDouble(), 0.0, d),
                    0.0,
                    1.0
                )
            } else {
                0.5
            }
            if (e > 0.0) {
                axis = Direction.Axis.Y
                h = MathHelper.clamp(
                    MathHelper.inverseLerp(
                        entityPos.getComponentAlongAxis(axis) - blockPos.getComponentAlongAxis(axis).toDouble(), 0.0, e
                    ), 0.0, 1.0
                )
            } else {
                h = 0.0
            }
            axis = if (portalAxis === Direction.Axis.X) Direction.Axis.Z else Direction.Axis.X
            val i = entityPos.getComponentAlongAxis(axis) - (blockPos.getComponentAlongAxis(axis).toDouble() + 0.5)
            return Vec3d(g, h, i)
        }

        /**
         * Determines a [TeleportTarget] based on a specific portal.
         *
         *
         * The offset, velocity, and angle are modified based on the portal's axis.
         */
        fun getNetherTeleportTarget(
            destination: ServerWorld,
            portalRect: BlockLocating.Rectangle,
            portalAxis: Direction.Axis,
            offset: Vec3d,
            entity: Entity,
            velocity: Vec3d,
            yaw: Float,
            pitch: Float
        ): TeleportTarget {
            val blockPos = portalRect.lowerLeft
            val blockState = destination.getBlockState(blockPos)
            val axis = blockState.getOrEmpty(Properties.HORIZONTAL_AXIS).orElse(Direction.Axis.X)
            val d = portalRect.width.toDouble()
            val e = portalRect.height.toDouble()
            val entityDimensions = entity.getDimensions(entity.pose)
            val i = if (portalAxis === axis) 0 else 90
            val vec3d = if (portalAxis === axis) velocity else Vec3d(velocity.z, velocity.y, -velocity.x)
            val f = entityDimensions.width.toDouble() / 2.0 + (d - entityDimensions.width.toDouble()) * offset.getX()
            val g = (e - entityDimensions.height.toDouble()) * offset.getY()
            val h = 0.5 + offset.getZ()
            val bl = axis === Direction.Axis.X
            val vec3d2 = Vec3d(
                blockPos.x.toDouble() + if (bl) f else h,
                blockPos.y.toDouble() + g,
                blockPos.z.toDouble() + if (bl) h else f
            )
            val vec3d3 = findOpenPosition(vec3d2, destination, entity, entityDimensions)
            return TeleportTarget(vec3d3, vec3d, yaw + i.toFloat(), pitch)
        }

        private fun findOpenPosition(
            fallback: Vec3d,
            world: ServerWorld,
            entity: Entity,
            dimensions: EntityDimensions
        ): Vec3d {
            if (dimensions.width > 4.0f || dimensions.height > 4.0f) {
                return fallback
            }
            val d = dimensions.height.toDouble() / 2.0
            val vec3d = fallback.add(0.0, d, 0.0)
            val voxelShape = VoxelShapes.cuboid(
                Box.of(vec3d, dimensions.width.toDouble(), 0.0, dimensions.width.toDouble()).stretch(0.0, 1.0, 0.0)
                    .expand(1.0E-6)
            )
            val optional = world.findClosestCollision(
                entity,
                voxelShape,
                vec3d,
                dimensions.width.toDouble(),
                dimensions.height.toDouble(),
                dimensions.width.toDouble()
            )
            val optional2 = optional.map { pos: Vec3d -> pos.subtract(0.0, d, 0.0) }
            return optional2.orElse(fallback)
        }
    }
}
