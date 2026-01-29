package com.bludos.optimised.mixin;

import com.bludos.optimised.render.AggressiveCulling;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(ModelBlockRenderer.class)
public class ModelBlockRendererMixin {

    @Redirect(
        method = "tesselateBlock",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/List;get(I)Ljava/lang/Object;"
        )
    )
    private Object bldo$cullFace(
        List<BakedQuad> list,
        int index,
        BlockAndTintGetter level,
        BlockState state,
        BlockPos pos
    ) {
        BakedQuad quad = list.get(index);
        Direction face = quad.getDirection();

        if (face != null && AggressiveCulling.shouldCull(level, pos, state, face)) {
            return null;
        }

        return quad;
    }
}