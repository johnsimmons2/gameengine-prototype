package graphics;

import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import level.Level;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class Renderer {

    private float color = 1;
    private float time = 0f;

    public void prepare() {
        //glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT); //| GL_DEPTH_BUFFER_BIT);
        glClearColor(0.2f, 0.38f, 0.31f, 1);
       // glEnable(GL_VERTEX_PROGRAM_POINT_SIZE);
    }

    public void renderMesh(Mesh mesh) {
        GL30.glBindVertexArray(mesh.getVAO());
        glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void render(Level lvl) {
//        lvl.getShader().enable();
        GL30.glBindVertexArray(lvl.getGeometry().getVaoID());
        glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, lvl.getGeometry().getIboID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, lvl.getGeometry().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);

//        lvl.getShader().disable();
    }

}
