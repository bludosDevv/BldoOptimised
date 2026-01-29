package com.bludos.optimised.events;

import com.bludos.optimised.runtime.BldoRuntimeState;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientMovementTracker {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;

        var mc = Minecraft.getInstance();
        if (mc.player == null) return;

        double dx = mc.player.getDeltaMovement().x;
        double dy = mc.player.getDeltaMovement().y;
        double dz = mc.player.getDeltaMovement().z;

        BldoRuntimeState.playerMoving = (dx * dx + dy * dy + dz * dz) > 0.0001;
    }
}