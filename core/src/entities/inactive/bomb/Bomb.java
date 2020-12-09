package entities.inactive.bomb;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.character.Player;
import entities.inactive.Inactive;

public abstract class Bomb extends Inactive {
    private final int flameRadius;
    protected Player player;

    protected float exploreTime;

    public float getExploreTime() {
        return exploreTime;
    }

    public void setExploreTime(float exploreTime) {
        this.exploreTime = exploreTime;
    }

    public int getFlameRadius() {
        return flameRadius;
    }

    public Bomb(Sprite sprite, Player player) {
        super(sprite);

        this.player = player;
        this.flameRadius = player.getFlameRadius();

        exploreTime = 2f;
    }

    @Override
    public void remove() {
        removed = true;
        player.incBomb();
    }
}
