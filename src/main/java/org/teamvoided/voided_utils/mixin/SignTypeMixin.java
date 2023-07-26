package org.teamvoided.voided_utils.mixin;

import net.minecraft.block.BlockSetType;
import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeMixin {
    @Invoker("<init>")
    static SignType invokeInit(String name, BlockSetType blockSetType) {
        throw new AssertionError();
    }


    @Invoker("register")
    static SignType registerNew(SignType type) {
        throw new AssertionError();
    }
}