package graphics;

public enum Direction {
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int xDir;
    private final int yDir;

    Direction(int xDir, int yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public int getX() {
        return xDir;
    }

    public int getY() {
        return yDir;
    }
}
