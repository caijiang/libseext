package me.jiangcai.lib.seext;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
public class FileUtilsTest {
    @Test
    public void fileExtensionName() throws Exception {
        assertThat(FileUtils.fileExtensionName("http://www.baidu.com/robot.txt?v=1"))
                .isEqualTo("txt");

        assertThat(FileUtils.fileExtensionName("/www.baidu.com/robot.txt?v=1"))
                .isEqualTo("txt");

        assertThat(FileUtils.fileExtensionName("robot.txt"))
                .isEqualTo("txt");
    }

}