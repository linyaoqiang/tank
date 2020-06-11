package com.giant.model.map;

import com.giant.helper.BlockPool;
import com.giant.model.TankHome;
import com.giant.model.block.Block;
import com.giant.model.block.DefaultGrassBlock;

import java.util.ArrayList;
import java.util.List;

import static com.giant.helper.GameHelper.*;
import static com.giant.constant.GameConstant.*;
import static com.giant.constant.BlockConstant.*;

public class RandomGameMap extends GameMap {
    public RandomGameMap() {
    }

    public void initRandomMap(int blockCount) {
        List<Block> mapBlocks = new ArrayList<>();
        this.setMapBlocks(mapBlocks);
        for (int i = 0; i < blockCount; i++) {
            int x = getRandomInt(MARGIN_LEFT, GAME_WIDTH - MARGIN_RIGHT);
            int y = getRandomInt(MARGIN_TOP, GAME_HEIGHT - MARGIN_BOTTOM);
            int blockType = getRandomInt(DEFAULT_WALL_BLOCK, SMALL_STEEL_BLOCK);
            Block block = BlockPool.getBlock(blockType);
            block.setX(x);
            block.setY(y);
            if (collideOtherBlocks(block)) {
                BlockPool.theReturn(block);
                i--;
                continue;
            }
            block.setVisible(true);
            mapBlocks.add(block);
        }
        this.setTankHome(TankHome.getDefaultTankHome());
        this.getMapBlocks().addAll(this.getTankHome().getHomeBlocks());
        this.wallBlock2SmallBlock();
    }
}
