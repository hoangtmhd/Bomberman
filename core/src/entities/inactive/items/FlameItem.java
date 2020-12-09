package entities.inactive.items;

import app.management.map.BlockedManagement;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.character.Player;

public class FlameItem extends Item {
    public FlameItem(Sprite sprite, BlockedManagement blockedManagement) {
        super(sprite, blockedManagement, "flames");
    }

    @Override
    public void collide(Entity entity) {
        if (showing() && entity instanceof Player) {
            Player player = (Player) entity;
            player.incFlameRadius();
            remove();
        }
    }
}
