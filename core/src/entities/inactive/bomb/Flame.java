package entities.inactive.bomb;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.inactive.Inactive;

public abstract class Flame extends Inactive {
    public Flame(Sprite sprite) {
        super(sprite);

        removed = true;
        removeTime = 1f;
    }

    @Override
    public boolean isDestroy() {
        return (removeTime <= 0);
    }

    protected Animation<TextureRegion> getAnimation(String name) {
        TextureAtlas textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("0000", prepareRegion(name + ".png"));
        textureAtlas.addRegion("0001", prepareRegion(name + "1.png"));
        textureAtlas.addRegion("0002", prepareRegion(name + "2.png"));
        textureAtlas.addRegion("0003", prepareRegion(name + "1.png"));
        textureAtlas.addRegion("0004", prepareRegion(name + ".png"));
        return new Animation<TextureRegion>(1/5f, textureAtlas.getRegions());
    }
}
