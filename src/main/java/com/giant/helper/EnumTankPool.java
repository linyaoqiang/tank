package com.giant.helper;

import com.giant.constant.TankConstant;
import com.giant.model.tank.CommonEnemyTank;
import com.giant.model.tank.EliteEnemyTank;
import com.giant.model.tank.EnemyTank;

import java.util.ArrayList;
import java.util.List;

public class EnumTankPool {
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 30;
    private static List<EnemyTank> commonPool = new ArrayList<>();
    private static List<EnemyTank> elitePool = new ArrayList<>();
    private static List<EnemyTank> epicPool = new ArrayList<>();

    static {
        init();
    }

    private static void init() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            commonPool.add(EnemyTankFactory.createCommonEnemyTank());
            elitePool.add(EnemyTankFactory.createEliteEnemyTank());
            epicPool.add(EnemyTankFactory.createEpicEnemyTank());
        }
    }

    public static EnemyTank getCommon() {
        EnemyTank enemyTank = null;
        if (commonPool.size() > 0) {
            enemyTank = commonPool.remove(0);
        } else if (commonPool.size() <= 0) {
            enemyTank = EnemyTankFactory.createCommonEnemyTank();
        }
        return enemyTank;
    }

    public static EnemyTank getElite() {
        EnemyTank enemyTank = null;
        if (elitePool.size() > 0) {
            enemyTank = elitePool.remove(0);
        } else if (elitePool.size() <= 0) {
            enemyTank = EnemyTankFactory.createEliteEnemyTank();
        }
        return enemyTank;
    }

    public static EnemyTank getEpic() {
        EnemyTank enemyTank = null;
        if (epicPool.size() > 0) {
            enemyTank = epicPool.remove(0);
        } else if (epicPool.size() <= 0) {
            enemyTank = EnemyTankFactory.createEpicEnemyTank();
        }
        return enemyTank;
    }

    public static void theReturn(EnemyTank enemyTank) {
        List<EnemyTank> pool = null;
        if (enemyTank instanceof CommonEnemyTank) {
            enemyTank.setHp(TankConstant.COMMON_ENEMY_TANK_HP);
            pool = commonPool;
        } else if (enemyTank instanceof EliteEnemyTank) {
            pool = elitePool;
            enemyTank.setHp(TankConstant.ELITE_ENEMY_TANK_HP);
        } else {
            enemyTank.setHp(TankConstant.EPIC_ENEMY_TANK_HP);
            pool = epicPool;
        }

        if (pool.size() < MAX_SIZE) {
            EnemyTankFactory.resetEnemyTank(enemyTank);
            pool.add(enemyTank);
        }
    }
}
