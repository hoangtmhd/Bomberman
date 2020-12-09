package app.management.map;

import app.game.GameMode;
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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import entities.character.Player;
import entities.character.enemy.*;
import entities.inactive.Brick;
import entities.inactive.Portal;
import entities.inactive.bomb.Flame;
import entities.inactive.items.Item;

import java.util.ArrayList;
import java.util.LinkedList;

public class MapManagement implements Management {
    public static final int CELL_SIZE = 16;
    private final GameMode gameMode;

    private boolean win = false;
    private boolean lose = false;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final BlockedManagement blockedManagement;
    private final BombManagement bombManagement;

    private final Player player;

    private final ArrayList<Item> items = new ArrayList<>();
    private final ArrayList<Portal> portals = new ArrayList<>();
    private final ArrayList<Brick> bricks = new ArrayList<>();

    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private final LinkedList<Flame> flames = new LinkedList<>();

    public MapManagement(int curLevel, GameMode gameMode) {
        this.gameMode = gameMode;

        String levelFilePath = "levels/Level" + String.format("%d", curLevel) + ".tmx";
        map = new TmxMapLoader().load(levelFilePath);

        blockedManagement = new BlockedManagement((TiledMapTileLayer) map.getLayers().get("Still"));

        bombManagement = new BombManagement(blockedManagement, flames);

        map.getLayers().get("Inactive").setVisible(false);
        map.getLayers().get("Enemy").setVisible(false);

        renderer = new OrthogonalTiledMapRenderer(map);

        // Player.
        player = new Player(getPlayerInitSprite(), getPlayerInitHitBox(),
                blockedManagement, bombManagement);

        // Inactive.
        blockedManagement.initInactive((TiledMapTileLayer) map.getLayers().get("Inactive"),
                items, portals, bricks);

        // Enemy.
        TiledMapTileLayer enemyLayer = (TiledMapTileLayer) map.getLayers().get("Enemy");
        for (int y = 0; y < blockedManagement.getHeight(); ++y) {
            for (int x = 0; x < blockedManagement.getWidth(); ++x)
                if (enemyLayer.getCell(x, y) != null) {
                    String key = enemyLayer.getCell(x, y).getTile().getProperties().get("enemy", String.class);
                    switch (key) {
                        case "balloom":
                            enemies.add(new Balloom(getInitEnemySprite(x, y), blockedManagement));
                            break;
                        case "oneal":
                            enemies.add(new Oneal(getInitEnemySprite(x, y), blockedManagement, player));
                            break;
                        case "dahl":
                            enemies.add(new Dahl(getInitEnemySprite(x, y), blockedManagement));
                            break;
                        case "doria":
                            enemies.add(new Doria(getInitEnemySprite(x, y), blockedManagement, player));
                            break;
                    }
                }
        }
    }

    public float getWidth() {
        return blockedManagement.getWidth();
    }

    public float getHeight() {
        return blockedManagement.getHeight();
    }

    public Player getPlayer() {
        return player;
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

    private boolean checkEnemy() {
        for (Enemy enemy : enemies) if (!enemy.isDestroy()) {
            return false;
        }
        return true;
    }

    private void checkCollision() {
        if (player.isLive()) {
            for (Portal portal : portals) if (!portal.isDestroy() && portal.showing() && !win) {
                if (Intersector.overlaps(player.getHitBox(), portal.getHitBox())) {
                    System.out.println("Hit Portal");
                    win = checkEnemy() || (gameMode == GameMode.CANT_KILL_ENEMY);
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
        }

        if (flames.size() > 0) {
            for (Flame flame : flames) {
                if (player.isLive() && Intersector.overlaps(flame.getHitBox(), player.getHitBox())) {
                    player.collide(flame);
                }
                for (Enemy enemy : enemies) if (!enemy.isDestroy()) {
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

        if (gameMode == GameMode.CANT_KILL_ENEMY) {
            for (Enemy enemy : enemies) if (enemy.isDestroy()) {
                lose = true;
                return;
            }
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

        bombManagement.draw(delta, renderer.getBatch());

        for (Enemy enemy : enemies) {
            enemy.draw(renderer.getBatch());
        }


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
        player.getTexture().dispose();
        bombManagement.dispose();
        for (Enemy enemy : enemies) enemy.getTexture().dispose();
        for (Brick brick : bricks) brick.getTexture().dispose();
        for (Item item : items) item.getTexture().dispose();
        for (Portal portal : portals) portal.getTexture().dispose();
    }
}
