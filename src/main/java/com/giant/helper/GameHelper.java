package com.giant.helper;

import com.giant.model.Bullet;
import com.giant.model.block.Block;
import com.giant.model.tank.Tank;

import java.awt.*;

import static com.giant.constant.GameConstant.*;

/**
 * 游戏帮助者，用于许多的判断和工具方法
 */
public class GameHelper {
    private static Rectangle rectangle1 = new Rectangle();
    private static Rectangle rectangle2 = new Rectangle();

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static Color getRandomColor() {
        int red = getRandomInt(0, 255);
        int green = getRandomInt(0, 255);
        int blue = getRandomInt(0, 255);
        return new Color(red, green, blue);
    }

    public static boolean collide(Tank tank, Bullet bullet) {
        rectangle1.setLocation(tank.getX() - tank.getRadius(), tank.getY() - tank.getRadius());
        rectangle1.setSize(2 * tank.getRadius(), 2 * tank.getRadius());

        rectangle2.setLocation(bullet.getX() - bullet.getRadius(), bullet.getY() - bullet.getRadius());
        rectangle2.setSize(2 * bullet.getRadius(), 2 * bullet.getRadius());

        return rectangle1.intersects(rectangle2);
    }

    public static boolean collide(Block block1, Block block2) {
        return collide(block1, block2.getX(), block2.getY(), block2.getWidth(), block2.getHeight());
    }

    public static boolean collide(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        rectangle1.setLocation(x1, y1);
        rectangle1.setSize(w1, h1);

        rectangle2.setLocation(x2, y2);
        rectangle2.setSize(w2, h2);
        return rectangle1.intersects(rectangle2);
    }

    public static boolean collide(Block block, Tank tank) {
        int x2 = tank.getX() - tank.getRadius();
        int y2 = tank.getY() - tank.getRadius();
        int w2 = 2 * tank.getRadius();
        int h2 = 2 * tank.getRadius();
        return collide(block, x2, y2, w2, h2);
    }

    public static boolean collide(Block block, Bullet bullet) {
        int x2 = bullet.getX() - bullet.getRadius();
        int y2 = bullet.getY() - bullet.getRadius();
        int w2 = 2 * bullet.getRadius();
        int h2 = 2 * bullet.getRadius();
        return collide(block, x2, y2, w2, h2);
    }

    public static boolean collide(Tank tank, int x2, int y2, int w2, int h2) {
        rectangle1.setLocation(tank.getX() - tank.getRadius(), tank.getY() - tank.getRadius());
        rectangle1.setSize(2 * tank.getRadius(), 2 * tank.getRadius());

        rectangle2.setLocation(x2, y2);
        rectangle2.setSize(w2, h2);
        return rectangle1.intersects(rectangle2);
    }

    public static boolean collide(Block block, int x2, int y2, int w2, int h2) {
        return collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x2, y2, w2, h2);
    }

    public static String getRandomName() {
        return ADJECTIVES[getRandomInt(0, ADJECTIVES.length - 1)] + "的" + NOUNS[getRandomInt(0, NOUNS.length - 1)];
    }
}
