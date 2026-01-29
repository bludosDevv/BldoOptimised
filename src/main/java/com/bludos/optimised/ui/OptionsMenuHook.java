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
        if (!(event.getScreen() instanceof OptionsScreen screen)) return;

        int centerX = screen.width / 2;
        int bottomY = screen.height - 29;

        Button bldoButton = Button.builder(
                Component.literal("Bldo Optimised"),
                btn -> screen.getMinecraft().setScreen(
                        new BldoOptimisedScreen(screen)
                )
        ).bounds(centerX - 155, bottomY, 150, 20).build();

        event.addListener(bldoButton);

        
        event.getListeners().stream()
                .filter(w -> w instanceof Button btn
                        && btn.getMessage().getString().equals("Done"))
                .findFirst()
                .ifPresent(w -> {
                    Button done = (Button) w;
                    done.setX(centerX + 5);
                    done.setY(bottomY);
                });
    }
}