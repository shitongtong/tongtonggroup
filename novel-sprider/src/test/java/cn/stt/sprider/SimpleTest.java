package cn.stt.sprider;

import cn.stt.sprider.db.DBPool;
import cn.stt.sprider.db.YiQueryRunner;
import cn.stt.sprider.entity.NovelEntity;
import cn.stt.sprider.utils.StringUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016-11-15.
 */
public class SimpleTest {
    private static final String JDBC_FILE = "jdbc.properties";

    /*static {
        System.out.println(JDBC_FILE);
    }*/

    public static void main(String[] args) {

    }

    @Test
    public void testConn() throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "INSERT INTO t_article(articlename, pinyin, initial ,keywords ,authorid ,author ,category ,subcategory, intro ,fullflag ,postdate,dayvisit, weekvisit, monthvisit,  allvisit, dayvote, weekvote, monthvote, allvote ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Integer isFull = null;
        NovelEntity novel = new NovelEntity();
        if (novel.getFullFlag() != null && novel.getFullFlag()){
            isFull = 1;
        }else{
            isFull = 0;
        }
        Object[] params = new Object[]{novel.getNovelName(), novel.getPinyin(), novel.getInitial(), StringUtil.trimToEmpty(novel.getKeywords()), Integer.valueOf(0), novel.getAuthor(), novel.getTopCategory(), novel.getSubCategory(), novel.getIntro(), isFull, new Timestamp(System.currentTimeMillis()), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        Number b = (Number)queryRunner.save(conn, sql, params);

    }
}
