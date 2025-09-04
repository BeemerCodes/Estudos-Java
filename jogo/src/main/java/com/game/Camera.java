package com.game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

public class Camera {

    private final Vector3f position;
    private Vector3f front;
    private final Vector3f up;

    private float yaw = -90.0f;
    private float pitch = 0.0f;

    private final float sensitivity = 0.1f;
    
    private double lastX = 1280.0 / 2.0;
    private double lastY = 720.0 / 2.0;
    private boolean firstMouse = true;

    public Camera() {
        this.position = new Vector3f(0,0,0);
        this.front = new Vector3f(0.0f, 0.0f, -1.0f);
        this.up = new Vector3f(0.0f, 1.0f, 0.0f);
    }

    public void setPosition(float x, float y, float z) {
        this.position.set(x,y,z);
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getDirection() {
        return front;
    }
    
    public Matrix4f getViewMatrix() {
        return new Matrix4f().lookAt(position, new Vector3f(position).add(front), up);
    }
    
    public void getMovementDirection(Vector3f moveDir) {
        moveDir.set(0,0,0);
        long window = glfwGetCurrentContext();

        Vector3f forward = new Vector3f(front).normalize();
        forward.y = 0;

        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) moveDir.add(forward);
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) moveDir.sub(forward);
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) moveDir.sub(new Vector3f(forward).cross(up).normalize());
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) moveDir.add(new Vector3f(forward).cross(up).normalize());
        
        if (moveDir.lengthSquared() > 0) {
            moveDir.normalize();
        }
    }
    
    public void processMouseMovement(double xpos, double ypos) {
        if (firstMouse) {
            lastX = xpos;
            lastY = ypos;
            firstMouse = false;
        }

        double xoffset = (xpos - lastX) * sensitivity;
        double yoffset = (lastY - ypos) * sensitivity;
        lastX = xpos;
        lastY = ypos;

        yaw += xoffset;
        pitch += yoffset;

        if (pitch > 89.0f) pitch = 89.0f;
        if (pitch < -89.0f) pitch = -89.0f;
        
        Vector3f direction = new Vector3f();
        direction.x = (float) (cos(toRadians(yaw)) * cos(toRadians(pitch)));
        direction.y = (float) (sin(toRadians(pitch)));
        direction.z = (float) (sin(toRadians(yaw)) * cos(toRadians(pitch)));
        front = direction.normalize();
    }
}