package entities.character;

import app.management.map.BlockedManagement;
import app.management.map.Direction;
import app.management.map.MapManagement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import entities.Entity;

public abstract class Character extends Entity {
    protected Vector2 velocity;

    protected Direction direction;
    protected float speed;

    protected boolean moveOnBrick;
    protected boolean moveOnBomb;

    protected BlockedManagement blockedManagement;

    public boolean isMoveOnBrick() {
        return moveOnBrick;
    }

    public boolean isMoveOnBomb() {
        return moveOnBomb;
    }

    public Character(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite);
        velocity = new Vector2(0, 0);

        speed = 30;
        direction = Direction.STAND;

        moveOnBrick = false;
        moveOnBomb = false;

        this.blockedManagement = blockedManagement;
    }

    @Override
    public void draw(Batch batch) {
        if (!removed) {
            update(Gdx.graphics.getDeltaTime());
        }
        super.draw(batch);
    }

    public void update(float delta) {
        move(delta);
    }

    public void move(float delta) {
        velocity.x = direction.getX() * speed;
        velocity.y = direction.getY() * speed;

        float oldX = hitBox.getX(), oldY = hitBox.getY();
        boolean collisionX = false, collisionY = false;

        // move on x.
        hitBox.setX(hitBox.getX() + velocity.x * delta);

        if (velocity.x < 0) {
            // all left pixels.
            collisionX = collideLeft();

        } else if (velocity.x > 0) {
            // all right pixels.
            collisionX = collideRight();
        }

        if (collisionX) {
            hitBox.setX(oldX);
            velocity.x = 0;
        }

        // move on y.
        hitBox.setY(hitBox.getY() + velocity.y * delta);

        if (velocity.y < 0) {
            // all bottom pixels.
            collisionY = collideBottom();
        } else if (velocity.y > 0) {
            // all top pixels.
            collisionY = collideTop();
        }

        if (collisionY) {
            hitBox.setY(oldY);
            velocity.y = 0;
        }

        setPosition(getX() + (hitBox.getX() - oldX), getY() + (hitBox.getY() - oldY));
    }

    private boolean collideRight() {
        boolean collide = false;

        for (float step = 1; step < hitBox.getHeight(); ++step) {
            int xUnit = (int) ((hitBox.getX() + hitBox.getWidth()) / MapManagement.CELL_SIZE);
            int yUnit = (int) ((hitBox.getY() + step) / MapManagement.CELL_SIZE);
            if (collide = blockedManagement.checkBlocked(xUnit, yUnit, this)) {
                break;
            }
        }

        if (collide) {
            int xUnit = (int) hitBox.getX() / MapManagement.CELL_SIZE;
            int yUnit = (int) hitBox.getY() / MapManagement.CELL_SIZE;
            int yDelta = (int) hitBox.getY() - yUnit * MapManagement.CELL_SIZE;
            if (yDelta <= MapManagement.CELL_SIZE / 2) {
                if (!blockedManagement.checkBlocked(xUnit + 1, yUnit, this)) {
                    hitBox.setY((yUnit + 1) * MapManagement.CELL_SIZE - hitBox.getHeight());
                    collide = false;
                }
            } else if (yDelta > MapManagement.CELL_SIZE / 2 + 1) {
                if (!blockedManagement.checkBlocked(xUnit + 1, yUnit + 1, this)) {
                    hitBox.setY((yUnit + 1) * MapManagement.CELL_SIZE);
                    collide = false;
                }
            }
        }

        return collide;
    }

    private boolean collideLeft() {
        boolean collide = false;

        for (float step = 1; step < hitBox.getHeight(); step = step + 1) {
            int xUnit = (int) ((hitBox.getX()) / MapManagement.CELL_SIZE);
            int yUnit = (int) ((hitBox.getY() + step) / MapManagement.CELL_SIZE);
            if (collide = blockedManagement.checkBlocked(xUnit, yUnit, this)) {
                break;
            }
        }

        if (collide) {
            int xUnit = (int) hitBox.getX() / MapManagement.CELL_SIZE;
            int yUnit = (int) hitBox.getY() / MapManagement.CELL_SIZE;
            int yDelta = (int) hitBox.getY() - yUnit * MapManagement.CELL_SIZE;
            if (yDelta <= MapManagement.CELL_SIZE / 2) {
                if (!blockedManagement.checkBlocked(xUnit, yUnit, this)) {
                    hitBox.setY((yUnit + 1) * MapManagement.CELL_SIZE - hitBox.getHeight());
                    collide = false;
                }
            } else if (yDelta > MapManagement.CELL_SIZE / 2 + 1) {
                if (!blockedManagement.checkBlocked(xUnit, yUnit + 1, this)) {
                    hitBox.setY((yUnit + 1) * MapManagement.CELL_SIZE);
                    collide = false;
                }
            }
        }

        return collide;
    }

    private boolean collideTop() {
        boolean collide = false;

        for (float step = 1; step < hitBox.getWidth(); ++step) {
            int xUnit = (int) ((hitBox.getX() + step) / MapManagement.CELL_SIZE);
            int yUnit = (int) ((hitBox.getY() + hitBox.getHeight()) / MapManagement.CELL_SIZE);
            if (collide = blockedManagement.checkBlocked(xUnit, yUnit, this)) {
                break;
            }
        }

        if (collide) {
            int xUnit = (int) hitBox.getX() / MapManagement.CELL_SIZE;
            int yUnit = (int) hitBox.getY() / MapManagement.CELL_SIZE;
            int xDelta = (int) hitBox.getX() - xUnit * MapManagement.CELL_SIZE;
            if (xDelta <= MapManagement.CELL_SIZE / 2) {
                if (!blockedManagement.checkBlocked(xUnit, yUnit + 1, this)) {
                    hitBox.setX((xUnit + 1) * MapManagement.CELL_SIZE - hitBox.getWidth());
                    collide = false;
                }
            } else if (xDelta > MapManagement.CELL_SIZE / 2 + 1) {
                if (!blockedManagement.checkBlocked(xUnit + 1, yUnit + 1, this)) {
                    hitBox.setX((xUnit + 1) * MapManagement.CELL_SIZE);
                    collide = false;
                }
            }
        }

        return collide;
    }

    private boolean collideBottom() {
        boolean collide = false;

        for (float step = 1; step < hitBox.getWidth(); ++step) {
            int xUnit = (int) ((hitBox.getX() + step) / MapManagement.CELL_SIZE);
            int yUnit = (int) ((hitBox.getY()) / MapManagement.CELL_SIZE);
            if (collide = blockedManagement.checkBlocked(xUnit, yUnit, this)) {
                break;
            }
        }

        if (collide) {
            int xUnit = (int) hitBox.getX() / MapManagement.CELL_SIZE;
            int yUnit = (int) hitBox.getY() / MapManagement.CELL_SIZE;
            int xDelta = (int) hitBox.getX() - xUnit * MapManagement.CELL_SIZE;
            if (xDelta <= MapManagement.CELL_SIZE / 2) {
                if (!blockedManagement.checkBlocked(xUnit, yUnit, this)) {
                    hitBox.setX((xUnit + 1) * MapManagement.CELL_SIZE - hitBox.getWidth());
                    collide = false;
                }
            } else if (xDelta > MapManagement.CELL_SIZE / 2 + 1) {
                if (!blockedManagement.checkBlocked(xUnit + 1, yUnit, this)) {
                    hitBox.setX((xUnit + 1) * MapManagement.CELL_SIZE);
                    collide = false;
                }
            }
        }

        return collide;
    }
}
