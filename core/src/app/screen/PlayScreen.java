package app.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PlayScreen implements Screen {
    public static final int START_LEVEL = 1;
    public static final int START_LIFE = 3;

    private final Game game;
    private final Stage stage;

    private final int curLevel;
    private final int lifeLeft;

    public PlayScreen(Game game, int level, int curLifeLeft) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        curLevel = level;
        lifeLeft = curLifeLeft;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
