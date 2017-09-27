package org.luffy.libs.libseext;

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
        return me.jiangcai.lib.seext.ImageUtils.scaleToFixedHeight(originalImage, height);
    }

    /**
     * 缩放原图至指定宽度
     *
     * @param originalImage 原图
     * @param width         指定宽度
     * @return 新图
     */
    public static BufferedImage scaleToFixedWidth(BufferedImage originalImage, int width) {
        return me.jiangcai.lib.seext.ImageUtils.scaleToFixedWidth(originalImage, width);
    }

    /**
     * 缩放原图指定比例
     *
     * @param originalImage 原图
     * @param times         比例
     * @return 新图 新图的高度为原图高度*times
     */
    public static BufferedImage scaleTo(BufferedImage originalImage, double times) {
        return me.jiangcai.lib.seext.ImageUtils.scaleTo(originalImage, times);
    }

}
