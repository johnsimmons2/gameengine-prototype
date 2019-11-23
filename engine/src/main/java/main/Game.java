package main;

import graphics.Mesh;
import graphics.Renderer;
import graphics.shaders.ShaderProgram;
import graphics.shaders.impl.TexturedShader;
import input.InputHandler;
import input.InputReader;
import level.Level;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
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
    private Mesh mesh;

    private List<ShaderProgram> loadedShaders;

    private static final int WIDTH = 1020;
    private static final int HEIGHT = 780;

    private boolean running = false;
    private long window;
    private Window window2;

    private Game() {
        inputReader = new InputReader();
        inputHandler = new InputHandler();
    }

    public boolean isRunning() {
        return running;
    }

    private void initLevel() {
        float[] vertices = new float[] {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0
        };

        int[] indices = new int[] {
                0, 1, 2,
                0, 3, 2
        };

        mesh = new Mesh(vertices, indices);
        mesh.create();
        renderer = new Renderer();
        level = new Level();
    }

    private void initWindow() throws IllegalStateException {
        if (glfwInit() != (GL_TRUE == 1)) {
            System.err.println("Failed to initialize GLFW"); //TODO: Log
            throw new IllegalStateException("Failed to initialize glfw");
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        /**
         * MacOS Compatibility
         */


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
//        for (ShaderProgram shader : loadedShaders) {
  //          shader.cleanUp();
    //    }
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void run() {
        running = true;
        renderer = new Renderer();
        int count = 0;
        while(running) {
            if (glfwWindowShouldClose(window) == (GL_TRUE == 1)) {
                terminate();
            }
            update();
            render();
        }
        stop();
    }

    private void render() {
        renderer.prepare();
//        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        glClearColor(1, 0, 0, 1);
//        level.render();
        int i = glGetError();
        if (i != GL_NO_ERROR) {
            System.err.println(i + " GL ERROR");
            terminate();
        }
        renderer.render(level);
        glfwSwapBuffers(window);
    }

    public void fuck() {
        window2 = new Window(WIDTH, HEIGHT, "Game");
        renderer = new Renderer();
        window2.create();
        float[] vertices = new float[] {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, 0.5f, 0
        };

        int[] indices = new int[] {
                0, 1, 2,
                0, 3, 2
        };

        mesh = new Mesh(vertices, indices);
        mesh.create();
        run();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initWindow();
        game.initLevel();
        game.run();
//        game.fuckYOU();
//        game.initLevel();
//        game.run();
    }

}
