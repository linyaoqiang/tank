package com.giant.constant;

import com.giant.panel.GamePanel;

import java.awt.*;
import java.io.*;
import java.util.Arrays;

/**
 * 游戏常量
 */
public class GameConstant {
    /**
     * 窗口标题
     */
    public static final String GAME_TITLE = "坦克大战";

    /**
     * 窗口大小
     */
    public static final int GAME_WIDTH = 1200;
    public static final int GAME_HEIGHT = 900;

    /**
     * 窗口位置
     */
    public static int gameX;
    public static int gameY;

    /**
     * 窗口状态
     */
    public static final int STATE_MENU = 0;
    public static final int STATE_HELP = 1;
    public static final int STATE_ABOUT = 2;
    public static final int STATE_RUNNING = 3;
    public static final int STATE_OVER = 4;
    public static final int STATE_DESIGN = 5;
    public static final int STATE_END = 6;

    public static final Font GAME_FONT = new Font("微软雅黑", Font.ITALIC, 30);

    /**
     * the end 或者game over的提示字符串
     */
    public static final String ESC_STRING = "ESC退出程序";
    public static final String ENTER_STRING = "ENTER回到菜单";
    public static final Font FONT_END = new Font("微软雅黑", Font.BOLD, 40);
    public static final int STRING_Y = GAME_HEIGHT - GamePanel.insetY - 60;
    public static final int ESC_STRING_X = 50;
    public static final int ENETER_STRING_X = 800;

    public static String[] NOUNS;
    public static String[] ADJECTIVES;

    public static final String SAVE_GAME_PATH = "info/game.tmp";

    /**
     * 加载游戏窗体的位置，坦克名称集合的加载
     */
    static {
        /**
         * 初始化gameX,gameY
         */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        gameY = height - GAME_HEIGHT >> 1;
        gameX = width - GAME_WIDTH >> 1;


        /**
         * 初始化名词和形容词
         */

        try {
            BufferedReader in = new BufferedReader(new FileReader("knowledge" + File.separator + "n.tmp"));
            NOUNS = getStreamString(in);
            in = new BufferedReader(new FileReader("knowledge" + File.separator + "adj.tmp"));
            ADJECTIVES = getStreamString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String[] getStreamString(BufferedReader in) throws IOException {
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString().split("、");
    }
}
