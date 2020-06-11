package com.giant.model.block;

public abstract class SmallBlock extends Block {
    public SmallBlock(int x, int y) {
        super(x, y);
    }

    public SmallBlock() {
        this.setWidth(SMALL_BLOCK_WIDTH);
        this.setHeight(SMALL_BLOCK_HEIGHT);
    }
}
