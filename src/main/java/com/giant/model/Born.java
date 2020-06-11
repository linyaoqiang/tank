package com.giant.model;

import java.awt.*;
import java.io.Serializable;

import static com.giant.constant.ImageConstant.BORN_IMAGES;

/**
 * 出生效果类
 */
public class Born implements Serializable {
    private int x;
    private int y;
    private boolean visible;
    private int currentIndex = 0;
    private static final int INDEX_SIZE = BORN_IMAGES.length << 2;

    public Born(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Born() {

    }

    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        g.drawImage(BORN_IMAGES[currentIndex >> 2], x, y, null);
        currentIndex++;
        if (currentIndex >= INDEX_SIZE) {
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
