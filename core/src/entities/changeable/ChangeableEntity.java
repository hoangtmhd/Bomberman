package entities.changeable;

import entities.Entity;
import graphics.Sprite;

public abstract class ChangeableEntity extends Entity {
    protected boolean remove;

    public boolean isRemove() {
        return remove;
    }

    public ChangeableEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        remove = false;
    }

    public abstract void update();

    @Override
    public void act(float delta) {
        super.act(delta);
        update();
    }

    public abstract void entityRemove();
}
