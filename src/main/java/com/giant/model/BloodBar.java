package com.giant.model;

import com.giant.constant.GameConstant;
import com.giant.constant.TankConstant;

import java.awt.*;
import java.io.Serializable;

/**
 * 血条类
 */
public class BloodBar implements Serializable {
    private static final int BAR_WIDTH = 60;
    private static final int BAR_HEIGHT = 6;
    private int x;
    private int y;
    private int hp;
    private int maxHp;

    public BloodBar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BloodBar() {
    }

    public void reset() {
        this.hp = this.maxHp;
    }

    public void draw(Graphics g) {
        /**
         * 背景填充色
         */
        g.setColor(Color.RED);
        g.fillRect(x, y, BAR_WIDTH, BAR_HEIGHT);

        /**
         * 血条
         */
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, hp * BAR_WIDTH / maxHp, BAR_HEIGHT);

        /**
         * 边框
         */
        g.setColor(Color.WHITE);
        g.drawRect(x, y, BAR_WIDTH, BAR_HEIGHT);

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
}
