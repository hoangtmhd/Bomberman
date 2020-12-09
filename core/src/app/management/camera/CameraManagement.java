package app.management.camera;

import app.management.Management;
import app.management.map.MapManagement;
import com.badlogic.gdx.graphics.OrthographicCamera;
import entities.character.Player;

public class CameraManagement implements Management {
    public static final int SCALED_RADIUS = 2;
    public static final int CAMERA_WIDTH = MapManagement.CELL_SIZE * 40;
    public static final int CAMERA_HEIGHT = MapManagement.CELL_SIZE * 25;

    private final float mapWidth;
    private final float mapHeight;

    private final OrthographicCamera camera;
    private final Player player;

    public CameraManagement(float mapWidth, float mapHeight, Player player) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.player = player;
        camera = new OrthographicCamera();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void show() {
        camera.zoom = 1f/SCALED_RADIUS;
    }

    @Override
    public void render(float delta) {
        updatePosition();
        camera.update();
    }

    private void updatePosition() {
        // calculate x.
        float xBase = (1f * CAMERA_WIDTH / SCALED_RADIUS) / 2;
        float xLim = mapWidth * MapManagement.CELL_SIZE - xBase;

        float xFocus = player.getX();
        if (xFocus < xBase) {
            xFocus = xBase;
        }
        if (xFocus > xLim) {
            xFocus = xLim;
        }

        // calculate y.
        float yBase = (1f * CAMERA_HEIGHT / SCALED_RADIUS) / 2;
        float yLim = mapHeight * MapManagement.CELL_SIZE - yBase;

        float yFocus = player.getY();
        if (yFocus < yBase) {
            yFocus = yBase;
        }
        if (yFocus > yLim) {
            yFocus = yLim;
        }

        camera.position.set(xFocus, yFocus, 0);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
    }
}
