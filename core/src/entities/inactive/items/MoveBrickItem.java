package entities.inactive.items;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.character.Player;

public class MoveBrickItem extends Item {
    public MoveBrickItem(Sprite sprite, BlockedManagement blockedManagement) {
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
            player.moveOnBrick(true);
            remove();
        }
    }
}
