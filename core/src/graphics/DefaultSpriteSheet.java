package graphics;

/**
 * DỮ liệu ảnh của game gốc có kích thước các sprite giống nhau là 16x16.
 */
public class DefaultSpriteSheet extends SpriteSheet {
    public DefaultSpriteSheet(String imagePath, int spritesSize) {
        super(imagePath, spritesSize, spritesSize);
    }

    public static DefaultSpriteSheet tiles =
            new DefaultSpriteSheet("textures/classic.png", 16);
}
