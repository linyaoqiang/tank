package com.giant.model.block;

public abstract class DefaultBlock extends Block{
    public DefaultBlock(int x, int y) {
        super(x, y);
    }

    public DefaultBlock() {
        this.setWidth(DEFAULT_BLOCK_WIDTH);
        this.setHeight(DEFAULT_BLOCK_HEIGHT);
    }
}
