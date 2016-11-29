package cn.stt.sprider.loader;

import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.enums.ProgramEnum;
import cn.stt.sprider.model.Category;
import cn.stt.sprider.model.DuoYinZi;
import cn.stt.sprider.model.Template;
import cn.stt.sprider.utils.FileUtil;
import cn.stt.sprider.utils.PropertiesUtil;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016-11-11.
 */
public class InitCfgLoader {

    private static Logger logger = LoggerFactory.getLogger(InitCfgLoader.class);

    public static void load() throws Exception {
//        loadLogback();
        loadCollectConfig();
        loadCategories();
        loadSiteConfig();
        loadDuoYinZi();
    }

    private static void loadDuoYinZi() throws ConfigurationException {
        List list = FileUtil.readFile2List("duoyinzi", "utf-8");
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            String ss = (String)var2.next();
            GlobalConfig.duoyin.add(new DuoYinZi(ss.split("=")[0], ss.split("=")[1]));
        }

    }

    private static void loadSiteConfig() throws ConfigurationException {
        try {
            GlobalConfig.site = PropertiesUtil.load("site.ini", "utf-8");
        } catch (ConfigurationException var1) {
            throw new ConfigurationException("读取配置文件site.ini出错，" + var1.getMessage());
        }

        //本站域名, 需要生成静态页时此项必填
        GlobalConfig.localSite.setSiteUrl(GlobalConfig.site.getString("local_site_url"));
        //本站名称， 需要生成静态页时此项必填
        GlobalConfig.localSite.setSiteName(GlobalConfig.site.getString("local_site_name"));
        GlobalConfig.localSite.setProgram(ProgramEnum.parseEnum(GlobalConfig.site.getString("local_program")));
        GlobalConfig.localSite.setBasePath(GlobalConfig.site.getString("base_path"));
        //本站txt文件编码
        GlobalConfig.localSite.setCharset(GlobalConfig.site.getString("charset"));
        GlobalConfig.localSite.setTxtFile(GlobalConfig.site.getString("txt_file"));
        GlobalConfig.localSite.setHtmlFile(GlobalConfig.site.getString("html_file"));
        GlobalConfig.localSite.setCoverDir(GlobalConfig.site.getString("cover_dir"));
        GlobalConfig.localSite.setUsePinyin(GlobalConfig.site.getInteger("use_pinyin", Integer.valueOf(0)));
        Template tp = new Template();
        tp.setIndex(GlobalConfig.site.getString("template_index"));
        tp.setList(GlobalConfig.site.getString("template_list"));
        tp.setTop(GlobalConfig.site.getString("template_top"));
        tp.setInfo(GlobalConfig.site.getString("template_info"));
        tp.setInfoURL(GlobalConfig.site.getString("url_info"));
        tp.setChapter(GlobalConfig.site.getString("template_chapter"));
        tp.setRowSize(Integer.valueOf(GlobalConfig.site.getInt("chapter_row_size", 4)));
        tp.setChapterURL(GlobalConfig.site.getString("url_chapter"));
        tp.setReader(GlobalConfig.site.getString("template_reader"));
        tp.setReaderURL(GlobalConfig.site.getString("url_reader"));
        GlobalConfig.localSite.setTemplate(tp);
    }

    private static void loadCategories() throws Exception {
        BufferedReader reader = null;

        try {
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileUtil.locateAbsolutePathFromClasspath("category.ini")), "UTF-8"));

            //返回读取指定资源的输入流
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = loader.getResourceAsStream("category.ini");
            reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            String e = null;
            boolean grade = true;

            while ((e = reader.readLine()) != null) {
                if ("[small]".equalsIgnoreCase(e)) {
                    grade = false;
                } else {
                    grade = true;
                }

                String[] s = e.split("\\|");
                if (s.length == 3) {
                    Category c = new Category();
                    c.setId(s[0]);
                    c.setName(s[1]);
                    c.setWords(Arrays.asList(s[2].split(",")));
                    if (!grade) {
                        GlobalConfig.SUB_CATEGORY.add(c);
                    } else {
                        GlobalConfig.TOP_CATEGORY.add(c);
                    }
                }
            }
//            reader.close();
        } catch (Exception var12) {
            throw new Exception("加载分类异常！");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    throw new IOException("IO异常！");
                }
            }
        }
    }

    private static void loadCollectConfig() throws ConfigurationException {
        try {
            GlobalConfig.collect = PropertiesUtil.load("collect.ini", "utf-8");
        } catch (ConfigurationException var1) {
            throw new ConfigurationException("读取配置文件出错，" + var1.getMessage());
        }
    }

    /**
     * 加载日志文件（貌似不需要）
     */
    public static void loadLogback() {
        File file = FileUtil.locateAbsolutePathFromClasspath("logback.xml");
        String absolutePath = file.getAbsolutePath();
//        LogUtils.load(absolutePath);
    }
}
