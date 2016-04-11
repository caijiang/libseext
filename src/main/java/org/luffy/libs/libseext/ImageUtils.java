package org.luffy.libs.libseext;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图片工具类
 *
 * @author CJ
 * @since 1.2
 */
public class ImageUtils {

    /**
     * 缩放原图至指定高度
     *
     * @param originalImage 原图
     * @param height        指定高度
     * @return 新图
     */
    public static BufferedImage scaleToFixedHeight(BufferedImage originalImage, int height) {
        return scaleTo(originalImage, (double)height / (double)originalImage.getHeight());
    }

    /**
     * 缩放原图至指定宽度
     *
     * @param originalImage 原图
     * @param width         指定宽度
     * @return 新图
     */
    public static BufferedImage scaleToFixedWidth(BufferedImage originalImage, int width) {
        return scaleTo(originalImage, (double)width / (double)originalImage.getWidth());
    }

    /**
     * 缩放原图指定比例
     *
     * @param originalImage 原图
     * @param times         比例
     * @return 新图 新图的高度为原图高度*times
     */
    public static BufferedImage scaleTo(BufferedImage originalImage, double times) {
        int width = (int) (originalImage.getWidth() * times);
        int height = (int) (originalImage.getHeight() * times);
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

}
