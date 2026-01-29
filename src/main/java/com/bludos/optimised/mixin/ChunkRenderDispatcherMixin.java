package com.bludos.optimised.mixin;

import com.bludos.optimised.runtime.BldoChunkBudget;
import com.bludos.optimised.runtime.BldoRuntimeState;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkRenderDispatcher.class)
public class ChunkRenderDispatcherMixin {

    @Inject(
        method = "schedule",
        at = @At("HEAD"),
        cancellable = true
    )
    private void bldo$limitSchedules(ChunkRenderDispatcher.RenderChunk chunk, CallbackInfo ci) {
        if (!BldoRuntimeState.chunkBudgetEnabled) return;

        if (BldoChunkBudget.schedulesThisTick >= BldoChunkBudget.maxSchedulesPerTick) {
            ci.cancel();
            return;
        }

        BldoChunkBudget.schedulesThisTick++;
    }
}