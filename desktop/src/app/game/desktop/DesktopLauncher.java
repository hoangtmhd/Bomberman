package app.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import app.game.BombermanGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bomberman";
		config.width = 16 * 40;
		config.height = 16 * 25;
		config.resizable = false;

		new LwjglApplication(new BombermanGame(), config);
	}
}
