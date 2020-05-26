package ru.geekbrains.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.Explosion;

public class Ship extends Sprite {
    protected Vector2 v;
    protected Vector2 v0;
    protected Vector2 bulletV;
    protected Rect worldBounds;
    protected ExplosionPool explosionPool;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected float reloadTimer;
    protected float reloadInterval;
    protected Sound sound;
    protected float bulletHeight;
    protected int damage;
    protected int hp;
    private Vector2 bulletStartPosition;

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v = new Vector2();
        v0 = new Vector2();
        bulletV = new Vector2();
        bulletStartPosition = new Vector2();
    }

    public Ship(BulletPool bulletPool, ExplosionPool explosionPool,  Rect worldBounds, Sound sound){
        v = new Vector2();
        v0 = new Vector2();
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.sound = sound;
        bulletV = new Vector2();
        bulletStartPosition = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        reloadTimer += delta;
        if (reloadTimer > reloadInterval) {
            shoot();
            reloadTimer = 0f;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    private void shoot(){
        Bullet bullet = bulletPool.obtain();
        if (bulletV.y < 0f) {
            bulletStartPosition.set(pos.x, pos.y - getHalfHeight());
        } else {
            bulletStartPosition.set(pos.x, pos.y + getHalfHeight());
        }
        bullet.set(this, bulletRegion, bulletStartPosition, bulletV, bulletHeight, worldBounds, damage);
        sound.play(0.4f);
    }

    private void boom (){
        Explosion explosion = explosionPool.obtain();
        explosion.set (getHeight(), pos);
    }
}
