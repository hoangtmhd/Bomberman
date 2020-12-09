package app.management.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import entities.character.Character;
import entities.inactive.Brick;
import entities.inactive.Portal;
import entities.inactive.items.*;

import java.util.ArrayList;

public class BlockedManagement {
    private final ArrayList<ArrayList<BlockedType>> blockedData;
    private final ArrayList<ArrayList<Brick>> brickData;
    private final int width;
    private final int height;

    public BlockedManagement(TiledMapTileLayer stillLayer) {
        this.width = stillLayer.getWidth();
        this.height = stillLayer.getHeight();
        blockedData = new ArrayList<>();
        brickData = new ArrayList<>();
        for (int y = 0; y < height; ++y) {
            blockedData.add(new ArrayList<BlockedType>());
            brickData.add(new ArrayList<Brick>());
            for (int x = 0; x < width; ++x) {
                brickData.get(y).add(null);
                if (stillLayer.getCell(x, y) != null &&
                        stillLayer.getCell(x, y).getTile().getProperties().containsKey("wall")) {
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
        if (xUnit < 0 || yUnit < 0 || xUnit >= width || yUnit >= height) {
            return BlockedType.WALL;
        }
        return blockedData.get(yUnit).get(xUnit);
    }

    private void setData(int xUnit, int yUnit, BlockedType newData) {
        blockedData.get(yUnit).set(xUnit, newData);
    }

    public void initInactive(TiledMapTileLayer inactiveLayer,
                             ArrayList<Item> items, ArrayList<Portal> portals, ArrayList<Brick> bricks) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) if (inactiveLayer.getCell(x, y) != null) {
                MapProperties mapProperties = inactiveLayer.getCell(x, y).getTile().getProperties();
                if (mapProperties.containsKey("portal")) {
                    // create Portal.
                    portals.add(new Portal(getInitSprite(x, y)));
                    // create Brick.
                    Brick brick = new Brick(getInitSprite(x, y));
                    bricks.add(brick);
                    brickData.get(y).set(x, brick);
                    setData(x, y, BlockedType.BRICK);
                } else if (mapProperties.containsKey("brick")) {
                    // create Brick.
                    Brick brick = new Brick(getInitSprite(x, y));
                    bricks.add(brick);
                    brickData.get(y).set(x, brick);
                    setData(x, y, BlockedType.BRICK);
                } else if (mapProperties.containsKey("item")) {
                    // create Item.
                    String key = mapProperties.get("item", String.class);
                    switch (key) {
                        case "flame":
                            items.add(new FlameItem(getInitSprite(x, y), this));
                            break;
                        case "bomb":
                            //items.add(new BombItem(getInitSprite(x, y), this));
                            break;
                        case "speed":
                            items.add(new SpeedItem(getInitSprite(x, y), this));
                            break;
                        case "moveBrick":
                            items.add(new MoveBrickItem(getInitSprite(x, y), this));
                            break;
                        case "moveBomb":
                            items.add(new MoveBombItem(getInitSprite(x, y), this));
                            break;
                    }
                    Brick brick = new Brick(getInitSprite(x, y));
                    bricks.add(brick);
                    brickData.get(y).set(x, brick);
                    setData(x, y, BlockedType.BRICK);
                }
            }
        }
    }

    private Sprite getInitSprite(int x, int y) {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprites/portal.png")));
        sprite.setPosition(x * MapManagement.CELL_SIZE, y * MapManagement.CELL_SIZE);
        return sprite;
    }

    public void addBomb(int xUnit, int yUnit) {
        setData(xUnit, yUnit, BlockedType.BOMB);
    }

    public void destroy(int xUnit, int yUnit) {
        if (getData(xUnit, yUnit) == BlockedType.BRICK) {
            Brick brick = brickData.get(yUnit).get(xUnit);
            brick.remove();
            brickData.get(yUnit).set(xUnit, null);
        }
        setData(xUnit, yUnit, BlockedType.NONE);
    }

    public boolean checkBlocked(int xUnit, int yUnit, Character character) {
        if (xUnit < 0 || yUnit < 0 || xUnit >= width || yUnit >= height) {
            return true;
        }
        BlockedType data = getData(xUnit, yUnit);
        switch (data) {
            case WALL:
                return true;
            case BRICK:
                return !character.isMoveOnBrick();
            case BOMB:
                return !character.isMoveOnBomb() && !character.isOnBomb();
        }
        return false;
    }
}
