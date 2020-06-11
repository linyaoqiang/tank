package com.giant.constant;

import com.giant.panel.GamePanel;

import java.awt.*;
import java.io.File;

import static com.giant.helper.LoadImageHelper.*;

public interface TankConstant {
    /**
     * 坦克的默认信息常量
     */
    int DEFAULT_HP = 500;
    int DEFAULT_ATK = 100;
    int DEFAULT_RADIUS = 16;
    int DEFAULT_SPEED = 5;
    int IMAGE_TANK_RADIUS = 30;
    int FIRE_TIME = 200;

    Font TANK_NAME_FONT = new Font("微软雅黑", Font.ITALIC, 12);
    /**
     * 坦克状态
     */
    int STAND = 0;
    int MOVING = 1;
    int DIE = 3;

    /**
     * 定义方向
     */
    int DIR_UP = 0;
    int DIR_DOWN = 1;
    int DIR_LEFT = 2;
    int DIR_RIGHT = 3;


    /**
     * 游戏玩家编号
     * 1p
     * 2p
     */
    int PLAYER_1 = 0;
    int PLAYER_2 = 1;
    int PLAYER_1_BORN_X = 400;
    int PLAYER_2_BORN_X = 800;
    int PLAYER_BORN_Y = GameConstant.GAME_HEIGHT - IMAGE_TANK_RADIUS - GamePanel.insetY;
    int PLAYER_BORN_DIR = DIR_UP;

    /**
     * 子弹常量
     */
    int DEFAULT_BULLET_SPEED = DEFAULT_SPEED << 1;
    int DEFAULT_BULLET_RADIUS = (IMAGE_TANK_RADIUS >> 2) - 2;
    int DEFAULT_TANK_VISIBLE_BULLET_SIZE = 5;

    int COMMON_TANK_ID = 0;
    int ELITE_TANK_ID = 1;
    int EPIC_TANK_ID = 2;


    /**
     * 敌人相关信息
     * 可以取的值范围是1-100
     */
    int ENEMY_MOVE_POINT = 40;
    int ENEMY_FIRE_POINT = 30;

    /**
     * 普通敌人的信息
     */
    int COMMON_ENEMY_TANK_HP = 200;
    int COMMON_ENEMY_TANK_ATK = 50;
    int COMMON_ENEMY_TANK_SPEED = 3;

    /**
     * 精英敌人的信息
     */
    int ELITE_ENEMY_TANK_HP = 600;
    int ELITE_ENEMY_TANK_ATK = 100;
    int ELITE_ENEMY_TANK_SPEED = 5;

    /**
     * 巨型坦克(史诗敌人)的信息
     */
    int EPIC_ENEMY_TANK_HP = 2000;
    int EPIC_ENEMY_TANK_ATK = 150;
    int EPIC_ENEMY_TANK_SPEED = 8;

}
