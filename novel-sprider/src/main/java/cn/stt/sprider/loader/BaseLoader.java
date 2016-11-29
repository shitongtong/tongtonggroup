//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.loader;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.enums.UserAgentEnum;

public class BaseLoader {
    private static Logger logger = LoggerFactory.getLogger(BaseLoader.class);

    public BaseLoader() {
    }

    public static void loadInitParam() {
        logger.debug("开始加载采集参数...");
        String userAgent = GlobalConfig.collect.getString("userAgent");
        GlobalConfig.USER_AGENT = UserAgentEnum.parseEnum(userAgent);
        logger.debug("userAgent=={}",userAgent);

    }

    public void loadCategory() throws ConfigurationException {
        logger.debug("开始加载分类目录...");
    }
}
