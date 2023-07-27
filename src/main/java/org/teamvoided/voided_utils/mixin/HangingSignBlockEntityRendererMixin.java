package org.teamvoided.voided_utils.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.resource.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.blocks.CustomSign;

@Mixin(HangingSignBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class HangingSignBlockEntityRendererMixin extends SignBlockEntityRendererMixin {
	@Inject(method = "getSignTexture", at = @At("HEAD"), cancellable = true)
	private void getSignTexture(CallbackInfoReturnable<Material> ci) {
		if (this.renderedBlockEntity != null) {
			if (this.renderedBlockEntity.getCachedState().getBlock() instanceof CustomSign signBlock) {
				ci.setReturnValue(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, signBlock.getTexture()));
			}
		}
	}
}
