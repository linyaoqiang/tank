package com.giant.helper;

import com.giant.model.block.*;
import com.giant.model.tank.CommonEnemyTank;
import com.giant.model.tank.EliteEnemyTank;
import com.giant.model.tank.EnemyTank;

import java.util.ArrayList;
import java.util.List;

import static com.giant.constant.BlockConstant.*;

/**
 * 地图元素块池
 */
public class BlockPool {
    private static final int DEFAULT_SIZE = 5;
    private static final int MAX_SIZE = 15;
    private static List<Block> defaultWallBlockPool = new ArrayList<>();
    private static List<Block> defaultSteelBlockPool = new ArrayList<>();
    private static List<Block> defaultWaterBlockPool = new ArrayList<>();
    private static List<Block> defaultGrassBlockPool = new ArrayList<>();
    private static List<Block> smallWallBlockPool = new ArrayList<>();
    private static List<Block> smallSteelBlockPool = new ArrayList<>();


    static {
        init();
    }

    private static void init() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            defaultWallBlockPool.add(BlockFactory.createBlock(DEFAULT_WALL_BLOCK));
            defaultSteelBlockPool.add(BlockFactory.createBlock(DEFAULT_STEEL_BLOCK));
            defaultWaterBlockPool.add(BlockFactory.createBlock(DEFAULT_WATER_BLOCK));
            defaultGrassBlockPool.add(BlockFactory.createBlock(DEFAULT_GRASS_BLOCK));
            smallWallBlockPool.add(BlockFactory.createBlock(SMALL_WALL_BLOCK));
            smallSteelBlockPool.add(BlockFactory.createBlock(SMALL_STEEL_BLOCK));
        }
    }

    public static Block getBlock(int id) {
        switch (id) {
            case DEFAULT_WALL_BLOCK:
                return getWallBlock();
            case DEFAULT_STEEL_BLOCK:
                return getSteelBlock();
            case DEFAULT_WATER_BLOCK:
                return getWaterBlock();
            case DEFAULT_GRASS_BLOCK:
                return getGrassBlock();
            case SMALL_WALL_BLOCK:
                return getSmallWallBlock();
            case SMALL_STEEL_BLOCK:
                return getSmallSteelBlock();
            case TANK_HOME_BLOCK:
                return new TankHomeBlock();
        }
        return null;
    }


    public static Block getWallBlock() {
        Block block = null;
        if (defaultWallBlockPool.size() > 0) {
            block = defaultWallBlockPool.remove(0);
        } else if (defaultWallBlockPool.size() <= 0) {
            block = BlockFactory.createBlock(DEFAULT_WALL_BLOCK);
        }
        block.setVisible(true);
        return block;
    }

    public static Block getSteelBlock() {
        Block block = null;
        if (defaultSteelBlockPool.size() > 0) {
            block = defaultSteelBlockPool.remove(0);
        } else if (defaultSteelBlockPool.size() <= 0) {
            block = BlockFactory.createBlock(DEFAULT_STEEL_BLOCK);
        }
        block.setVisible(true);
        return block;
    }

    public static Block getWaterBlock() {
        Block block = null;
        if (defaultWaterBlockPool.size() > 0) {
            block = defaultWaterBlockPool.remove(0);
        } else if (defaultWaterBlockPool.size() <= 0) {
            block = BlockFactory.createBlock(DEFAULT_WATER_BLOCK);
        }
        block.setVisible(true);
        return block;
    }

    public static Block getGrassBlock() {
        Block block = null;
        if (defaultGrassBlockPool.size() > 0) {
            block = defaultGrassBlockPool.remove(0);
        } else if (defaultGrassBlockPool.size() <= 0) {
            block = BlockFactory.createBlock(DEFAULT_GRASS_BLOCK);
        }
        block.setVisible(true);
        return block;
    }

    public static Block getSmallWallBlock() {
        Block block = null;
        if (smallWallBlockPool.size() > 0) {
            block = smallWallBlockPool.remove(0);
        } else if (smallWallBlockPool.size() <= 0) {
            block = BlockFactory.createBlock(SMALL_WALL_BLOCK);
        }
        block.setVisible(true);
        return block;
    }

    public static Block getSmallSteelBlock() {
        Block block = null;
        if (smallSteelBlockPool.size() > 0) {
            block = smallSteelBlockPool.remove(0);
        } else if (smallSteelBlockPool.size() <= 0) {
            block = BlockFactory.createBlock(SMALL_STEEL_BLOCK);
        }
        block.setVisible(true);
        return block;
    }

    public static void theReturn(Block block) {
        List<Block> pool = null;
        if (block instanceof DefaultWallBlock) {
            pool = defaultWallBlockPool;
        } else if (block instanceof DefaultSteelBlock) {
            pool = defaultSteelBlockPool;
        } else if (block instanceof DefaultWaterBlock) {
            pool = defaultWaterBlockPool;
        } else if (block instanceof DefaultGrassBlock) {
            pool = defaultGrassBlockPool;
        } else if (block instanceof SmallWallBlock) {
            pool = smallWallBlockPool;
        } else if (block instanceof SmallSteelBlock) {
            pool = smallSteelBlockPool;
        }
        if(pool==null){
            return;
        }

        if (pool.size() < MAX_SIZE) {
            pool.add(block);
        }
    }
}
