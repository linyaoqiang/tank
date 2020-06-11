package com.giant.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 存储游戏信息
 */
public class GameInformation {
    private String[] abouts;
    private String[] helps;
    private int levelCount;
    private static GameInformation instance;

    private GameInformation() {

    }

    /**
     * 获取唯一的实例
     *  加载关卡数，加载关于信息，加载帮助信息
     * @return
     */
    public static GameInformation getInstance() {

        if (instance == null) {
            try(BufferedInputStream in = new BufferedInputStream(new FileInputStream("info/gameInfo.txt"))) {
                instance = new GameInformation();
                Properties properties = new Properties();
                properties.load(in);
                instance.levelCount = Integer.parseInt(properties.getProperty("levelCount"));
                String info=properties.getProperty("about");
                info = new String(info.getBytes("ISO-8859-1"), "utf-8");
                instance.abouts = info.split(",");
                info = properties.getProperty("help");
                info = new String(info.getBytes("ISO-8859-1"), "utf-8");
                instance.helps =info.split(",");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String[] getAbouts() {
        return abouts;
    }

    public void setAbouts(String[] abouts) {
        this.abouts = abouts;
    }

    public String[] getHelps() {
        return helps;
    }

    public void setHelps(String[] helps) {
        this.helps = helps;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }
}
