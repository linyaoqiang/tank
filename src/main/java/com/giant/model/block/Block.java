package com.giant.model.block;

import com.giant.constant.BlockConstant;
import com.giant.constant.ImageConstant;
import com.giant.helper.GameHelper;
import com.giant.model.Bullet;
import com.giant.model.tank.Tank;

import java.awt.*;
import java.io.Serializable;

/**
 * 地图元素块
 */
public abstract class Block implements BlockConstant, Serializable {
    /**
     * 元素块左上角的位置
     */
    private int x;
    private int y;


    /**
     * 标识元素块大小
     */
    private int width;
    private int height;

    private int id;

    /**
     * 该方块对应的图片
     */
    private transient Image image;

    private boolean visible;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean collide(Block block) {
        return GameHelper.collide(this, block);
    }

    public Block() {
    }

    /**
     * 默认判断与子弹碰撞的方法，可以从子类看具体的实现
     * 具体看子类实现，以下默认方法都是看子类
     *
     * @param bullet
     * @return
     */
    public boolean collide(Bullet bullet) {
        if (bullet != null) {
            return GameHelper.collide(this, bullet);
        }
        return false;
    }

    /**
     * 默认与子弹铺砖的处理方法
     *
     * @param bullet
     */
    public void collideBulletHanding(Bullet bullet) {
        bullet.setVisible(false);
        this.setVisible(false);
    }

    /**
     * 默认判断与坦克碰撞的方法
     *
     * @param tank
     * @return
     */
    public boolean collide(Tank tank) {
        return designCollide(tank);
    }

    /**
     * 用于设计地图时使用的碰撞检测
     *
     * @param tank
     * @return
     */
    public boolean designCollide(Tank tank) {
        return GameHelper.collide(this, tank);
    }

    /**
     * 默认与坦克碰撞的处理方法
     *
     * @param tank
     */
    public void collideTankHanding(Tank tank) {
        designCollideTankHanding(tank);
    }

    /**
     * 用于设计地图时使用的碰撞处理方法
     *
     * @param tank
     */
    public void designCollideTankHanding(Tank tank) {
        tank.collideBlockHanding(this);
    }

    /**
     * 设计地图时的碰撞处理
     *
     * @param tank
     */
    public void collideForDesign(Tank tank) {
        if (designCollide(tank)) {
            designCollideTankHanding(tank);
        }
    }

    /**
     * 绘制方法
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(ImageConstant.BLOCK_IMAGES[id], x, y, null);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Block{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", id=" + id +
                ", image=" + image +
                ", visible=" + visible +
                '}';
    }
}
