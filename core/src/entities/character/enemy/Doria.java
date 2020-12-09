package entities.character.enemy;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.character.Player;
import entities.character.enemy.ai.AIMedium;

public class Doria extends Enemy {

    public Doria(Sprite sprite, BlockedManagement blockedManagement, Player player) {
        super(sprite, blockedManagement, "kondoria");
        ai = new AIMedium(player, this);
        points = 500;
        MAX_STEPS = 3f;
        steps = 0f;
        speed = 15f;
        moveOnBrick = true;
        moveOnBomb = true;
    }
}
