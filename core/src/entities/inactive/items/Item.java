package entities.inactive.items;

import app.management.map.BlockedManagement;
import app.management.map.BlockedType;
import app.management.map.MapManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.inactive.Inactive;

public abstract class Item extends Inactive {
    private final BlockedManagement blockedManagement;

    public Item(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite);
        this.blockedManagement = blockedManagement;
    }

    protected boolean hiding() {
        int xUnit = (int) getX() / MapManagement.CELL_SIZE;
        int yUnit = (int) getY() / MapManagement.CELL_SIZE;

        return (blockedManagement.getData(xUnit, yUnit) != BlockedType.BRICK);
    }
}
