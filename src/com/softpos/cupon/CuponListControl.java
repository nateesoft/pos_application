package com.softpos.cupon;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.MSG;

public class CuponListControl {

    public ArrayList<CuponlistBean> listCuponlist() {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        ArrayList<CuponlistBean> listBean = new ArrayList<>();
        try {
            String sql = "select * from cuponlist";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CuponlistBean bean = new CuponlistBean();

                bean.setCuCode(rs.getString("CuCode"));
                bean.setPCode(rs.getString("PCode"));

                listBean.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();            
        } finally{
            mysql.close();
        }

        return listBean;
    }

    public ArrayList<CuponlistBean> listCuponlist(String CuCode) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        ArrayList<CuponlistBean> listBean = new ArrayList<>();
        try {
            String sql = "select * from cuponlist "
                    + "where CuCode='" + CuCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CuponlistBean bean = new CuponlistBean();

                bean.setCuCode(rs.getString("CuCode"));
                bean.setPCode(rs.getString("PCode"));

                listBean.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }

        return listBean;
    }

    public CuponlistBean getCuponlist(String CuCode) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        CuponlistBean bean = new CuponlistBean();
        try {
            String sql = "select * from cuponlist "
                    + "where CuCode='" + CuCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                bean.setCuCode(rs.getString("CuCode"));
                bean.setPCode(rs.getString("PCode"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }

        return bean;
    }

    public void saveCuponlist(CuponlistBean bean) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        try {
            String sql = "insert into cuponlist (CuCode,PCode)  "
                    + "values('" + bean.getCuCode() + "','" + bean.getPCode() + "')";
            String sqlChk = "select * from cuponlist "
                    + "where CuCode='" + bean.getCuCode() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sqlChk);
            if (rs.next()) {
                updateCuponlist(bean);
            } else {
                stmt.executeUpdate(sql);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }
    }

    public void updateCuponlist(CuponlistBean bean) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        try {
            String sql = "update cuponlist set "
                    + "CuCode='" + bean.getCuCode() + "', "
                    + "PCode='" + bean.getPCode() + "' "
                    + "where CuCode='" + bean.getCuCode() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }
    }
}
