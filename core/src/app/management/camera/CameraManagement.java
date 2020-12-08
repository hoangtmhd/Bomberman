package app.management.camera;

import app.management.Management;
import app.management.map.MapManagement;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraManagement implements Management {
    public static final int SCALED_RADIUS = 2;
    public static final int CAMERA_WIDTH = MapManagement.CELL_SIZE * 40;
    public static final int CAMERA_HEIGHT = MapManagement.CELL_SIZE * 25;

    private OrthographicCamera camera;

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.zoom = 1f/SCALED_RADIUS;
    }

    @Override
    public void render(float delta) {
        updatePosition();
        camera.update();
    }

    private void updatePosition() {
        camera.position.set((1f * CAMERA_WIDTH / SCALED_RADIUS) / 2,
                (1f * CAMERA_HEIGHT / SCALED_RADIUS) / 2, 0);
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
