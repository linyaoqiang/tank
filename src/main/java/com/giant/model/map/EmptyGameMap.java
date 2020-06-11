package com.giant.model.map;

import com.giant.model.TankHome;
import com.giant.model.block.Block;

import java.util.ArrayList;

public class EmptyGameMap extends GameMap {
    public EmptyGameMap() {
        this.setMapBlocks(new ArrayList<>());
    }

    public void reset() {
        this.setTankHome(TankHome.getDefaultTankHome());
        this.getTankHome().reset();
        this.getMapBlocks().addAll(this.getTankHome().getHomeBlocks());
    }
}
