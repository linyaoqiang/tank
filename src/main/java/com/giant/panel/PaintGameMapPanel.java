package com.giant.panel;

import com.giant.constant.GameConstant;
import com.giant.frame.GameFrame;
import com.giant.helper.BlockPool;
import com.giant.model.block.*;
import com.giant.model.map.EmptyGameMap;
import com.giant.model.map.GameMap;
import com.giant.model.tank.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.event.KeyEvent.*;
import static com.giant.constant.BlockConstant.*;

public class PaintGameMapPanel extends JPanel {
    private static final String PAINT_TITLE = "1.墙,2.钢,3.水,4.草地,5.小墙,6.小钢," +
            "7.在控制台打印地图信息,Esc.退出编辑,Delete.删除";
    private GameFrame gameFrame;
    private static int insetX = GamePanel.insetX;
    private static int insetY = GamePanel.insetY;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private Runnable runnable = new PaintRunnable();
    private boolean running;
    private PlayerTank playerTank = PlayerTank.PLAYER_ONE;
    private EmptyGameMap gameMap = (EmptyGameMap) GameMap.EMPTY_GAME_MAP;

    public PaintGameMapPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }

    /**
     * 绘制的核心方法
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.fillRect(0, 0, GameConstant.GAME_WIDTH, GameConstant.GAME_HEIGHT);
        playerTank.draw(g);
        gameMap.draw(g);
    }

    /**
     * 开启编辑地图
     */
    public void start() {
        this.gameFrame.setTitle(PAINT_TITLE);
        this.running = true;
        gameMap.reset();
        playerTank.reset();
        executorService.execute(runnable);
    }

    /**
     * 关闭编辑地图
     */
    public void stop() {
        returnBlocks();
        running = false;
    }

    /**
     * 退出时归还所有的地图块
     */
    private void returnBlocks() {
        gameMap.returnBlocks();
    }

    /**
     * 初始化
     */
    private void init() {
        this.setLocation(0, 0);
        this.setSize(GameConstant.GAME_WIDTH, GameConstant.GAME_HEIGHT);

    }

    /**
     * 添加方块
     * 如果重叠了，进行提升
     * @param id
     */
    public synchronized void addBlock(int id) {
        Block block = BlockPool.getBlock(id);
        block.setX(playerTank.getX() - playerTank.getRadius());
        block.setY(playerTank.getY() - playerTank.getRadius());
        if (gameMap.collideOtherBlocks(block)) {
            JOptionPane.showMessageDialog(this, "方块重叠了");
            return;
        }
        gameMap.getMapBlocks().add(block);
        block.designCollideTankHanding(playerTank);
    }

    /**
     * 打印地图信息
     * 玩家家园信息
     * 除了玩家家园信息
     */
    private synchronized void printMap() {
        List<Block> blockList = gameMap.getMapBlocks();
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (Block block : blockList) {
            buffer.append("{");
            int type = getBlockType(block);
            buffer.append("" + block.getX() + "," + block.getY() + "," + type);
            buffer.append("}");
            if (type == TANK_HOME_BLOCK) {
                System.out.println("坦克家园: {" + block.getX() + "," + block.getY() + "," + type + "}");
            }
        }
        buffer.append("]");
        System.out.println(buffer.toString());
    }

    /**
     * 删除地图块
     * 与其发生碰撞则删除
     */
    private void deleteBlocks() {
        for (Iterator<Block> iterator = gameMap.getMapBlocks().iterator(); iterator.hasNext(); ) {
            Block block = iterator.next();
            if (block instanceof TankHomeBlock) {
                continue;
            }
            if (block.collide(playerTank)) {
                iterator.remove();
                BlockPool.theReturn(block);
            }
        }
    }

    /**
     * 获取元素块的编号
     * @param block
     * @return
     */
    private int getBlockType(Block block) {
        return block.getId();
    }

    /**
     * 松开按键回调的方法
     * @param e
     */
    public void releasedKeyEvent(KeyEvent e) {
        playerTank.keyReleasedKeyEvent(e);
    }

    class PaintRunnable implements Runnable {

        @Override
        public void run() {
            while (running) {
                playerTank.move();
                repaint();
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 编辑地图的按键事件核心方法
     * @param e
     */
    public void keyEvent(KeyEvent e) {
        playerTank.keyEvent(e);
        paintKeyEvent(e);
    }

    /**
     * 窗体按键事件
     * @param e
     */
    public void paintKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_1:
            case VK_NUMPAD1:
                addBlock(0);
                break;
            case VK_2:
            case VK_NUMPAD2:
                addBlock(1);
                break;
            case VK_3:
            case VK_NUMPAD3:
                addBlock(2);
                break;
            case VK_4:
            case VK_NUMPAD4:
                addBlock(3);
                break;
            case VK_5:
            case VK_NUMPAD5:
                addBlock(4);
                break;
            case VK_6:
            case VK_NUMPAD6:
                addBlock(5);
                break;
            case VK_7:
            case VK_NUMPAD7:
                printMap();
                break;
            case VK_ESCAPE:
                stop();
                gameFrame.setTitle(GameConstant.GAME_TITLE);
                gameFrame.showMenu(this);
                break;
            case VK_DELETE:
                deleteBlocks();

        }
    }
}
