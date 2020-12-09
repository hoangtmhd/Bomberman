package entities.character;

import app.management.map.BlockedManagement;
import app.management.map.BombManagement;
import app.management.map.Direction;
import app.management.map.MapManagement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import entities.Entity;
import entities.character.enemy.Enemy;
import entities.inactive.bomb.Bomb;
import entities.inactive.bomb.Flame;
import entities.inactive.bomb.normal.NormalBomb;
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

    private final Animation<TextureRegion> deadAnimation;

    private final BombManagement bombManagement;

    private int score = 0;

    public Player(Sprite sprite, Rectangle rect,
                  BlockedManagement blockedManagement, BombManagement bombManagement) {
        super(sprite, blockedManagement);

        hitBox.x = rect.x;
        hitBox.y = rect.y;
        hitBox.width = rect.width;
        hitBox.height = rect.height;

        this.blockedManagement = blockedManagement;

        this.bombManagement = bombManagement;

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

        // dead.
        TextureAtlas deadTA = new TextureAtlas();
        deadTA.addRegion("0000", prepareRegion("player_down.png"));
        deadTA.addRegion("0001", prepareRegion("player_dead1.png"));
        deadTA.addRegion("0002", prepareRegion("player_dead2.png"));
        deadTA.addRegion("0003", prepareRegion("player_dead3.png"));
        deadAnimation = new Animation<TextureRegion>(3/4f, deadTA.getRegions());

        animation = stillDownAnimation;
    }

    public int getScore() {
        return score;
    }

    public void incScore(int bonus) {
        score += bonus;
    }

    public void planBomb() {
        if (numBomb > 0) {
            --numBomb;
            // create new bomb.
            Bomb bomb = new NormalBomb(getBombInitSprite(), this);
            bombManagement.add(bomb);
        }
    }

    public Sprite getBombInitSprite() {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprites/portal.png")));
        int xUnit = (int) (hitBox.getX() + hitBox.getWidth()/2 - 1) / MapManagement.CELL_SIZE;
        int yUnit = (int) (hitBox.getY() + hitBox.getHeight()/2 - 1) / MapManagement.CELL_SIZE;
        sprite.setPosition(xUnit * MapManagement.CELL_SIZE, yUnit * MapManagement.CELL_SIZE);
        return sprite;
    }

    public int getFlameRadius() {
        return flameRadius;
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
        if (removed) return false;
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
        if (removed) return false;
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
    public void remove() {
        removed = true;
        removeTime = 3f;
        time = 0;
        animation = deadAnimation;
    }

    @Override
    public boolean isDestroy() {
        return super.isDestroy() && removeTime <= 0;
    }

    public boolean isLive() {
        return !removed;
    }

    @Override
    public void collide(Entity entity) {
        if (entity instanceof Item) {
            entity.collide(this);
            return;
        }
        if (entity instanceof Flame) {
            entity.collide(this);
            return;
        }
        if (entity instanceof Enemy) {
            remove();
        }
    }
}
