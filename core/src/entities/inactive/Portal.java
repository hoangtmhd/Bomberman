package entities.inactive;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Entity;

public class Portal extends Inactive {
    public Portal(Sprite sprite) {
        super(sprite);

        hitBox.set(getX() + 1, getY() + 2, 13, 12);

        TextureAtlas textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("0000", prepareRegion("portal.png"));
        animation = new Animation<TextureRegion>(0f, textureAtlas.getRegions());
    }

    @Override
    public void remove() {
    }

    @Override
    public void collide(Entity entity) {
    }
}
