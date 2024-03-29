package graphics.shaders.impl;

import graphics.shaders.ShaderProgram;

public class StaticShader extends ShaderProgram {

    public static final String VERTEX = "src/main/resources/shaders/static.vert";
    public static final String FRAGMENT = "src/main/resources/shaders/static.frag";

    public StaticShader() {
        super(VERTEX, FRAGMENT);
    }

    @Override
    protected void getAllUniformLocations() {

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
