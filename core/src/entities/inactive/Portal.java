package entities.inactive;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;

public class Portal extends Inactive {
    public Portal(Sprite sprite) {
        super(sprite);

        hitBox.set(getX() + 1, getY() + 2, 13, 12);
    }

    @Override
    public void collide(Entity entity) {
    }
}
