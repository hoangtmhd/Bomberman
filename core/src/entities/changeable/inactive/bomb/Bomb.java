package entities.changeable.inactive.bomb;

import app.screens.PlayScreen;
import entities.EntityMapData;
import entities.changeable.character.Bomber;
import entities.changeable.inactive.InactiveEntity;
import graphics.Sprite;

import java.util.ArrayList;

public class Bomb extends InactiveEntity {
    private final ArrayList<ArrayList<EntityMapData>> mapData;
    private final Bomber bomber;
    private double timeToExplode;
    private boolean exploded;

    public Bomb(int xUnit, int yUnit, ArrayList<ArrayList<EntityMapData>> mapData, Bomber bomber) {
        super(xUnit, yUnit, Sprite.bomb);
        this.mapData = mapData;
        this.bomber = bomber;
        timeToExplode = 120;
        exploded = false;
        int h = mapData.size() - yUnit - 1;
        mapData.get(h).set(xUnit, EntityMapData.BOMB);
    }

    private void explore() {
        exploded = true;
        int xBomb = (int) hitBox.getX() / PlayScreen.SCALED_SIZE;
        int yBomb = (int) hitBox.getY() / PlayScreen.SCALED_SIZE;
        int h = mapData.size() - yBomb - 1;
        mapData.get(h).set(xBomb, EntityMapData.GRASS);
        bomber.incBomb();
    }

    @Override
    public void update() {
        super.update();
        if (timeToExplode > 0) {
            --timeToExplode;
        } else {
            if (!exploded) {
                explore();
                remove = true;
            }
        }
    }

    @Override
    public void entityRemove() {
        // animation.
        remove();
    }
}
