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
                hitBox.setX(hitBox.getX() + 1);
                hitBox.setWidth(hitBox.getWidth() - 2);
                hitBox.setHeight(hitBox.getHeight() - 1);
                animation = getAnimation("explosion_vertical_top_last");
                break;
            case DOWN:
                hitBox.setX(hitBox.getX() + 1);
                hitBox.setY(hitBox.getY() + 1);
                hitBox.setWidth(hitBox.getWidth() - 2);
                hitBox.setHeight(hitBox.getHeight() - 1);
                animation = getAnimation("explosion_vertical_down_last");
                break;
            case RIGHT:
                hitBox.setY(hitBox.getY() + 1);
                hitBox.setWidth(hitBox.getWidth() - 1);
                hitBox.setHeight(hitBox.getHeight() - 2);
                animation = getAnimation("explosion_horizontal_right_last");
                break;
            case LEFT:
                hitBox.setX(hitBox.getX() + 1);
                hitBox.setY(hitBox.getY() + 1);
                hitBox.setWidth(hitBox.getWidth() - 1);
                hitBox.setHeight(hitBox.getHeight() - 2);
                animation = getAnimation("explosion_horizontal_left_last");
                break;
            case VERTICAL:
                hitBox.setX(hitBox.getX() + 1);
                hitBox.setWidth(hitBox.getWidth() - 2);
                animation = getAnimation("explosion_vertical");
                break;
            case HORIZONTAL:
                hitBox.setY(hitBox.getY() + 1);
                hitBox.setHeight(hitBox.getHeight() - 2);
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
