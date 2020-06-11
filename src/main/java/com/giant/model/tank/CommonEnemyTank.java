package com.giant.model.tank;

import com.giant.constant.ImageConstant;

public class CommonEnemyTank extends EnemyTank {
    public CommonEnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        this.setId(COMMON_TANK_ID);
        this.setAtk(COMMON_ENEMY_TANK_ATK);
        this.setHp(COMMON_ENEMY_TANK_HP);
        this.setSpeed(COMMON_ENEMY_TANK_SPEED);
        this.getBloodBar().setMaxHp(COMMON_ENEMY_TANK_HP);
        this.getBloodBar().reset();
    }


}
