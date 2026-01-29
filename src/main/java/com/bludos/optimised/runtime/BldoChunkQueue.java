package com.bludos.optimised.runtime;

import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import java.util.ArrayDeque;
import java.util.Queue;

public class BldoChunkQueue {

    private static final Queue<ChunkRenderDispatcher.RenderChunk> queue = new ArrayDeque<>();

    public static void enqueue(ChunkRenderDispatcher.RenderChunk chunk) {
        if (!queue.contains(chunk)) {
            queue.add(chunk);
        }
    }

    public static ChunkRenderDispatcher.RenderChunk poll() {
        return queue.poll();
    }
}