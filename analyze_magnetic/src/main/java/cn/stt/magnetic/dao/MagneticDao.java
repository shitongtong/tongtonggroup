package cn.stt.magnetic.dao;

import cn.stt.magnetic.po.Magnetic;
import cn.stt.pager.mybatis.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MagneticDao {
    int deleteByPrimaryKey(String hash);

    int insert(Magnetic record);

    int insertSelective(Magnetic record);

    Magnetic selectByPrimaryKey(String hash);

    int updateByPrimaryKeySelective(Magnetic record);

    int updateByPrimaryKey(Magnetic record);

    /**
     * 条件查询
     *
     * @param paramMap
     * @param page
     * @return
     */
    List<Magnetic> selectByParams(@Param(value = "paramMap") Map<String, Object> paramMap, Page page);

    List<Magnetic> selectListByParams(@Param(value = "paramMap")Map<String, Object> paramMap);

    int updateStatus(@Param(value = "hash") String hash, @Param(value = "sha1") String sha1,@Param(value = "status") int status);
}