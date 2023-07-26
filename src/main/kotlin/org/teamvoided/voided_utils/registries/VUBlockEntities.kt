package org.teamvoided.voided_utils.registries

import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.HangingSignBlockEntity
import net.minecraft.block.entity.SignBlockEntity
import net.minecraft.datafixer.TypeReferences
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Util
import org.teamvoided.voided_utils.VoidedUtils.id

object VUBlockEntities {
    val CUSTOM_SIGN: BlockEntityType<SignBlockEntity> =
        Registry.register(
            Registries.BLOCK_ENTITY_TYPE, id("sign"), BlockEntityType.Builder.create(
                ::SignBlockEntity,
                VUBlocks.CHARRED_SIGN,
                VUBlocks.CHARRED_WALL_SIGN
            ).build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY,"sign"))
        )

    val CUSTOM_HANGING_SIGN: BlockEntityType<HangingSignBlockEntity> =
        Registry.register(Registries.BLOCK_ENTITY_TYPE, id("hanging_sign"), BlockEntityType.Builder.create(
            ::HangingSignBlockEntity,
            VUBlocks.CHARRED_HANGING_SIGN,
            VUBlocks.CHARRED_WALL_HANGING_SIGN
        ).build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, "hanging_sign")))

    fun init() {}
}