package app.run;

import app.screens.PlayScreen;
import com.badlogic.gdx.Game;

public class BombermanGame extends Game {
	@Override
	public void create () {
		setScreen(new PlayScreen(this, 1));
	}

	@Override
	public void render () {
		super.render();
		// Update.

	}
	
	@Override
	public void dispose () {
	}
}
