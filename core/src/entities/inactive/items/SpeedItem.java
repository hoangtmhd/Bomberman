package entities.inactive.items;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.character.Player;

public class SpeedItem extends Item {
    public SpeedItem(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "speed");
    }

    @Override
    public void collide(Entity entity) {
        if (showing() && entity instanceof Player) {
            Player player = (Player) entity;
            player.incSpeed();
            remove();
        }
    }
}
