package graphics.textures;

import utils.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int width, height;
    private int textureID;

    public Texture(BufferedImage image) {
        textureID = load(image);
    }

    public Texture(String path) {
        textureID = load(getBufferedImageFromPath(path));
    }

    private int load(BufferedImage image) {
        width = image.getWidth();
        height = image.getHeight();
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);
            // Reorder bits for opengl
            data[i] = a << 24 | b << 16 | g << 8 | r;
        }
        int result = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, result);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
        glBindTexture(GL_TEXTURE_2D, 0);
        return result;
    }

    private BufferedImage getBufferedImageFromPath(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureID);
    }

    public void unBind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getTextureID() {
        return textureID;
    }
}
