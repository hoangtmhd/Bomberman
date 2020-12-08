package app.screen;

import app.management.camera.CameraManagement;
import app.management.map.MapManagement;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen implements Screen {
    public static final int START_LEVEL = 1;
    public static final int START_LIFE = 3;

    private final Game game;

    private final int curLevel;
    private int lifeLeft;

    private final MapManagement mapManagement;
    private final CameraManagement cameraManagement;

    public PlayScreen(Game game, int level, int curLifeLeft) {
        this.game = game;

        curLevel = level;
        lifeLeft = curLifeLeft;

        mapManagement = new MapManagement(curLevel);
        cameraManagement = new CameraManagement();
    }

    private void nextLevel() {
        game.setScreen(new PlayScreen(game, curLevel + 1, curLevel));
    }

    private void gameOver() {
        if (lifeLeft == 0) {
            game.setScreen(new MenuScreen(game));
        }
        --lifeLeft;
    }

    @Override
    public void show() {
        mapManagement.show();
        cameraManagement.show();
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setTitle("Gameplay");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraManagement.render(delta);

        mapManagement.setView(cameraManagement.getCamera());
        mapManagement.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        mapManagement.resize(width, height);
        cameraManagement.resize(width, height);
    }

    @Override
    public void pause() {
        mapManagement.pause();
        cameraManagement.pause();
    }

    @Override
    public void resume() {
        mapManagement.resume();
        cameraManagement.resume();
    }

    @Override
    public void hide() {
        mapManagement.hide();
        cameraManagement.hide();
        dispose();
    }

    @Override
    public void dispose() {
        mapManagement.dispose();
        cameraManagement.dispose();
    }
}