package main;

import input.InputHandler;
import input.InputReader;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private InputHandler inputHandler;
    private InputReader inputReader;

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

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
        if (glfwInit() != (GL_TRUE == 1)) {
            System.err.println("Failed to initialize GLFW"); //TODO: Log
            throw new IllegalStateException("Failed to initialize glfw");
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
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
        glfwSetWindowPos(window, vidmode.width(),
                0);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_1D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void terminate() {
        running = false;
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);
    }

    private void update() {
        glfwPollEvents();
        inputHandler.handle(this);
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
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void main(String[] args) {
       Game game = new Game();
       game.run();
    }

}
