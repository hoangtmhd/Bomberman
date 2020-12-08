package entities;

import app.management.map.MapManagement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity extends Sprite {
    protected boolean removed;

    protected Rectangle hitBox;

    protected Animation<TextureRegion> animation;
    protected float time = 0f;

    public Entity(Sprite sprite) {
        super(sprite);
        hitBox = new Rectangle();
        removed = false;
    }

    @Override
    public void draw(Batch batch) {
        if (!removed) {
            time += Gdx.graphics.getDeltaTime();
            batch.draw(animation.getKeyFrame(time, true), getX(), getY());
        }
    }

    public static TextureRegion prepareRegion(String imagePath) {
        Texture texture = new Texture(Gdx.files.internal("sprites/" + imagePath));

        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }

        Pixmap pixmap = texture.getTextureData().consumePixmap();

        Pixmap pixels = new Pixmap(MapManagement.CELL_SIZE, MapManagement.CELL_SIZE, Pixmap.Format.RGBA8888);

        final int TRANSPARENT_COLOR = 0xff00ffff;
        final int CLEAR = 0x00000000;

        for (int x = 0; x < MapManagement.CELL_SIZE; ++x) {
            for (int y = 0; y < MapManagement.CELL_SIZE; ++y) {
                if (pixmap.getPixel(x, y) == TRANSPARENT_COLOR) {
                    pixels.drawPixel(x, y, CLEAR);
                } else {
                    pixels.drawPixel(x, y, pixmap.getPixel(x, y));
                }
            }
        }
        return new TextureRegion(new Texture(pixels));
    }
}
