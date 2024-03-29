package main;

import graphics.Renderer;
import graphics.shaders.ShaderProgram;
import input.InputHandler;
import input.InputReader;
import level.Level;
import math.Vector3f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 *  It is necessary to have the following run configurations:
 *      VM: -XstartOnFirstThread -Djava.awt.headless=true
 *  The former allows Textures to render via ImageIO.read() without hanging
 *
 * TODO:
 *
 *
 * TODO: (notes)
 *      Currently it works on Windows, but on Mac it freezes up when rendering. Almost as if loading the graphics.textures
 *          is synchronous
 */
public class Game {

    private InputHandler inputHandler;
    private InputReader inputReader;
    private Renderer renderer;
    private Level level;

    private long time;
    private float deltaTime;

    private static final int WIDTH = 1020;
    private static final int HEIGHT = 780;

    private boolean running = false;
    private long window;

    private Game() {
        inputReader = new InputReader();
        inputHandler = new InputHandler();
    }

    private void initLevel() {
        renderer = new Renderer();
        level = new Level();
    }

    private void initWindow() throws IllegalStateException {
        if (glfwInit() != (GL_TRUE == 1)) {
            System.err.println("Failed to initialize GLFW"); //TODO: Log
            throw new IllegalStateException("Failed to initialize glfw");
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        window = glfwCreateWindow(WIDTH, HEIGHT, "Title", NULL, NULL);
        if (window == NULL) {
            System.err.println("Could not create GLFW Window");
            throw new IllegalStateException("Failed to create the window");
        }
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwSetKeyCallback(window, inputReader);
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, WIDTH/2, HEIGHT/4);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glfwShowWindow(window);
        deltaTime = 0f;
        time = System.currentTimeMillis();
    }

    public void terminate() {
        running = false;
    }

    private void update() {
        glfwPollEvents();
        inputHandler.handle(this);
    }

    public void stop() {
        if (running)
            running = false;
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void run() {
        deltaTime = System.currentTimeMillis() - time;
        float deltaTime2 = 0f;
        float deltaTime3 = 0f;
        running = true;
        renderer = new Renderer();
        boolean flag = true;
        int count = 0;
        while(running) {
            System.out.println(deltaTime2);
            deltaTime3 += deltaTime / 1000000;
            if (flag) {
                deltaTime2 += deltaTime / 5000;
                if (deltaTime2 > 3)
                    flag = false;
            } else {
                deltaTime2 -= deltaTime / 5000;
                if (deltaTime2 <= 1)
                    flag = true;
            }
            if (glfwWindowShouldClose(window) == (GL_TRUE == 1)) {
                terminate();
            }
            level.getShader().setUniform3f("offset", new Vector3f(deltaTime3, deltaTime3, 0));
            level.getShader().setUniform1f("time", deltaTime2);
            update();
            render();
        }
        stop();
        time = System.currentTimeMillis();
    }

    private void render() {
        int i = glGetError();
        if (i != GL_NO_ERROR) {
            System.err.println(i + " GL ERROR");
            terminate();
        }
        renderer.prepare();
        renderer.render(level);
        glfwSwapBuffers(window);
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.initWindow();
        game.initLevel();
        game.run();
    }

}
