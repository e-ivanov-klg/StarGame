package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

public class MainShip extends Ship {
    private static final float SHIP_SPEED = 0.5f;
    private static final float SIZE = 0.15f;
    private static final float MARGIN = 0.1f;
    private static final int HP = 30;

    private boolean leftKeyPressed;
    private boolean rightKeyPressed;
    private boolean touchDragged;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(atlas.findRegion("main_ship"), 1,2,2);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV.set(0f,0.4f);
        v.setZero();
        leftKeyPressed = false;
        rightKeyPressed = false;
        reloadInterval = 0.3f;
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletHeight = 0.01f;
        damage = 1;
        hp = HP;
        touchDragged = false;
    }

    public void initNewGame() {
        flushDestroy();
        hp = HP;
        leftKeyPressed = false;
        rightKeyPressed = false;
        pos.x = 0f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (!touchDragged) {
            pos.mulAdd(v, delta);
        } else pos.set(v.x, pos.y);
        if (!touchDragged) {
            pos.mulAdd(v,delta);
        } else pos.set(v.x, pos.y);
        if (v.x < 0 && getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }
        if (v.x > 0 && getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightPrportion(SIZE);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (touchDragged) {
            touchDragged = false;
            v.set(pos);
        }
        v.setZero();
        if (touch.x < 0) {
            rightKeyPressed = false;
            stop(Input.Keys.LEFT);
        }
        if (touch.x > 0) {
            leftKeyPressed = false;
            stop(Input.Keys.RIGHT);
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
        return false;
    }

    public boolean keyUp(int keycode) {
        stop(keycode);
        return false;
    }

    private void moveLeft () {
        v.set(SHIP_SPEED * -1, 0f);
        leftKeyPressed = true;
    }

    private void moveRight () {
        v.set(SHIP_SPEED, 0f);
        rightKeyPressed = true;
    }

    private void stop (int keycode) {
        if (keycode == Input.Keys.LEFT) {
            leftKeyPressed = false;
            if (rightKeyPressed) {
                v.set(SHIP_SPEED, 0f);
            } else v.set (0f,0f);
        }
        if (keycode == Input.Keys.RIGHT) {
            rightKeyPressed = false;
            if (leftKeyPressed) {
                v.set(SHIP_SPEED * -1, 0f);
            } else v.set (0f,0f);
        }
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (isMe(touch) || touchDragged) {
            touchDragged = true;
            v.set(touch.x, 0f);
        }
        return false;
    }

    public void dispose (){
        sound.dispose();
    }

    public boolean isBulletCollision (Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom()
        );
    }

}
