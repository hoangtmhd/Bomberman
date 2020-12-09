package entities.character.enemy;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.character.enemy.ai.AILow;

import java.util.Random;

public class Dahl extends Enemy {
    private float normalTime = 0f;
    private float jumpTime = 0f;

    public Dahl(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "doll");
        ai = new AILow();
        points = 400;
        MAX_STEPS = 5f;
        steps = 0f;
        speed = 35;
    }

    //thi thoang co 1 buoc nhay (25%)
    @Override
    public void update(float delta) {
        // change direction.
        Random random = new Random();
        int jump = random.nextInt(2);
        if (jump == 0) {
            if (normalTime <= 0 && jumpTime <= 0) {
                speed *= 2;
                jumpTime = 3f;
                normalTime = 2f;
            }
            if (steps <= 0f) {
                direction = ai.calculateDirection();
                changeDirection(direction);
                steps = MAX_STEPS;
            }
        }
        super.update(delta);
        if (jumpTime > 0) {
            jumpTime -= delta;
        } else {
            speed = 35;
            if (normalTime > 0) {
                normalTime -= delta;
            }
        }
    }
}
