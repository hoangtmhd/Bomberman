package app.management.map;

public enum Direction {
    STAND(0, 0),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final float x;
    private final float y;

    Direction(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
