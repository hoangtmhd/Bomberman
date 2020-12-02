package entities.changeable.character;

import app.screens.PlayScreen;
import entities.Entity;
import entities.EntityMapData;
import entities.changeable.inactive.bomb.Bomb;
import graphics.Sprite;

import java.util.ArrayList;

public class Bomber extends Character {
    private int curBomb;
    private Bomb bomb;

    public Bomber(int xUnit, int yUnit, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, Sprite.player_right, mapData);
        curBomb = 1;
    }

    public void planBomb() {
        if (curBomb > 0) {
            --curBomb;
            int xBomb = (int) hitBox.getX() / PlayScreen.SCALED_SIZE;
            int yBomb = (int) hitBox.getY() / PlayScreen.SCALED_SIZE;
            bomb = new Bomb(xBomb, yBomb, mapData, this);
            this.getStage().addActor(bomb);
        }
    }

    public void incBomb() {
        ++curBomb;
    }

    @Override
    public void reset() {
        super.reset();
        remove = false;
        curBomb = 1;
    }

    @Override
    public void collide(Entity entity) {

    }

    @Override
    public void entityRemove() {
        // animation.
    }
}
