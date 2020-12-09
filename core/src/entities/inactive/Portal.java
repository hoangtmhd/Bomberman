package entities.inactive;

import app.management.map.BlockedManagement;
import app.management.map.BlockedType;
import app.management.map.MapManagement;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Entity;

public class Portal extends Inactive {
    private final BlockedManagement blockedManagement;

    public Portal(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite);

        this.blockedManagement = blockedManagement;

        hitBox.set(getX() + 1, getY() + 2, 13, 12);

        TextureAtlas textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("0000", prepareRegion("portal.png"));
        animation = new Animation<TextureRegion>(0f, textureAtlas.getRegions());
    }

    public boolean showing() {
        int xUnit = (int) getX() / MapManagement.CELL_SIZE;
        int yUnit = (int) getY() / MapManagement.CELL_SIZE;

        return (blockedManagement.getData(xUnit, yUnit) != BlockedType.BRICK);
    }

    @Override
    public void remove() {
    }

    @Override
    public void collide(Entity entity) {
    }
}
