package app.game;

import app.screen.MenuScreen;
import com.badlogic.gdx.Game;

public class BombermanGame extends Game {
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
}
