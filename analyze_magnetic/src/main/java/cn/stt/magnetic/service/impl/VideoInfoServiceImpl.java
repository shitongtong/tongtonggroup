package cn.stt.magnetic.service.impl;

import cn.stt.magnetic.dao.VideoInfoDao;
import cn.stt.magnetic.po.VideoInfo;
import cn.stt.magnetic.service.VideoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-11-09.
 */
@Service
public class VideoInfoServiceImpl implements VideoInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VideoInfoDao videoInfoMapper;

    @Override
    public int addVideoInfo(VideoInfo videoInfo) {
        if (videoInfo != null){
            videoInfo.setCreateTime(System.currentTimeMillis());
            return videoInfoMapper.insertSelective(videoInfo);
        }
        return 0;
    }
}
