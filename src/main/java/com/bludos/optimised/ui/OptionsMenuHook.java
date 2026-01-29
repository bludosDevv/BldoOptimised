package com.bludos.optimised.ui;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.OptionsScreen;
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
int baseY = screen.height / 6 + 168; // vanilla bottom row Y

// Left button (Bldo)
Button bldoButton = Button.builder(
        Component.literal("Bldo Optimised"),
        btn -> screen.getMinecraft().setScreen(
                new BldoOptimisedScreen(screen)
        )
).bounds(centerX - 155, baseY, 150, 20).build();

event.addListener(bldoButton);

// Move Done button to right slot
for (var child : screen.children()) {
    if (child instanceof Button button) {
        if (button.getMessage().getString().equals("Done")) {
            button.setX(centerX + 5);
            button.setY(baseY);
            button.setWidth(150);
            break;
        }
    }
}
    }
}