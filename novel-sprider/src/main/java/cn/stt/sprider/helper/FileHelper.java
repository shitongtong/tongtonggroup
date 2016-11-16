//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.helper;

import cn.stt.sprider.constants.GlobalConfig;
import cn.stt.sprider.entity.ChapterEntity;
import cn.stt.sprider.entity.NovelEntity;
import cn.stt.sprider.utils.FileUtil;
import cn.stt.sprider.utils.StringUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public FileHelper() {
    }

    public static void writeTxtFile(NovelEntity novel, ChapterEntity chapter, String content) throws IOException {
        String localPath = getTxtFilePath(chapter);
        String dir = localPath.substring(0, localPath.lastIndexOf("/"));

        try {
            if(!(new File(dir)).exists()) {
                (new File(dir)).mkdirs();
            }

            FileUtil.writeFile(new File(localPath), content, GlobalConfig.localSite.getCharset());
        } catch (IOException var6) {
            throw new IOException(var6);
        }
    }

    public static void writeLastTxtFile(String localPath, String content) throws IOException {
        String dir = localPath.substring(0, localPath.lastIndexOf("/"));

        try {
            if(!(new File(dir)).exists()) {
                (new File(dir)).mkdirs();
            }

            FileUtil.writeFile(new File(localPath), content, GlobalConfig.localSite.getCharset());
        } catch (IOException var4) {
            throw new IOException(var4);
        }
    }

    public static void downImage(String remotePath, NovelEntity novel, String suffix) {
        String localPath = getCoverDir(novel);
        if(!(new File(localPath)).exists()) {
            (new File(localPath)).mkdirs();
        }

        localPath = localPath + novel.getNovelNo() + "s" + suffix;
        if(!(new File(localPath)).exists()) {
            FileUtil.download(remotePath, localPath);
        }

    }

    public static List<String[]> readRunArgs(String fileName) {
        URL url = FileUtil.locateFromClasspath(fileName);
        File file = FileUtil.fileFromURL(url);
        ArrayList list = new ArrayList();

        try {
            InputStreamReader e = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(e);
            String lineTxt = null;

            while((lineTxt = bufferedReader.readLine()) != null) {
                if(StringUtil.isNotBlank(lineTxt) && !lineTxt.trim().startsWith("#")) {
                    list.add(lineTxt.trim().split("\\s"));
                }
            }

            e.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return list;
    }

    public static String getHtmlFilePath(NovelEntity novel, ChapterEntity chapter) {
        String chapterNo = chapter == null?"index":chapter.getChapterNo().toString();
        return GlobalConfig.localSite.getHtmlFile().replace("#subDir#", String.valueOf(novel.getNovelNo().intValue() / 1000)).replace("#articleNo#", String.valueOf(novel.getNovelNo())).replace("#chapterNo#", chapterNo).replace("#pinyin#", StringUtil.isBlank(novel.getPinyin())?"":novel.getPinyin());
    }

    public static String getTxtFilePath(ChapterEntity chapter) {
        return GlobalConfig.localSite.getTxtFile().replace("#subDir#", String.valueOf(chapter.getNovelNo().intValue() / 1000)).replace("#articleNo#", String.valueOf(chapter.getNovelNo())).replace("#chapterNo#", String.valueOf(chapter.getChapterNo()));
    }

    public static String getLastTxtFilePath(NovelEntity novel) {
        return GlobalConfig.localSite.getTxtFile().replace("#subDir#", String.valueOf(novel.getNovelNo().intValue() / 1000)).replace("#articleNo#", String.valueOf(novel.getNovelNo())).replace("#chapterNo#", "last");
    }

    public static String getCoverDir(NovelEntity novel) {
        String file = GlobalConfig.localSite.getCoverDir();
        if(!file.endsWith("/")) {
            file = file + "/";
        }

        return file.replace("#subDir#", String.valueOf(novel.getNovelNo().intValue() / 1000)).replace("#articleNo#", String.valueOf(novel.getNovelNo()));
    }
}
