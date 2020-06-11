package com.giant.model.tank;

import com.giant.helper.EnumTankPool;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemyTankCamp implements Serializable {
    private List<EnemyTank> enemyTanks;
    private List<EnemyTank> toWarTanks = new ArrayList<>();
    private int currentEnemyIndex;
    private int aliveCount;


    public EnemyTankCamp(List<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public EnemyTankCamp() {

    }

    public synchronized void toWar() {
        if (currentEnemyIndex == enemyTanks.size()) {
            return;
        }
        EnemyTank enemyTank = enemyTanks.get(currentEnemyIndex++);
        this.aliveCount++;
        this.toWarTanks.add(enemyTank);
    }

    public boolean isDie(EnemyTank enemyTank) {
        return enemyTank.isDie();
    }

    public void die(EnemyTank enemyTank) {
        if (enemyTanks.contains(enemyTank)) {
            aliveCount--;
        }
        //重置其子弹并归还到BulletPool中
        enemyTank.reset();
        EnumTankPool.theReturn(enemyTank);
    }

    public boolean isLose() {
        if (aliveCount == 0 && currentEnemyIndex == enemyTanks.size()) {
            return true;
        }
        return false;
    }


    public void setEnemyTanks(List<EnemyTank> enemyTanks) {
        this.aliveCount = 0;
        this.currentEnemyIndex = 0;
        this.toWarTanks.clear();
        this.enemyTanks = enemyTanks;
    }

    public List<EnemyTank> getToWarTanks() {
        return toWarTanks;
    }

    public void move() {
        for (int i = 0; i < toWarTanks.size(); i++) {
            EnemyTank enemyTank = toWarTanks.get(i);
            enemyTank.removeBullets();
            enemyTank.moveBullets();
            enemyTank.move();
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < toWarTanks.size(); i++) {
            EnemyTank enemyTank = toWarTanks.get(i);
            enemyTank.draw(g);
        }
    }


    public synchronized void recycleTanks() {
        for (Iterator<EnemyTank> iterator = toWarTanks.iterator(); iterator.hasNext(); ) {
            EnemyTank enemyTank = iterator.next();
            if (isDie(enemyTank) && !enemyTank.getExplode().isVisible()) {
                die(enemyTank);
                iterator.remove();
            }
        }
    }


    @Override
    public String toString() {
        return "EnemyTankCamp{" +
                "enemyTanks=" + enemyTanks +
                ", toWarTanks=" + toWarTanks +
                ", currentEnemyIndex=" + currentEnemyIndex +
                ", aliveCount=" + aliveCount +
                '}';
    }
}
