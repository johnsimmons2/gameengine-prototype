package graphics.geometry;

import graphics.textures.Texture;

public class TexturedGeometry {

    private Geometry geometry;
    private Texture texture;

    public TexturedGeometry(Geometry geometry, Texture texture) {
        this.geometry = geometry;
        this.texture = texture;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public Texture getTexture() {
        return texture;
    }
}
