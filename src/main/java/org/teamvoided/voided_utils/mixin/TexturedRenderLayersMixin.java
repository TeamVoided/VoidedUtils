package org.teamvoided.voided_utils.mixin;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.resource.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.voided_utils.MaterialRegistry;

import java.util.function.Consumer;

@Mixin(TexturedRenderLayers.class)

public class TexturedRenderLayersMixin {
    @Inject(method = "addDefaultTextures", at = @At("RETURN"))
    private static void injectSigns(Consumer<Material> consumer, CallbackInfo info) {
        MaterialRegistry.INSTANCE.getIdentifiers().forEach(consumer);
    }
}
