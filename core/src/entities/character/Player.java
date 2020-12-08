package entities.character;

import app.management.map.BlockedManagement;
import app.management.map.Direction;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import entities.Entity;
import entities.inactive.items.Item;

public class Player extends Character implements InputProcessor {
    private int numBomb;
    private int flameRadius;

    private final Animation<TextureRegion> moveRightAnimation;
    private final Animation<TextureRegion> moveLeftAnimation;
    private final Animation<TextureRegion> moveUpAnimation;
    private final Animation<TextureRegion> moveDownAnimation;

    private final Animation<TextureRegion> stillRightAnimation;
    private final Animation<TextureRegion> stillLeftAnimation;
    private final Animation<TextureRegion> stillUpAnimation;
    private final Animation<TextureRegion> stillDownAnimation;

    public Player(Sprite sprite, Rectangle rect, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement);

        hitBox.x = rect.x;
        hitBox.y = rect.y;
        hitBox.width = rect.width;
        hitBox.height = rect.height;

        this.blockedManagement = blockedManagement;

        numBomb = 1;
        flameRadius = 1;

        // move right.
        TextureAtlas moveRightTA = new TextureAtlas();
        moveRightTA.addRegion("0000", prepareRegion("player_right.png"));
        moveRightTA.addRegion("0001", prepareRegion("player_right_1.png"));
        moveRightTA.addRegion("0002", prepareRegion("player_right_2.png"));
        moveRightAnimation = new Animation<TextureRegion>(1/5f, moveRightTA.getRegions());

        // move left.
        TextureAtlas moveLeftTA = new TextureAtlas();
        moveLeftTA.addRegion("0000", prepareRegion("player_left.png"));
        moveLeftTA.addRegion("0001", prepareRegion("player_left_1.png"));
        moveLeftTA.addRegion("0002", prepareRegion("player_left_2.png"));
        moveLeftAnimation = new Animation<TextureRegion>(1/5f, moveLeftTA.getRegions());

        // move up.

        TextureAtlas moveUpTA = new TextureAtlas();
        moveUpTA.addRegion("0000", prepareRegion("player_up.png"));
        moveUpTA.addRegion("0001", prepareRegion("player_up_1.png"));
        moveUpTA.addRegion("0002", prepareRegion("player_up_2.png"));
        moveUpAnimation = new Animation<TextureRegion>(1/5f, moveUpTA.getRegions());

        // move down.

        TextureAtlas moveDownTA = new TextureAtlas();
        moveDownTA.addRegion("0000", prepareRegion("player_down.png"));
        moveDownTA.addRegion("0001", prepareRegion("player_down_1.png"));
        moveDownTA.addRegion("0002", prepareRegion("player_down_2.png"));
        moveDownAnimation = new Animation<TextureRegion>(1/5f, moveDownTA.getRegions());
        // still right.
        TextureAtlas stillRightTA = new TextureAtlas();
        stillRightTA.addRegion("0000", prepareRegion("player_right.png"));
        stillRightAnimation = new Animation<TextureRegion>(0f, stillRightTA.getRegions());

        // still left
        TextureAtlas stillLeftTA = new TextureAtlas();
        stillLeftTA.addRegion("0000", prepareRegion("player_left.png"));
        stillLeftAnimation = new Animation<TextureRegion>(0f, stillLeftTA.getRegions());

        // still up.
        TextureAtlas stillUpTA = new TextureAtlas();
        stillUpTA.addRegion("0000", prepareRegion("player_up.png"));
        stillUpAnimation = new Animation<TextureRegion>(0f, stillUpTA.getRegions());

        // still down.
        TextureAtlas stillDownTA = new TextureAtlas();
        stillDownTA.addRegion("0000", prepareRegion("player_down.png"));
        stillDownAnimation = new Animation<TextureRegion>(0f, stillDownTA.getRegions());

        animation = stillRightAnimation;
    }

    public void planBomb() {
        if (numBomb > 0) {
            --numBomb;
            // create new bomb.
        }
    }

    public void incFlameRadius() {
        ++flameRadius;
    }

    public void incBomb() {
        ++numBomb;
    }

    public void incSpeed() {
        speed = speed * 1.25f;
    }

    public void moveOnBrick(boolean moveOnBrick) {
        this.moveOnBrick = moveOnBrick;
    }

    public void moveOnBomb(boolean moveOnBomb) {
        this.moveOnBomb = moveOnBomb;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                // player move up.
                direction = Direction.UP;
                animation = moveUpAnimation;
                return true;
            case Input.Keys.S:
                // player move down.
                direction = Direction.DOWN;
                animation = moveDownAnimation;
                return true;
            case Input.Keys.D:
                // player move right.
                direction = Direction.RIGHT;
                animation = moveRightAnimation;
                return true;
            case Input.Keys.A:
                // player move left.
                direction = Direction.LEFT;
                animation = moveLeftAnimation;
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
                    animation = stillLeftAnimation;
                    return true;
                }
                break;
            case Input.Keys.D:
                if (direction == Direction.RIGHT) {
                    direction = Direction.STAND;
                    animation = stillRightAnimation;
                    return true;
                }
                break;
            case Input.Keys.W:
                if (direction == Direction.UP) {
                    direction = Direction.STAND;
                    animation = stillUpAnimation;
                    return true;
                }
                break;
            case Input.Keys.S:
                if (direction == Direction.DOWN) {
                    direction = Direction.STAND;
                    animation = stillDownAnimation;
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
        if (entity instanceof Item) {
            entity.collide(this);
        }
    }
}
