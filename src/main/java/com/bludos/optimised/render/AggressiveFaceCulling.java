package com.bludos.optimised.render;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class AggressiveFaceCulling {

    public static boolean shouldCull(
        BlockState self,
        BlockState neighbor,
        Direction face
    ) {
        if (neighbor.isSolidRender(null, null)) return true;
        if (neighbor.isCollisionShapeFullBlock(null, null)) return true;
        if (neighbor.getLightBlock(null, null) == 15) return true;
        return false;
    }
}