package com.giant.frame;

import com.giant.panel.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import static com.giant.constant.GameConstant.*;

/**
 * 游戏窗体
 */
public class GameFrame extends JFrame {
    private int gameState;
    private MenuPanel menuPanel;
    private GamePanel gamingPanel;
    private PaintGameMapPanel paintGameMapPanel;
    private TheEndPanel theEndPanel;
    private GameOverPanel gameOverPanel;
    private AboutPanel aboutPanel;
    private HelpPanel helpPanel;

    private JPanel currentPanel;

    public GameFrame() {
        init();

    }

    /**
     * 进行初始化的核心方法
     */
    private void init() {
        /**
         * 初始化窗体
         *
         */
        initFrame();
        /**
         * 初始化组件
         */
        initComponents();
        /**
         * 初始化按键监听
         */
        initEventListener();
        /**
         * 绘制界面，让界面显示出来
         */
        repaint();
    }


    /**
     * 初始化所有的组件
     */
    private void initComponents() {
        this.menuPanel = new MenuPanel(this);
        this.gamingPanel = new GamePanel(this, 60);
        this.paintGameMapPanel = new PaintGameMapPanel(this);
        this.theEndPanel = new TheEndPanel(this);
        this.gameOverPanel = new GameOverPanel(this);
        this.aboutPanel = new AboutPanel(this);
        this.helpPanel = new HelpPanel(this);
        /**
         * 游戏初始界面为菜单界面
         */
        this.add(menuPanel);
    }

    /**
     * 初始化窗体
     */
    private void initFrame() {
        this.setTitle(GAME_TITLE);
        this.setBounds(gameX, gameY, GAME_WIDTH, GAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * 初始化监听
     */
    private void initEventListener() {
        /**
         * 添加按键监听
         */
        this.addKeyListener(new KeyAdapter() {

            /**
             * 根据当前窗体的显示状态进行不同按键按下回调方法
             * @param e   按键事件对象
             */
            @Override
            public void keyPressed(KeyEvent e) {
                switch (gameState) {
                    case STATE_MENU:
                        menuKeyEvent(e);
                        break;
                    case STATE_ABOUT:
                        aboutKeyEvent(e);
                        break;
                    case STATE_HELP:
                        helpKeyEvent(e);
                        break;
                    case STATE_RUNNING:
                        runningKeyEvent(e);
                        break;
                    case STATE_DESIGN:
                        designKeyEvent(e);
                        break;
                    case STATE_OVER:
                    case STATE_END:
                        endOrOverKeyEvent(e);
                }
            }

            /**
             * 按键松开时执行的回调方法
             * 只有设计和游戏中时需要进行按键松开的处理
             * @param e 事件对象
             */
            @Override
            public void keyReleased(KeyEvent e) {
                switch (gameState) {
                    case STATE_RUNNING:
                        runningReleasedKeyEvent(e);
                        break;
                    case STATE_DESIGN:
                        designReleasedKeyEvent(e);
                }
            }
        });
    }

    /**
     * 游戏中按键松开的回调方法
     *
     * @param e
     */
    private void runningReleasedKeyEvent(KeyEvent e) {
        gamingPanel.keyReleasedKeyEvent(e);
    }

    /**
     * 设计地图时按键松开的回调方法
     *
     * @param e
     */
    private void designReleasedKeyEvent(KeyEvent e) {
        paintGameMapPanel.releasedKeyEvent(e);
    }


    /**
     * 游戏通关或者是游戏结束时的回调函数
     *
     * @param e
     */
    private void endOrOverKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_ENTER:
                showMenu(currentPanel);
                break;
        }
    }

    /**
     * 游戏中的按键按下回调
     *
     * @param e
     */
    private void runningKeyEvent(KeyEvent e) {
        gamingPanel.keyEvent(e);
    }

    /**
     * 以下都是不同状态的回调
     *
     * @param e
     */
    private void helpKeyEvent(KeyEvent e) {
        showMenu(currentPanel);
    }

    private void aboutKeyEvent(KeyEvent e) {
        showMenu(currentPanel);
    }

    private void menuKeyEvent(KeyEvent e) {
        menuPanel.keyEvent(e);
    }

    private void designKeyEvent(KeyEvent e) {
        paintGameMapPanel.keyEvent(e);
    }


    /**
     * 展示不同的panel的方法
     * 以下都是
     *
     * @param panel
     */
    public void showMenu(JPanel panel) {
        this.remove(panel);
        this.add(menuPanel);
        this.gameState = STATE_MENU;
        menuPanel.updateUI();
    }

    public void showTheEnd(JPanel panel) {
        this.remove(panel);
        this.add(theEndPanel);
        this.gameState = STATE_END;
        this.currentPanel = theEndPanel;
        showPanel(theEndPanel);
    }

    public void showGameOver(JPanel panel) {
        this.remove(panel);
        this.add(gameOverPanel);
        this.gameState = STATE_OVER;
        this.currentPanel = gameOverPanel;
        showPanel(gameOverPanel);
    }

    public void showAbout() {
        this.remove(menuPanel);
        this.add(aboutPanel);
        this.gameState = STATE_ABOUT;
        this.currentPanel = aboutPanel;
        aboutPanel.repaint();
    }

    public void showHelp() {
        this.remove(menuPanel);
        this.add(helpPanel);
        this.gameState = STATE_ABOUT;
        this.currentPanel = helpPanel;
        helpPanel.repaint();
    }

    private void playPre() {
        this.remove(menuPanel);
        this.add(gamingPanel);
        this.gameState = STATE_RUNNING;
    }

    /**
     * 开启一个人游戏
     */
    public void newOnePlayerGame() {
        playPre();
        this.gamingPanel.startOnePlayerGame();
    }

    /**
     * 开启双人游戏
     */
    public void newTwoPlayerGame() {
        playPre();
        this.gamingPanel.startTwoPlayerGame();
    }

    /**
     * 继续游戏
     */
    public void continueTheGame() {
        playPre();
        gamingPanel.continueTheGame();
    }

    /**
     * 设计地图
     */
    public void designGameMap() {
        this.remove(menuPanel);
        this.add(paintGameMapPanel);
        this.gameState = STATE_DESIGN;
        this.gamingPanel.setRunning(false);
        this.paintGameMapPanel.start();
    }

    /**
     * 有一些JPanel中有绘制图片，所以必须要多次绘制才能展示
     *
     * @param panel
     */
    private void showPanel(JPanel panel) {
        for (int i = 0; i < 5; i++) {
            panel.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置是否播放音乐
     *
     * @param playMusic
     */
    public void setPlayMusic(boolean playMusic) {
        gamingPanel.setPlayMusic(playMusic);
    }

    /**
     * 查看是否播放音乐
     *
     * @return
     */
    public boolean isPlayMusic() {
        return gamingPanel.isPlayMusic();
    }


    /**
     * 绘制信息
     *
     * @param infos
     * @param g
     */

    public static void drawInfo(String title, String[] infos, Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(FONT_END);
        g.drawString(title, (GAME_WIDTH - GamePanel.insetX - 30 * title.length()) / 2, 160);
        g.setFont(GAME_FONT);
        for (int i = 0; i < infos.length; i++) {
            String str = infos[i];
            int x = (GAME_WIDTH - GamePanel.insetX - 30 * str.length()) / 2;
            g.drawString(str, x, i * 60 + GamePanel.insetY + 200);
        }
    }
}
