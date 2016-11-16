//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.factory.impl;


import cn.stt.sprider.enums.ProgramEnum;
import cn.stt.sprider.factory.IServiceFactory;
import cn.stt.sprider.service.IChapterService;
import cn.stt.sprider.service.IHtmlBuilder;
import cn.stt.sprider.service.INovelService;
import cn.stt.sprider.service.jieqi.ChapterServiceImpl;
import cn.stt.sprider.service.jieqi.HtmlBuilderImpl;
import cn.stt.sprider.service.jieqi.NovelServiceImpl;

public class ServiceFactory implements IServiceFactory {
    public ServiceFactory() {
    }

    public INovelService createNovelService(String key) {
        Object novelService = null;
        if (key.equalsIgnoreCase(ProgramEnum.JIEQI.getName())) {
            novelService = new NovelServiceImpl();
        } else if (key.equalsIgnoreCase(ProgramEnum.YIDU.getName())) {
            novelService = new cn.stt.sprider.service.yidu.NovelServiceImpl();
        } else if (key.equalsIgnoreCase(ProgramEnum.MINIMALIST.getName())) {
            novelService = new cn.stt.sprider.service.minimalist.NovelServiceImpl();
        }

        return (INovelService) novelService;
    }

    public IChapterService createChapterService(String key) {
        Object chapterService = null;
        if (key.equalsIgnoreCase(ProgramEnum.JIEQI.getName())) {
            chapterService = new ChapterServiceImpl();
        } else {
            chapterService = new cn.stt.sprider.service.yidu.ChapterServiceImpl();
        }

        return (IChapterService) chapterService;
    }

    public IHtmlBuilder createHtmlBuilder(String key) {
        HtmlBuilderImpl htmlBuilder = null;
        if (key.equalsIgnoreCase(ProgramEnum.JIEQI.getName())) {
            htmlBuilder = new HtmlBuilderImpl();
        }

        return htmlBuilder;
    }
}
