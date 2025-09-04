package com.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

import com.game.world.BlockType;
import com.game.world.World;

public class Player {
    private static final float GRAVITY = -25.0f; 
    private static final float JUMP_POWER = 9.0f;   
    private static final float MOVE_SPEED = 5.0f;
    private static final float DEATH_PLANE_Y = -10.0f;
    
    private static final float PLAYER_WIDTH = 0.6f;  
    private static final float PLAYER_HEIGHT = 1.8f; 
    private static final float CAMERA_HEIGHT = 1.62f; 

    private final Camera camera;
    private final Vector3f position;
    private final Vector3f velocity;
    private final Vector3f spawnPoint;
    private final Vector3f halfExtents; 
    private boolean onGround = false;

    public Player(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
        this.spawnPoint = new Vector3f(x, y, z);
        this.velocity = new Vector3f(0, 0, 0);
        this.camera = new Camera();
        this.halfExtents = new Vector3f(PLAYER_WIDTH / 2.0f, PLAYER_HEIGHT / 2.0f, PLAYER_WIDTH / 2.0f);
        updateCamera();
    }

    public Camera getCamera() {
        return camera;
    }

    public Matrix4f getViewMatrix() {
        return camera.getViewMatrix();
    }

    public void processMouseMovement(double xpos, double ypos) {
        camera.processMouseMovement(xpos, ypos);
    }

    public void processKeyboard(long window) {
        Vector3f moveDir = new Vector3f();
        camera.getMovementDirection(moveDir);

        velocity.x = moveDir.x * MOVE_SPEED;
        velocity.z = moveDir.z * MOVE_SPEED;

        if (onGround && glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS) {
            velocity.y = JUMP_POWER;
            onGround = false; 
        }
    }

    public void update(float deltaTime, World world) {
        velocity.y += GRAVITY * deltaTime;

        if (velocity.y < -50.0f) { 
            velocity.y = -50.0f;
        }

        float dx = velocity.x * deltaTime;
        float dy = velocity.y * deltaTime;
        float dz = velocity.z * deltaTime;

        onGround = false;
        
        moveAndCollide(world, 0, dy, 0); 
        moveAndCollide(world, dx, 0, 0); 
        moveAndCollide(world, 0, 0, dz); 

        if (position.y < DEATH_PLANE_Y) {
            respawn();
        }
        updateCamera();
    }

    private void respawn() {
        this.position.set(spawnPoint);
        this.velocity.set(0, 0, 0);
        this.onGround = true; 
    }

    private void updateCamera() {
        camera.setPosition(position.x, position.y + CAMERA_HEIGHT, position.z);
    }

    private void moveAndCollide(World world, float dx, float dy, float dz) {
        Vector3f desiredPosition = new Vector3f(position).add(dx, dy, dz);

        float playerMinX = desiredPosition.x - halfExtents.x;
        float playerMaxX = desiredPosition.x + halfExtents.x;
        float playerMinY = desiredPosition.y; 
        float playerMaxY = desiredPosition.y + halfExtents.y * 2.0f; 

        float playerMinZ = desiredPosition.z - halfExtents.z;
        float playerMaxZ = desiredPosition.z + halfExtents.z;

        int startX = (int) Math.floor(playerMinX);
        int endX = (int) Math.floor(playerMaxX);
        int startY = (int) Math.floor(playerMinY);
        int endY = (int) Math.floor(playerMaxY);
        int startZ = (int) Math.floor(playerMinZ);
        int endZ = (int) Math.floor(playerMaxZ);

        Vector3f collisionResolution = new Vector3f(dx, dy, dz); 
        
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                for (int z = startZ; z <= endZ; z++) {
                    if (world.getBlock(x, y, z) == BlockType.AIR) {
                        continue;
                    }

                    float blockMinX = x; float blockMaxX = x + 1;
                    float blockMinY = y; float blockMaxY = y + 1;
                    float blockMinZ = z; float blockMaxZ = z + 1;

                    if (playerMaxX > blockMinX && playerMinX < blockMaxX &&
                        playerMaxY > blockMinY && playerMinY < blockMaxY &&
                        playerMaxZ > blockMinZ && playerMinZ < blockMaxZ) 
                    {
                        if (dy != 0) { 
                            if (dy < 0) { 
                                desiredPosition.y = blockMaxY; 
                                onGround = true;
                            } else {
                                desiredPosition.y = blockMinY - (halfExtents.y * 2.0f); 
                            }
                            velocity.y = 0; 
                            collisionResolution.y = desiredPosition.y - position.y; 
                        }
                        
                        if (dx != 0) { 
                            if (dx < 0) { 
                                desiredPosition.x = blockMaxX + halfExtents.x; 
                            } else { 
                                desiredPosition.x = blockMinX - halfExtents.x; 
                            }
                            velocity.x = 0; 
                            collisionResolution.x = desiredPosition.x - position.x;
                        }
                        
                        if (dz != 0) { 
                            if (dz < 0) { 
                                desiredPosition.z = blockMaxZ + halfExtents.z; 
                            } else { 
                                desiredPosition.z = blockMinZ - halfExtents.z; 
                            }
                            velocity.z = 0;
                            collisionResolution.z = desiredPosition.z - position.z; 
                        }
                    }
                }
            }
        }
        position.add(collisionResolution);
    }
}