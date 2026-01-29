package com.bludos.optimised.render;

import com.bludos.optimised.config.BldoRuntimeConfig;

public class EntityRenderDecimator {

    private static int frameCounter = 0;

    public static boolean shouldRenderEntity() {
        frameCounter++;
        int div = Math.max(1, BldoRuntimeConfig.entityRenderDivisor);
        return frameCounter % div == 0;
    }
}