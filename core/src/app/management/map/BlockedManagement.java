package app.management.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import entities.Entity;
import entities.character.Character;
import entities.inactive.Portal;
import entities.inactive.items.Item;

import java.util.ArrayList;

public class BlockedManagement {
    private final ArrayList<ArrayList<BlockedType>> blockedData;
    private final int width;
    private final int height;

    public BlockedManagement(TiledMapTileLayer stillLayer) {
        this.width = stillLayer.getWidth();
        this.height = stillLayer.getHeight();
        blockedData = new ArrayList<>();
        for (int y = 0; y < height; ++y) {
            blockedData.add(new ArrayList<BlockedType>());
            for (int x = 0; x < width; ++x) {
                if (stillLayer.getCell(x, y).getTile().getProperties().containsKey("wall")) {
                    blockedData.get(y).add(BlockedType.WALL);
                }
                else {
                    blockedData.get(y).add(BlockedType.NONE);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BlockedType getData(int xUnit, int yUnit) {
        return blockedData.get(yUnit).get(xUnit);
    }

    private void setData(int xUnit, int yUnit, BlockedType newData) {
        blockedData.get(yUnit).set(xUnit, newData);
    }

    public void initInactive(TiledMapTileLayer inactiveLayer, ArrayList<Item> items, ArrayList<Portal> portals) {
        /*
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (inactiveLayer.getCell(x, y).getTile().getProperties().containsKey("portal")) {
                    portals.add(new Portal(getPortalInitSprite(x, y)));

                    setData(x, y, BlockedType.NONE);
                } else
            }
        }

         */
    }

    private Sprite getPortalInitSprite(int x, int y) {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprites/portal.png")));
        sprite.setPosition(x * MapManagement.CELL_SIZE, (y + 1) * MapManagement.CELL_SIZE);
        return sprite;
    }

    public void addBomb(int xUnit, int yUnit) {
        setData(xUnit, yUnit, BlockedType.BOMB);
    }

    public void destroy(int xUnit, int yUnit) {
        setData(xUnit, yUnit, BlockedType.NONE);
    }

    public boolean checkBlocked(int xUnit, int yUnit, Character character) {
        if (xUnit < 0 || yUnit < 0 || xUnit >= width || yUnit >= height) {
            return false;
        }
        BlockedType data = getData(xUnit, yUnit);
        switch (data) {
            case WALL:
                return true;
            case BRICK:
                return !character.isMoveOnBrick();
            case BOMB:
                return !character.isMoveOnBomb();
        }
        return false;
    }
}
