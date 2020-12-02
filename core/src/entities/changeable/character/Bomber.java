package entities.changeable.character;

import entities.Entity;
import graphics.Sprite;

public class Bomber extends Character {
    private int maxBomb;
    private int curBomb;
    private int lifeLeft;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.player_right);
        maxBomb = 1;
        curBomb = 0;
        lifeLeft = 3;
    }

    public void planBomb() {
        if (curBomb < maxBomb) {
            ++curBomb;
        }
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
