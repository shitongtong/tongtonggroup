package cn.stt.magnetic.dao;

import cn.stt.magnetic.po.VideoInfo;

public interface VideoInfoMapper {
    int deleteByPrimaryKey(String hash);

    int insert(VideoInfo record);

    int insertSelective(VideoInfo record);

    VideoInfo selectByPrimaryKey(String hash);

    int updateByPrimaryKeySelective(VideoInfo record);

    int updateByPrimaryKey(VideoInfo record);
}