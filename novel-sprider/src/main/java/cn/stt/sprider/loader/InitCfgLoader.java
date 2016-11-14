package cn.stt.sprider.loader;

import cn.stt.sprider.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Administrator on 2016-11-11.
 */
public class InitCfgLoader {

    private static Logger logger = LoggerFactory.getLogger(InitCfgLoader.class);

    public static void load() {
        loadLogback();
        /*loadCollectConfig();
        loadCategories();
        loadSiteConfig();
        loadDuoYinZi();*/
    }

    public static void loadLogback(){
        File file = FileUtil.locateAbsolutePathFromClasspath("logback.xml");
    }
}
