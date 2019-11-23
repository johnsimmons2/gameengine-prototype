package level;

import graphics.GraphicsLoader;
import graphics.IRenderable;
import graphics.Mesh;
import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import graphics.shaders.impl.StaticShader;
import graphics.textures.Texture;
import graphics.VertexArray;
import graphics.shaders.ShaderProgram;

public class Level {

    private VertexArray background;
    private Texture backgroundTexture;
    private TexturedGeometry tg;
    private ShaderProgram shader;
    private Geometry geom;
    private GraphicsLoader loader;
    private Mesh mesh;

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
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };

        loader = new GraphicsLoader();
        //geom = loader.loadToVAOGeometry(vertices, indices);
        this.geom = new Geometry(vertices, indices);
        mesh = new Mesh(vertices, indices);
        mesh.create();
       // this.shader = new StaticShader();
        this.indicesCount = indices.length;
    }
public Mesh getMesh() {
        return mesh;
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
