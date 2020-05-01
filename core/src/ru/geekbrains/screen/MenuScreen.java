package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture background;
    private Vector2 pos;
    private Vector2 touch;
    private Vector2 speed;
    private Vector2 pos1;

    @Override
    public void show() {
        super.show();
        img = new Texture("textures/ball.png");
        background = new Texture ("textures/back_img.gif");
        pos = new Vector2();
        pos1 = new Vector2();
        touch = new Vector2();
        speed = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pos1.set(touch);
        if (pos1.sub(pos).len() > speed.len() ) {
            pos.add(speed);
        }
        batch.begin();
        batch.draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {

        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        touch.set(touch.x-50, touch.y-50);
        speed.set(touch.cpy().sub(pos));
        speed.nor();
        speed.scl(3.0f);
        return false;
    }
}
