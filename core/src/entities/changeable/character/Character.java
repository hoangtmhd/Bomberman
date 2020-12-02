package entities.changeable.character;

import com.badlogic.gdx.math.Rectangle;
import entities.Entity;
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
        moving = false;
        speed = 4;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void reset() {
        this.setPosition(xDefault, yDefault);
        direction = directionDefault;
    }

    private void move() {
        hitBox.setPosition(hitBox.getX() + direction.getX() * speed,
                hitBox.getY() + direction.getY() * speed);
        this.setPosition(hitBox.getX() - sprite.getHitBoxX() * Sprite.SCALED_RADIUS,
                hitBox.getY() - sprite.getHitBoxY() * Sprite.SCALED_RADIUS);
    }

    public abstract boolean canMove();

    public abstract boolean collide(Entity entity);

    @Override
    public void update() {
        if (moving && canMove()) {
            move();
        }
    }
}
