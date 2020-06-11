package com.giant.model.map;

import com.giant.helper.BlockPool;
import com.giant.model.Bullet;
import com.giant.model.TankHome;
import com.giant.model.block.Block;
import com.giant.model.block.DefaultWallBlock;
import com.giant.model.tank.Tank;
import com.giant.panel.GamePanel;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.giant.constant.TankConstant.*;

public abstract class GameMap implements Serializable {
    public static final int MARGIN_TOP = 3 * IMAGE_TANK_RADIUS;
    public static final int MARGIN_LEFT = 3 * IMAGE_TANK_RADIUS;
    public static final int MARGIN_RIGHT = 3 * IMAGE_TANK_RADIUS + GamePanel.insetX;
    public static final int MARGIN_BOTTOM = 8 * IMAGE_TANK_RADIUS + GamePanel.insetY;
    public static final GameMap RANDOM_GAME_MAP = new RandomGameMap();
    public static final GameMap EMPTY_GAME_MAP = new EmptyGameMap();
    public static final GameMap LEVEL_GAME_MAP = new LevelGameMap();
    /**
     * 地图元素块
     */
    private List<Block> mapBlocks;
    private TankHome tankHome;

    public GameMap() {
    }

    /**
     * 绘制地图元素块
     * @param g
     */
    public void draw(Graphics g) {
        for (int i = 0; i < mapBlocks.size(); i++) {
            Block block = mapBlocks.get(i);
            block.draw(g);
        }
    }

    /**
     * 大墙方法转换成四个小墙的核心方法
     */
    public void wallBlock2SmallBlock() {
        List<Block> smallWallBlock = new ArrayList<>();
        for (Iterator<Block> iterator = mapBlocks.iterator(); iterator.hasNext(); ) {
            Block block = iterator.next();
            if (block instanceof DefaultWallBlock) {
                /**
                 * 将DefaultWallBlock转换成四个小块
                 */
                List<Block> blocks = default2Small((DefaultWallBlock) block);
                /**
                 * 添加到集合中
                 */
                smallWallBlock.addAll(blocks);
                /**
                 * 移除大墙
                 */
                iterator.remove();
            }
        }
        /**
         * 为了保证不出错，在遍历完成之后再添加，迭代器要求不能在迭代时不能添加
         */
        mapBlocks.addAll(smallWallBlock);
    }

    /**
     * 大墙转小墙
     * @param wallBlock
     * @return
     */
    private List<Block> default2Small(DefaultWallBlock wallBlock) {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Block block = BlockPool.getSmallWallBlock();
                block.setX(wallBlock.getX() + i * (wallBlock.getWidth() >> 1));
                block.setY(wallBlock.getY() + j * (wallBlock.getWidth() >> 1));
                blocks.add(block);
                block.setVisible(true);
            }
        }
        return blocks;
    }

    /**
     * 判断方块是否已经存在的方块重叠
     * @param block
     * @return
     */
    public boolean collideOtherBlocks(Block block) {
        for (int i = 0; i < mapBlocks.size(); i++) {
            Block mapBlock = mapBlocks.get(i);
            if (block.collide(mapBlock)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 地图(所有地图元素块与坦克和子弹的碰撞检测和处理)
     * @param tanks
     */
    public void collide(List<Tank> tanks) {
        for (Block mapBlock : mapBlocks) {
            for (Tank tank : tanks) {
                /**
                 * 如果与坦克碰撞了
                 */
                if (mapBlock.collide(tank)) {
                    /**
                     * 地图元素快与坦克的碰撞处理
                     */
                    mapBlock.collideTankHanding(tank);
                }
                /**
                 * 获取当前坦克的所有子弹
                 */
                List<Bullet> bullets = tank.getBulletList();
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet bullet = bullets.get(i);
                    /**
                     * 检测是否发生碰撞
                     */
                    if (mapBlock.collide(bullet)) {
                        /**
                         * 子弹与地图元素块的碰撞处理
                         */
                        mapBlock.collideBulletHanding(bullet);
                    }
                }
            }
        }

        recycle();
    }

    /**
     * 将地图元素块归还到地图元素块池中
     */
    public void returnBlocks() {
        for (Block block : getMapBlocks()) {
            BlockPool.theReturn(block);
        }
        /**
         * 清除重置
         */
        getMapBlocks().clear();
    }

    /**
     * 回收地图元素块，如果该元素块已经被子弹打掉，则清除，并归还对象池中
     */
    private void recycle() {
        for (Iterator<Block> iterator = mapBlocks.iterator(); iterator.hasNext(); ) {
            Block block = iterator.next();
            if (!block.isVisible()) {
                iterator.remove();
                BlockPool.theReturn(block);
            }
        }
    }

    public List<Block> getMapBlocks() {
        return mapBlocks;
    }

    public void setMapBlocks(List<Block> mapBlocks) {
        this.mapBlocks = mapBlocks;
    }

    public TankHome getTankHome() {
        return tankHome;
    }

    public void setTankHome(TankHome tankHome) {
        this.tankHome = tankHome;
    }
}
