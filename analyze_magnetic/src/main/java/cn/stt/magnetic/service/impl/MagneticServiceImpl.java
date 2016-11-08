package cn.stt.magnetic.service.impl;

import cn.stt.magnetic.dao.MagneticMapper;
import cn.stt.magnetic.po.Magnetic;
import cn.stt.magnetic.service.MagneticService;
import cn.stt.pager.mybatis.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-11-08.
 */
@Service
public class MagneticServiceImpl implements MagneticService {

    @Autowired
    MagneticMapper magneticMapper;

    @Override
    public Page<Magnetic> selectByParams(Map<String, Object> paramMap, Page page) {
        List<Magnetic> magneticList = magneticMapper.selectByParams(paramMap, page);
        page.setList(magneticList);
        return page;
    }
}
