package com.giant.panel;

import com.giant.frame.GameFrame;
import com.giant.model.GameInformation;

import javax.swing.*;

import java.awt.*;

import static com.giant.constant.GameConstant.*;
import static com.giant.constant.GameConstant.GAME_FONT;

public class HelpPanel extends JPanel {
    private GameFrame gameFrame;
    private static final String HELP_TITLE = "游戏帮助";


    public HelpPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }

    private void init() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocation(0, 0);
    }

    @Override
    public void paint(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        String[] helps = GameInformation.getInstance().getHelps();
        GameFrame.drawInfo(HELP_TITLE, helps, g);
    }


}
