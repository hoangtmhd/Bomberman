package entities.changeable.inactive.item;

import entities.EntityMapData;
import graphics.Sprite;

import java.util.ArrayList;

public class BombItem extends Item {
    public BombItem(int xUnit, int yUnit, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, Sprite.powerup_bombs, mapData);
    }
}
