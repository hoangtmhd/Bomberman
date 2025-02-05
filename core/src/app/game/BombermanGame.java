package app.game;

import app.screen.MenuScreen;
import com.badlogic.gdx.Game;

public class BombermanGame extends Game {
	public static final int MAX_LEVEL = 2;
	private int highScore = 0;

	public int getHighScore() {
		return highScore;
	}

	public void updateHighScore(int score) {
		highScore = Math.max(highScore, score);
	}

	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
