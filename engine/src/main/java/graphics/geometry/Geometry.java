package graphics.geometry;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Geometry {

    private float[] vertices;
    private int[] indices;

    private int vaoID, vboID, iboID;
    private int vertexCount;

    public Geometry(int vaoID, int vboID, int iboID, float[] vertices, int[] indices) {
        this.vaoID = vaoID;
        this.iboID = iboID;
        this.vboID = vboID;
        this.vertices = vertices;
        this.indices = indices;
    }

    public int[] getIndices() {
        return indices;
    }

    public float[] getVertices() {
        return vertices;
    }

    /**
     * @return The ID of the VAO which contains the data about all the geometry
     *         of this model.
     */
    public int getVaoID() {
        return vaoID;
    }

    public int getVboID() {
        return vboID;
    }

    public int getIboID() {
        return iboID;
    }

    /**
     * @return The number of vertices in the model.
     */
    public int getVertexCount() {
        return vertexCount;
    }

}