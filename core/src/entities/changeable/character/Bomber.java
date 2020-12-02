package entities.changeable.character;

import graphics.Sprite;

public class Bomber extends Character {
    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.player_right);
    }

    @Override
    public void update() {
        if (moving) {
            hitBox.setPosition(hitBox.getX() + direction.getX() * speed,
                    hitBox.getY() + direction.getY() * speed);
            this.setPosition(hitBox.getX() - sprite.getHitBoxX() * Sprite.SCALED_RADIUS,
                    hitBox.getY() - sprite.getHitBoxY() * Sprite.SCALED_RADIUS);
        }
    }

    @Override
    public void entityRemove() {

    }
}
