package cn.stt.magnetic.service;

import cn.stt.magnetic.po.Magnetic;
import cn.stt.pager.mybatis.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Administrator on 2016-11-08.
 */
public interface MagneticService {

    /**
     * 条件查询
     *
     * @param paramMap
     * @param page
     * @return
     */
    Page<Magnetic> selectByParams(Map<String, Object> paramMap, Page page);

}
