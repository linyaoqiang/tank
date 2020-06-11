package com.giant.panel;

import com.giant.constant.GameConstant;
import com.giant.frame.GameFrame;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.giant.constant.MenuConstant.*;
import static com.giant.constant.GameConstant.*;
import static com.giant.constant.ImageConstant.*;

public class MenuPanel extends JPanel {
    /**
     * 当前菜单的索引
     */
    private int menuIndex = 0;
    /**
     * 坦克图标的位置
     */
    private static final int MENU_TANK_X = (GAME_WIDTH - 128 - GamePanel.insetX) / 2;
    private static final int MENU_TANK_Y = 50;
    private GameFrame gameFrame;

    public MenuPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }

    private void init() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocation(0, 0);
    }

    /**
     * 绘制
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.setFont(GAME_FONT);
        g.setColor(Color.BLACK);
        /**
         * 绘制背景
         */
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.drawImage(MENU_TANK, MENU_TANK_X, MENU_TANK_Y, null);
        final int MARGIN_SIZE = 50;
        final int MARGIN_TOP = GAME_HEIGHT / 3;
        final int X = (GAME_WIDTH - 4 * 30) / 2;
        for (int i = 0; i < MENU_CONTENT.length; i++) {
            int y = MARGIN_TOP + MARGIN_SIZE * i;
            if (i == menuIndex) {
                g.setColor(Color.RED);
                g.drawImage(MENU_ARROW, X - MENU_ARROW_MARGIN_RIGHT, y - MARGIN_SIZE / 3 - 20, null);
            } else {
                g.setColor(Color.WHITE);
            }
            String append = "";
            if (i == IS_PLAY_MUSIC) {
                boolean flag = gameFrame.isPlayMusic();
                append = flag ? "开" : "关";
            }
            g.drawString(MENU_CONTENT[i] + append, X, y);
        }
        this.updateUI();
    }


    /**
     * 按键按下时进行处理
     *
     * @param e
     */
    public void keyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (this.menuIndex < MENU_CONTENT.length - 1) {
                    menuIndex++;
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (menuIndex > 0) {
                    menuIndex--;
                }
                break;
            case KeyEvent.VK_ENTER:
                enterHanding();

        }
        //每次响应事件后重画
        this.repaint();
    }

    /**
     * 按下回车的事件处理
     */
    private void enterHanding() {
        switch (menuIndex) {
            case PLAYER_ONE_GAME:
                gameFrame.newOnePlayerGame();
                break;
            case PLAYER_TWO_GAME:
                gameFrame.newTwoPlayerGame();
                break;
            case CONTINUE_GAME:
                gameFrame.continueTheGame();
                break;
            case IS_PLAY_MUSIC:
                gameFrame.setPlayMusic(!gameFrame.isPlayMusic());
                break;
            case EDIT_MAP:
                gameFrame.designGameMap();
                break;
            case GAME_ABOUT:
                gameFrame.showAbout();
                break;
            case GAME_HELP:
                gameFrame.showHelp();
                break;
            case EXIT:
                System.exit(0);
        }
    }
}
