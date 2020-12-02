package entities;

public enum EntityMapData {
    GRASS,
    WALL,
    BRICK,
    PORTAL,
    BOMBER,
    BALLOOM,
    ONEAL,
    BOMB_ITEM,
    FLAME_ITEM,
    SPEED_ITEM,
    BOMB;

    public static EntityMapData valueOf(char ch) {
        switch (ch) {
            case '#':
                return WALL;
            case '*':
                return BRICK;
            case 'x':
                return PORTAL;
            case 'p':
                return BOMBER;
            case '1':
                return BALLOOM;
            case '2':
                return ONEAL;
            case 'b':
                return BOMB_ITEM;
            case 'f':
                return FLAME_ITEM;
            case 's':
                return SPEED_ITEM;
        }
        return GRASS;
    }
}

