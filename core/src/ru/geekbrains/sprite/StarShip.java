package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class StarShip extends Sprite {

    private static final float SHIP_SPEED = 0.01f;
    private boolean leftKeyPressed;
    private boolean rightKeyPressed;

    private Vector2 shipSpeed;
    private Rect worldBounds;


    public StarShip(TextureRegion region) {
        super(region);
        shipSpeed = new Vector2();
        leftKeyPressed = false;
        rightKeyPressed = false;
        this.worldBounds = new Rect();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (shipSpeed.x < 0 && getLeft() > worldBounds.getLeft()) {
            pos.add(shipSpeed);
        }
        if (shipSpeed.x > 0 && getRight() < worldBounds.getRight()) {
            pos.add(shipSpeed);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightPrportion(0.15f);
        setBottom(worldBounds.getBottom());
        this.worldBounds = worldBounds;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            shipSpeed.set(SHIP_SPEED * -1, 0f);
            leftKeyPressed = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            shipSpeed.set(SHIP_SPEED, 0f);
            rightKeyPressed = true;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            leftKeyPressed = false;
            if (rightKeyPressed) {
                shipSpeed.set(SHIP_SPEED, 0f);
            } else shipSpeed.set (0f,0f);
        }
        if (keycode == Input.Keys.RIGHT) {
            rightKeyPressed = false;
            if (leftKeyPressed) {
                shipSpeed.set(SHIP_SPEED * -1, 0f);
            } else shipSpeed.set (0f,0f);
        }
        return false;
    }


}
