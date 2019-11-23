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
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.2f, 0.38f, 0.31f, 0.5f);
    }

    public void render(Level lvl) {
        TexturedGeometry tg = lvl.getTexturedGeometry();
        lvl.getShader().enable();
        GL30.glBindVertexArray(tg.getGeometry().getVaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, lvl.getTexturedGeometry().getTexture().getID());
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, tg.getGeometry().getIboID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, tg.getGeometry().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        lvl.getShader().disable();
    }

}
