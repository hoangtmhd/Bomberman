package entities.changeable.character;

import entities.Entity;
import graphics.Sprite;

public class Bomber extends Character {
    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.player_right);
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }

    @Override
    public void entityRemove() {

    }
}
