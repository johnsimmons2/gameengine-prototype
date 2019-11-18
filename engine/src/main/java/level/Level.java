package level;

import graphics.IRenderable;
import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;

public class Level implements IRenderable {

    private VertexArray background;
    private Texture backgroundTexture;

    public Level() {
        float[] vertices = new float[] {
            -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
            -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
            0.0f, 10.0f * 9.0f / 16.0f, 0.0f,
            0.0f, -10.0f * 9.0f / 16.0f, 0.0f
        };

        byte[] indices = new byte[] {
            0, 1, 2,
            2, 3, 0
        };

        float[] tcs = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };

        background = new VertexArray(vertices, indices, tcs);
        backgroundTexture = new Texture("resources/bg.png");
    }

    public void render() {
        backgroundTexture.bind();
        Shader.BACKGROUND.enable();
        background.render();
        Shader.BACKGROUND.disable();
        backgroundTexture.unBind();
    }

}
