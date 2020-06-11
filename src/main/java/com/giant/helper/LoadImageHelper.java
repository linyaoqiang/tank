package com.giant.helper;

import java.awt.*;

/**
 * 用于加载图片的工具
 */
public class LoadImageHelper {
    public static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();

    public static Image getImage(String imageLocation) {
        return TOOLKIT.getImage(imageLocation);
    }
}
