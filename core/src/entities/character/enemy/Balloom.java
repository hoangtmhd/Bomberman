package entities.character.enemy;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Balloom extends Enemy {
    public Balloom(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "balloom");
    }
}
