package entities.inactive;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;

public class Brick extends Entity {
    public Brick(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void collide(Entity entity) {
    }
}
