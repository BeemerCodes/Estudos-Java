package com.game.world;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import com.game.rendering.Shader;

public class World {
    private final Chunk chunk;

    public World() {
        chunk = new Chunk();
    }

    public BlockType getBlock(int x, int y, int z) {
        if (x >= 0 && x < Chunk.CHUNK_WIDTH && y >= 0 && y < Chunk.CHUNK_HEIGHT && z >= 0 && z < Chunk.CHUNK_WIDTH) {
            return chunk.getBlock(x, y, z);
        }
        return BlockType.AIR;
    }

    public void setBlock(int x, int y, int z, BlockType type) {
        if (x >= 0 && x < Chunk.CHUNK_WIDTH && y >= 0 && y < Chunk.CHUNK_HEIGHT && z >= 0 && z < Chunk.CHUNK_WIDTH) {
            chunk.setBlock(x, y, z, type);
        }
    }

    public RaycastResult raycast(Vector3f start, Vector3f direction, float maxDistance) {
        Vector3f currentPos = new Vector3f(start);
        Vector3f step = new Vector3f(direction).normalize().mul(0.05f);

        Vector3i lastAirBlock = new Vector3i();
        
        for (float i = 0; i < maxDistance; i += 0.05f) {
            int x = (int) Math.floor(currentPos.x);
            int y = (int) Math.floor(currentPos.y);
            int z = (int) Math.floor(currentPos.z);
            
            if (getBlock(x, y, z) != BlockType.AIR) {
                return new RaycastResult(new Vector3i(x, y, z), lastAirBlock);
            }
            
            lastAirBlock.set(x,y,z);
            currentPos.add(step);
        }
        return null;
    }
    
    public void draw(Shader shader) {
        Matrix4f model = new Matrix4f().identity();
        shader.setMatrix4f("model", model);
        chunk.draw();
    }
    
    public void cleanup() {
        chunk.cleanup();
    }
}