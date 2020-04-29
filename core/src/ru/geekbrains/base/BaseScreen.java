package ru.geekbrains.base;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseScreen implements Screen {
    protected SpriteBatch batch;


    @Override
    public void show() {
        // Инициализация всего, что происходит. аналог метода create
        System.out.println("show");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        // Когда поменяли размер экрана
        System.out.println("width = " + width + "; height = " + height);
    }

    @Override
    public void pause() {
        // когда свернули приложение
        System.out.println("pause");
    }

    @Override
    public void resume() {
        // Когда развернули приложение
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("despose");
        batch.dispose();
    }
}
