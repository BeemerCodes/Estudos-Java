package com.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

import com.game.world.BlockType;
import com.game.world.World;

public class Player {
    // --- Constantes de Física e Jogo (AJUSTADAS PARA COMPORTAMENTO DO MINECRAFT) ---
    private static final float GRAVITY = -25.0f; // Gravidade ajustada para uma queda mais realista
    private static final float JUMP_POWER = 9.0f;   // Força do pulo ajustada
    private static final float MOVE_SPEED = 5.0f;
    private static final float DEATH_PLANE_Y = -10.0f;
    
    // Altura e largura do jogador como no Minecraft
    private static final float PLAYER_WIDTH = 0.6f;   // Ligeiramente menor que 1 bloco para passar por frestas
    private static final float PLAYER_HEIGHT = 1.8f;  // Permite passar por vãos de 2 blocos, mas não 1
    private static final float CAMERA_HEIGHT = 1.62f; // Altura dos olhos, aproximadamente no topo do jogador (ligeiramente ajustado)

    private final Camera camera;
    private final Vector3f position;
    private final Vector3f velocity;
    private final Vector3f spawnPoint;
    private final Vector3f halfExtents; // Meia largura para facilitar cálculos de AABB
    private boolean onGround = false;

    public Player(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
        this.spawnPoint = new Vector3f(x, y, z);
        this.velocity = new Vector3f(0, 0, 0);
        this.camera = new Camera();
        // halfExtents é metade da largura para XZ, e a altura total para Y
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
            onGround = false; // Desliga onGround imediatamente para evitar múltiplos saltos
        }
    }

    public void update(float deltaTime, World world) {
        // Aplica a gravidade
        velocity.y += GRAVITY * deltaTime;

        // Limita a velocidade de queda máxima para evitar bugs de colisão em alta velocidade
        if (velocity.y < -50.0f) { // Valor arbitrário, pode ser ajustado
            velocity.y = -50.0f;
        }

        // Calcula o deslocamento desejado para este frame
        float dx = velocity.x * deltaTime;
        float dy = velocity.y * deltaTime;
        float dz = velocity.z * deltaTime;

        // Resetar onGround no início de cada frame antes de resolver a colisão Y
        onGround = false;
        
        // Move e resolve colisões em cada eixo separadamente
        // Ordem Y -> X -> Z é importante para colisões de chão/teto
        // O jogador tem que ter o "bottom" em position.y
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
        this.onGround = true; // Garante que não cai após o respawn
    }

    private void updateCamera() {
        camera.setPosition(position.x, position.y + CAMERA_HEIGHT, position.z);
    }

    private void moveAndCollide(World world, float dx, float dy, float dz) {
        Vector3f desiredPosition = new Vector3f(position).add(dx, dy, dz);

        // AABB do jogador na desiredPosition (note que position.y é a base do jogador)
        float playerMinX = desiredPosition.x - halfExtents.x;
        float playerMaxX = desiredPosition.x + halfExtents.x;
        float playerMinY = desiredPosition.y; 
        float playerMaxY = desiredPosition.y + halfExtents.y * 2.0f; // Multiplica por 2 pois halfExtents.y é metade da altura

        float playerMinZ = desiredPosition.z - halfExtents.z;
        float playerMaxZ = desiredPosition.z + halfExtents.z;

        // Blocos que o jogador pode estar sobrepondo (range expandido para cobertura total)
        int startX = (int) Math.floor(playerMinX);
        int endX = (int) Math.floor(playerMaxX);
        int startY = (int) Math.floor(playerMinY);
        int endY = (int) Math.floor(playerMaxY);
        int startZ = (int) Math.floor(playerMinZ);
        int endZ = (int) Math.floor(playerMaxZ);

        Vector3f collisionResolution = new Vector3f(dx, dy, dz); // Armazena o quanto vamos mover para resolver
        
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                for (int z = startZ; z <= endZ; z++) {
                    if (world.getBlock(x, y, z) == BlockType.AIR) {
                        continue;
                    }

                    // Bounding box do bloco (sempre 1x1x1 unidade)
                    float blockMinX = x; float blockMaxX = x + 1;
                    float blockMinY = y; float blockMaxY = y + 1;
                    float blockMinZ = z; float blockMaxZ = z + 1;

                    // Verifica se há colisão entre o jogador (na desiredPosition) e o bloco
                    if (playerMaxX > blockMinX && playerMinX < blockMaxX &&
                        playerMaxY > blockMinY && playerMinY < blockMaxY &&
                        playerMaxZ > blockMinZ && playerMinZ < blockMaxZ) 
                    {
                        // Colisão detectada, resolve no eixo correspondente
                        if (dy != 0) { // Tentativa de mover no eixo Y
                            if (dy < 0) { // Caindo (colisão com o chão)
                                desiredPosition.y = blockMaxY; // Ajusta a base do jogador para o topo do bloco
                                onGround = true;
                            } else { // Subindo (colisão com o teto)
                                desiredPosition.y = blockMinY - (halfExtents.y * 2.0f); // Ajusta o topo do jogador para a base do bloco
                            }
                            velocity.y = 0; // Zera a velocidade Y
                            collisionResolution.y = desiredPosition.y - position.y; // Ajusta o movimento efetivo de Y
                        }
                        
                        if (dx != 0) { // Tentativa de mover no eixo X
                            if (dx < 0) { // Movendo para esquerda
                                desiredPosition.x = blockMaxX + halfExtents.x; // Ajusta o lado esquerdo do jogador para o lado direito do bloco
                            } else { // Movendo para direita
                                desiredPosition.x = blockMinX - halfExtents.x; // Ajusta o lado direito do jogador para o lado esquerdo do bloco
                            }
                            velocity.x = 0; // Zera a velocidade X
                            collisionResolution.x = desiredPosition.x - position.x; // Ajusta o movimento efetivo de X
                        }
                        
                        if (dz != 0) { // Tentativa de mover no eixo Z
                            if (dz < 0) { // Movendo para trás
                                desiredPosition.z = blockMaxZ + halfExtents.z; // Ajusta a frente do jogador para trás do bloco
                            } else { // Movendo para frente
                                desiredPosition.z = blockMinZ - halfExtents.z; // Ajusta a parte de trás do jogador para a frente do bloco
                            }
                            velocity.z = 0; // Zera a velocidade Z
                            collisionResolution.z = desiredPosition.z - position.z; // Ajusta o movimento efetivo de Z
                        }
                    }
                }
            }
        }
        // Aplica o movimento final corrigido à posição do jogador
        position.add(collisionResolution);
    }
}