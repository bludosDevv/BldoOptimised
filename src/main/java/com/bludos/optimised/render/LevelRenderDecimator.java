package com.bludos.optimised.render;

public class LevelRenderDecimator {

    public static int FRAME_DIVISOR = 2;

    private static int frameCounter = 0;

    public static boolean shouldRenderLevel() {
        frameCounter++;
        return frameCounter % FRAME_DIVISOR == 0;
    }
}