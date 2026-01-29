package com.bludos.optimised.ui;

import com.bludos.optimised.config.BldoRuntimeConfig;
import com.bludos.optimised.render.LevelRenderDecimator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BldoOptimisedScreen extends Screen {

    private final Screen parent;

    public BldoOptimisedScreen(Screen parent) {
        super(Component.literal("Bldo Optimised"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = this.height / 4;

        this.addRenderableWidget(
            Button.builder(
                getEntityRenderLabel(),
                btn -> {
                    if (BldoRuntimeConfig.entityRenderDivisor == 1) {
                        BldoRuntimeConfig.entityRenderDivisor = 2;
                    } else if (BldoRuntimeConfig.entityRenderDivisor == 2) {
                        BldoRuntimeConfig.entityRenderDivisor = 3;
                    } else {
                        BldoRuntimeConfig.entityRenderDivisor = 1;
                    }
                    btn.setMessage(getEntityRenderLabel());
                }
            ).bounds(centerX - 100, startY, 200, 20).build()
        );

        this.addRenderableWidget(
            Button.builder(
                getWorldRenderLabel(),
                btn -> {
                    if (LevelRenderDecimator.FRAME_DIVISOR == 1) {
                        LevelRenderDecimator.FRAME_DIVISOR = 2;
                    } else if (LevelRenderDecimator.FRAME_DIVISOR == 2) {
                        LevelRenderDecimator.FRAME_DIVISOR = 3;
                    } else {
                        LevelRenderDecimator.FRAME_DIVISOR = 1;
                    }
                    btn.setMessage(getWorldRenderLabel());
                }
            ).bounds(centerX - 100, startY + 24, 200, 20).build()
        );

        this.addRenderableWidget(
            Button.builder(
                Component.literal("Done"),
                b -> onClose()
            ).bounds(centerX - 75, this.height - 29, 150, 20).build()
        );
    }

    private Component getEntityRenderLabel() {
        return switch (BldoRuntimeConfig.entityRenderDivisor) {
            case 1 -> Component.literal("Entities: Full");
            case 2 -> Component.literal("Entities: Balanced");
            case 3 -> Component.literal("Entities: Aggressive");
            default -> Component.literal("Entities: Custom");
        };
    }

    private Component getWorldRenderLabel() {
        return switch (LevelRenderDecimator.FRAME_DIVISOR) {
            case 1 -> Component.literal("World Rendering: Full");
            case 2 -> Component.literal("World Rendering: ~30 FPS");
            case 3 -> Component.literal("World Rendering: ~20 FPS");
            default -> Component.literal("World Rendering: Custom");
        };
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTick) {
        gfx.fill(0, 0, width, height, 0xAA000000);
        super.render(gfx, mouseX, mouseY, partialTick);
        gfx.drawCenteredString(
            this.font,
            this.title,
            this.width / 2,
            20,
            0xFFFFFF
        );
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(parent);
    }
}