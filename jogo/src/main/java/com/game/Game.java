package com.game;

import org.joml.Matrix4f;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE; // Importações para VBOs
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE; // Importações para Shaders
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT; // Importações para VAOs
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.glfw.GLFWErrorCallback;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLineWidth;
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
import static org.lwjgl.system.MemoryUtil.NULL;

import com.game.rendering.Shader;
import com.game.rendering.Texture;
import com.game.world.BlockType;
import com.game.world.RaycastResult;
import com.game.world.World;

public class Game {

    private long window;
    private Shader blockShader;      // Shader para os blocos
    private Shader crosshairShader;  // Shader para a mira
    private Matrix4f projection;
    private Texture texture;
    private World world;
    private Player player;
    
    private int crosshairVao;
    private int crosshairVbo;

    private float lastFrameTime = 0.0f;
    private float deltaTime = 0.0f;
    
    private boolean leftMouseButtonPressed = false;
    private boolean rightMouseButtonPressed = false;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Não foi possível inicializar o GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // Forçar OpenGL 3.3
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); // Usar Core Profile

        window = glfwCreateWindow(1280, 720, "Quebre e Construa!", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Falha ao criar a janela GLFW");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // Habilita V-Sync
        glfwShowWindow(window);

        createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE); // Habilita o culling para melhor desempenho e correção de faces invisíveis

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPosCallback(window, (win, xpos, ypos) -> {
            if (player != null) player.processMouseMovement(xpos, ypos);
        });
        
        blockShader = new Shader("/shaders/default.vert", "/shaders/default.frag");
        crosshairShader = new Shader("/shaders/crosshair.vert", "/shaders/crosshair.frag"); 
        texture = new Texture("src/main/resources/textures/container.jpg");
        world = new World();
        
        player = new Player(8.0f, 80.0f, 8.0f); // Posição inicial do jogador
        
        // FOV de 70 graus é um bom equilíbrio, mas pode ser ajustado se a sensação de "longe" persistir
        projection = new Matrix4f().perspective((float) Math.toRadians(70.0f), 1280.0f / 720.0f, 0.05f, 1000.0f);

        initCrosshair();
    }

    private void initCrosshair() {
        // Agora a mira é um "X" branco
        float size = 0.015f; // Tamanho do X (metade do comprimento da linha)
        float gap = 0.005f;  // Espaço no centro do X (para criar as "aberturas")
        float[] vertices = { 
            // Linha Horizontal
            -(size + gap), 0.0f, 0.0f, // Esquerda
            -gap,          0.0f, 0.0f, // Esquerda interna
            
             gap,          0.0f, 0.0f, // Direita interna
             (size + gap), 0.0f, 0.0f, // Direita
            
            // Linha Vertical
            0.0f, -(size + gap), 0.0f, // Inferior
            0.0f, -gap,          0.0f, // Inferior interna
            
            0.0f,  gap,          0.0f, // Superior interna
            0.0f,  (size + gap), 0.0f  // Superior
        };
        
        crosshairVao = glGenVertexArrays();
        crosshairVbo = glGenBuffers();
        glBindVertexArray(crosshairVao);
        glBindBuffer(GL_ARRAY_BUFFER, crosshairVbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); // O shader da mira só precisa de posição (3 floats)
        glEnableVertexAttribArray(0); // Habilita o atributo 0
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    private void processInput() {
        if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
            glfwSetWindowShouldClose(window, true);
        }
        player.processKeyboard(window);

        RaycastResult ray = world.raycast(player.getCamera().getPosition(), player.getCamera().getDirection(), 5.0f);

        boolean leftMouse = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS;
        if (leftMouse && !leftMouseButtonPressed && ray != null) {
            world.setBlock(ray.block.x, ray.block.y, ray.block.z, BlockType.AIR);
        }
        leftMouseButtonPressed = leftMouse;

        boolean rightMouse = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_RIGHT) == GLFW_PRESS;
        if (rightMouse && !rightMouseButtonPressed && ray != null) {
            world.setBlock(ray.adjacent.x, ray.adjacent.y, ray.adjacent.z, BlockType.STONE);
        }
        rightMouseButtonPressed = rightMouse;
    }
    
    private void loop() {
        glClearColor(0.5f, 0.8f, 1.0f, 1.0f);

        while (!glfwWindowShouldClose(window)) {
            float currentFrameTime = (float)glfwGetTime();
            deltaTime = currentFrameTime - lastFrameTime;
            lastFrameTime = currentFrameTime;
            
            processInput();
            player.update(deltaTime, world);
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // --- RENDERIZAÇÃO DA CENA 3D ---
            texture.bind();
            blockShader.use(); // Usa o shader dos blocos
            
            Matrix4f view = player.getViewMatrix();
            blockShader.setMatrix4f("projection", projection);
            blockShader.setMatrix4f("view", view);
            blockShader.setMatrix4f("model", new Matrix4f().identity()); // Model matrix para blocos (identidade)
            
            world.draw(blockShader);

            // --- RENDERIZAÇÃO DA MIRA 2D ---
            drawCrosshair();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void drawCrosshair() {
        glDisable(GL_DEPTH_TEST); // Garante que a mira desenhe por cima de tudo
        glDisable(GL_CULL_FACE);  // Desabilita o culling para a mira 2D

        crosshairShader.use(); // Usa o shader da mira

        glBindVertexArray(crosshairVao);
        // O atributo 0 já está habilitado na initCrosshair
        glLineWidth(2.0f); // Espessura da linha da mira (ajustado para melhor visibilidade)
        glDrawArrays(GL_LINES, 0, 8); // Desenha 4 segmentos de linha (8 vértices) para formar o X

        // Não precisa desabilitar o atributo 0, pois ele é gerenciado pelo VAO
        glBindVertexArray(0);

        glEnable(GL_CULL_FACE); // Reabilita o culling para a cena 3D
        glEnable(GL_DEPTH_TEST); // Reabilita o teste de profundidade
    }

    private void cleanup() {
        // --- LIMPEZA DA MIRA ---
        glDeleteBuffers(crosshairVbo);
        glDeleteVertexArrays(crosshairVao);

        glfwSetCursorPosCallback(window, null);
        world.cleanup();
        texture.cleanup();
        blockShader.cleanup();     // Limpa o shader dos blocos
        crosshairShader.cleanup(); // Limpa o shader da mira
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}