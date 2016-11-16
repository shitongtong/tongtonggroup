//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.constants;

import cn.stt.sprider.enums.UserAgentEnum;
import cn.stt.sprider.model.Category;
import cn.stt.sprider.model.DuoYinZi;
import cn.stt.sprider.model.Site;
import cn.stt.sprider.model.User;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfig {
    public static PropertiesConfiguration site;
    public static PropertiesConfiguration collect;
    public static PropertiesConfiguration config;
    public static List<DuoYinZi> duoyin = new ArrayList();
    public static UserAgentEnum USER_AGENT;
    public static User ADMIN;
    public static Site localSite;
    public static List<Category> TOP_CATEGORY;
    public static List<Category> SUB_CATEGORY;

    static {
        USER_AGENT = UserAgentEnum.DEFAULT;
        ADMIN = null;
        localSite = new Site();
        TOP_CATEGORY = new ArrayList();
        SUB_CATEGORY = new ArrayList();
    }

    public GlobalConfig() {
    }
}
