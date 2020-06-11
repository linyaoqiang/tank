package com.giant.model.block;

import com.giant.constant.ImageConstant;
import com.giant.model.Bullet;
import com.giant.model.tank.Tank;

public class DefaultWaterBlock extends DefaultBlock {
    public DefaultWaterBlock(int x, int y) {
        super(x, y);
    }

    public DefaultWaterBlock() {
        super();
        this.setId(this.DEFAULT_WATER_BLOCK);
    }

    @Override
    public boolean collide(Bullet bullet) {
        return false;
    }

}
