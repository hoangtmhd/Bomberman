package entities;

import app.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import graphics.Sprite;

public abstract class Entity extends Image {
    public static final int ENTITY_SIZE = 16;

    protected Sprite sprite;
    protected Rectangle hitBox;

    public Entity(int xUnit, int yUnit, Sprite sprite) {
        super(sprite.getTexture());

        this.setSize(this.getWidth() * Sprite.SCALED_RADIUS,
                this.getHeight() * Sprite.SCALED_RADIUS);

        float xPos = xUnit * PlayScreen.SCALED_SIZE;
        float yPos = yUnit * PlayScreen.SCALED_SIZE;

        this.setPosition(xPos, yPos);

        this.sprite = sprite;

        float xHitBox = xPos + sprite.getHitBoxX() * Sprite.SCALED_RADIUS;
        float yHitBox = yPos + sprite.getHitBoxY() * Sprite.SCALED_RADIUS;
        float wHitBox = (sprite.getHitBoxWidth() - sprite.getHitBoxX()) * Sprite.SCALED_RADIUS;
        float hHitBox = (sprite.getHitBoxHeight() - sprite.getHitBoxY()) * Sprite.SCALED_RADIUS;

        hitBox = new Rectangle(xHitBox, yHitBox, wHitBox, hHitBox);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
