package com.bludos.optimised.ui;

import com.bludos.optimised.render.BldoCullingState;
import com.bludos.optimised.ui.widget.BldoButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
        int cx = this.width / 2;
        int y = this.height / 4;

        this.addRenderableWidget(
            new BldoButton(
                cx - 100,
                y,
                200,
                20,
                getCullingLabel(),
                b -> {
                    BldoCullingState.enabled = !BldoCullingState.enabled;
                    b.setMessage(getCullingLabel());
                }
            )
        );

        this.addRenderableWidget(
            new BldoButton(
                cx - 100,
                y + 28,
                200,
                20,
                Component.literal("More features soon"),
                b -> {}
            )
        );

        this.addRenderableWidget(
            new BldoButton(
                cx + 5,
                this.height - 29,
                150,
                20,
                Component.literal("Done"),
                b -> onClose()
            )
        );
    }

    private Component getCullingLabel() {
        return BldoCullingState.enabled
            ? Component.literal("Aggressive Culling: ON")
            : Component.literal("Aggressive Culling: OFF");
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        g.fill(0, 0, width, height, 0xAA000000);
        super.render(g, mx, my, pt);
        g.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(parent);
    }
}