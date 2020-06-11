package com.giant.model.tank;

import com.giant.constant.ImageConstant;

public class EpicEnemyTank extends EnemyTank {
    public EpicEnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        this.setId(EPIC_TANK_ID);
        this.setAtk(EPIC_ENEMY_TANK_ATK);
        this.setHp(EPIC_ENEMY_TANK_HP);
        this.setSpeed(EPIC_ENEMY_TANK_SPEED);
        this.getBloodBar().setMaxHp(EPIC_ENEMY_TANK_HP);
        this.getBloodBar().reset();
    }
}
