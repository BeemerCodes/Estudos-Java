package com.game;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_LINES;
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
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Crosshair {
    private int vaoId;
    private int vboId;

    public Crosshair(float size) {
        float[] vertices = {
            // Linha Horizontal
            -size, 0.0f,
             size, 0.0f,
            // Linha Vertical
            0.0f, -size,
            0.0f,  size
        };

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void draw() {
        // Usa um shader simples (não fornecido, usa o shader padrão ou um específico para GUI)
        // Para simplicidade, vamos assumir que um shader que desenha em branco está ativo.
        // O ideal é ter um shader específico para a GUI.
        
        glDisable(GL_DEPTH_TEST); // Desenha por cima de tudo
        
        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);
        
        // Define a cor da mira aqui, se o shader permitir (ex: via uniform)
        // Por simplicidade, vamos desenhar como linhas
        glLineWidth(2.0f); // Define a espessura da linha
        glDrawArrays(GL_LINES, 0, 4);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        
        glEnable(GL_DEPTH_TEST); // Reabilita o teste de profundidade para a cena 3D
    }

    public void cleanup() {
        glDeleteBuffers(vboId);
        glDeleteVertexArrays(vaoId);
    }
}