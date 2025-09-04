package com.game.world;

import org.joml.Vector3i;

public class RaycastResult {
    public final Vector3i block;
    public final Vector3i adjacent;

    public RaycastResult(Vector3i block, Vector3i adjacent) {
        this.block = block;
        this.adjacent = adjacent;
    }
}