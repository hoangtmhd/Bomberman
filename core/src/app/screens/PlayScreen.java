package app.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import entities.Entity;
import entities.EntityMapData;
import entities.changeable.character.Bomber;
import entities.changeable.character.Enemy.Enemy;
import entities.changeable.inactive.Brick;
import entities.changeable.inactive.InactiveEntity;
import entities.changeable.inactive.bomb.Bomb;
import entities.changeable.inactive.item.BombItem;
import entities.changeable.inactive.item.FlameItem;
import entities.changeable.inactive.item.Item;
import entities.changeable.inactive.item.SpeedItem;
import entities.still.Grass;
import entities.still.Portal;
import entities.still.StillEntity;
import entities.still.Wall;
import graphics.Sprite;
import input.PlayerInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayScreen implements Screen {
    private final Stage stage;
    private final Game game;

    private boolean isGamePaused;
    private int lifeLeft;
    private int enemyLeft;

    public static final int CAMERA_WIDTH = 16;
    public static final int CAMERA_HEIGHT = 13;

    public static final int SCALED_SIZE = Entity.ENTITY_SIZE * Sprite.SCALED_RADIUS;

    private Bomber bomber;
    private final ArrayList<Portal> portals = new ArrayList<>();
    private final ArrayList<StillEntity> stillEntities = new ArrayList<>();
    private final ArrayList<InactiveEntity> inactiveEntities = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<ArrayList<EntityMapData>> mapData = new ArrayList<>();

    private final int curLevel;


    public PlayScreen(Game game, int curLevel, int curLifeLeft) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        isGamePaused = false;
        lifeLeft = curLifeLeft;
        enemyLeft = 0;

        this.curLevel = curLevel;
        // create.
        try {
            getLevelData(curLevel);
        } catch (Exception e) {
            System.exit(0);
        }
    }

    private void getLevelData(int curLevel) throws FileNotFoundException {
        String levelFilePath = "levels/Level" + String.format("%02d", curLevel) + ".txt";
        Scanner input = new Scanner(new File(levelFilePath));

        int WORLD_HEIGHT = input.nextInt();
        int WORLD_WIDTH = input.nextInt();
        input.nextLine();

        for (int h = 0; h < WORLD_HEIGHT; ++h) {
            mapData.add(new ArrayList<EntityMapData>());
            for (int w = 0; w < WORLD_WIDTH; ++w) {
                mapData.get(h).add(EntityMapData.GRASS);
            }
        }

        for (int h = 0; h < WORLD_HEIGHT; ++h) {
            String lineData = input.nextLine();
            for (int x = 0; x < WORLD_WIDTH; ++x) {
                int y = WORLD_HEIGHT - h - 1;
                EntityMapData data = EntityMapData.valueOf(lineData.charAt(x));
                // Player.
                if (data == EntityMapData.BOMBER) {
                    bomber = new Bomber(x, y, mapData);
                }

                // still Entities.
                if (data == EntityMapData.WALL) {
                    stillEntities.add(new Wall(x, y));
                    mapData.get(h).set(x, EntityMapData.WALL);
                } else if (data == EntityMapData.PORTAL) {
                    stillEntities.add(new Grass(x, y));
                    portals.add(new Portal(x, y));
                    inactiveEntities.add(new Brick(x, y));
                    mapData.get(h).set(x, EntityMapData.BRICK);
                } else {
                    stillEntities.add(new Grass(x, y));
                }

                // inactive Entities.
                switch (data) {
                    case BRICK:
                        // Brick.
                        inactiveEntities.add(new Brick(x, y));
                        mapData.get(h).set(x, EntityMapData.BRICK);
                        break;
                    case BOMB_ITEM:
                        // Bomb Item.
                        inactiveEntities.add(new BombItem(x, y, mapData));
                        inactiveEntities.add(new Brick(x, y));
                        mapData.get(h).set(x, EntityMapData.BRICK);
                        break;
                    case FLAME_ITEM:
                        // Flame Item.
                        inactiveEntities.add(new FlameItem(x, y, mapData));
                        inactiveEntities.add(new Brick(x, y));
                        mapData.get(h).set(x, EntityMapData.BRICK);
                        break;
                    case SPEED_ITEM:
                        // Speed Item.
                        inactiveEntities.add(new SpeedItem(x, y, mapData));
                        inactiveEntities.add(new Brick(x, y));
                        mapData.get(h).set(x, EntityMapData.BRICK);
                        break;
                }

                // changeable Entities.
                switch (data) {
                    case BALLOOM:
                        // Balloom.
                        break;
                    case ONEAL:
                        // Oneal.
                        break;
                }
            }
        }

        // Show.
        for (Entity entity : stillEntities) {
            stage.addActor(entity);
        }
        for (Entity entity : portals) {
            stage.addActor(entity);
        }
        for (Entity entity : inactiveEntities) {
            stage.addActor(entity);
        }
        for (Entity entity : enemies) {
            stage.addActor(entity);
        }
        stage.addActor(bomber);
        input.close();
    }

    private void checkCollision() {
        if (enemyLeft == 0) {
            for (Portal portal : portals) {
                if (Intersector.overlaps(bomber.getHitBox(), portal.getHitBox())) {
                    // Win Animation.
                    nextLevel();
                }
            }
        }
        for (Entity entity : inactiveEntities) {
            if (!(entity instanceof Item)) continue;
            Item item = (Item) entity;
            if (Intersector.overlaps(bomber.getHitBox(), entity.getHitBox()) && !item.hide()) {
                bomber.collide(entity);
            }
        }
        for (Entity entity : enemies) {
            if (Intersector.overlaps(bomber.getHitBox(), entity.getHitBox())) {
                bomber.collide(entity);
            }
        }
    }

    private void nextLevel() {
        game.setScreen(new PlayScreen(game, curLevel + 1, lifeLeft));
    }

    private void gameOver() {
        if (lifeLeft == 0) {
            // Game Over Screen
            return;
        }
        --lifeLeft;
        bomber.setRemove(true);
        bomber.reset();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new PlayerInput(bomber));
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setTitle("Gameplay");

        if (!isGamePaused) {
            checkCollision();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        isGamePaused = true;
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
