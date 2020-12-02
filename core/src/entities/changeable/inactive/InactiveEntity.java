package entities.changeable.inactive;

import entities.changeable.ChangeableEntity;
import graphics.Sprite;

public abstract class InactiveEntity extends ChangeableEntity {
    public InactiveEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }
}
