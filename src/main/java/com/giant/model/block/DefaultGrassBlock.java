package com.giant.model.block;

import com.giant.constant.ImageConstant;
import com.giant.model.Bullet;
import com.giant.model.tank.Tank;

public class DefaultGrassBlock extends DefaultBlock{
    public DefaultGrassBlock(int x, int y) {
        super(x, y);
    }

    public DefaultGrassBlock() {
        super();
        this.setId(DEFAULT_GRASS_BLOCK);
    }

    @Override
    public boolean collide(Bullet bullet) {
        return false;
    }

    @Override
    public void collideTankHanding(Tank tank) {

    }
}
