package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

public class Enemy extends Ship {
    private boolean firstShoot;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        super(bulletPool,  explosionPool, worldBounds, sound);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (firstShoot) {
            reloadTimer = reloadInterval;
            firstShoot = false;
        }
        if (this.getBottom() < worldBounds.getTop()  - getHeight()) {
            pos.mulAdd(v, delta);
        } else {
            pos.mulAdd(v0, delta);
        }
        if (this.getBottom() <= worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set (
            TextureRegion[] region,
            Vector2 v0,
            Vector2 v,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            int hp,
            float height
    ){
        this.regions = region;
        this.v0.set(v0);
        this.v.set(v);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightPrportion(height);
        firstShoot = true;
    }
}
