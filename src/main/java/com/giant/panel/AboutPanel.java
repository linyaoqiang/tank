package com.giant.panel;

import com.giant.constant.ImageConstant;
import com.giant.frame.GameFrame;
import com.giant.model.GameInformation;
import com.giant.model.LevelInformation;

import javax.swing.*;

import java.awt.*;

import static com.giant.constant.GameConstant.*;

public class AboutPanel extends JPanel {
    private GameFrame gameFrame;
    private static final String ABOUT_TITLE = "游戏关于";


    public AboutPanel(GameFrame gameFrame) {
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
        String[] abouts = GameInformation.getInstance().getAbouts();
        GameFrame.drawInfo(ABOUT_TITLE, abouts, g);
    }
}
