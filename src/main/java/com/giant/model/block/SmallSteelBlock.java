package com.giant.model.block;

import com.giant.constant.ImageConstant;
import com.giant.model.Bullet;

public class SmallSteelBlock extends SmallBlock{
    public SmallSteelBlock(int x, int y) {
        super(x, y);
    }

    public SmallSteelBlock() {
        super();
        this.setId(SMALL_STEEL_BLOCK);
    }

    @Override
    public void collideBulletHanding(Bullet bullet) {
        bullet.setVisible(false);
    }
}
