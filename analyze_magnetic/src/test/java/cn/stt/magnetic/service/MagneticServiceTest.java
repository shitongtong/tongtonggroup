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

/**
 * Created by Administrator on 2016-11-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class MagneticServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MagneticService magneticService;

    @Test
    public void testSelectByParams() {
        Map<String, Object> paramMap = new HashMap<>();
        Page page = new Page(2, 100);
//        paramMap.put("hash","4EB478339E1C0787EF1A4DB7DF58E6FC3C5F1BEA/4");
//        paramMap.put("sha1", "9B26024A1539BAFCD2F378A6A245F8979F9E06A3");
        paramMap.put("status", 1);
        page = magneticService.selectByParams(paramMap, page);
//        page.initialize();
//        List list = page.getList();
        logger.info("totalCount:" + page.getCount());
    }

    @Test
    public void testSelectListByParams() {
        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("hash","4EB478339E1C0787EF1A4DB7DF58E6FC3C5F1BEA/4");
//        paramMap.put("sha1", "9B26024A1539BAFCD2F378A6A245F8979F9E06A3");
        paramMap.put("status", 0);
        paramMap.put("offset", 0);
        paramMap.put("limit", 10);
        List list = magneticService.selectListByParams(paramMap);
        logger.info("totalCount:" + list.size());
    }

}