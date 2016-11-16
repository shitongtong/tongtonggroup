//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.service;


import cn.stt.sprider.entity.NovelEntity;

import java.sql.SQLException;
import java.util.Map;

public interface INovelService {
    int update(NovelEntity var1) throws SQLException;

    void repair(NovelEntity var1, NovelEntity var2) throws SQLException;

    boolean exist(String var1) throws SQLException;

    Number saveNovel(NovelEntity var1) throws SQLException;

    NovelEntity find(String var1) throws SQLException;

    Map<String, Object> loadSystemParam() throws SQLException;

    Number getMaxPinyin(String var1) throws SQLException;
}
