package entities.still;

import entities.Entity;
import graphics.Sprite;

public abstract class StillEntity extends Entity {
    public StillEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }
}
