package graphics.geometry;

public class Geometry {

    private int vaoID, vboID, iboID;
    private int vertexCount;

    public Geometry(int vaoID, int vboID, int iboID, int vertexCount) {
        this.vaoID = vaoID;
        this.iboID = iboID;
        this.vboID = vboID;
        this.vertexCount = vertexCount;
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