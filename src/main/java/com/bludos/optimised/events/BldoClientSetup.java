package com.bludos.optimised.events;

import com.bludos.optimised.runtime.BldoChunkBudget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BldoClientSetup {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent e) {
        BldoChunkBudget.schedulesThisTick = 0;
    }
}