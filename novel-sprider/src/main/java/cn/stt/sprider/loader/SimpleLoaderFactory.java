//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.loader;

import cn.stt.sprider.enums.ProgramEnum;
import cn.stt.sprider.exception.BaseException;
import cn.stt.sprider.loader.impl.JieQiConfigLoader;
import cn.stt.sprider.loader.impl.MinimalistConfigLoader;
import cn.stt.sprider.loader.impl.YiDuConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoaderFactory {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoaderFactory.class);

    public SimpleLoaderFactory() {
    }

    public static ILoader create(ProgramEnum e) {
        if(e == null) {
            throw new BaseException("获取本地站点使用的程序异常！");
        } else {
            Object loader = null;
            if(e == ProgramEnum.YIDU) {
                loader = new YiDuConfigLoader();
            } else if(e == ProgramEnum.JIEQI) {
                loader = new JieQiConfigLoader();
            }else if(e == ProgramEnum.MINIMALIST){
                loader = new MinimalistConfigLoader();
            }

            logger.debug("产生加载策略： " + e.getName());
            return (ILoader)loader;
        }
    }
}
