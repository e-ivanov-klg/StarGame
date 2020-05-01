package ru.geekbrains.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;

    @Override
    public void show() {
        // Инициализация всего, что происходит. аналог метода create
        //System.out.println("show");
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        // Когда поменяли размер экрана
        //System.out.println("width = " + width + "; height = " + height);
    }

    @Override
    public void pause() {
        // когда свернули приложение
        //System.out.println("pause");
    }

    @Override
    public void resume() {
        // Когда развернули приложение
        //System.out.println("resume");
    }

    @Override
    public void hide() {
        //System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        //System.out.println("despose");
        batch.dispose();
    }
    // -------------------------------------------------------------------
    @Override
    public boolean keyDown(int keycode) {
        //System.out.println("keyDown, keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //System.out.println("keyUp, keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        //System.out.println("keyTyped, char = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //координаты, номер пальца, номер кнопки
        //System.out.println("touchDown = " + screenX + ", " + screenY + "; pointer = " + pointer + "; button = " + button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //System.out.println("touchUp = " + screenX + ", " + screenY + "; pointer = " + pointer + "; button = " + button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // После перетаскивания
        //System.out.println("touchDragged = " + screenX + ", " + screenY + "; pointer = " + pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // движение колесика amount 1 или -1 в зависимости отнаправоения
        //System.out.println("scrolled = " + amount);
        return false;
    }
}
