package entities.changeable.inactive.item;

import entities.EntityMapData;
import graphics.Sprite;

import java.util.ArrayList;

public class SpeedItem extends Item {
    public SpeedItem(int xUnit, int yUnit, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, Sprite.powerup_speed, mapData);
    }
}
