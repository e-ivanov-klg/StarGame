package ru.geekbrains.pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rect worldbounds;
    private Sound sound;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldbounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldbounds = worldbounds;
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, worldbounds, sound);
    }

    @Override
    public void dispose() {
        super.dispose();
        sound.dispose();
    }
}
