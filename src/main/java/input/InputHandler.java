package input;

import main.Game;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {

    public void handle(Game game) {

        if (InputReader.keys[GLFW_KEY_SPACE]) {
            System.out.println("Space pressed");
        } else if (InputReader.keys[GLFW_KEY_ESCAPE]) {
            System.out.println("Escape pressed");
            game.terminate();
        }

    }
}
