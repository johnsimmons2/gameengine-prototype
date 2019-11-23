package level;

import graphics.GraphicsLoader;
import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import graphics.shaders.impl.StaticShader;
import graphics.shaders.impl.TexturedShader;
import graphics.textures.Texture;
import graphics.shaders.ShaderProgram;

public class Level {

    private Texture backgroundTexture;
    private TexturedGeometry tg;
    private ShaderProgram shader;
    private Geometry geom;
    private GraphicsLoader loader;

    private int indicesCount;

    public Level() {
        float[] vertices = new float[] {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0
        };

        int[] indices = new int[] {
            0, 1, 2,
            0, 3, 2
        };

        float[] tcs = new float[] {
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };

        loader = new GraphicsLoader();
        geom = loader.loadToVAO(vertices, indices, tcs);
        backgroundTexture = new Texture("src/main/resources/bg.png");

        tg = new TexturedGeometry(geom, backgroundTexture);

        this.shader = new TexturedShader();
        this.indicesCount = indices.length;
    }

    public ShaderProgram getShader() {
        return this.shader;
    }

    public int getIndicesCount() {
        return indicesCount;
    }

    public Geometry getGeometry() {
        return this.geom;
    }

    public TexturedGeometry getTexturedGeometry() {
        return this.tg;
    }

}
