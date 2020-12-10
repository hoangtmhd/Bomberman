package app.screen;

import app.game.BombermanGame;
import app.game.GameMode;
import app.management.camera.CameraManagement;
import app.management.map.MapManagement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen implements Screen {
    public static final int START_LEVEL = 1;
    public static final int START_LIFE = 3;

    private final BombermanGame game;
    private final GameMode gameMode;

    private final int curLevel;
    private final int lifeLeft;
    private int curScore;

    private final MapManagement mapManagement;
    private final CameraManagement cameraManagement;

    private final Music music;
    private float pauseMusicGap = 0f;
    private boolean muteMusic;

    public PlayScreen(BombermanGame game, int level, int curLifeLeft, int score,
                      GameMode gameMode, boolean muteMusic) {
        this.game = game;
        this.gameMode = gameMode;
        this.muteMusic = muteMusic;

        music = Gdx.audio.newMusic(Gdx.files.internal("sound/soundtrack.wav"));

        curLevel = level;
        lifeLeft = curLifeLeft;
        curScore = score;

        mapManagement = new MapManagement(curLevel, gameMode);
        cameraManagement = new CameraManagement(mapManagement.getWidth(), mapManagement.getHeight(),
                mapManagement.getPlayer());

        music.play();
        music.setLooping(true);

        if (muteMusic && music.isPlaying()) music.pause();
    }

    private void nextLevel() {
        System.out.println("New Level");
        curScore += mapManagement.getPlayer().getScore();
        game.updateHighScore(curScore);
        dispose();
        if (curLevel == BombermanGame.MAX_LEVEL) {
            game.setScreen(new MenuScreen(game));
        } else {
            game.setScreen(new PlayScreen(game, curLevel + 1, lifeLeft, curScore,
                    gameMode, muteMusic));
        }
    }

    private void gameOver() {
        System.out.println("Game Over");
        curScore += mapManagement.getPlayer().getScore();
        game.updateHighScore(curScore);
        dispose();
        if (lifeLeft == 1) {
            game.setScreen(new MenuScreen(game));
        } else {
            game.setScreen(new PlayScreen(game, curLevel, lifeLeft - 1, curScore,
                    gameMode, muteMusic));
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

        Gdx.graphics.setTitle("Score: " + (curScore + mapManagement.getPlayer().getScore()));

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraManagement.render(delta);

        mapManagement.setView(cameraManagement.getCamera());
        mapManagement.render(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            if (pauseMusicGap <= 0) {
                if (music.isPlaying()) {
                    music.pause();
                    muteMusic = true;
                }
                else {
                    music.play();
                    muteMusic = false;
                }
                pauseMusicGap = 1/2f;
            }
        }
        if (pauseMusicGap > 0) {
            pauseMusicGap -= delta;
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
    }

    @Override
    public void dispose() {
        mapManagement.dispose();
        cameraManagement.dispose();
        music.stop();
        music.dispose();
    }
}