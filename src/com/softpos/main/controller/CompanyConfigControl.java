package com.softpos.main.controller;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.softpos.main.utils.MSG;

public class CompanyConfigControl {

    public CompanyConfigControl() {
    }

    public boolean includeVat() {
        String vatType = "";
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
            
        } finally {
            mysql.close();
        }

        return vatType.equals("I");
    }

    public boolean excludeVat() {
        String vatType = "";
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
            
        } finally{
            mysql.close();
        }

        return vatType.equals("E");
    }
}
