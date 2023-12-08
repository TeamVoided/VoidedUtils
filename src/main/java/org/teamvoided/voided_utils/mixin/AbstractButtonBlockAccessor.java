package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractButtonBlock.class)
public abstract class AbstractButtonBlockAccessor {
    @Accessor("blockSetType")
    public abstract BlockSetType getBlockSetType();
}
