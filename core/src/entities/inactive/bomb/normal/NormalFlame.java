package entities.inactive.bomb.normal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.inactive.bomb.Flame;
import entities.inactive.bomb.FlameType;

public class NormalFlame extends Flame {
    public NormalFlame(Sprite sprite, FlameType type) {
        super(sprite);

        switch (type) {
            case CENTER:
                animation = getAnimation("bomb_exploded");
                break;
            case UP:
                animation = getAnimation("explosion_vertical_top_last");
                break;
            case DOWN:
                animation = getAnimation("explosion_vertical_down_last");
                break;
            case RIGHT:
                animation = getAnimation("explosion_horizontal_right_last");
                break;
            case LEFT:
                animation = getAnimation("explosion_horizontal_left_last");
                break;
            case VERTICAL:
                animation = getAnimation("explosion_vertical");
                break;
            case HORIZONTAL:
                animation = getAnimation("explosion_horizontal");
                break;
        }
    }

    @Override
    public void remove() {
        removed = true;
    }

    @Override
    public void collide(Entity entity) {

    }
}
