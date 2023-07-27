package org.teamvoided.voided_utils.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.resource.Material;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voided_utils.blocks.CustomSign;

@Mixin(SignBlockEntityRenderer.class)
public class SignBlockEntityRendererMixin {

    @Unique
    protected SignBlockEntity renderedBlockEntity;

    @WrapOperation(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/SignBlockEntityRenderer;renderModel(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/util/SignType;Lnet/minecraft/client/model/Model;)V")
    )
    @SuppressWarnings("unused")
    private void setRenderedBlockEntity(SignBlockEntityRenderer instance, MatrixStack matrices, VertexConsumerProvider verticesProvider, int light, int overlay, SignType type, Model model, Operation<Void> original, SignBlockEntity signBlockEntity) {
        this.renderedBlockEntity = signBlockEntity;
        original.call(instance, matrices, verticesProvider, light, overlay, type, model);
        this.renderedBlockEntity = null;
    }


    @Inject(method = "getSignTexture", at = @At("HEAD"), cancellable = true)
    private void getSignTexture(CallbackInfoReturnable<Material> ci) {
        if (this.renderedBlockEntity != null) {
            if (this.renderedBlockEntity.getCachedState().getBlock() instanceof CustomSign signBlock) {
                ci.setReturnValue(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, signBlock.getTexture()));
            }
        }
    }
}
