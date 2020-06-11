package com.giant.model.block;

import com.giant.constant.ImageConstant;
import com.giant.model.Bullet;
import com.giant.model.tank.Tank;

public class DefaultWallBlock extends DefaultBlock {
    public DefaultWallBlock(int x, int y) {
        super(x, y);
    }

    public DefaultWallBlock() {
        super();
        this.setId(DEFAULT_WALL_BLOCK);
    }

}
