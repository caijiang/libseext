package me.jiangcai.lib.seext;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author CJ
 * @since 1.3.7
 */
public class FileUtils {

    /**
     * @return 这个path相关的文件资源的扩展名
     */
    public static String fileExtensionName(Object path) {
        try {
            return fileExtensionName(new URI(path.toString()).getPath());
        } catch (URISyntaxException e) {
            try {
                return fileExtensionName(new URL(path.toString()).getPath());
            } catch (MalformedURLException e1) {
                return fileExtensionName(path.toString());
            }
        }
    }

    private static String fileExtensionName(String path) {
        int index = path.lastIndexOf(".");
        return path.substring(index + 1, path.length());
    }
}
