package com.softpos.main.program;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.MSG;

public class CompanyConfig {

    public CompanyConfig() {
    }

    public boolean includeVat() {
        String vatType = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select P_VatType from posconfigsetup";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                vatType = rs.getString(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return vatType.equals("I");
    }

    public boolean excludeVat() {
        String vatType = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select P_VatType from posconfigsetup";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                vatType = rs.getString(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }

        return vatType.equals("E");
    }
}
