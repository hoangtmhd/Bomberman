package app.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import entities.Entity;
import entities.EntityMapData;
import entities.changeable.ChangeableEntity;
import entities.changeable.character.Bomber;
import entities.changeable.inactive.InactiveEntity;
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

    public static final int CAMERA_WIDTH = 16;
    public static final int CAMERA_HEIGHT = 13;

    public static final int SCALED_SIZE = Entity.ENTITY_SIZE * Sprite.SCALED_RADIUS;
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;

    private final ArrayList<StillEntity> stillEntities = new ArrayList<>();
    private final ArrayList<InactiveEntity> inactiveEntities = new ArrayList<>();
    private final ArrayList<ChangeableEntity> changeableEntities = new ArrayList<>();
    private Bomber bomber;

    private final int curLevel;


    public PlayScreen(Game game, int curLevel) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

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

        WORLD_HEIGHT = input.nextInt();
        WORLD_WIDTH = input.nextInt();
        input.nextLine();

        for (int h = 0; h < WORLD_HEIGHT; ++h) {
            String mapData = input.nextLine();
            for (int x = 0; x < WORLD_WIDTH; ++x) {
                int y = WORLD_HEIGHT - h - 1;
                EntityMapData data = EntityMapData.valueOf(mapData.charAt(x));
                // Player.
                if (data == EntityMapData.BOMBER) {
                    bomber = new Bomber(x, y);
                }

                // still Entities.
                StillEntity stillEntity;
                if (data == EntityMapData.WALL) {
                    stillEntity = new Wall(x, y);
                    stillEntities.add(stillEntity);
                } else if (data == EntityMapData.PORTAL) {
                    stillEntity = new Grass(x, y);
                    stillEntities.add(stillEntity);
                    stillEntity = new Portal(x, y);
                    stillEntities.add(stillEntity);
                } else {
                    stillEntity = new Grass(x, y);
                    stillEntities.add(stillEntity);
                }

                // inactive Entities.
                InactiveEntity inactiveEntity;
                switch (data) {
                    case BRICK:
                        // Brick.
                        break;
                    case BOMB_ITEM:
                        // Bomb Item.
                        break;
                    case FLAME_ITEM:
                        // Flame Item.
                        break;
                    case SPEED_ITEM:
                        // Speed Item.
                        break;
                }

                // changeable Entities.
                ChangeableEntity changeableEntity;
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
        for (Entity entity : inactiveEntities) {
            stage.addActor(entity);
        }
        for (Entity entity : changeableEntities) {
            stage.addActor(entity);
        }
        stage.addActor(bomber);
        input.close();
    }

    private void nextLevel() {
        game.setScreen(new PlayScreen(game, curLevel + 1));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new PlayerInput(bomber));
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setTitle("Gameplay");
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
