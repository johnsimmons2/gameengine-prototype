package math;

public class Vector3f {

    /**
     * 'z' is depth test
     */
    public float x, y, z;

    public Vector3f() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    /**
     * Create a Vector3f object
     * @param x x distance
     * @param y y distance
     * @param z render depth
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
