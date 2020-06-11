package com.giant.model;

import com.giant.constant.TankConstant;
import com.giant.helper.BlockPool;
import com.giant.helper.EnumTankPool;
import com.giant.helper.GameHelper;
import com.giant.model.block.Block;
import com.giant.model.block.TankHomeBlock;
import com.giant.model.map.LevelGameMap;
import com.giant.model.tank.EnemyTank;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 加载关卡信息的核心单例类
 */
public class LevelInformation {
    private int levelCount;
    private int currentLevel;
    private static LevelInformation levelInformation;
    private static final String REGEX = "\\{\\d*\\,\\d*\\,\\d\\}";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * 私有构造
     */
    private LevelInformation() {

    }

    /**
     * 获取唯一的实例
     * @return
     */
    public static LevelInformation getInstance() {
        if (levelInformation == null) {
            levelInformation = new LevelInformation();
            levelInformation.levelCount = GameInformation.getInstance().getLevelCount();
        }
        return levelInformation;
    }

    /**
     * 加载关卡信息的核心方法
     * @param level
     * @param levelGameMap
     * @param enemyTanks
     */
    public void level(int level, LevelGameMap levelGameMap, List<EnemyTank> enemyTanks) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("level/lv_" + level + ".lv"))) {
            Properties properties = new Properties();
            properties.load(in);
            loadLevelGameMap(levelGameMap, properties);
            loadEnemyTanks(enemyTanks, properties);
            levelGameMap.wallBlock2SmallBlock();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载敌人坦克
     * @param enemyTanks
     * @param properties
     */
    private void loadEnemyTanks(List<EnemyTank> enemyTanks, Properties properties) {
        int commonCount = Integer.parseInt(properties.getProperty("commonCount"));
        int eliteCount = Integer.parseInt(properties.getProperty("eliteCount"));
        int epicCount = Integer.parseInt(properties.getProperty("epicCount"));
        while (true) {
            int n = GameHelper.getRandomInt(0, 2);
            switch (n) {
                case 0:
                    if (commonCount > 0) {
                        commonCount--;
                        EnemyTank enemyTank = EnumTankPool.getCommon();
                        enemyTanks.add(enemyTank);
                    }
                    break;
                case 1:
                    if (eliteCount > 0) {
                        eliteCount--;
                        EnemyTank enemyTank = EnumTankPool.getElite();
                        enemyTanks.add(enemyTank);
                    }
                    break;
                case 2:
                    if (epicCount > 0) {
                        epicCount--;
                        EnemyTank enemyTank = EnumTankPool.getEpic();
                        enemyTanks.add(enemyTank);
                    }
                    break;

            }
            if (commonCount <= 0 && eliteCount <= 0 && epicCount <= 0) {
                break;
            }
        }
    }

    /**
     * 加载地图
     * @param levelGameMap
     * @param properties
     */
    private void loadLevelGameMap(LevelGameMap levelGameMap, Properties properties) {
        List<String[]> blockInfoList = getBlockInfos(properties);
        levelGameMap.setMapBlocks(getBlocks(blockInfoList));
        levelGameMap.setTankHome(getTankHome(properties));
        levelGameMap.getMapBlocks().add(levelGameMap.getTankHome().getTankHomeBlock());
    }

    /**
     * 加载地图元素块
     * @param blockInfoList
     * @return
     */
    private List<Block> getBlocks(List<String[]> blockInfoList) {
        List<Block> blocks = new ArrayList<>();
        for (String[] strings : blockInfoList) {
            blocks.add(getBlock(strings));
        }
        return blocks;
    }

    /**
     * 加载地图元素块信息
     * @param properties
     * @return
     */
    private List<String[]> getBlockInfos(Properties properties) {
        List<String[]> array = new ArrayList<>();
        String mapInfo = properties.getProperty("map");
        Matcher matcher = PATTERN.matcher(mapInfo);
        while (matcher.find()) {
            String group = matcher.group();
            group = group.replace("{", "").replace("}", "");
            array.add(group.split(","));
        }
        return array;
    }

    /**
     * 加载玩家大本营
     * @param properties
     * @return
     */
    private TankHome getTankHome(Properties properties) {
        TankHome tankHome = new TankHome();
        String blockInfo = properties.getProperty("homeBlock");
        blockInfo = blockInfo.replace("{", "").replace("}", "");
        String[] blockInfos = blockInfo.split(",");
        tankHome.setTankHomeBlock((TankHomeBlock) getBlock(blockInfos));
        tankHome.getTankHomeBlock().setVisible(true);
        return tankHome;
    }


    /**
     * 获取元素块
     * @param blockInfos
     * @return
     */
    public Block getBlock(String[] blockInfos) {
        int x = Integer.parseInt(blockInfos[0]);
        int y = Integer.parseInt(blockInfos[1]);
        int type = Integer.parseInt(blockInfos[2]);
        Block block = BlockPool.getBlock(type);
        block.setX(x);
        block.setY(y);
        return block;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
