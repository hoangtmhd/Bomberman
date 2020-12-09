package entities.inactive.items;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.character.Player;

public class BombItem extends Item {
    public BombItem(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "bombs");
    }

    @Override
    public void remove() {
        removed = true;
    }

    @Override
    public void collide(Entity entity) {
        if (hiding() && entity instanceof Player) {
            Player player = (Player) entity;
            player.incBomb();
            remove();
        }
    }
}
