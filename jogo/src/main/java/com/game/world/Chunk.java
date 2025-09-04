package com.game.world;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import org.lwjgl.system.MemoryUtil;

public class Chunk {
    public static final int CHUNK_WIDTH = 16;
    public static final int CHUNK_HEIGHT = 256;
    
    private final BlockType[][][] blocks;
    private int vaoId;
    private int vboId;
    private int vertexCount;

    public Chunk() {
        blocks = new BlockType[CHUNK_WIDTH][CHUNK_HEIGHT][CHUNK_WIDTH];
        generateTerrain();
        buildMesh();
    }

    public void setBlock(int x, int y, int z, BlockType type) {
        if (x < 0 || x >= CHUNK_WIDTH || y < 0 || y >= CHUNK_HEIGHT || z < 0 || z >= CHUNK_WIDTH) {
            return;
        }
        if (blocks[x][y][z] == type) {
            return;
        }
        blocks[x][y][z] = type;
        buildMesh();
    }

    public BlockType getBlock(int x, int y, int z) {
        if (x < 0 || x >= CHUNK_WIDTH || y < 0 || y >= CHUNK_HEIGHT || z < 0 || z >= CHUNK_WIDTH) {
            return BlockType.AIR;
        }
        return blocks[x][y][z];
    }
    
    private void generateTerrain() {
        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int z = 0; z < CHUNK_WIDTH; z++) {
                for (int y = 0; y < CHUNK_HEIGHT; y++) {
                    if (y < 60) {
                        blocks[x][y][z] = BlockType.STONE;
                    } else if (y < 63) {
                        blocks[x][y][z] = BlockType.DIRT;
                    } else if (y == 63) {
                        blocks[x][y][z] = BlockType.GRASS;
                    } else {
                        blocks[x][y][z] = BlockType.AIR;
                    }
                }
            }
        }
    }

    private boolean isBlockActive(int x, int y, int z) {
        // Assume que blocos fora dos limites do chunk são AR para não renderizar faces internas
        if (x < 0 || x >= CHUNK_WIDTH || y < 0 || y >= CHUNK_HEIGHT || z < 0 || z >= CHUNK_WIDTH) {
            return false;
        }
        return blocks[x][y][z] != BlockType.AIR;
    }
    
    public void buildMesh() {
        if (vaoId != 0) {
            glDeleteBuffers(vboId);
            glDeleteVertexArrays(vaoId);
        }
        
        List<Float> vertices = new ArrayList<>();
        for (int x = 0; x < CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                for (int z = 0; z < CHUNK_WIDTH; z++) {
                    if (!isBlockActive(x, y, z)) {
                        continue;
                    }
                    // Adiciona faces apenas se o bloco adjacente for AR (ou fora dos limites)
                    if (!isBlockActive(x, y + 1, z)) { addFaceVertices(x, y, z, "top", vertices); }
                    if (!isBlockActive(x, y - 1, z)) { addFaceVertices(x, y, z, "bottom", vertices); }
                    if (!isBlockActive(x, y, z + 1)) { addFaceVertices(x, y, z, "front", vertices); }
                    if (!isBlockActive(x, y, z - 1)) { addFaceVertices(x, y, z, "back", vertices); }
                    if (!isBlockActive(x + 1, y, z)) { addFaceVertices(x, y, z, "right", vertices); }
                    if (!isBlockActive(x - 1, y, z)) { addFaceVertices(x, y, z, "left", vertices); }
                }
            }
        }
        
        if (vertices.isEmpty()) {
            vertexCount = 0;
            return;
        }

        vertexCount = vertices.size() / 5; // 3 posições + 2 UVs
        float[] vertexArray = new float[vertices.size()];
        for(int i = 0; i < vertices.size(); i++) vertexArray[i] = vertices.get(i);

        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(vertexBuffer);

        // Atributo 0: Posição (3 floats)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        // Atributo 1: Coordenadas de Textura (2 floats)
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    private void addFaceVertices(float x, float y, float z, String face, List<Float> vertices) {
        // Posições dos vértices do bloco (-0.5 a +0.5 para centralizar)
        float x0 = x - 0.5f; float x1 = x + 0.5f;
        float y0 = y - 0.5f; float y1 = y + 0.5f;
        float z0 = z - 0.5f; float z1 = z + 0.5f;

        // Todas as faces agora têm a ordem dos vértices consistentemente no sentido anti-horário (CCW)
        // para que o back-face culling funcione corretamente.
        float[] faceVertices = switch (face) {
            case "top" -> new float[]{ // +Y face
                x0, y1, z1, 0.0f, 0.0f, // Triângulo 1
                x1, y1, z1, 1.0f, 0.0f,
                x1, y1, z0, 1.0f, 1.0f,
                x1, y1, z0, 1.0f, 1.0f, // Triângulo 2
                x0, y1, z0, 0.0f, 1.0f,
                x0, y1, z1, 0.0f, 0.0f
            };
            case "bottom" -> new float[]{ // -Y face
                x0, y0, z0, 0.0f, 1.0f, // Triângulo 1
                x1, y0, z0, 1.0f, 1.0f,
                x1, y0, z1, 1.0f, 0.0f,
                x1, y0, z1, 1.0f, 0.0f, // Triângulo 2
                x0, y0, z1, 0.0f, 0.0f,
                x0, y0, z0, 0.0f, 1.0f
            };
            case "front" -> new float[]{ // +Z face
                x0, y0, z1, 0.0f, 0.0f, // Triângulo 1
                x1, y0, z1, 1.0f, 0.0f,
                x1, y1, z1, 1.0f, 1.0f,
                x1, y1, z1, 1.0f, 1.0f, // Triângulo 2
                x0, y1, z1, 0.0f, 1.0f,
                x0, y0, z1, 0.0f, 0.0f
            };
            case "back" -> new float[]{ // -Z face
                x1, y0, z0, 1.0f, 0.0f, // Triângulo 1
                x0, y0, z0, 0.0f, 0.0f,
                x0, y1, z0, 0.0f, 1.0f,
                x0, y1, z0, 0.0f, 1.0f, // Triângulo 2
                x1, y1, z0, 1.0f, 1.0f,
                x1, y0, z0, 1.0f, 0.0f
            };
            case "right" -> new float[]{ // +X face
                x1, y0, z1, 1.0f, 0.0f, // Triângulo 1
                x1, y0, z0, 0.0f, 0.0f,
                x1, y1, z0, 0.0f, 1.0f,
                x1, y1, z0, 0.0f, 1.0f, // Triângulo 2
                x1, y1, z1, 1.0f, 1.0f,
                x1, y0, z1, 1.0f, 0.0f
            };
            case "left" -> new float[]{ // -X face
                x0, y0, z0, 0.0f, 0.0f, // Triângulo 1
                x0, y0, z1, 1.0f, 0.0f,
                x0, y1, z1, 1.0f, 1.0f,
                x0, y1, z1, 1.0f, 1.0f, // Triângulo 2
                x0, y1, z0, 0.0f, 1.0f,
                x0, y0, z0, 0.0f, 0.0f
            };
            default -> new float[0];
        };
        for(float v : faceVertices) vertices.add(v);
    }
    
    public void draw() {
        if (vertexCount > 0) {
            glBindVertexArray(vaoId);
            glDrawArrays(GL_TRIANGLES, 0, vertexCount);
            glBindVertexArray(0);
        }
    }
    
    public void cleanup() {
        if (vaoId != 0) {
            glDeleteBuffers(vboId);
            glDeleteVertexArrays(vaoId);
        }
    }
}