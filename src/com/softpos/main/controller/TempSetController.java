package com.softpos.main.controller;

import com.softpos.main.model.TempSetBean;
import com.softpos.database.util.MySQLConnect;
import java.sql.SQLException;
import java.sql.Statement;
import sun.natee.project.util.ThaiUtil;

public class TempSetController {

    public static void save(TempSetBean bean, MySQLConnect mysql) throws SQLException {
        String sql = "INSERT INTO tempset "
                + "(PTableNo, PIndex, PCode, PDesc, PPostStock, PProTry, POption, PTime) "
                + "VALUES ('" + bean.getPTableNo() + "', '" + bean.getPIndex() + "', '" + bean.getPCode() + "', "
                + "'" + ThaiUtil.Unicode2ASCII(bean.getPDesc()) + "', '" + bean.getPPostStock() + "',"
                + "'" + bean.getPProTry() + "', '" + bean.getPOption() + "', " + bean.getPTime() + ")";
        try (Statement stmt = mysql.getConnection().createStatement()) {
            stmt.execute(sql);
        }
    }
}
