package graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;

public class Sprite {
    public static final int SCALED_RADIUS = 2;

    private final Pixmap pixels;

    private final int hitBoxX;
    private final int hitBoxY;
    private final int hitBoxWidth;
    private final int hitBoxHeight;

    /*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */
    public static Sprite grass = new Sprite(6, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite brick = new Sprite(7, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite wall = new Sprite(5, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite portal = new Sprite(4, 0, DefaultSpriteSheet.tiles,
            1, 1, 14, 14);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(0, 0, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_down = new Sprite(2, 0, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_left = new Sprite(3, 0, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_right = new Sprite(1, 0, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);

    public static Sprite player_up_1 = new Sprite(0, 1, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_up_2 = new Sprite(0, 2, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);

    public static Sprite player_down_1 = new Sprite(2, 1, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_down_2 = new Sprite(2, 2, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);

    public static Sprite player_left_1 = new Sprite(3, 1, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_left_2 = new Sprite(3, 2, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);

    public static Sprite player_right_1 = new Sprite(1, 1, DefaultSpriteSheet.tiles,
            0, 0, 12 ,16);
    public static Sprite player_right_2 = new Sprite(1, 2, DefaultSpriteSheet.tiles,
            0, 0, 12, 16);

    public static Sprite player_dead_1 = new Sprite(4, 2, DefaultSpriteSheet.tiles,
            0, 0, 14, 16);
    public static Sprite player_dead_2 = new Sprite(5, 2, DefaultSpriteSheet.tiles,
            0, 0, 13, 16);
    public static Sprite player_dead_3 = new Sprite(6, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOM
    public static Sprite balloom_left_1 = new Sprite(9, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite balloom_left_2 = new Sprite(9, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite balloom_left_3 = new Sprite(9, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite balloom_right_1 = new Sprite(10, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite balloom_right_2 = new Sprite(10, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite balloom_right_3 = new Sprite(10, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite balloom_dead = new Sprite(9, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    //ONEAL
    public static Sprite oneal_left_1 = new Sprite(11, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite oneal_left_2 = new Sprite(11, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite oneal_left_3 = new Sprite(11, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite oneal_right_1 = new Sprite(12, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite oneal_right_2 = new Sprite(12, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite oneal_right_3 = new Sprite(12, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite oneal_dead = new Sprite(11, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    //Doll
    public static Sprite doll_left_1 = new Sprite(13, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite doll_left_2 = new Sprite(13, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite doll_left_3 = new Sprite(13, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite doll_right_1 = new Sprite(14, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite doll_right_2 = new Sprite(14, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite doll_right_3 = new Sprite(14, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite doll_dead = new Sprite(13, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    //Minvo
    public static Sprite minvo_left_1 = new Sprite(8, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite minvo_left_2 = new Sprite(8, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite minvo_left_3 = new Sprite(8, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite minvo_right_1 = new Sprite(9, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite minvo_right_2 = new Sprite(9, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite minvo_right_3 = new Sprite(9, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite minvo_dead = new Sprite(8, 8, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    //Kondoria
    public static Sprite kondoria_left_1 = new Sprite(10, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite kondoria_left_2 = new Sprite(10, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite kondoria_left_3 = new Sprite(10, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite kondoria_right_1 = new Sprite(11, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite kondoria_right_2 = new Sprite(11, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite kondoria_right_3 = new Sprite(11, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite kondoria_dead = new Sprite(10, 8, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    //ALL
    public static Sprite mob_dead_1 = new Sprite(15, 0, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite mob_dead_2 = new Sprite(15, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite mob_dead_3 = new Sprite(15, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(0, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite bomb_1 = new Sprite(1, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite bomb_2 = new Sprite(2, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(0, 4, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite bomb_exploded_1 = new Sprite(0, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite bomb_exploded_2 = new Sprite(0, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite explosion_vertical = new Sprite(1, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_vertical_1 = new Sprite(2, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_vertical_2 = new Sprite(3, 5, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite explosion_horizontal = new Sprite(1, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_horizontal_1 = new Sprite(1, 8, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_horizontal_2 = new Sprite(1, 9, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite explosion_horizontal_left_last = new Sprite(0, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_horizontal_left_last_1 = new Sprite(0, 8, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_horizontal_left_last_2 = new Sprite(0, 9, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite explosion_horizontal_right_last = new Sprite(2, 7, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_horizontal_right_last_1 = new Sprite(2, 8, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_horizontal_right_last_2 = new Sprite(2, 9, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite explosion_vertical_top_last = new Sprite(1, 4, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_vertical_top_last_1 = new Sprite(2, 4, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_vertical_top_last_2 = new Sprite(3, 4, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public static Sprite explosion_vertical_down_last = new Sprite(1, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_vertical_down_last_1 = new Sprite(2, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite explosion_vertical_down_last_2 = new Sprite(3, 6, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(7, 1, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite brick_exploded_1 = new Sprite(7, 2, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite brick_exploded_2 = new Sprite(7, 3, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(0, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite powerup_flames = new Sprite(1, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite powerup_speed = new Sprite(2, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite powerup_wallpass = new Sprite(3, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite powerup_detonator = new Sprite(4, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite powerup_bombpass = new Sprite(5, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);
    public static Sprite powerup_flamepass = new Sprite(6, 10, DefaultSpriteSheet.tiles,
            0, 0, 16, 16);

    public Sprite(int xSrc, int ySrc, SpriteSheet sheet, int hX, int hY, int hW, int hH) {
        pixels = sheet.getSpritePixmap(xSrc, ySrc);

        hitBoxX = hX;
        hitBoxY = hY;
        hitBoxWidth = hW;
        hitBoxHeight = hH;
    }

    public Sprite(int width, int height, Color color) {
        pixels = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixels.setColor(color);
        pixels.fill();

        hitBoxX = 0;
        hitBoxY = 0;
        hitBoxWidth = width;
        hitBoxHeight = height;
    }

    public int getHitBoxX() {
        return hitBoxX;
    }

    public int getHitBoxY() {
        return hitBoxY;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }

    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public Texture getTexture() {
        TextureData textData = new PixmapTextureData(pixels, Pixmap.Format.RGBA8888,
                false, false, true);

        return new Texture(textData);
    }
}
