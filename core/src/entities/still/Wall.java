package entities.still;

import graphics.Sprite;

public class Wall extends StillEntity {
    public Wall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall);
    }
}
