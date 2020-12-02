package app.run.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import app.run.BombermanGame;
import entities.Entity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bomberman";
		config.width = Entity.ENTITY_SIZE * 40;
		config.height = Entity.ENTITY_SIZE * 25;
		config.resizable = false;

		new LwjglApplication(new BombermanGame(), config);
	}
}
