package main;

import graphics.Renderer;
import graphics.shaders.ShaderProgram;
import graphics.shaders.impl.TexturedShader;
import input.InputHandler;
import input.InputReader;
import level.Level;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

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

    private List<ShaderProgram> loadedShaders;

    private static final int WIDTH = 1020;
    private static final int HEIGHT = 780;

    private boolean running = false;
    private long window;

    private Game() {
        inputReader = new InputReader();
        inputHandler = new InputHandler();
        initWindow();
    }

    public boolean isRunning() {
        return running;
    }

    private void initWindow() throws IllegalStateException {
        loadedShaders = new ArrayList<>();
        renderer = new Renderer();
        if (glfwInit() != (GL_TRUE == 1)) {
            System.err.println("Failed to initialize GLFW"); //TODO: Log
            throw new IllegalStateException("Failed to initialize glfw");
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        /**
         * MacOS Compatibility
         */
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
        glfwShowWindow(window);
        glfwSetKeyCallback(window, inputReader);
        GL.createCapabilities();
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, 0, 0);

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

//        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f,
//                10.0f,
//                -10.0f * 9.0f / 16.0f,
//                10.0f * 9.0f / 16.0f,
//                -1.0f,
//                1.0f);

        float[] vertices = new float[] {
//            -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
//            -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
//            0.0f, 10.0f * 9.0f / 16.0f, 0.0f,
//            0.0f, -10.0f * 9.0f / 16.0f, 0.0f
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0
        };


        float[] tcs = new float[] {
                0, 1,
                0, 1,
                1, 1,
                1, 0
        };

        byte[] indices = new byte[] {
                0, 1, 3,
                3, 1, 2
        };
//
//        GraphicsLoader loader = new GraphicsLoader();
//        Geometry geometry = loader.loadToVAO(vertices, tcs, indices);
//        Texture texture = new Texture("src/main/resources/bg.png");
//        TexturedGeometry tgeom = new TexturedGeometry(geometry, texture);
//        staticShader.enable();
//        staticShader.setUniformMat4f("pr_matrix", pr_matrix);
//        staticShader.setUniform1i("tex", 1);
//        staticShader.disable();
       // loadedShaders.add(staticShader);

        level = new Level();
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
        for (ShaderProgram shader : loadedShaders) {
            shader.cleanUp();
        }
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void run() {
        running = true;
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
//        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        level.render();
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
       game.run();
    }

}
