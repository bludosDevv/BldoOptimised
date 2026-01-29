package com.bludos.optimised.render;

public class LevelRenderDecimator {

    // Render world every N frames
    public static final int FRAME_DIVISOR = 2;

    private static int frameCounter = 0;

    public static boolean shouldRenderLevel() {
        frameCounter++;
        return frameCounter % FRAME_DIVISOR == 0;
    }
}