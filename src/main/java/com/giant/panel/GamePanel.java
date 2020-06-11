package com.giant.panel;

import com.giant.frame.GameFrame;
import com.giant.model.LevelInformation;
import com.giant.model.map.GameMap;
import com.giant.model.map.LevelGameMap;
import com.giant.model.tank.EnemyTank;
import com.giant.model.tank.EnemyTankCamp;
import com.giant.model.tank.PlayerTank;
import com.giant.model.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.giant.constant.GameConstant.*;
import static com.giant.constant.MusicConstant.*;

public class GamePanel extends JPanel {
    /**
     * 定义运行状态和线程池
     * 定义Runnable对象，用于绘制界面和数据变更，执行窗体的核心方法，播放音效等等
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(8);
    private Runnable paintRunnable;
    private Runnable enemyRunnable;
    private PlayMusicRunnable playMusicRunnable;
    private boolean running;
    private boolean playMusic = true;
    private int fps;


    /**
     * 游戏相关信息
     * 玩家
     * 游戏窗体父组件
     * 敌人坦克大本营
     * 游戏地图
     */
    private List<PlayerTank> playerTanks;
    private GameFrame gameFrame;
    private EnemyTankCamp enemyTankCamp;
    private LevelGameMap gameMap = (LevelGameMap) GameMap.LEVEL_GAME_MAP;


    /**
     * 指定当前自身的一个变量
     * GamePanel只有一个实例
     */
    public static GamePanel me;


    /**
     * 定义程序补白部分，Swing程序在运行时，会有部分内容不展示出来，被用于填充等等
     */
    public static int insetX;
    public static int insetY;


    public GamePanel(GameFrame gameFrame, int fps) {
        this.gameFrame = gameFrame;
        this.fps = fps;
        GamePanel.me = this;
        /**
         * 初始化游戏面板
         */
        init();
    }


    /**
     * 开启游戏的核心方法
     */
    public void start() {
        running = true;
        executorService.execute(paintRunnable);
        executorService.execute(enemyRunnable);
    }

    /**
     * 开启单人游戏
     */
    public void startOnePlayerGame() {
        playerTanks = new ArrayList<>();
        playerTanks.add(PlayerTank.PLAYER_ONE);
        startNextLevel();
    }

    /**
     * 开启双人游戏
     */
    public void startTwoPlayerGame() {
        playerTanks = new ArrayList<>();
        playerTanks.add(PlayerTank.PLAYER_ONE);
        playerTanks.add(PlayerTank.PLAYER_TWO);
        startNextLevel();
    }

    /**
     * 游戏结束的方法
     */
    public void over() {
        running = false;
        LevelInformation.getInstance().setCurrentLevel(0);
    }


