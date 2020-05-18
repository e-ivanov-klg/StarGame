package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Star;
import ru.geekbrains.sprite.StarShip;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Star stars[];
    private TextureAtlas atlas;
    private StarShip starShip;
    private TextureRegion region;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/back_img.gif");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        stars = new Star[150];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        region = new TextureRegion(atlas.findRegion("main_ship"));
        region.setRegion(region.getRegionX(),region.getRegionY(), region.getRegionWidth()/2, region.getRegionHeight());
        starShip = new StarShip(region);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        starShip.draw(batch);
        batch.end();
    }

    private void update(float delta) {
        for (Star star : stars){
            star.update(delta);
        }
        starShip.update(delta);
    }

    @Override
    public void resize(Rect worldBounds) {
       background.resize(worldBounds);
       for (Star star : stars) {
           star.resize(worldBounds);
       }
       starShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        starShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        starShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }
}
