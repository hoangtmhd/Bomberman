package app.run;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graphics.Sprite;

import java.awt.*;

public class BombermanGame extends ApplicationAdapter {
	Stage stage;
	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());

		// Create.
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

		// Update.

	}
	
	@Override
	public void dispose () {
		// Dispose.

		stage.dispose();
	}
}
