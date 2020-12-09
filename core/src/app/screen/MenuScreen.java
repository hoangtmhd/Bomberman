package app.screen;

import app.game.GameMode;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen  implements Screen {
    private final Game game;
    private final Stage stage;
    private final Skin skin;

    private GameMode gameMode;
    private boolean muteMusic;

    public MenuScreen(final Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Label label = new Label("Bomberman", skin);
        label.setPosition((float) Gdx.graphics.getWidth()/2 - (label.getWidth()/2),
                (float) Gdx.graphics.getHeight()*5/6);
        stage.addActor(label);

        final Button startButton = new TextButton("Start Game", skin);
        startButton.setSize((float) Gdx.graphics.getWidth()/5,
                (float) Gdx.graphics.getHeight()/10);
        startButton.setPosition((float) Gdx.graphics.getWidth()/8*7 - startButton.getWidth()/2,
                (float) Gdx.graphics.getHeight()/7 + startButton.getHeight()/2);
        startButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startGame();
                return true;
            }
        });
        stage.addActor(startButton);

        final Button exitButton = new TextButton("Exit Game", skin);
        exitButton.setSize((float) Gdx.graphics.getWidth()/5,
                (float) Gdx.graphics.getHeight()/10);
        exitButton.setPosition((float) Gdx.graphics.getWidth()/8*7 - exitButton.getWidth()/2,
                (float) Gdx.graphics.getHeight()/7 - exitButton.getHeight()/2);
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                exitGame();
                return true;
            }
        });
        stage.addActor(exitButton);

        gameMode = GameMode.NORMAL;
        final TextButton modeButton = new TextButton("Mode: Normal", skin);
        modeButton.setSize((float) Gdx.graphics.getWidth()/5,
                (float) Gdx.graphics.getHeight()/10);
        modeButton.setPosition((float) Gdx.graphics.getWidth()/8 - modeButton.getWidth()/2,
                (float) Gdx.graphics.getHeight()/7 + modeButton.getHeight()/2);
        modeButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (gameMode) {
                    case NORMAL:
                        gameMode = GameMode.CANT_KILL_ENEMY;
                        modeButton.setText("Mode: Friendly");
                        break;
                    case CANT_KILL_ENEMY:
                        gameMode = GameMode.ICE_BOMB;
                        modeButton.setText("Mode: Ice");
                        break;
                    case ICE_BOMB:
                        gameMode = GameMode.NORMAL;
                        modeButton.setText("Mode: Normal");
                        break;
                }
                return true;
            }
        });
        stage.addActor(modeButton);

        muteMusic = false;
        final TextButton muteButton = new TextButton("Mute: Off", skin);
        muteButton.setSize((float) Gdx.graphics.getWidth()/5,
                (float) Gdx.graphics.getHeight()/10);
        muteButton.setPosition((float) Gdx.graphics.getWidth()/8 - modeButton.getWidth()/2,
                (float) Gdx.graphics.getHeight()/7 - modeButton.getHeight()/2);
        muteButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (muteMusic) {
                    muteMusic = false;
                    muteButton.setText("Mute: Off");
                } else {
                    muteMusic = true;
                    muteButton.setText("Mute: On");
                }
                return true;
            }
        });
        stage.addActor(muteButton);
    }

    private void startGame() {
        System.out.println("Start Game");
        dispose();
        game.setScreen(new PlayScreen(game, PlayScreen.START_LEVEL, PlayScreen.START_LIFE,
                gameMode, muteMusic));
    }

    private void exitGame() {
        System.out.println("Exit Game");
        dispose();
        System.exit(0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setTitle("Menu Screen");
        Gdx.gl.glClearColor(0.4765625f, 0.1171875f, 0.5703125f, 1);
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
        skin.dispose();
        stage.dispose();
    }
}
