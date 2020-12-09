package entities.inactive.bomb.normal;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Entity;
import entities.character.Player;
import entities.inactive.bomb.Bomb;

public class NormalBomb extends Bomb {
    public NormalBomb(Sprite sprite, Player player) {
        super(sprite, player);

        TextureAtlas stillTA = new TextureAtlas();
        stillTA.addRegion("0000", prepareRegion("bomb.png"));
        stillTA.addRegion("0001", prepareRegion("bomb_1.png"));
        stillTA.addRegion("0002", prepareRegion("bomb_2.png"));
        stillTA.addRegion("0003", prepareRegion("bomb_1.png"));
        animation = new Animation<TextureRegion>(1/5f, stillTA.getRegions());
    }

    @Override
    public void collide(Entity entity) {
    }
}
