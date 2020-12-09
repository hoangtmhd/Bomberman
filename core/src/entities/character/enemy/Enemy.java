package entities.character.enemy;

import app.management.map.BlockedManagement;
import app.management.map.Direction;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Entity;
import entities.character.Character;
import entities.character.enemy.ai.AI;
import entities.inactive.bomb.Flame;

public abstract class Enemy extends Character {
    protected AI ai;
    protected int points;
    protected float MAX_STEPS;
    protected float steps;

    private final Animation<TextureRegion> moveRightAnimation;
    private final Animation<TextureRegion> moveLeftAnimation;

    private final Animation<TextureRegion> stillRightAnimation;
    private final Animation<TextureRegion> stillLeftAnimation;

    private final Animation<TextureRegion> deadAnimation;

    public Enemy(Sprite sprite, BlockedManagement blockedManagement, String name) {
        super(sprite, blockedManagement);

        // move right.
        TextureAtlas moveRightTA = new TextureAtlas();
        moveRightTA.addRegion("0000", prepareRegion(name + "_right1.png"));
        moveRightTA.addRegion("0001", prepareRegion(name + "_right2.png"));
        moveRightTA.addRegion("0002", prepareRegion(name + "_right3.png"));
        moveRightAnimation = new Animation<TextureRegion>(1/5f, moveRightTA.getRegions());

        // move left.
        TextureAtlas moveLeftTA = new TextureAtlas();
        moveLeftTA.addRegion("0000", prepareRegion(name + "_left1.png"));
        moveLeftTA.addRegion("0001", prepareRegion(name + "_left2.png"));
        moveLeftTA.addRegion("0002", prepareRegion(name + "_left3.png"));
        moveLeftAnimation = new Animation<TextureRegion>(1/5f, moveLeftTA.getRegions());

        // still right.
        TextureAtlas stillRightTA = new TextureAtlas();
        stillRightTA.addRegion("0000", prepareRegion(name + "_right1.png"));
        stillRightAnimation = new Animation<TextureRegion>(0f, stillRightTA.getRegions());

        // still left
        TextureAtlas stillLeftTA = new TextureAtlas();
        stillLeftTA.addRegion("0000", prepareRegion(name + "_left1.png"));
        stillLeftAnimation = new Animation<TextureRegion>(0f, stillLeftTA.getRegions());

        // dead.
        TextureAtlas deadTA = new TextureAtlas();
        deadTA.addRegion("0000", prepareRegion(name + "_dead.png"));
        deadTA.addRegion("0001", prepareRegion("mob_dead1.png"));
        deadTA.addRegion("0002", prepareRegion("mob_dead2.png"));
        deadTA.addRegion("0003", prepareRegion("mob_dead3.png"));
        deadAnimation = new Animation<TextureRegion>(5/8f, deadTA.getRegions());
        animation = stillLeftAnimation;
    }

    @Override
    public void update(float delta) {
        // change direction.
        if (steps <= 0) {
            direction = ai.calculateDirection();
            changeDirection(direction);
            steps = MAX_STEPS;
        }

        // call changeDirection when want to move, then call stop if blocked.
        super.update(delta);
        if (canMove()) {
            steps -= delta;
        } else {
            stop();
            steps = 0;
        }
    }

    protected void changeDirection(Direction direction) {
        switch (direction) {
            case RIGHT:
            case DOWN:
                animation = moveRightAnimation;
                break;
            case LEFT:
            case UP:
                animation = moveLeftAnimation;
                break;
        }
        this.direction = direction;
    }

    protected void stop() {
        switch (direction) {
            case RIGHT:
            case DOWN:
                animation = stillRightAnimation;
                break;
            case LEFT:
            case UP:
                animation = stillLeftAnimation;
                break;
        }
        direction = Direction.STAND;
    }

    @Override
    public void remove() {
        removed = true;
        removeTime = 5/2f;
        time = 0;
        animation = deadAnimation;
    }

    @Override
    public void collide(Entity entity) {
        if (entity instanceof Flame) {
            remove();
            // TODO: them diem cho player.
        }
    }
}
