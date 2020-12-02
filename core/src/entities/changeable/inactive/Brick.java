package entities.changeable.inactive;

import graphics.Sprite;

public class Brick extends InactiveEntity {
    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.brick);
    }


    @Override
    public void entityRemove() {
        // animation.
        remove();
    }
}
