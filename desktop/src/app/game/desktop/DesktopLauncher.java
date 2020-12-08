package app.game.desktop;

import app.management.camera.CameraManagement;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import app.game.BombermanGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bomberman";
		config.width = CameraManagement.CAMERA_WIDTH;
		config.height = CameraManagement.CAMERA_HEIGHT;
		config.resizable = false;

		new LwjglApplication(new BombermanGame(), config);
	}
}
