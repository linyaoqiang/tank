package com.giant.model.tank;

import com.giant.constant.TankConstant;
import com.giant.helper.BulletPool;
import com.giant.helper.ExplodePool;
import com.giant.helper.GameHelper;
import com.giant.model.BloodBar;
import com.giant.model.Born;
import com.giant.model.Bullet;
import com.giant.model.Explode;
import com.giant.model.block.Block;
import com.giant.panel.GamePanel;

import static com.giant.constant.GameConstant.*;

import static com.giant.helper.GameHelper.*;
import static com.giant.constant.MusicConstant.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tank implements TankConstant, Serializable {
    /**
     * 坦克的中心点坐标，与地图元素块的x,y不一样
     */
    private int x;
    private int y;
    /**
     * 半径
     */
    private int radius;
    /**
     * 坦克的一些数值
     */
    private int hp;
    private int atk;
    private int dir;
    private int speed;
    private int state;

    /**
     * 随机生产名字和   颜色(颜色已经不随机，觉得没有意义)
     */
    private String name;
    private Color color;

    /**
     * 坦克自身爆炸效果
     */
    private Explode explode;
    /**
     * 血条
     */
    private BloodBar bloodBar;


    /**
     * 出生效果
     */
    private Born born;

    /**
     * 子弹集合
     */
    private List<Bullet> bulletList = new ArrayList<>();

    /**
     * 上一次开火的时间
     */
    private long lastTime;

    /**
     * 构造，进行初始化
     *
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.color = Color.WHITE;
        this.radius = DEFAULT_RADIUS;
        this.atk = DEFAULT_ATK;
        this.hp = DEFAULT_HP;
        this.speed = DEFAULT_SPEED;
        this.name = getRandomName();
        this.bloodBar = new BloodBar();
        this.bloodBar.setMaxHp(hp);
        this.bloodBar.reset();
    }


    /**
     * 使用画笔绘制坦克的方法
     * 该方法已经废弃，使用子类方法
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(color);
        /**
         * 画坦克身
         */
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);

        /**
         * 画坦克的大炮
         */
        int endX = x;
        int endY = y;
        switch (dir) {
            case DIR_UP:
                endY = y - 2 * radius;
                break;
            case DIR_DOWN:
                endY = y + 2 * radius;
                break;
            case DIR_LEFT:
                endX = x - 2 * radius;
                break;
            case DIR_RIGHT:
                endX = x + 2 * radius;
                break;
        }
        g.drawLine(x, y, endX, endY);
        drawBullets(g);
        drawExplode(g);
        drawName(g);
        drawBloodBar(g);
    }

    /**
     * 事件处理，其实不应该写在这里，因为只有playerTank才需要键盘监听
     *
     * @param e
     */
    public void keyEvent(KeyEvent e) {
        PlayerTank playerTank = (PlayerTank) this;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                dir = DIR_DOWN;
                playerTank.setMove(true);
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                dir = DIR_UP;
                playerTank.setMove(true);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                dir = DIR_LEFT;
                playerTank.setMove(true);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                dir = DIR_RIGHT;
                playerTank.setMove(true);
                break;
            case KeyEvent.VK_I:
            case KeyEvent.VK_NUMPAD5:
                playerTank.setFire(true);
                break;
        }
    }

    /**
     * 移动坦克的核心方法
     */
    public void move() {
        switch (getDir()) {
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
        //出界处理
        movePost();
    }

    /**
     * 坦克出界处理
     */
    private void movePost() {
        /**
         * 修正水平方向的位置
         */
        if (x < radius) {
            x = radius;
        } else if (x > (GAME_WIDTH - radius - GamePanel.insetX)) {
            x = GAME_WIDTH - radius - GamePanel.insetX;
        }

        /**
         * 修正垂直方向的位置
         */
        if (y < radius) {
            y = radius;
        } else if (y > (GAME_HEIGHT - radius - GamePanel.insetY)) {
            y = GAME_HEIGHT - radius - GamePanel.insetY;
        }

    }

    /**
     * 发射子弹
     */
    public void fire() {
        /**
         * 判断是否可以开炮
         */
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime < FIRE_TIME) {
            return;
        }
        int bulletX = x;
        int bulletY = y;

        switch (dir) {
            case DIR_UP:
                bulletY -= radius;
                break;
            case DIR_DOWN:
                bulletY += radius;
                break;
            case DIR_LEFT:
                bulletX -= radius;
                break;
            case DIR_RIGHT:
                bulletX += radius;
                break;
        }
        Bullet bullet = BulletPool.get();
        bullet.setX(bulletX);
        bullet.setY(bulletY);
        bullet.setAtk(this.atk);
        bullet.setColor(this.color);
        bullet.setDir(this.dir);
        bullet.setVisible(true);
        this.bulletList.add(bullet);
        /**
         * 记录上一次开炮的时间
         */
        this.lastTime = currentTime;
        /**
         * 播放音乐
         */
        GamePanel.me.playMusic(MUSIC_FIRE);

    }

    /**
     * 画子弹
     *
     * @param g
     */
    protected void drawBullets(Graphics g) {
        for (int i = 0; i < this.bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            bullet.draw(g);
        }
    }

    /**
     * 画名字
     */
    protected void drawName(Graphics g) {
        g.setColor(color);
        g.setFont(TANK_NAME_FONT);
        g.drawString(name, x - radius, y - 60);
    }

    /**
     * 画爆炸
     *
     * @param g
     */
    public void drawExplode(Graphics g) {

        if (explode != null) {
            explode.setX(this.x - 2 * radius);
            explode.setY(this.y - 2 * radius);
            explode.draw(g);
        }

        if (explode != null && !explode.isVisible()) {
            ExplodePool.theReturn(explode);
        }
    }

    /**
     * 画血条
     */
    protected void drawBloodBar(Graphics g) {
        /**
         * 更新血条状态再画血条
         */
        updateBloodBar();
        bloodBar.draw(g);
    }

    protected void drawBorn(Graphics g) {
        if (born != null && born.isVisible()) {
            born.setX(this.x - radius);
            born.setY(this.y - radius);
            born.draw(g);
        }
    }

    /**
     * 更新血条
     */
    private void updateBloodBar() {
        this.bloodBar.setHp(this.hp);
        this.bloodBar.setX(x - radius);
        this.bloodBar.setY(y - 35);
    }

    /**
     * 移动子弹
     */
    public void moveBullets() {
        for (Bullet bullet : this.bulletList) {
            bullet.move();
        }
    }

    /**
     * 移除出界子弹
     */
    public void removeBullets() {
        bulletList.removeIf(bullet -> !bullet.isVisible());
    }

    /**
     * 进行碰撞处理
     */
    public void collideBullets(List<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (GameHelper.collide(this, bullet)) {
                //子弹消失
                bullet.setVisible(false);
                /**
                 * 判断是否已经死亡，如果死亡则，不继续判断，防止多次爆炸
                 */
                if (hp <= 0) {
                    continue;
                }
                //自身受到伤害
                hp -= bullet.getAtk();
                //发出碰撞声音
                GamePanel.me.playMusic(MUSIC_HIT);
                //生命值小于等于0时，爆炸效果
                if (hp <= 0) {
                    if (explode == null) {
                        explode = ExplodePool.get();
                    }
                    explode.setVisible(true);
                    explode.setIndex(0);
                    GamePanel.me.playMusic(MUSIC_BLAST);
                }

            }
        }
    }

    /**
     * 与地图块碰撞时，调整坦克自身位置的方法，该方法会在地图元素块的处理方法中进行调用
     *
     * @param block
     */
    public void collideBlockHanding(Block block) {
        switch (dir) {
            case DIR_UP:
                this.setY(block.getY() + block.getHeight() + radius);
                break;
            case DIR_DOWN:
                this.setY(block.getY() - radius);
                break;
            case DIR_LEFT:
                this.setX(block.getX() + block.getWidth() + radius);
                break;
            case DIR_RIGHT:
                this.setX(block.getX() - radius);
                break;
        }
    }

    /**
     * 重置状态
     */
    public void reset() {
        for (Bullet bullet : getBulletList()) {
            bullet.setVisible(false);
            BulletPool.theReturn(bullet);
        }
        getBulletList().clear();
    }


    /**
     * 是否死亡
     *
     * @return
     */
    public boolean isDie() {
        if (hp <= 0) {
            return true;
        }
        return false;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public Explode getExplode() {
        return explode;
    }

    public void setExplode(Explode explode) {
        this.explode = explode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BloodBar getBloodBar() {
        return bloodBar;
    }

    public void setBloodBar(BloodBar bloodBar) {
        this.bloodBar = bloodBar;
    }

    public Born getBorn() {
        return born;
    }

    public void setBorn(Born born) {
        this.born = born;
    }
}