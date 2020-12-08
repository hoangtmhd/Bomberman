package app.management.map;

import app.management.Management;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import entities.character.Player;

public class MapManagement implements Management {
    public static final int CELL_SIZE = 16;

    private final int curLevel;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Player player;

    private BlockedManagement blockedManagement;

    public MapManagement(int curLevel) {
        this.curLevel = curLevel;
    }

    public void setView(OrthographicCamera camera) {
        renderer.setView(camera);
    }

    @Override
    public void show() {
        String levelFilePath = "levels/Level" + String.format("%d", curLevel) + ".tmx";
        map = new TmxMapLoader().load(levelFilePath);

        blockedManagement = new BlockedManagement((TiledMapTileLayer) map.getLayers().get("Still"));

        renderer = new OrthogonalTiledMapRenderer(map);

        player = new Player(getPlayerInitSprite(), getPlayerInitHitBox(), blockedManagement);

        Gdx.input.setInputProcessor(player);
    }

    private Sprite getPlayerInitSprite() {
        TextureMapObject tile = (TextureMapObject) (map.getLayers().get("Player").getObjects().get("zTile"));

        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprites/player_right.png")));
        sprite.setPosition(tile.getX(), tile.getY());
        return sprite;
    }

    private Rectangle getPlayerInitHitBox() {
        return ((RectangleMapObject) map.getLayers().get("Player").getObjects().get("zHitBox")).getRectangle();
    }

    @Override
    public void render(float delta) {
        renderer.render();

        renderer.getBatch().begin();
        // draw to batch.
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
