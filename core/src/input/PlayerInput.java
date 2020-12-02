package input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import entities.changeable.character.Bomber;
import graphics.Direction;

public class PlayerInput implements InputProcessor {
    Bomber bomber;

    public PlayerInput(Bomber bomber) {
        super();
        this.bomber = bomber;
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("Button Down");
        switch (keycode) {
            case Input.Keys.UP:
                bomber.setMoving(true);
                bomber.setDirection(Direction.UP);
                return true;
            case Input.Keys.DOWN:
                bomber.setMoving(true);
                bomber.setDirection(Direction.DOWN);
                return true;
            case Input.Keys.RIGHT:
                bomber.setMoving(true);
                bomber.setDirection(Direction.RIGHT);
                return true;
            case Input.Keys.LEFT:
                bomber.setMoving(true);
                bomber.setDirection(Direction.LEFT);
                return true;
            case Input.Keys.SPACE:
                bomber.planBomb();
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.DOWN:
            case Input.Keys.RIGHT:
            case Input.Keys.LEFT:
                bomber.setMoving(false);
                return true;
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
}
