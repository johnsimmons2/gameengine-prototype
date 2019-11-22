package graphics.shaders.impl;

import graphics.shaders.ShaderProgram;

public class TexturedShader extends ShaderProgram {

    /**
     * TODO: Could just make it one final static string and have it cleverly nab .vert and .frag conditional on
     *          the graphics.shader files having same name...
     */
    public static final String VERTEX_SRC = "src/main/resources/shaders/static.vert";
    public static final String FRAGMENT_SRC = "src/main/resources/shaders/static.frag";

    public TexturedShader() {
        super(VERTEX_SRC, FRAGMENT_SRC);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texcoord");
    }
}
