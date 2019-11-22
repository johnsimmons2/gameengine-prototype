package level;

import graphics.GraphicsLoader;
import graphics.IRenderable;
import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import graphics.shaders.impl.StaticShader;
import graphics.textures.Texture;
import graphics.VertexArray;
import graphics.shaders.ShaderProgram;

public class Level implements IRenderable {

    private VertexArray background;
    private Texture backgroundTexture;
    private TexturedGeometry tg;
    private ShaderProgram shader;
    private Geometry geom;

    private int indicesCount;

    public Level() {
        //this.shader = shader;
        float[] vertices = new float[] {
//            -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
//            -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
//            0.0f, 10.0f * 9.0f / 16.0f, 0.0f,
//            0.0f, -10.0f * 9.0f / 16.0f, 0.0f
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0
        };

        byte[] indices = new byte[] {
            0, 1, 3,
            3, 1, 2
        };

        float[] tcs = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };

        GraphicsLoader loader = new GraphicsLoader();
        geom = loader.loadToVAOGeometry(vertices, indices);
        this.shader = new StaticShader();
        this.indicesCount = indices.length;
        //backgroundTexture = new Texture("src/main/resources/bg.png");
        //tg = new TexturedGeometry(geometry, backgroundTexture);

        //background = new VertexArray(vertices, indices, tcs);
    }

    public ShaderProgram getShader() {
        return this.shader;
    }

    public int getIndicesCount() {
        return indicesCount;
    }

    public void render() {
        //backgroundTexture.bind();
//        shader.enable();
        //background.render()

//        shader.disable();
        //backgroundTexture.unBind();
    }

    public Geometry getGeometry() {
        return this.geom;
    }

    public TexturedGeometry getTexturedGeometry() {
        return this.tg;
    }

}
