package level;

import graphics.GraphicsLoader;
import graphics.IRenderable;
import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import graphics.textures.Texture;
import graphics.VertexArray;
import graphics.shaders.ShaderProgram;

public class Level implements IRenderable {

    private VertexArray background;
    private Texture backgroundTexture;
    private TexturedGeometry tg;
    private ShaderProgram shader;

    public Level(ShaderProgram shader) {
        this.shader = shader;
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

        GraphicsLoader loader = new GraphicsLoader();
        Geometry geometry = loader.loadToVAO(vertices, tcs, indices);
        backgroundTexture = new Texture("src/main/resources/bg.png");
        tg = new TexturedGeometry(geometry, backgroundTexture);

        //background = new VertexArray(vertices, indices, tcs);
    }

    public void render() {
        backgroundTexture.bind();
        shader.enable();
        background.render();
        shader.disable();
        backgroundTexture.unBind();
    }

    public TexturedGeometry getTexturedGeometry() {
        return this.tg;
    }

}
