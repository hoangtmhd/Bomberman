package entities.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import entities.Entity;

public abstract class Character extends Entity {
    // the movement velocity
    protected final Vector2 velocity;

    public Character(Sprite sprite) {
        super(sprite);
        velocity = new Vector2(0, 0);
    }

    @Override
    public void update(float delta) {
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
    }
}