    /**
     * 绘制整个界面的方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        /**
         * 设置颜色
         */
        g.setColor(Color.BLACK);
        /**
         * 填充背景
         */
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        /**
         * 画玩家和敌人坦克还有地图块
         */
        draw(g);
    }

    /**
     * 进行碰撞处理的统一方法
     */
    private void collide() {
        /**
         * 坦克与子弹的碰撞检测和处理
         */
        tankCollideBullet();
        /**
         * 坦克，子弹与地图块的碰撞处理
         */
        mapCollide();
    }

    /**
     * 地图元素块与子弹还有坦克的碰撞处理
     */
    private void mapCollide() {
        List<Tank> tanks = new ArrayList<>();
        tanks.addAll(enemyTankCamp.getToWarTanks());
        tanks.addAll(playerTanks);
        /**
         *地图执行碰撞的核心处理方法
         */
        gameMap.collide(tanks);
    }


    /**
     * 所有坦克与子弹的碰撞处理
     * 玩家子弹与敌人的碰撞处理
     * 敌人子弹与玩家的碰撞处理
     */
    private void tankCollideBullet() {
        List<EnemyTank> tanks = enemyTankCamp.getToWarTanks();
        for (int i = 0; i < tanks.size(); i++) {
            EnemyTank enemyTank = tanks.get(i);
            for (PlayerTank playerTank : playerTanks) {
                /**
                 * 玩家子弹与敌机的碰撞处理
                 */
                playerTank.collideBullets(enemyTank.getBulletList());
                /**
                 * 敌机子弹与玩家的碰撞处理
                 */
                enemyTank.collideBullets(playerTank.getBulletList());
            }
        }
    }


    /**
     * 移动的处理方法
     */
    private void move() {
        playerMoveAndFire();
        handingPlayerBullets();
        moveEnemyTanks();
    }

    private void handingPlayerBullets() {
        for (PlayerTank playerTank : playerTanks) {
            playerTank.removeBullets();
            playerTank.moveBullets();
        }
    }


    private synchronized void moveEnemyTanks() {
        enemyTankCamp.move();
    }


    private void draw(Graphics g) {
        drawPlayers(g);
        drawEnemyTanks(g);
        drawGameMap(g);
    }

    private void playerMoveAndFire() {
        for (Iterator<PlayerTank> iterator = playerTanks.iterator(); iterator.hasNext(); ) {
            PlayerTank playerTank = iterator.next();
            if (playerTank.getHp() <= 0 && !playerTank.getExplode().isVisible()) {
                iterator.remove();
                continue;
            }
            playerTank.move();
            playerTank.fire();
        }
    }

    private void drawPlayers(Graphics g) {
        for (PlayerTank playerTank : playerTanks) {
            if (playerTank.getHp() <= 0 && !playerTank.getExplode().isVisible()) {
                continue;
            }
            playerTank.draw(g);
        }
    }

    private void resetPlayers() {
        for (PlayerTank playerTank : playerTanks) {
            playerTank.reset();
        }
    }

    private void drawEnemyTanks(Graphics g) {
        enemyTankCamp.draw(g);
    }

    private void drawGameMap(Graphics g) {
        gameMap.draw(g);
    }

    private void recycleEnemyTanks() {
        enemyTankCamp.recycleTanks();
    }

    private void exitToMenu(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            int index = JOptionPane.showConfirmDialog(this, "确定退出游戏到菜单界面?", "回到菜单", JOptionPane.OK_CANCEL_OPTION);
            if (index == JOptionPane.OK_OPTION) {
                index = JOptionPane.showConfirmDialog(this, "是否保存游戏进度?", "保存游戏", JOptionPane.OK_CANCEL_OPTION);
                if (index == JOptionPane.OK_OPTION) {
                    save();
                }
                over();
                LevelInformation.getInstance().setCurrentLevel(0);
                gameFrame.showMenu(this);
            }
        }
    }

    public void keyEvent(KeyEvent e) {
        exitToMenu(e);
        for (PlayerTank playerTank : playerTanks) {
            if (playerTank.getHp() <= 0) {
                continue;
            }
            playerTank.keyEvent(e);
        }
    }

    private synchronized void enemyAi() {
        enemyTankCamp.toWar();
        List<EnemyTank> enemyTanks = enemyTankCamp.getToWarTanks();
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.ai(playerTanks);
        }
    }

    /**
     * 进入下一关的核心方法
     */
    private void startNextLevel() {
        /**
         * 播放开始的音乐
         */
        playMusic(MUSIC_START);
        /**
         * 重置玩家信息,只有活下来的玩家才能进行重置
         */
        resetPlayers();

        /**
         * 获取到下一关
         */
        int level = LevelInformation.getInstance().getCurrentLevel() + 1;
        /**
         * 设置当前的关卡
         */
        LevelInformation.getInstance().setCurrentLevel(level);

        /**
         * 如果gameMap的地图元素块集合不为空，则进行清空，并放回到BlockPool中，进行复用
         */
        if (gameMap.getMapBlocks() != null) {
            gameMap.returnBlocks();
        }

        /**
         * 创建所有敌人坦克的集合
         */
        List<EnemyTank> enemyTanks = new ArrayList<>();
        /**
         * 设置敌人坦克的集合，并对其内部数据进行初始化
         */
        enemyTankCamp.setEnemyTanks(enemyTanks);
        /**
         * 加载关卡信息
         */
        LevelInformation.getInstance().level(level, gameMap, enemyTanks);

        /**
         * 如果当前不是运行状态，则运行游戏
         */
        if (running == false) {
            start();
        }
    }


    /**
     * 播放音乐
     *
     * @param musicPath
     */
    public void playMusic(String musicPath) {
        if (playMusic) {
            playMusicRunnable.setMusicPath(musicPath);
            executorService.execute(playMusicRunnable);
        }
    }

    /**
     * 初始化游戏面板的核心方法
     */
    private void init() {
        /**
         * 初始化面板数据
         */
        initPanel();
        /**
         * 初始化游戏运行时必要Runnable对象
         */
        initRunnable();
        /**
         * 初始化敌人坦克大本营
         */
        initEnemyCamp();
    }

    /**
     * 判断游戏是否结束并进行处理
     */
    private void gameOver() {
        boolean over = true;
        for (PlayerTank playerTank : playerTanks) {
            /**
             * 如果有至少一个玩家没有死亡，则游戏没有结束
             */
            if (playerTank.getHp() > 0) {
                over = false;
                break;
            }
        }
        /**
         * 如果还有剩余玩家，则继续判断是否玩家的大本营是否被打掉
         * 如果被打掉，则游戏结束
         */
        if (!over) {
            over = !gameMap.getTankHome().getTankHomeBlock().isVisible();
        }
        /**
         * 如果游戏结束进行处理
         */
        if (over) {
            over();
            gameFrame.showGameOver(this);
            playMusic(MUSIC_GAME_OVER);
        }
    }

    /**
     * 判断游戏是否胜利或者通过，并进行处理
     */
    private void win() {
        /**
         * 判断敌人大本营是否失败
         */
        if (enemyTankCamp.isLose()) {
            /**
             * 如果通关了进行处理
             */
            if (throughAllLevels()) {
                //TODO  THE END
                over();
                gameFrame.showTheEnd(this);
                playMusic(MUSIC_WIN);
            } else {
                /**
                 * 如果不是通关，则进入下一关
                 */
                startNextLevel();
            }

        }
    }

    /**
     * 判断是否通关
     *
     * @return
     */
    private boolean throughAllLevels() {
        if (LevelInformation.getInstance().getLevelCount() == LevelInformation.getInstance().getCurrentLevel()) {
            return true;
        }
        return false;
    }

    /**
     * 保存游戏进度的核心方法
     */
    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_GAME_PATH))) {
            out.writeObject(playerTanks);
            out.writeObject(enemyTankCamp);
            out.writeObject(gameMap);
            out.write(LevelInformation.getInstance().getCurrentLevel());
            JOptionPane.showMessageDialog(this, "游戏保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 继续游戏的核心方法
     */
    public void continueTheGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_GAME_PATH));) {
            Object object = in.readObject();
            if (object == null) {
                JOptionPane.showMessageDialog(gameFrame, "没有存档");
                gameFrame.showMenu(this);
                return;
            }
            List<PlayerTank> playerTanks = (List<PlayerTank>) object;
            EnemyTankCamp enemyTankCamp = (EnemyTankCamp) in.readObject();
            LevelGameMap gameMap = (LevelGameMap) in.readObject();
            int currentLevel = in.read();
            this.playerTanks = playerTanks;
            this.enemyTankCamp = enemyTankCamp;
            this.gameMap = gameMap;
            LevelInformation.getInstance().setCurrentLevel(currentLevel);
            start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化panel
     */
    private void initPanel() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocation(0, 0);
        Insets insets = gameFrame.getInsets();
        insetY = insets.top + insets.bottom;
        insetX = insets.left + insets.right;
    }

    /**
     * 初始化Runnable对象
     */
    private void initRunnable() {
        this.paintRunnable = new PaintRunnable();
        this.enemyRunnable = new TankAi();
        this.playMusicRunnable = new PlayMusicRunnable();
    }

    private void initEnemyCamp() {
        enemyTankCamp = new EnemyTankCamp();
    }

    public void keyReleasedKeyEvent(KeyEvent e) {
        for (PlayerTank playerTank : playerTanks) {
            playerTank.keyReleasedKeyEvent(e);
        }
    }

    class PaintRunnable implements Runnable {

        @Override
        public void run() {
            while (running) {
                //清除敌机
                recycleEnemyTanks();

                //子弹和敌机的移动
                move();

                //碰撞处理
                collide();

                //画
                repaint();

                //如果胜利了，进行处理
                win();

                //如果失败进行处理
                gameOver();
                try {
                    Thread.sleep(1000 / fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class TankAi implements Runnable {

        @Override
        public void run() {
            while (GamePanel.this.running) {
                enemyAi();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class PlayMusicRunnable implements Runnable {

        private String musicPath;

        public void setMusicPath(String musicPath) {
            this.musicPath = musicPath;
        }

        @Override
        public void run() {
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(musicPath));
                MUSIC_PLAYER.start(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    public boolean isPlayMusic() {
        return playMusic;
    }

    public void setPlayMusic(boolean playMusic) {
        this.playMusic = playMusic;
    }
}
