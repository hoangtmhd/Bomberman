package entities.inactive.items;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.character.Player;

public class MoveBombItem extends Item {
    public MoveBombItem(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "bombpass");
    }

    @Override
    public void remove() {
        removed = true;
    }

    @Override
    public void collide(Entity entity) {
        if (showing() && entity instanceof Player) {
            Player player = (Player) entity;
            player.moveOnBomb(true);
            remove();
        }
    }
}
