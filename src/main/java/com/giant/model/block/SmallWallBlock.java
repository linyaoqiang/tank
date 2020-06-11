package com.giant.model.block;

import com.giant.constant.ImageConstant;

public class SmallWallBlock extends SmallBlock {
    public SmallWallBlock(int x, int y) {
        super(x, y);
    }

    public SmallWallBlock() {
        super();
        this.setId(SMALL_WALL_BLOCK);
    }
}
