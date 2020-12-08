package entities.character.enemy;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.character.Character;

public abstract class Enemy extends Character {
    public Enemy(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement);
    }

    @Override
    public void update(float delta) {
        // change direction.
        super.update(delta);
    }
}
