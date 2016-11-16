//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.factory;


import cn.stt.sprider.service.IChapterService;
import cn.stt.sprider.service.IHtmlBuilder;
import cn.stt.sprider.service.INovelService;

public interface IServiceFactory {
    INovelService createNovelService(String var1);

    IChapterService createChapterService(String var1);

    IHtmlBuilder createHtmlBuilder(String var1);
}
