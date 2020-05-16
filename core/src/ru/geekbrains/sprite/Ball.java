package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Ball extends Sprite {
    //private Vector2 pos;
    private Vector2 pos1;
    private Vector2 touch;
    private Vector2 speed;

    private static final float V_LEN = 0.01f;

    public Ball(Texture texture) {
        super(new TextureRegion(texture));
        //pos = new Vector2();
        pos1 = new Vector2();
        touch = new Vector2();
        speed = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightPrportion(0.1f);
        this.pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {
        pos1.set(touch);
        if (pos1.sub(pos).len() > V_LEN) {
            pos.add(speed);
        } else {
            pos.set(touch);
            speed.setZero();
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        speed.set(touch.sub(pos)).setLength(V_LEN);
        return false;
    }
}
