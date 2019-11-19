package graphics;

import graphics.geometry.Geometry;
import graphics.geometry.TexturedGeometry;
import level.Level;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;


public class Renderer {

    public void prepare() {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(1, 0, 0, 1);
    }

    public void render(Level lvl) {
        render(lvl.getTexturedGeometry());
    }

    public void render(TexturedGeometry tg) {
        Geometry geometry = tg.getGeometry();
        glBindVertexArray(geometry.getVaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, tg.getTexture().getTextureID());
        glDrawElements(GL_TRIANGLES, geometry.getVertexCount(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

}
