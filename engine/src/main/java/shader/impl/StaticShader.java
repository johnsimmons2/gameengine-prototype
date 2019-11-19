package shader.impl;

import shader.ShaderProgram;

public class StaticShader extends ShaderProgram {

    /**
     * TODO: Could just make it one final static string and have it cleverly nab .vert and .frag conditional on
     *          the shader files having same name...
     */
    public static final String VERTEX_SRC = "src/main/resources/shaders/bg.vert";
    public static final String FRAGMENT_SRC = "src/main/resources/shaders/bg.frag";

    public StaticShader() {
        super(VERTEX_SRC, FRAGMENT_SRC);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
