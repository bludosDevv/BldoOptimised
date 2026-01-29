package com.bludos.optimised.render;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class AggressiveCulling {

    public static boolean shouldCull(
        BlockAndTintGetter level,
        BlockPos pos,
        BlockState state,
        Direction face
    ) {
        if (!BldoCullingState.enabled) return false;

        BlockPos npos = pos.relative(face);
        BlockState neighbor = level.getBlockState(npos);

        if (neighbor.isSolidRender(level, npos)) return true;
        if (neighbor.getLightBlock(level, npos) == 15) return true;

        return false;
    }
}