package entities.character.enemy;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.character.enemy.ai.AILow;

public class Balloom extends Enemy {
    public Balloom(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "balloom");
        ai = new AILow();
        points = 100;
        MAX_STEPS = 5f;
        steps = 0f;
    }
}
