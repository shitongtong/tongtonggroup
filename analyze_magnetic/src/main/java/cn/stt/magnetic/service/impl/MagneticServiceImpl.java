package cn.stt.magnetic.service.impl;

import cn.stt.magnetic.dao.MagneticDao;
import cn.stt.magnetic.po.Magnetic;
import cn.stt.magnetic.service.MagneticService;
import cn.stt.pager.mybatis.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-11-08.
 */
@Service
public class MagneticServiceImpl implements MagneticService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MagneticDao magneticMapper;

    @Override
    public Page<Magnetic> selectByParams(Map<String, Object> paramMap, Page page) {
        List<Magnetic> magneticList = magneticMapper.selectByParams(paramMap, page);
        page.setList(magneticList);
        return page;
    }

    @Override
    public List<Magnetic> selectListByParams(Map<String, Object> paramMap) {
        return magneticMapper.selectListByParams(paramMap);
    }

    @Override
    public int updateStatus(String hash, String sha1, int status) {
        return magneticMapper.updateStatus(hash, sha1, status);
    }
}
