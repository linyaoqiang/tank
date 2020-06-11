package com.giant.helper;

import com.giant.model.Explode;

import java.util.ArrayList;
import java.util.List;

/**
 * 爆炸效果池
 */
public class ExplodePool {
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 20;
    private static List<Explode> pool = new ArrayList<>();

    static {
        init();
    }

    private static void init() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            pool.add(new Explode());
        }
    }

    public static Explode get() {
        Explode explode = null;
        if (pool.size() > 0) {
            explode = pool.remove(0);
        } else if (pool.size() <= 0) {
            explode = new Explode();
        }
        return explode;
    }

    public static void theReturn(Explode explode) {
        if (pool.size() < MAX_SIZE) {
            pool.add(explode);
        }
    }
}
