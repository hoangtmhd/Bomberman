package entities.character.enemy;

import app.management.map.BlockedManagement;
import app.management.map.MapManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.character.Player;
import entities.character.enemy.ai.AILow;

public class Oneal extends Enemy {
    private final Player player;

    public Oneal(Sprite sprite, BlockedManagement blockedManagement, Player player) {
        super(sprite, blockedManagement, "oneal");
        ai = new AILow();
        points = 200;
        MAX_STEPS = 5f;
        steps = 0f;
        speed = 40f;

        this.player = player;
    }

    @Override
    public void update(float delta) {
        if (Math.abs(player.getHitBox().getX() - hitBox.getX()) < 2 * MapManagement.CELL_SIZE &&
                Math.abs(player.getHitBox().getY() - hitBox.getY()) < 2 * MapManagement.CELL_SIZE) {
            AILow thisAI = (AILow) ai;
            direction = thisAI.nearbyPlayer(player);
            changeDirection(direction);
            speed = 55f;
        } else {
            speed = 40f;
        }

        super.update(delta);
    }
}
