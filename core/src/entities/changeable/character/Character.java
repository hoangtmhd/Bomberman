package entities.changeable.character;

import app.screens.PlayScreen;
import entities.Entity;
import entities.EntityMapData;
import entities.changeable.ChangeableEntity;
import graphics.Direction;
import graphics.Sprite;

import java.util.ArrayList;

public abstract class Character extends ChangeableEntity {
    protected final ArrayList<ArrayList<EntityMapData>> mapData;

    protected Direction direction;
    protected boolean isMoving;
    protected int speed;
    protected boolean moveBrick;

    // Default;
    protected final float xDefault;
    protected final float yDefault;

    public Character(int xUnit, int yUnit, Sprite sprite, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, sprite);

        this.mapData = mapData;

        xDefault = this.getX();
        yDefault = this.getY();
        direction = Direction.STAND;
        isMoving = false;
        speed = Sprite.SCALED_RADIUS;
        moveBrick = false;
    }

    public void incSpeed() {
        if (speed < PlayScreen.SCALED_SIZE/4) {
            speed += Sprite.SCALED_RADIUS;
        }
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void reset() {
        this.setPosition(xDefault, yDefault);
        isMoving = false;
        direction = Direction.STAND;
        speed = 1;
    }

    private void move() {
        int pixelsMove = calcMove();
        hitBox.setPosition(hitBox.getX() + direction.getX() * pixelsMove,
                hitBox.getY() + direction.getY() * pixelsMove);
        this.setPosition(hitBox.getX() - sprite.getHitBoxX() * Sprite.SCALED_RADIUS,
                hitBox.getY() - sprite.getHitBoxY() * Sprite.SCALED_RADIUS);
    }

    public int calcMove() {
        int res = speed;
        int xUnit = (int) hitBox.getX() / PlayScreen.SCALED_SIZE;
        int yUnit = (int) hitBox.getY() / PlayScreen.SCALED_SIZE;
        int xLim = (int) (hitBox.getX() + hitBox.getWidth() - 1) / PlayScreen.SCALED_SIZE;
        int yLim = (int) (hitBox.getY() + hitBox.getHeight() - 1) / PlayScreen.SCALED_SIZE;

        switch (direction) {
            case UP:
                for (int y = yUnit + 1; y <= yUnit + 2; ++y) {
                    for (int x = xUnit; x <= xLim; ++x) {
                        if (blocked(x, y)) {
                            int dist = y * PlayScreen.SCALED_SIZE
                                    - (int) (hitBox.getY() + hitBox.getHeight());
                            res = Math.min(res, dist);
                        }
                    }
                }
                if (res == 0) {
                    int xDelta = (int) hitBox.getX() - xUnit * PlayScreen.SCALED_SIZE;
                    if (xDelta <= PlayScreen.SCALED_SIZE/2) {
                        if (!blocked(xUnit, yUnit + 1)) {
                            hitBox.setX((xUnit + 1) * PlayScreen.SCALED_SIZE - hitBox.getWidth());
                            res = speed;
                        }
                    } else if (xDelta > PlayScreen.SCALED_SIZE/2 + 1) {
                        if (!blocked(xUnit + 1, yUnit + 1)) {
                            hitBox.setX((xUnit + 1) * PlayScreen.SCALED_SIZE);
                            res = speed;
                        }
                    }
                }
                break;
            case DOWN:
                for (int x = xUnit; x <= xLim; ++x) {
                    if (blocked(x, yUnit - 1)) {
                        int dist = (int) hitBox.getY() - yUnit * PlayScreen.SCALED_SIZE;
                        res = Math.min(res, dist);
                    }
                }
                if (res == 0) {
                    int xDelta = (int) hitBox.getX() - xUnit * PlayScreen.SCALED_SIZE;
                    if (xDelta <= PlayScreen.SCALED_SIZE/2) {
                        if (!blocked(xUnit, yUnit - 1)) {
                            hitBox.setX((xUnit + 1) * PlayScreen.SCALED_SIZE - hitBox.getWidth());
                            res = speed;
                        }
                    } else if (xDelta > PlayScreen.SCALED_SIZE/2 + 1) {
                        if (!blocked(xUnit + 1, yUnit - 1)) {
                            hitBox.setX((xUnit + 1) * PlayScreen.SCALED_SIZE);
                            res = speed;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int y = yUnit; y <= yLim; ++y) {
                    for (int x = xUnit + 1; x <= xUnit + 2; ++x) {
                        if (blocked(x, y)) {
                            int dist = x * PlayScreen.SCALED_SIZE
                                    - (int) (hitBox.getX() + hitBox.getWidth());
                            res = Math.min(res, dist);
                        }
                    }
                }
                if (res == 0) {
                    int yDelta = (int) hitBox.getY() - yUnit * PlayScreen.SCALED_SIZE;
                    if (yDelta <= PlayScreen.SCALED_SIZE/2) {
                        if (!blocked(xUnit + 1, yUnit)) {
                            hitBox.setY((yUnit + 1) * PlayScreen.SCALED_SIZE - hitBox.getHeight());
                            res = speed;
                        }
                    } else if (yDelta > PlayScreen.SCALED_SIZE/2 + 1) {
                        if (!blocked(xUnit + 1, yUnit + 1)) {
                            hitBox.setY((yUnit + 1) * PlayScreen.SCALED_SIZE);
                            res = speed;
                        }
                    }
                }
                break;
            case LEFT:
                for (int y = yUnit; y <= yLim; ++y) {
                    if (blocked(xUnit - 1, y)) {
                        int dist = (int) hitBox.getX() - xUnit * PlayScreen.SCALED_SIZE;
                        res = Math.min(res, dist);
                    }
                }
                if (res == 0) {
                    int yDelta = (int) hitBox.getY() - yUnit * PlayScreen.SCALED_SIZE;
                    if (yDelta <= PlayScreen.SCALED_SIZE/2) {
                        if (!blocked(xUnit - 1, yUnit)) {
                            hitBox.setY((yUnit + 1) * PlayScreen.SCALED_SIZE - hitBox.getHeight());
                            res = speed;
                        }
                    } else if (yDelta > PlayScreen.SCALED_SIZE/2 + 1) {
                        if (!blocked(xUnit - 1, yUnit + 1)) {
                            hitBox.setY((yUnit + 1) * PlayScreen.SCALED_SIZE);
                            res = speed;
                        }
                    }
                }
                break;
        }

        return res;
    }

    private boolean blocked(int xUnit, int yUnit) {
        if (xUnit < 0 || yUnit < 0 || yUnit >= mapData.size() || xUnit >= mapData.get(0).size()) {
            return false;
        }
        int h = mapData.size() - yUnit - 1;
        EntityMapData data = mapData.get(h).get(xUnit);
        return data == EntityMapData.WALL || (data == EntityMapData.BRICK && !moveBrick) ||
                data == EntityMapData.BOMB;
    }

    public abstract void collide(Entity entity);

    @Override
    public void update() {
        super.update();
        if (isMoving && !remove) {
            move();
        }
    }
}
