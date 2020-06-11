package com.giant.model.block;

import com.giant.constant.ImageConstant;
import com.giant.model.Bullet;

public class DefaultSteelBlock extends DefaultBlock {
    public DefaultSteelBlock(int x, int y) {
        super(x, y);
    }

    public DefaultSteelBlock() {
        super();
        this.setId(DEFAULT_STEEL_BLOCK);
    }

    @Override
    public void collideBulletHanding(Bullet bullet) {
        bullet.setVisible(false);
    }
}
