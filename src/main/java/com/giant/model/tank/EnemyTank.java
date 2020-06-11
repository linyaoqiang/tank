package com.giant.model.tank;

import com.giant.constant.ImageConstant;
import com.giant.helper.GameHelper;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class EnemyTank extends Tank {
    private transient Image[] enemyTankImages;

    private int id;

    public EnemyTank(int x, int y, int dir) {
        super(x, y, dir);
        this.setRadius(IMAGE_TANK_RADIUS);
    }

    public void ai(List<PlayerTank> playerTanks) {
        coreApi(playerTanks);
        moveAi();
        fireAi();
    }

    public void moveAi() {
        int randomNumber = GameHelper.getRandomInt(1, 100);
        if (randomNumber > ENEMY_MOVE_POINT) {
            this.setState(STAND);
            return;
        }
        this.setState(MOVING);
        randomNumber = GameHelper.getRandomInt(0, 3);
        this.setDir(randomNumber);
    }

    /**
     * 坦克核心ai算法，其实几乎没写，觉得不需要敌人不需要太聪明
     * @param playerTanks
     */
    public void coreApi(List<PlayerTank> playerTanks) {
        /**
         * 判断敌人是否看到玩家的坦克
         */
        PlayerTank playerTank = seeingPlayerTank(playerTanks);
        if (playerTank != null) {
            this.fire();
        }
    }

    public void fireAi() {
        int randomNumber = GameHelper.getRandomInt(1, 100);
        if (randomNumber > ENEMY_FIRE_POINT) {
            return;
        }
        this.fire();
    }

    public PlayerTank seeingPlayerTank(List<PlayerTank> playerTanks) {
        for (PlayerTank playerTank : playerTanks) {
            if (playerTank != null) {
                switch (getDir()) {
                    case DIR_UP:
                        if (GameHelper.collide(playerTank, this.getX() - this.getRadius(), playerTank.getY() - getRadius(), 2 * getRadius(), 2 * getRadius())) {
                            return playerTank;
                        }
                    case DIR_DOWN:
                        if (playerTank.getX() == this.getX() && playerTank.getY() > this.getY()) {
                            return playerTank;
                        }
                    case DIR_LEFT:
                        if (playerTank.getY() == this.getY() && playerTank.getX() < this.getX()) {
                            return playerTank;
                        }
                    case DIR_RIGHT:
                        if (playerTank.getY() == this.getY() && playerTank.getX() > this.getX()) {
                            return playerTank;
                        }
                }
            }
        }
        return null;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImageConstant.ENEMY_TANK_IMAGES[id][getDir()], getX() - getRadius(), getY() - getRadius(), null);
        drawBullets(g);
        drawExplode(g);
        drawName(g);
        drawBloodBar(g);
        drawBorn(g);
    }

    @Override
    public void move() {
        if (getState() == MOVING) {
            super.move();
        }
    }

    public Image[] getEnemyTankImages() {
        return enemyTankImages;
    }

    public void setEnemyTankImages(Image[] enemyTankImages) {
        this.enemyTankImages = enemyTankImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
