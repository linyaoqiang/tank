package com.giant.model.tank;

import com.giant.constant.ImageConstant;

public class EliteEnemyTank extends EnemyTank {

    public EliteEnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        this.setId(ELITE_TANK_ID);
        this.setAtk(ELITE_ENEMY_TANK_ATK);
        this.setHp(ELITE_ENEMY_TANK_HP);
        this.setSpeed(ELITE_ENEMY_TANK_SPEED);
        this.getBloodBar().setMaxHp(ELITE_ENEMY_TANK_HP);
        this.getBloodBar().reset();
    }
}
