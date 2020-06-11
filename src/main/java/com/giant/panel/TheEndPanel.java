package com.giant.panel;

import com.giant.constant.GameConstant;
import com.giant.constant.ImageConstant;
import com.giant.frame.GameFrame;

import javax.swing.*;

import java.awt.*;

import static com.giant.constant.GameConstant.*;

public class TheEndPanel extends JPanel {
    private GameFrame gameFrame;
    private int insetX;
    private int insetY;


    public TheEndPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }

    private void init() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocation(0, 0);
        this.insetX = GamePanel.insetX;
        this.insetY = GamePanel.insetY;
    }

    @Override
    public void paint(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(FONT_END);
        g.drawString(ESC_STRING, ESC_STRING_X, STRING_Y);
        g.drawString(ENTER_STRING, ENETER_STRING_X, STRING_Y);
        g.drawImage(ImageConstant.END, (GAME_WIDTH - insetX - ImageConstant.END_WIDTH) / 2, (GAME_HEIGHT - insetY - ImageConstant.END_HEIGHT) / 2, null);
    }
}
