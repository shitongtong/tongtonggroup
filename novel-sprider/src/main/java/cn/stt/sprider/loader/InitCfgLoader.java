package cn.stt.sprider.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016-11-11.
 */
public class InitCfgLoader {

    private static Logger logger = LoggerFactory.getLogger(InitCfgLoader.class);

    public static void load() throws Exception {
        loadLogback();
        loadCollectConfig();
        loadCategories();
        loadSiteConfig();
        loadDuoYinZi();
    }

    public static void loadLogback(){

    }
}
