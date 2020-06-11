package com.giant.helper;

import com.giant.constant.GameConstant;
import com.giant.constant.TankConstant;
import com.giant.model.tank.CommonEnemyTank;
import com.giant.model.tank.EliteEnemyTank;
import com.giant.model.tank.EnemyTank;
import com.giant.model.tank.EpicEnemyTank;
import com.giant.panel.GamePanel;

public class EnemyTankFactory {
    public static EnemyTank createCommonEnemyTank() {
        EnemyTank enemyTank = new CommonEnemyTank(getTankX(), TankConstant.IMAGE_TANK_RADIUS, 1);
        return enemyTank;
    }

    public static EnemyTank createEliteEnemyTank() {
        EnemyTank enemyTank = new EliteEnemyTank(getTankX(), TankConstant.IMAGE_TANK_RADIUS, 1);
        return enemyTank;
    }

    public static EnemyTank createEpicEnemyTank() {
        return new EpicEnemyTank(getTankX(), TankConstant.IMAGE_TANK_RADIUS, 1);
    }

    public static void resetEnemyTank(EnemyTank enemyTank) {
        enemyTank.setX(getTankX());
        enemyTank.setY(TankConstant.IMAGE_TANK_RADIUS);
        enemyTank.setDir(1);
    }

    private static int getTankX() {
        int n = GameHelper.getRandomInt(0, 1);
        int x = 0;
        if (n == 0) {
            x = TankConstant.IMAGE_TANK_RADIUS;
        } else {
            x = GameConstant.GAME_WIDTH - TankConstant.IMAGE_TANK_RADIUS - GamePanel.insetX;
        }
        return x;
    }
}
