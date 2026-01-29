package com.bludos.optimised.ui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class BldoButton extends Button {

    public BldoButton(int x, int y, int w, int h, Component text, OnPress press) {
        super(x, y, w, h, text, press, DEFAULT_NARRATION);
    }

    @Override
    protected void renderWidget(GuiGraphics g, int mx, int my, float dt) {
        int bg = this.isHovered ? 0xCC202020 : 0xCC101010;
        int border = this.isHovered ? 0xFF3AFF3A : 0xFF000000;

        g.fill(getX(), getY(), getX() + width, getY() + height, bg);
        g.fill(getX(), getY(), getX() + width, getY() + 1, border);
        g.fill(getX(), getY() + height - 1, getX() + width, getY() + height, border);

        g.drawCenteredString(
            Minecraft.getInstance().font,
            getMessage(),
            getX() + width / 2,
            getY() + (height - 8) / 2,
            0xFFFFFF
        );
    }
}