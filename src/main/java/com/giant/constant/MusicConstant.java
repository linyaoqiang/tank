package com.giant.constant;

import sun.audio.AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 音乐文件常量命名空间
 */
public interface MusicConstant {
    String BASE_LOCATION = "music/";
    File MUSIC_FIlE = new File(BASE_LOCATION);
    String MUSIC_PATH = MUSIC_FIlE.getPath() + File.separator;

    String MUSIC_START = MUSIC_PATH + "start.wav";
    String MUSIC_FIRE = MUSIC_PATH + "fire.wav";
    String MUSIC_HIT = MUSIC_PATH + "hit.wav";
    String MUSIC_ADD = MUSIC_PATH + "add.wav";
    String MUSIC_BLAST = MUSIC_PATH + "explode.wav";
    String MUSIC_WIN = MUSIC_PATH + "win.wav";
    String MUSIC_BG = MUSIC_PATH + "bg.wav";
    String MUSIC_GAME_OVER = MUSIC_PATH + "gameover.wav";


    /**
     * 播放器
     */
    AudioPlayer MUSIC_PLAYER = AudioPlayer.player;
}
