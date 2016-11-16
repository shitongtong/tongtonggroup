//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.service;

import cn.stt.sprider.entity.ChapterEntity;
import cn.stt.sprider.entity.NovelEntity;
import cn.stt.sprider.enums.CategoryGradeEnum;
import cn.stt.sprider.model.Category;
import cn.stt.sprider.model.PreNextChapter;

import java.util.List;

public interface IHtmlBuilder {
    void buildChapterListHtml(NovelEntity var1, List<ChapterEntity> var2);

    void buildChapterCntHtml(NovelEntity var1, ChapterEntity var2, String var3, PreNextChapter var4);

    String loadChapterContent(ChapterEntity var1);

    Category getCategoryById(String var1, CategoryGradeEnum var2);
}
