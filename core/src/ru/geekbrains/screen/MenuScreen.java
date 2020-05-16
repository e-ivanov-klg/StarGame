package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Ball;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Background background;
    private Ball ball;

    @Override
    public void show() {
        super.show();
        img = new Texture("textures/ball.png");
        bg = new Texture("textures/back_img.gif");
        background = new Background(bg);
        ball = new Ball(img);
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        ball.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ball.update(delta);
        batch.begin();
        background.draw(batch);
        ball.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        bg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ball.touchDown(touch, pointer, button);
        return false;
    }
}
