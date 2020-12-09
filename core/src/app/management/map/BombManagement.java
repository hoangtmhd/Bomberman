package app.management.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.inactive.bomb.Bomb;
import entities.inactive.bomb.Flame;
import entities.inactive.bomb.FlameType;
import entities.inactive.bomb.normal.NormalBomb;
import entities.inactive.bomb.normal.NormalFlame;

import java.util.ArrayList;
import java.util.LinkedList;

public class BombManagement {
    private final ArrayList<ArrayList<Bomb>> bombData;
    private final ArrayList<ArrayList<Boolean>> explored;

    private final int width;
    private final int height;

    private final BlockedManagement blockedManagement;
    private final LinkedList<Flame> flames;

    private final Music exploreSound;
    private final Music setBombSound;

    public BombManagement(BlockedManagement blockedManagement, LinkedList<Flame> flames) {
        this.flames = flames;

        this.blockedManagement = blockedManagement;

        exploreSound = Gdx.audio.newMusic(Gdx.files.internal("sound/BOM_11_M.wav"));
        exploreSound.setLooping(false);

        setBombSound = Gdx.audio.newMusic(Gdx.files.internal("sound/BOM_SET.wav"));
        setBombSound.setLooping(false);

        width = blockedManagement.getWidth();
        height = blockedManagement.getHeight();


        bombData = new ArrayList<>();
        explored = new ArrayList<>();
        for (int y = 0; y < height; ++y) {
            bombData.add(new ArrayList<Bomb>());
            explored.add(new ArrayList<Boolean>());
            for (int x = 0; x < width; ++x) {
                bombData.get(y).add(null);
                explored.get(y).add(false);
            }
        }
    }

    private boolean isExplored(int xUnit, int yUnit) {
        return explored.get(yUnit).get(xUnit);
    }

    private void setExplored(int xUnit, int yUnit, boolean newStatus) {
        explored.get(yUnit).set(xUnit, newStatus);
    }

    public Bomb getData(int xUnit, int yUnit) {
        return bombData.get(yUnit).get(xUnit);
    }

    private void setData(int xUnit, int yUnit, Bomb newData) {
        bombData.get(yUnit).set(xUnit, newData);
    }

    public void add(Bomb bomb) {
        int xUnit = (int) (bomb.getX() / MapManagement.CELL_SIZE);
        int yUnit = (int) (bomb.getY() / MapManagement.CELL_SIZE);
        if (getData(xUnit, yUnit) != null) {
            bomb.remove();
        } else {
            setBombSound.play();
            setData(xUnit, yUnit, bomb);
            blockedManagement.addBomb(xUnit, yUnit);
        }
    }

    private void explore(int xUnit, int yUnit, Bomb bomb) {
        setExplored(xUnit, yUnit, true);
        flames.add(new NormalFlame(getInitFlameSpirte(xUnit, yUnit), FlameType.CENTER));

        // right
        for (int i = 1; i <= bomb.getFlameRadius(); ++i) {
            int x = xUnit + (int) Direction.RIGHT.getX() * i;
            int y = yUnit + (int) Direction.RIGHT.getY() * i;

            if (x < 0 || y < 0 || x >= width || y >= height) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.WALL) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BRICK) {
                setExplored(x, y, true);
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BOMB) {
                if (!isExplored(x, y)) {
                    explore(x, y, getData(x, y));
                }
                break;
            }

            if (bomb instanceof NormalBomb) {
                if (i == bomb.getFlameRadius()) {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.RIGHT));
                } else {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.HORIZONTAL));
                }
            }
        }

        // left
        for (int i = 1; i <= bomb.getFlameRadius(); ++i) {
            int x = xUnit + (int) Direction.LEFT.getX() * i;
            int y = yUnit + (int) Direction.LEFT.getY() * i;

            if (x < 0 || y < 0 || x >= width || y >= height) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.WALL) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BRICK) {
                setExplored(x, y, true);
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BOMB) {
                if (!isExplored(x, y)) {
                    explore(x, y, getData(x, y));
                }
                break;
            }

            if (bomb instanceof NormalBomb) {
                if (i == bomb.getFlameRadius()) {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.LEFT));
                } else {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.HORIZONTAL));
                }
            }
        }

        // up
        for (int i = 1; i <= bomb.getFlameRadius(); ++i) {
            int x = xUnit + (int) Direction.UP.getX() * i;
            int y = yUnit + (int) Direction.UP.getY() * i;

            if (x < 0 || y < 0 || x >= width || y >= height) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.WALL) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BRICK) {
                setExplored(x, y, true);
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BOMB) {
                if (!isExplored(x, y)) {
                    explore(x, y, getData(x, y));
                }
                break;
            }

            if (bomb instanceof NormalBomb) {
                if (i == bomb.getFlameRadius()) {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.UP));
                } else {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.VERTICAL));
                }
            }
        }

        // down
        for (int i = 1; i <= bomb.getFlameRadius(); ++i) {
            int x = xUnit + (int) Direction.DOWN.getX() * i;
            int y = yUnit + (int) Direction.DOWN.getY() * i;

            if (x < 0 || y < 0 || x >= width || y >= height) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.WALL) {
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BRICK) {
                setExplored(x, y, true);
                break;
            }

            if (blockedManagement.getData(x, y) == BlockedType.BOMB) {
                if (!isExplored(x, y)) {
                    explore(x, y, getData(x, y));
                }
                break;
            }

            if (bomb instanceof NormalBomb) {
                if (i == bomb.getFlameRadius()) {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.DOWN));
                } else {
                    flames.add(new NormalFlame(getInitFlameSpirte(x, y), FlameType.VERTICAL));
                }
            }
        }
    }

    private Sprite getInitFlameSpirte(int xUnit, int yUnit) {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sprites/bomb_exploded.png")));
        sprite.setPosition(xUnit * MapManagement.CELL_SIZE, yUnit * MapManagement.CELL_SIZE);
        return sprite;
    }

    private void update(float delta) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Bomb bomb = getData(x, y);
                if (bomb != null) {
                    bomb.setExploreTime(bomb.getExploreTime() - delta);
                }
            }
        }

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Bomb bomb = getData(x, y);
                if (bomb != null && bomb.getExploreTime() <= 0) {
                    explore(x, y, bomb);
                    exploreSound.play();
                }
            }
        }

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) if (isExplored(x, y)) {
                Bomb bomb = getData(x, y);
                if (bomb != null) {
                    bomb.remove();
                    bomb.getTexture().dispose();
                    setData(x, y, null);
                }
                blockedManagement.destroy(x, y);
                setExplored(x, y, false);
            }
        }
    }

    public void draw(float delta, Batch batch) {
        update(delta);

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Bomb bomb = bombData.get(y).get(x);
                if (bomb != null) {
                    bomb.draw(batch);
                }
            }
        }

        for (Flame flame : flames) if (!flame.isDestroy()) {
            flame.draw(batch);
        }

        while (flames.size() > 0 && flames.getFirst().isDestroy()) {
            flames.getFirst().getTexture().dispose();
            flames.remove();
        }
    }

    public void dispose() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Bomb bomb = bombData.get(y).get(x);
                if (bomb != null) {
                    bomb.getTexture().dispose();
                }
            }
        }

        while (flames.size() > 0) {
            flames.getFirst().getTexture().dispose();
            flames.remove();
        }

        exploreSound.dispose();
    }
}
