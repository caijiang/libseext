package me.jiangcai.lib.seext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片工具类
 *
 * @author CJ
 * @since 1.4
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
        return scaleTo(originalImage, (double) height / (double) originalImage.getHeight());
    }

    /**
     * 缩放原图至指定宽度
     *
     * @param originalImage 原图
     * @param width         指定宽度
     * @return 新图
     */
    public static BufferedImage scaleToFixedWidth(BufferedImage originalImage, int width) {
        return scaleTo(originalImage, (double) width / (double) originalImage.getWidth());
    }

    /**
     * @param originalImage 原图
     * @param imageType     指定图片类型
     * @param maxSize       长度
     * @return 处理原图，确保生成的最终数据不会超过maxSize
     */
    public static InputStream scaleToLimitSize(BufferedImage originalImage, String imageType, int maxSize) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        BufferedImage current = originalImage;
        while (true) {
            buffer.reset();
            ImageIO.write(current, imageType, buffer);
            buffer.flush();
            if (buffer.size() < maxSize)
                return new ByteArrayInputStream(buffer.toByteArray());
            current = scaleTo(current, 0.8);
        }
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
