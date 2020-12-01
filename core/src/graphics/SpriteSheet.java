package graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Các sprite (hình ảnh game) được lưu trữ vào các ảnh dữ liệu.
 * Class này giúp lấy ra các sprite riêng trong các ảnh dữ liệu đó.
 */
public abstract class SpriteSheet implements Disposable {
    private static final int TRANSPARENT_COLOR = 0xff00ffff;
    private static final int CLEAR = 0x00000000;
    private final String imagePath;
    private final int spritesWidth;
    private final int spritesHeight;
    private Pixmap pixels;

    /**
     * Tìm ảnh dữ liệu, cho trước kích thước các sprite trong ảnh.
     * @param imagePath Đường dẫn đến ảnh dữ liệu.
     * @param spritesWidth chiều rộng các sprite trong ảnh.
     * @param spritesHeight chiều cao các sprite trong ảnh.
     */
    public SpriteSheet(String imagePath, int spritesWidth, int spritesHeight) {
        this.imagePath = imagePath;
        this.spritesWidth = spritesWidth;
        this.spritesHeight = spritesHeight;
        load();
    }

    @Override
    public void dispose() {
        pixels.dispose();
    }

    /**
     * load ảnh dữ liệu thành Pixmap, xử lý dữ liệu ảnh bằng Pixmap.
     */
    private void load() {
        try {
            Texture texture = new Texture(Gdx.files.internal(imagePath));

            if (!texture.getTextureData().isPrepared()) {
                texture.getTextureData().prepare();
            }

            pixels = texture.getTextureData().consumePixmap();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * lấy dữ liệu của sprite tại cột xSrc, hàng ySrc tính từ góc trái trên của ảnh.
     * @param xSrc vị trí cột.
     * @param ySrc vị trí hàng.
     * @return Pixmap của sprite trong sheet.
     */
    public Pixmap getSpritePixmap(int xSrc, int ySrc) {
        Pixmap pixmap = new Pixmap(spritesWidth, spritesHeight, Pixmap.Format.RGBA8888);

        int xSheet = xSrc*spritesWidth;
        int ySheet = ySrc*spritesHeight;

        for (int x = 0; x < spritesWidth; ++x) {
            for (int y = 0; y < spritesHeight; ++y) {
                if (pixels.getPixel(xSheet + x, ySheet + y) == TRANSPARENT_COLOR) {
                    pixmap.drawPixel(x, y, CLEAR);
                } else {
                    pixmap.drawPixel(x, y, pixels.getPixel(xSheet + x, ySheet + y));
                }
            }
        }

        return pixmap;
    }
}
