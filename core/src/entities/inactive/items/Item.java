package entities.inactive.items;

import app.management.map.BlockedManagement;
import app.management.map.BlockedType;
import app.management.map.MapManagement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.inactive.Inactive;

public abstract class Item extends Inactive {
    private final BlockedManagement blockedManagement;

    private final Music sound;

    public Item(Sprite sprite, BlockedManagement blockedManagement, String name) {
        super(sprite);
        this.blockedManagement = blockedManagement;

        TextureAtlas textureAtlas = new TextureAtlas();
        textureAtlas.addRegion("0000", prepareRegion("powerup_" + name + ".png"));
        animation = new Animation<TextureRegion>(0f, textureAtlas.getRegions());

        sound = Gdx.audio.newMusic(Gdx.files.internal("sound/Item.wav"));
        sound.setLooping(false);
    }

    @Override
    public void remove() {
        removed = true;
        sound.play();
    }

    protected boolean showing() {
        int xUnit = (int) getX() / MapManagement.CELL_SIZE;
        int yUnit = (int) getY() / MapManagement.CELL_SIZE;

        return (blockedManagement.getData(xUnit, yUnit) != BlockedType.BRICK);
    }
}
