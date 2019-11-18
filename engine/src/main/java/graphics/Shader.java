package graphics;

import math.Matrix4f;
import math.Vector2f;
import math.Vector3f;
import utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * When ID is -1, invalidate shader
 */
public class Shader {

    public static Shader BACKGROUND;

    public static final int VERTEX_ATTRIBUTE = 0;
    public static final int TEXTURE_COORDINATES_ATTRIBUTE = 1;

    private Map<String, Integer> locationCache = new HashMap<>();

    private final int ID;
    private boolean enabled = false;

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }

    public static void loadAll() {
        BACKGROUND = new Shader("shaders/bg.vert", "shaders/bg.frag");
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }
        int result = glGetUniformLocation(ID, name);
        if (result == -1) {
            System.err.println("Could not find uniform variable" + name);
        } else {
            locationCache.put(name, result);
        }
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2i(String name, int x, int y) {
        if (!enabled) enable();
        glUniform2i(getUniform(name), x, y);
    }

    public void setUniform2i(String name, Vector2f vec) {
        if (!enabled) enable();
        glUniform2i(getUniform(name), (int) vec.x, (int) vec.y);
    }

    public void setUniform2f(String name, Vector2f vec) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), vec.x, vec.y);
    }

    public void setUniform3f(String name, Vector3f vec) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), vec.x, vec.y, vec.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) enable();
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

}
