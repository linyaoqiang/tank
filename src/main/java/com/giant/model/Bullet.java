package com.giant.model;

import com.giant.panel.GamePanel;

import java.awt.*;
import java.io.Serializable;

import static com.giant.constant.TankConstant.*;
import static com.giant.constant.GameConstant.*;

/**
 * 子弹类
 */
public class Bullet implements Serializable {
    private int x;
    private int y;
    private int dir;
    private int radius;
    private int atk;
    private int speed;
    private Color color;
    private boolean visible;

    public Bullet(int x, int y, int dir, int atk, Color color) {
        this();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.atk = atk;
        this.color = color;
    }

    public Bullet() {
        this.speed = DEFAULT_BULLET_SPEED;
        this.radius = DEFAULT_BULLET_RADIUS;
    }

    /**
     * 画
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    /**
     * 移动
     */
    public void move() {
        switch (dir) {
            case DIR_UP:
                y -= speed;
                break;
            case DIR_DOWN:
                y += speed;
                break;
            case DIR_LEFT:
                x -= speed;
                break;
            case DIR_RIGHT:
                x += speed;
                break;
        }
        moveBullet();
    }

    /**
     * 如果子弹出界了，标记状态为不可见
     */
    private void moveBullet() {
        if (x < 0 || x > GAME_WIDTH - GamePanel.insetX) {
            this.visible = false;
        }
        if (y < 0 || y > GAME_WIDTH - GamePanel.insetY) {
            this.visible = false;
        }
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

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
