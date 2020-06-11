package com.giant.model;

import java.awt.*;
import java.io.Serializable;

import static com.giant.constant.ImageConstant.*;

/**
 * 爆炸类
 */
public class Explode implements Serializable {
    private int x;
    private int y;
    private boolean visible;
    private int index;
    public static int play_count = 16;


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Explode() {
    }

    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        g.drawImage(BLAST_IMAGES[index >> 2], x, y, null);
        index++;
        if (index >= play_count) {
            visible = false;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isVisible() {
        return visible;
    }


    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
