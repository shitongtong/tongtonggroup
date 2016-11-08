package cn.stt.magnetic.service;

import cn.stt.pager.mybatis.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016-11-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
//        ,"classpath:logback.xml"
//        ,"classpath:mybatils-config.xml"
})
public class MagneticServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MagneticService magneticService;

    @Test
    public void testSelectByParams(){
        Map<String,Object> paramMap = new HashMap<>();
        Page page = new Page(2,10);
        page = magneticService.selectByParams(paramMap,page);
//        List list = page.getList();
        logger.info("totalCount:"+page.getCount());
        System.out.println("totalCount=="+page.getCount());
    }

}