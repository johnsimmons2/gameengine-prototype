package graphics;

import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import level.Level;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;


public class Renderer {

    private float color = 1;
    private float time = 0f;

    public void prepare() {
        time += 0.01f;
        color = (float) Math.sin(time);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(color, 0, 0, 1);
    }

    public void render(Level lvl) {
        //lvl.getShader().enable();
        //render(lvl.getTexturedGeometry());
//        glEnableVertexAttribArray(0);
//        glBindVertexArray(lvl.getTexturedGeometry().getGeometry().getVaoID());
//        glDrawElements(GL_TRIANGLES, lvl.getTexturedGeometry().getGeometry().getVertexCount(), GL_UNSIGNED_INT, 0);
//        glDisableVertexAttribArray(0);
//        glBindVertexArray(0);
        //lvl.getShader().disable();
        glBindVertexArray(lvl.getGeometry().getVaoID());
        lvl.getShader().enable();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, lvl.getGeometry().getIboID());
        glDrawElements(GL_TRIANGLES, lvl.getIndicesCount(), GL_UNSIGNED_INT, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        lvl.getShader().disable();
        glBindVertexArray(0);
    }

    public void render(TexturedGeometry tg) {
        Geometry geometry = tg.getGeometry();
        glBindVertexArray(geometry.getVaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, tg.getTexture().getID());
        glDrawElements(GL_TRIANGLES, geometry.getVertexCount(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

}
