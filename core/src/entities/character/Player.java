package entities.character;

import app.management.map.BlockedManagement;
import app.management.map.Direction;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import entities.Entity;

public class Player extends Character implements InputProcessor {
    public Player(Sprite sprite, Rectangle rect, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement);

        hitBox.x = rect.x;
        hitBox.y = rect.y;
        hitBox.width = rect.width;
        hitBox.height = rect.height;

        this.blockedManagement = blockedManagement;
    }

    public void planBomb() {

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                // player move up.
                direction = Direction.UP;
                return true;
            case Input.Keys.S:
                // player move down.
                direction = Direction.DOWN;
                return true;
            case Input.Keys.D:
                // player move right.
                direction = Direction.RIGHT;
                return true;
            case Input.Keys.A:
                // player move left.
                direction = Direction.LEFT;
                return true;
            case Input.Keys.SPACE:
                // plan bomb.
                planBomb();
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                if (direction == Direction.LEFT) {
                    direction = Direction.STAND;
                    return true;
                }
                break;
            case Input.Keys.D:
                if (direction == Direction.RIGHT) {
                    direction = Direction.STAND;
                    return true;
                }
                break;
            case Input.Keys.W:
                if (direction == Direction.UP) {
                    direction = Direction.STAND;
                    return true;
                }
                break;
            case Input.Keys.S:
                if (direction == Direction.DOWN) {
                    direction = Direction.STAND;
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void collide(Entity entity) {

    }
}
