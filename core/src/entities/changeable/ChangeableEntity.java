package entities.changeable;

import entities.Entity;
import graphics.Sprite;

public abstract class ChangeableEntity extends Entity {
    protected boolean remove;

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public ChangeableEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        remove = false;
    }

    public void update() {
        if (remove) {
            this.entityRemove();
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.update();
    }

    public abstract void entityRemove();
}
