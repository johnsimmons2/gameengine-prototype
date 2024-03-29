package input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class InputReader extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[65536];

    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW.GLFW_RELEASE;
    }

    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }

    public String getSignature() {
        return null;
    }

    public void callback(long args) {
        super.callback(args);
    }

    public void close() {
        super.close();
    }
}
