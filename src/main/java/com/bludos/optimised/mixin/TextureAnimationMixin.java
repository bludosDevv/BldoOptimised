package com.bludos.optimised.mixin;

import com.bludos.optimised.runtime.BldoRuntimeState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureAtlasSprite.class)
public class TextureAnimationMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void bldo$disableAnimation(CallbackInfo ci) {
        if (BldoRuntimeState.disableAnimations) {
            ci.cancel();
        }
    }
}