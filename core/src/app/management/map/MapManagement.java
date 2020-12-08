package app.management.map;

import app.management.Management;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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

        player = new Player(new Sprite(new Texture("sprites/player_right.png")), blockedManagement);
        player.setPosition(5 * CELL_SIZE,
                (((TiledMapTileLayer) map.getLayers().get("Still")).getHeight() - 7) * CELL_SIZE);
        Gdx.input.setInputProcessor(player);
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
