package com.giant.helper;

import com.giant.model.Bullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 子弹池，用于提高效率
 */
public class BulletPool {
    private static final int DEFAULT_SIZE = 50;
    private static final int MAX_SIZE = 100;
    private static List<Bullet> pool = new ArrayList<>();

    static {
        init();
    }

    private static void init() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            pool.add(new Bullet());
        }
    }

    public static Bullet get() {
        Bullet bullet = null;
        if (pool.size() > 0) {
            bullet = pool.remove(0);
        } else if (pool.size() <= 0) {
            bullet = new Bullet();
        }
        return bullet;
    }

    public static void theReturn(Bullet bullet) {
        if (pool.size() < MAX_SIZE) {
            pool.add(bullet);
        }
    }
}
