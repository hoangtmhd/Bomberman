package app.screen;

import app.game.BombermanGame;
import app.management.camera.CameraManagement;
import app.management.map.MapManagement;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen implements Screen {
    public static final int START_LEVEL = 1;
    public static final int START_LIFE = 3;

    private final Game game;

    private final int curLevel;
    private final int lifeLeft;

    private final MapManagement mapManagement;
    private final CameraManagement cameraManagement;

    private final Music music;

    public PlayScreen(Game game, int level, int curLifeLeft) {
        this.game = game;
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/soundtrack.wav"));

        curLevel = level;
        lifeLeft = curLifeLeft;

        mapManagement = new MapManagement(curLevel);
        cameraManagement = new CameraManagement(mapManagement.getWidth(), mapManagement.getHeight(),
                mapManagement.getPlayer());
        music.play();
        music.setLooping(true);
    }

    private void nextLevel() {
        System.out.println("New Level");
        if (curLevel == BombermanGame.MAX_LEVEL) {
            System.exit(0);
        }
        else {
            game.setScreen(new PlayScreen(game, curLevel + 1, lifeLeft));
        }
    }

    private void gameOver() {
        System.out.println("Game Over");
        if (lifeLeft == 1) {
            System.exit(0);
        } else {
            game.setScreen(new PlayScreen(game, curLevel, lifeLeft - 1));
        }
    }

    @Override
    public void show() {
        mapManagement.show();
        cameraManagement.show();
    }

    @Override
    public void render(float delta) {
        if (mapManagement.isWin()) {
            nextLevel();
        }

        if (mapManagement.isLose()) {
            gameOver();
        }

        Gdx.graphics.setTitle("Gameplay");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraManagement.render(delta);

        mapManagement.setView(cameraManagement.getCamera());
        mapManagement.render(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            if (music.isPlaying()) music.pause();
            else music.play();
        }
    }

    @Override
    public void resize(int width, int height) {
        mapManagement.resize(width, height);
        cameraManagement.resize(width, height);
    }

    @Override
    public void pause() {
        music.pause();
        mapManagement.pause();
        cameraManagement.pause();
    }

    @Override
    public void resume() {
        music.play();
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
        music.stop();
        music.dispose();
    }
}