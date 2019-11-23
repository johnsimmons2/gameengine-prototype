package graphics.shaders;

import math.Matrix4f;
import math.Vector2f;
import math.Vector3f;
import utils.FileUtils;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * When ID is -1, invalidate graphics.shader
 */
public abstract class ShaderProgram {

    public static final int VERTEX_ATTRIBUTE = 0;
    public static final int TEXTURE_COORDINATES_ATTRIBUTE = 1;

    private Map<String, Integer> locationCache = new HashMap<>();

    private int programID;
    private int vertexID;
    private int fragmentID;
    private boolean enabled = false;

    public ShaderProgram(String vertex, String fragment) {
        vertexID = loadShader(vertex, GL_VERTEX_SHADER);
        fragmentID = loadShader(fragment, GL_FRAGMENT_SHADER);
        programID = glCreateProgram();
        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);
        bindAttributes();
        glLinkProgram(programID);
        glValidateProgram(programID);
        getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();

    private static int loadShader(String source, int type) {
        if (type != GL_VERTEX_SHADER && type != GL_FRAGMENT_SHADER) {
            System.err.println("Incorrect type passed to loadShader(source, type)!");
        }
        String shader = FileUtils.loadAsString(source);
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shader);
        glCompileShader(shaderID);
        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile graphics.shader " + shaderID + ", " + source + ". Terminating proceess.");
            System.exit(-1);
        }
        System.out.println("Compiled shader with shader ID: " + shaderID);
        return shaderID;
    }

    protected abstract void bindAttributes();

    public void enable() {
        System.out.println("Enabled shader with vertex ID " + vertexID + " and frag ID " + fragmentID);
        glUseProgram(programID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

    protected void bindAttribute(int attribute, String variable) {
        glBindAttribLocation(programID, attribute, variable);
    }

    public void cleanUp() {
        disable();
        glDetachShader(programID, vertexID);
        glDetachShader(programID, fragmentID);
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        glDeleteShader(programID);
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }
        int result = glGetUniformLocation(programID, name);
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

}
