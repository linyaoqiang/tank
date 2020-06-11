package com.giant.constant;

import java.awt.*;
import java.io.File;


import static com.giant.helper.LoadImageHelper.*;

public interface ImageConstant {
    String BASE_LOCATION = "images/";
    File IMAGE_FIlE = new File(BASE_LOCATION);
    String IMAGE_PATH = IMAGE_FIlE.getPath() + File.separator;
    Image MENU_ARROW = getImage(IMAGE_PATH + "menu_arrow.gif");
    Image MENU_TANK = getImage(IMAGE_PATH + "menu_tank.png");
    Image TANK_HOME = getImage(IMAGE_PATH + "home.gif");
    Image END = getImage(IMAGE_PATH + "end.jpg");
    Image GAME_OVER = getImage(IMAGE_PATH + "gameover.png");

    int MENU_ARROW_MARGIN_RIGHT = 60;

    int END_WIDTH = 417;
    int END_HEIGHT = 406;


    int GAME_OVER_WIDTH = 479;
    int GAME_OVER_HEIGHT = 319;


    /**
     * 定义玩家坦克
     */
    Image[][] HERO_TANKS = new Image[][]{
            {
                    getImage(IMAGE_PATH + "p1tankU.gif"),
                    getImage(IMAGE_PATH + "p1tankD.gif"),
                    getImage(IMAGE_PATH + "p1tankL.gif"),
                    getImage(IMAGE_PATH + "p1tankR.gif")
            },
            {
                    getImage(IMAGE_PATH + "p2tankU.gif"),
                    getImage(IMAGE_PATH + "p2tankD.gif"),
                    getImage(IMAGE_PATH + "p2tankL.gif"),
                    getImage(IMAGE_PATH + "p2tankR.gif")
            }
    };

    /**
     * 定义敌人坦克
     */
    Image[][] ENEMY_TANK_IMAGES = {
            {
                    getImage(IMAGE_PATH + "enemy1U.gif"),
                    getImage(IMAGE_PATH + "enemy1D.gif"),
                    getImage(IMAGE_PATH + "enemy1L.gif"),
                    getImage(IMAGE_PATH + "enemy1R.gif")
            },
            {
                    getImage(IMAGE_PATH + "enemy2U.gif"),
                    getImage(IMAGE_PATH + "enemy2D.gif"),
                    getImage(IMAGE_PATH + "enemy2L.gif"),
                    getImage(IMAGE_PATH + "enemy2R.gif")
            },
            {
                    getImage(IMAGE_PATH + "enemy3U.gif"),
                    getImage(IMAGE_PATH + "enemy3D.gif"),
                    getImage(IMAGE_PATH + "enemy3L.gif"),
                    getImage(IMAGE_PATH + "enemy3R.gif")
            }
    };

    /**
     * 定义爆炸的图片
     */
    Image[] BLAST_IMAGES = {
            getImage(IMAGE_PATH + "blast1.gif"),
            getImage(IMAGE_PATH + "blast2.gif"),
            getImage(IMAGE_PATH + "blast3.gif"),
            getImage(IMAGE_PATH + "blast4.gif"),
            getImage(IMAGE_PATH + "blast5.gif"),
            getImage(IMAGE_PATH + "blast6.gif"),
            getImage(IMAGE_PATH + "blast7.gif"),
            getImage(IMAGE_PATH + "blast8.gif")
    };


    /**
     * 定义块的图片
     */
    Image[] BLOCK_IMAGES = {
            getImage(IMAGE_PATH + "walls.gif"),
            getImage(IMAGE_PATH + "steels.gif"),
            getImage(IMAGE_PATH + "water.gif"),
            getImage(IMAGE_PATH + "grass.gif"),
            getImage(IMAGE_PATH + "wall.gif"),
            getImage(IMAGE_PATH + "steel.gif"),
            TANK_HOME
    };


    /**
     * 定义出生的图片
     */
    Image[] BORN_IMAGES = {
            getImage(IMAGE_PATH + "born1.gif"),
            getImage(IMAGE_PATH + "born2.gif"),
            getImage(IMAGE_PATH + "born3.gif"),
            getImage(IMAGE_PATH + "born4.gif")
    };

}
