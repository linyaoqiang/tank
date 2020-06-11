package com.giant.model;

import com.giant.constant.GameConstant;
import com.giant.helper.BlockPool;
import com.giant.model.block.Block;
import com.giant.model.block.TankHomeBlock;
import com.giant.panel.GamePanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家大本营
 */
public class TankHome implements Serializable {
    private List<Block> homeBlocks = new ArrayList<>();
    private TankHomeBlock tankHomeBlock;

    public static TankHome getDefaultTankHome() {
        TankHome home = new TankHome();
        for (int i = 0; i < 3; i++) {
            Block block = BlockPool.getWallBlock();
            block.setX(((GameConstant.GAME_WIDTH - block.getWidth() - GamePanel.insetX) / 2) + (i - 1) * block.getWidth());
            block.setY(GameConstant.GAME_HEIGHT - 2 * block.getHeight() - GamePanel.insetY);
            home.homeBlocks.add(block);
        }
        for (int i = 0; i < 3; i++) {
            Block block = null;
            if (i != 1) {
                block = BlockPool.getWallBlock();
            } else {
                home.tankHomeBlock = new TankHomeBlock();
                block = home.tankHomeBlock;
            }
            block.setX(((GameConstant.GAME_WIDTH - block.getWidth() - GamePanel.insetX) / 2) + (i - 1) * block.getWidth());
            block.setY(GameConstant.GAME_HEIGHT - block.getHeight() - GamePanel.insetY);
            home.homeBlocks.add(block);
        }
        return home;
    }

    public TankHome(List<Block> homeBlocks, TankHomeBlock tankHomeBlock) {
        this.homeBlocks = homeBlocks;
        this.tankHomeBlock = tankHomeBlock;
    }

    public void reset() {
        for (Block block : homeBlocks) {
            block.setVisible(true);
        }
    }

    public TankHome() {
    }

    public List<Block> getHomeBlocks() {
        return homeBlocks;
    }

    public void setHomeBlocks(List<Block> homeBlocks) {
        this.homeBlocks = homeBlocks;
    }

    public TankHomeBlock getTankHomeBlock() {
        return tankHomeBlock;
    }

    public void setTankHomeBlock(TankHomeBlock tankHomeBlock) {
        this.tankHomeBlock = tankHomeBlock;
    }
}
