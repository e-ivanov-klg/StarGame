package ru.geekbrains.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import javax.swing.JButton;

import ru.geekbrains.math.MatrixUtils;
import ru.geekbrains.math.Rect;

public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    private Rect screeenBounds;
    private Rect worldBounds;
    private Rect glBounds;

    private Matrix4 worldToGL;
    private Matrix3 screenToWorld;

    private Vector2 touch;

    @Override
    public void show() {
        // Инициализация всего, что происходит. аналог метода create
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        screeenBounds = new Rect();
        worldBounds = new Rect();
        screenToWorld = new Matrix3();
        glBounds = new Rect(0,0,1f,1f);
        worldToGL = new Matrix4();
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.2f, 0.1f,1);
        Gdx.gl.glClear((GL20.GL_COLOR_BUFFER_BIT));
    }

    @Override
    public void resize(int width, int height) {   // Когда поменяли размер экран
        screeenBounds.setSize(width,height);
        screeenBounds.setLeft(0);
        screeenBounds.setBottom(0);
        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f * aspect);
        MatrixUtils.calcTransitionMatrix(worldToGL, worldBounds, glBounds);
        MatrixUtils.calcTransitionMatrix(screenToWorld, screeenBounds, worldBounds);
        batch.setProjectionMatrix(worldToGL);
        resize(worldBounds);
    }

    public void resize  (Rect worldBounds) {
        System.out.println("worldBounds worldbound.height = " + worldBounds.getHeight() + "/ worldBpounds.width = " + worldBounds.getWidth());
    }

    @Override
    public void pause() {
        // когда свернули приложение
    }

    @Override
    public void resume() {
        // Когда развернули приложение
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    // -------------------------------------------------------------------
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //координаты, номер пальца, номер кнопки
        touch.set(screenX, screeenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch, pointer, button);
        return false;
    }

    public boolean touchDown(Vector2 touch,int pointer, int button) {
        System.out.println("touchDown touch.x = " + touch.x + "/ touch.y = " + touch.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screeenBounds.getHeight() - screenY).mul(screenToWorld);
        touchUp(touch, pointer, button);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUp touch.x = " + touch.x + "/ touch.y = " + touch.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // После перетаскивания
        touch.set(screenX, screeenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDrugged touch.x = " + touch.x + "/ touch.y = " + touch.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // движение колесика amount 1 или -1 в зависимости отнаправоения
        return false;
    }
}
