package com.giant.helper;

import com.giant.model.block.*;

import static com.giant.constant.BlockConstant.*;

/**
 * 地图元素块工程
 */
public class BlockFactory {
    public static Block createBlock(int type) {
        switch (type) {
            case DEFAULT_WALL_BLOCK:
                return new DefaultWallBlock();
            case DEFAULT_STEEL_BLOCK:
                return new DefaultSteelBlock();
            case DEFAULT_WATER_BLOCK:
                return new DefaultWaterBlock();
            case DEFAULT_GRASS_BLOCK:
                return new DefaultGrassBlock();
            case SMALL_WALL_BLOCK:
                return new SmallWallBlock();
            case SMALL_STEEL_BLOCK:
                return new SmallSteelBlock();
        }
        return null;
    }
}
