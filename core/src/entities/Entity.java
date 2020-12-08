package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity extends Sprite {
    protected Rectangle hitBox;

    public Entity(Sprite sprite) {
        super(sprite);
        hitBox = new Rectangle();
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public abstract void update(float delta);

    public abstract void collide(Entity entity);
}
