package com.bludos.optimised.render;

public class EntityRenderDecimator {

    // Render entities every N frames
    public static int ENTITY_FRAME_DIVISOR = 2;

    private static int frameCounter = 0;

    public static boolean shouldRenderEntity() {
        frameCounter++;
        return frameCounter % ENTITY_FRAME_DIVISOR == 0;
    }
}