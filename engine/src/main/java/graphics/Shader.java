package graphics;

import utils.ShaderUtils;
import static org.lwjgl.opengl.GL20.*;

/**
 * When ID is -1, invalidate shader
 */
public class Shader {

    private final int ID;

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }

    public int getUniform(String name) {
        return glGetUniformLocation(ID, name);
    }

    public void setUniform(String name, int value) {
        glUniform1i(getUniform(name), value);
    }

    public void enable() {
        glUseProgram(ID);
    }

    public void disable() {
        glUseProgram(0);
    }

}
