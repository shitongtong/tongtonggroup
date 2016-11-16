//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.stt.sprider.service;

import cn.stt.sprider.db.DBPool;
import cn.stt.sprider.db.YiQueryRunner;
import cn.stt.sprider.model.User;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseService {
    protected static final String EMPTY = "";
    protected static User admin;

    public BaseService() {
    }

    public User getAdmin() throws SQLException {
        if(admin == null) {
            admin = this.loadAdmin();
        }

        return admin;
    }

    protected Integer getJieQiTimeStamp() {
        return Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000L));
    }

    protected abstract User loadAdmin() throws SQLException;

    public int update(String sql, Object[] params) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        return queryRunner.update(conn, sql, params);
    }

    public Object query(String sql, Object[] params) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        return queryRunner.query(conn, sql, new ScalarHandler(), params);
    }
}
