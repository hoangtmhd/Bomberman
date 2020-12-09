package entities.inactive;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Entity;

public class Brick extends Entity {
    private final Animation<TextureRegion> destroyAnimation;

    public Brick(Sprite sprite) {
        super(sprite);

        TextureAtlas stillTA = new TextureAtlas();
        stillTA.addRegion("0000", prepareRegion("brick.png"));
        animation = new Animation<TextureRegion>(0f, stillTA.getRegions());

        TextureAtlas destroyTA = new TextureAtlas();
        destroyTA.addRegion("0000", prepareRegion("brick_exploded.png"));
        destroyTA.addRegion("0001", prepareRegion("brick_exploded1.png"));
        destroyTA.addRegion("0002", prepareRegion("brick_exploded2.png"));
        destroyAnimation = new Animation<TextureRegion>(1/2f, destroyTA.getRegions());
    }

    @Override
    public void remove() {
        animation = destroyAnimation;
        removeTime = 3/2f;
        time = 0;
        removed = true;
    }

    @Override
    public void collide(Entity entity) {
    }
}
