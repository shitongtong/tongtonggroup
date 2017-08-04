package cn.stt.admin.back.dao;

import cn.stt.admin.back.po.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/31.
 */
@Transactional
public interface AppInfoDao extends JpaRepository<AppInfo,Long> {

    @Override
    List<AppInfo> findAll();

    @Override
    AppInfo getOne(Long i);

    //利用原生的SQL进行修改操作
    @Query(value = "update app_info set url=?1,version=?2,device_type=?3 where id=?4 ", nativeQuery = true)
    @Modifying
    int update(String url,String version,String deviceType,Long id);

    AppInfo findByAppInfoId(String appInfoId);
}
