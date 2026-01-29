package com.bludos.optimised.events;

import com.bludos.optimised.runtime.BldoChunkBudget;
import com.bludos.optimised.runtime.BldoChunkQueue;
import com.bludos.optimised.runtime.BldoRuntimeState;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BldoChunkTickProcessor {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;
        if (!BldoRuntimeState.chunkBudgetEnabled) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.levelRenderer == null) return;

        var dispatcher = mc.levelRenderer.getChunkRenderDispatcher();
        var camPos = mc.gameRenderer.getMainCamera().getPosition();

        for (int i = 0; i < BldoChunkBudget.maxChunkBuildsPerTick; i++) {
            var chunk = BldoChunkQueue.poll();
            if (chunk == null) break;
            chunk.rebuildChunkAsync(dispatcher, camPos);
        }
    }
}