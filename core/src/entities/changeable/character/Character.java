package entities.changeable.character;

import entities.changeable.ChangeableEntity;
import graphics.Direction;
import graphics.Sprite;

public abstract class Character extends ChangeableEntity {
    protected Direction direction;
    protected boolean moving;
    protected int speed;

    // Default;
    protected final float xDefault;
    protected final float yDefault;
    protected final Direction directionDefault;

    public Character(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);

        xDefault = this.getX();
        yDefault = this.getY();
        directionDefault = direction = Direction.RIGHT;
        moving = true;
        speed = 4;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
