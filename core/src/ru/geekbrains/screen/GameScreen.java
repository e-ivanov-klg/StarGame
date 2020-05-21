package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Explosion;
import ru.geekbrains.sprite.Star;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Star stars[];
    private TextureAtlas atlas;
    private MainShip mainShip;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;
    private EnemyEmitter enemyEmitter;

    Music music;

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
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds);
        enemyEmitter = new EnemyEmitter(atlas, enemyPool);
        mainShip = new MainShip(atlas, bulletPool, explosionPool);
        music  = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        playMusic();
    }

    @Override
    public void render(float delta) {
        update(delta);
        free();
        draw();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drowActiveSprites(batch);
        mainShip.draw(batch);
        enemyPool.drowActiveSprites(batch);
        explosionPool.drowActiveSprites(batch);
        batch.end();
    }

    private void update(float delta) {
        for (Star star : stars){
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
        mainShip.update(delta);
        enemyEmitter.generate(delta);
        enemyPool.updateActiveSprites(delta);
    }

    private void free ()
    {
        bulletPool.freeAllDestroyed();
        explosionPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
    }

    @Override
    public void resize(Rect worldBounds) {
       background.resize(worldBounds);
       for (Star star : stars) {
           star.resize(worldBounds);
       }
       mainShip.resize(worldBounds);
       enemyEmitter.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        mainShip.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch,pointer,button);
        return false;
    }

    private void playMusic() {
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }
}
