package com.giant.model.tank;

import com.giant.model.Born;
import com.giant.model.Bullet;
import com.giant.panel.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.giant.constant.ImageConstant.*;
import static com.giant.constant.GameConstant.*;

public class PlayerTank extends Tank {
    private int playerNumber;
    private boolean standAlone;
    public static final PlayerTank PLAYER_ONE = new PlayerTank(PLAYER_1_BORN_X, PLAYER_BORN_Y, PLAYER_BORN_DIR, PLAYER_1);
    public static final PlayerTank PLAYER_TWO = new PlayerTank(PLAYER_2_BORN_X, PLAYER_BORN_Y, PLAYER_BORN_DIR, PLAYER_2);
    private boolean fire;
    private boolean move;


    public PlayerTank(int x, int y, int dir, int playerNumber) {
        super(x, y, dir);
        this.playerNumber = playerNumber;
        this.setRadius(IMAGE_TANK_RADIUS);
        this.setBorn(new Born());
    }

    /**
     * 重写父类的方法，使用图片绘制坦克的方法
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        int x = getX() - getRadius();
        int y = getY() - getRadius();
        g.drawImage(HERO_TANKS[playerNumber][getDir()], x, y, null);
        drawBullets(g);
        drawExplode(g);
        drawName(g);
        drawBloodBar(g);
        drawBorn(g);
    }

    /**
     * 根据玩家编号确定玩家对应的按键事件
     *
     * @param e
     */
    @Override
    public void keyEvent(KeyEvent e) {
        if (equalsKeyCode(e.getKeyCode())) {
            super.keyEvent(e);
        }
    }

    /**
     * 判断是否是当前玩家对应的按键
     *
     * @param keyCode
     * @return
     */
    private boolean equalsKeyCode(int keyCode) {
        if (isStandAlone()) {
            return true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            return true;
        } else if (playerNumber == PLAYER_2) {
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
                    || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_NUMPAD5) {
                return true;
            }
        } else if (playerNumber == PLAYER_1) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S
                    || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_I) {
                return true;
            }
        }
        return false;
    }

    public boolean isStandAlone() {
        return standAlone;
    }

    public void setStandAlone(boolean standAlone) {
        this.standAlone = standAlone;
    }

    public void reset() {
        if (playerNumber == PLAYER_1) {
            this.setX(PLAYER_1_BORN_X);
        } else {
            this.setX(PLAYER_2_BORN_X);
        }
        this.setY(PLAYER_BORN_Y);
        this.setDir(PLAYER_BORN_DIR);
        this.setHp(DEFAULT_HP);
        this.getBorn().setCurrentIndex(0);
        this.getBorn().setVisible(true);
        super.reset();
    }

    @Override
    public void move() {
        if (this.isMove()) {
            super.move();
        }
    }

    public void fire() {
        if (this.isFire()) {
            super.fire();
        }
    }


    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void keyReleasedKeyEvent(KeyEvent e) {
        if (equalsKeyCode(e.getKeyCode())) {
            keyReleasedHanding(e);
        }
    }


    private void keyReleasedHanding(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.setMove(false);
                break;
            case KeyEvent.VK_I:
            case KeyEvent.VK_NUMPAD5:
                this.setFire(false);
                break;
        }
    }
}
