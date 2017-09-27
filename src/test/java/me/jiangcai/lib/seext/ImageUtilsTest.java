package me.jiangcai.lib.seext;

import org.assertj.core.data.Offset;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class ImageUtilsTest {

    @Test
    public void go() throws IOException {

        BufferedImage image = ImageIO.read(new ClassPathResource("/images/3264×2448.JPG").getInputStream());

        final BufferedImage width100Image = ImageUtils.scaleToFixedWidth(image, 100);
        assertThat(width100Image.getWidth())
                .isCloseTo(100, Offset.offset(1))
        ;
        // 按照比例
        assertThat(width100Image.getHeight())
                .isLessThan(100);

        //
        final BufferedImage height100Image = ImageUtils.scaleToFixedHeight(image, 100);
        assertThat(height100Image.getHeight())
                .isCloseTo(100, Offset.offset(1));
        assertThat(height100Image.getWidth())
                .isGreaterThan(100)
                .isLessThan(150);

        // 尺寸
        try (FileOutputStream outputStream = new FileOutputStream("target/test.png")) {
            StreamUtils.copy(ImageUtils.scaleToLimitSize(image, "png", 100 * 1024), outputStream);
        }


    }

}