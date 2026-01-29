package com.bludos.optimised.ui;

import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OptionsMenuHook {

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof OptionsScreen screen) {

            int x = screen.width / 2 - 155;
            int y = screen.height / 6 + 168;

            event.addListener(
                Button.builder(
                    Component.literal("Bldo Optimised"),
                    btn -> screen.getMinecraft().setScreen(
                        new BldoOptimisedScreen(screen)
                    )
                ).bounds(x, y, 150, 20).build()
            );
        }
    }
}