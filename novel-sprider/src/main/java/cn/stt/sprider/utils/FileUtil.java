package cn.stt.sprider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by Administrator on 2016-11-11.
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static URL locateFromClasspath(String resourceName) {
        URL url = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null){
            url = classLoader.getResource(resourceName);

        }

        return url;
    }
}
