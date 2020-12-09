package app.management.map;

import app.management.Management;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import entities.character.Player;
import entities.character.enemy.Balloom;
import entities.character.enemy.Enemy;
import entities.character.enemy.Oneal;
import entities.inactive.Brick;
import entities.inactive.Portal;
import entities.inactive.bomb.Flame;
import entities.inactive.items.Item;

import java.util.ArrayList;
import java.util.LinkedList;

public class MapManagement implements Management {
    public static final int CELL_SIZE = 16;

    private boolean win;
    private boolean lose;

    private final int curLevel;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private BombManagement bombManagement;

    private Player player;

    private ArrayList<Item> items;
    private ArrayList<Portal> portals;
    private ArrayList<Brick> bricks;

    private ArrayList<Enemy> enemies;

    private LinkedList<Flame> flames;

    public MapManagement(int curLevel) {
        this.curLevel = curLevel;
        win = false;
        lose = false;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLose() {
        return lose;
    }

    public void setView(OrthographicCamera camera) {
        renderer.setView(camera);
    }

    @Override
    public void show() {
        String levelFilePath = "levels/Level" + String.format("%d", curLevel) + ".tmx";
        map = new TmxMapLoader().load(levelFilePath);

        BlockedManagement blockedManagement = new BlockedManagement(
                (TiledMapTileLayer) map.getLayers().get("Still"));

        flames = new LinkedList<>();

        bombManagement = new BombManagement(blockedManagement, flames);

        map.getLayers().get("Inactive").setVisible(false);
        map.getLayers().get("Enemy").setVisible(false);

        renderer = new OrthogonalTiledMapRenderer(map);

        // Player.
        player = new Player(getPlayerInitSprite(), getPlayerInitHitBox(),
                blockedManagement, bombManagement);

        // Inactive.
        items = new ArrayList<>();
        portals = new ArrayList<>();
        bricks = new ArrayList<>();
        blockedManagement.initInactive((TiledMapTileLayer) map.getLayers().get("Inactive"),
                items, portals, bricks);

        // Enemy.
        enemies = new ArrayList<>();

        TiledMapTileLayer enemyLayer = (TiledMapTileLayer) map.getLayers().get("Enemy");
        for (int y = 0; y < blockedManagement.getHeight(); ++y) {
            for (int x = 0; x < blockedManagement.getWidth(); ++x) if (enemyLayer.getCell(x, y) != null) {
                MapProperties mapProperties = enemyLayer.getCell(x, y).getTile().getProperties();
                if (mapProperties.containsKey("balloom")) {
                    enemies.add(new Balloom(getInitEnemySprite(x, y), blockedManagement));
                } else if (mapProperties.containsKey("oneal")) {
                    enemies.add(new Oneal(getInitEnemySprite(x, y), blockedManagement));
                }
            }
        }

        Gdx.input.setInputProcessor(player);
    }

    private Sprite getInitEnemySprite(int xUnit, int yUnit) {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprites/player_right.png")));
        sprite.setPosition(xUnit * MapManagement.CELL_SIZE, yUnit * MapManagement.CELL_SIZE);
        return sprite;
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

    private void checkCollision() {
        for (Portal portal : portals) if (!portal.isDestroy()) {
            if (Intersector.overlaps(player.getHitBox(), portal.getHitBox())) {
                System.out.println("Hit Portal");
                win = true;
            }
        }
        for (Item item : items) if (!item.isDestroy()) {
            if (Intersector.overlaps(player.getHitBox(), item.getHitBox())) {
                System.out.println("Hit Item");
                player.collide(item);
            }
        }

        for (Enemy enemy : enemies) if (!enemy.isDestroy()) {
            if (Intersector.overlaps(player.getHitBox(), enemy.getHitBox())) {
                System.out.println("Hit Enemy");
                player.collide(enemy);
            }
        }

        if (flames.size() > 0) {
            for (Flame flame : flames) {
                if (Intersector.overlaps(flame.getHitBox(), player.getHitBox())) {
                    player.collide(flame);
                }
                for (Enemy enemy : enemies) {
                    if (Intersector.overlaps(flame.getHitBox(), enemy.getHitBox())) {
                        enemy.collide(flame);
                    }
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        checkCollision();

        if (player.isDestroy()) {
            lose = true;
            return;
        }

        renderer.render();

        renderer.getBatch().begin();
        // draw to batch.
        for (Item item : items) {
            item.draw(renderer.getBatch());
        }

        for (Portal portal : portals) {
            portal.draw(renderer.getBatch());
        }

        for (Brick brick : bricks) {
            brick.draw(renderer.getBatch());
        }

        for (Enemy enemy : enemies) {
            enemy.draw(renderer.getBatch());
        }

        bombManagement.draw(delta, renderer.getBatch());

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
