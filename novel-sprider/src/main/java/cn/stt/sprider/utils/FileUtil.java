package cn.stt.sprider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Administrator on 2016-11-11.
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static File locateAbsolutePathFromClasspath(String resourceName){
        File file = null;
        URL url = locateFromClasspath(resourceName);
        logger.debug("url.getPath()={}",url.getPath());
        file = fileFromURL(url);
        return file;
    }

    public static File fileFromURL(URL url){
        try {
            File file = new File(url.toURI());
            logger.debug("filename={}",file.getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File file = null;
        if(url != null){
            String fileName = url.getFile();
            logger.debug("fileName={}",fileName);   //fileName=/D:/project_git/tongtonggroup/novel-sprider/target/classes/logback.xml
            fileName = fileName.replace('/',File.separatorChar);
            logger.debug("分割符转换后：fileName={}",fileName);   //fileName=\D:\project_git\tongtonggroup\novel-sprider\target\classes\logback.xml

            /*
            TODO  不知下面代码用处
            int pos = 0;
            while((pos = fileName.indexOf(37, pos)) >= 0) {
                if(pos + 2 < fileName.length()) {
                    String hexStr = fileName.substring(pos + 1, pos + 3);
                    char ch = (char)Integer.parseInt(hexStr, 16);
                    fileName = fileName.substring(0, pos) + ch + fileName.substring(pos + 3);
                }
            }
            logger.debug("fileName={}",fileName);fileName不变*/

            file = new File(fileName);
        }

        return file;
    }

    /**
     * 查找具有给定名称的资源。
     * @param resourceName
     * @return
     */
    public static URL locateFromClasspath(String resourceName) {
        URL url = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null){
            //查找具有给定名称的资源。
            url = classLoader.getResource(resourceName);
            if (url != null){
                logger.debug("Locate file from the context classpath ({})",resourceName);
            }
        }

        if (url == null){
            //从用来加载类的搜索路径中查找具有指定名称的资源。
            url = ClassLoader.getSystemResource(resourceName);
            if (url != null){
                logger.debug("Locate file from the system classpath ({})",resourceName);
            }
        }

        return url;
    }
}
