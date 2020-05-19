package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class MainShip extends Sprite {

    private static final float SHIP_SPEED = 0.5f;
    private static final float SIZE = 0.15f;
    private static final float MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;

    private boolean leftKeyPressed;
    private boolean rightKeyPressed;

    private Vector2 shipSpeed;
    private Rect worldBounds;

    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulleetV;

    public MainShip(TextureAtlas atlas,  BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1,2,2);
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulleetV = new Vector2(0f,0.5f);
        shipSpeed = new Vector2();
        leftKeyPressed = false;
        rightKeyPressed = false;
        this.worldBounds = new Rect();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (shipSpeed.x < 0 && getLeft() > worldBounds.getLeft()) {
            pos.mulAdd(shipSpeed, delta);
        }
        if (shipSpeed.x > 0 && getRight() < worldBounds.getRight()) {
            pos.mulAdd(shipSpeed,delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightPrportion(SIZE);
        setBottom(worldBounds.getBottom() + MARGIN);
        this.worldBounds = worldBounds;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < 0) {
            moveLeft();
        }
        if (touch.x > 0) {
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (touch.x < 0) {
            rightKeyPressed = false;
            moveStop(Input.Keys.LEFT);
        }
        if (touch.x > 0) {
            leftKeyPressed = false;
            moveStop(Input.Keys.RIGHT);
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            moveLeft();
        }
        if (keycode == Input.Keys.RIGHT) {
            moveRight();
        }
        if (keycode == Input.Keys.SPACE) {
            shoot();
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        moveStop(keycode);
        return false;
    }

    private void moveLeft () {
        shipSpeed.set(SHIP_SPEED * -1, 0f);
        leftKeyPressed = true;
    }

    private void moveRight () {
        shipSpeed.set(SHIP_SPEED, 0f);
        rightKeyPressed = true;
    }

    private void moveStop (int keycode) {
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
    }

    private void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulleetV, 0.01f, worldBounds, 1);
    }
}
