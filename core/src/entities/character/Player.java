package entities.character;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Character {
    public Player(Sprite sprite) {
        super(sprite);
        velocity.y = -16;
    }
}
