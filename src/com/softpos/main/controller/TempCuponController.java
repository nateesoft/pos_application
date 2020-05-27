package com.softpos.main.controller;

import com.softpos.main.model.TCuponBean;
import com.softpos.main.model.TempCuponBean;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.MSG;

public class TempCuponController {

    public ArrayList<TempCuponBean> listTempcupon() {
        ArrayList<TempCuponBean> listBean = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from tempcupon";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TempCuponBean bean = new TempCuponBean();
                bean.setR_Index(rs.getString("R_Index"));
                bean.setR_Table(rs.getString("R_Table"));
                bean.setTerminal(rs.getString("Terminal"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTime(rs.getString("Time"));
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuQuan(rs.getInt("CuQuan"));
                bean.setCuAmt(rs.getFloat("CuAmt"));
                bean.setCuTotal(rs.getFloat("CuTotal"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuRedule(rs.getFloat("CuRedule"));
                bean.setCuPayment(rs.getFloat("CuPayment"));
                bean.setCuTextCode(rs.getString("CuTextCode"));
                bean.setCuTextComment(rs.getString("CuTextComment"));

                listBean.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return listBean;
    }

    public ArrayList<TempCuponBean> listTempcupon(String R_Index) {
        ArrayList<TempCuponBean> listBean = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from tempcupon "
                    + "where R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TempCuponBean bean = new TempCuponBean();
                bean.setR_Index(rs.getString("R_Index"));
                bean.setR_Table(rs.getString("R_Table"));
                bean.setTerminal(rs.getString("Terminal"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTime(rs.getString("Time"));
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuQuan(rs.getInt("CuQuan"));
                bean.setCuAmt(rs.getFloat("CuAmt"));
                bean.setCuTotal(rs.getFloat("CuTotal"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuRedule(rs.getFloat("CuRedule"));
                bean.setCuPayment(rs.getFloat("CuPayment"));
                bean.setCuTextCode(rs.getString("CuTextCode"));
                bean.setCuTextComment(rs.getString("CuTextComment"));

                listBean.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return listBean;
    }

    public TempCuponBean getTempcupon(String R_Index) {
        TempCuponBean bean = new TempCuponBean();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from tempcupon "
                    + "where R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setR_Index(rs.getString("R_Index"));
                bean.setR_Table(rs.getString("R_Table"));
                bean.setTerminal(rs.getString("Terminal"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTime(rs.getString("Time"));
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuQuan(rs.getInt("CuQuan"));
                bean.setCuAmt(rs.getFloat("CuAmt"));
                bean.setCuTotal(rs.getFloat("CuTotal"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuRedule(rs.getFloat("CuRedule"));
                bean.setCuPayment(rs.getFloat("CuPayment"));
                bean.setCuTextCode(rs.getString("CuTextCode"));
                bean.setCuTextComment(rs.getString("CuTextComment"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return bean;
    }

    public void clearTempOld(String index) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from tempcupon "
                    + "where r_index='" + index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }

        try {
            String sql = "delete from tempgift";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }

        mysql.close();
    }

    public void saveTempCupon(TempCuponBean bean) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            SimpleDateFormat simp = new SimpleDateFormat("HH:mm");
            String sql = "insert into tempcupon"
                    + "(R_Index,R_Table,Terminal,Cashier,Time,CuCode,CuQuan,CuAmt,"
                    + "CuTotal,CuDisc,CuRedule,CuPayment,CuTextCode,CuTextComment) "
                    + "values('" + bean.getR_Index() + "','" + bean.getR_Table() + "','" + bean.getTerminal() + "',"
                    + "'" + bean.getCashier() + "','" + simp.format(new Date()) + "','" + bean.getCuCode() + "','" + bean.getCuQuan() + "',"
                    + "'" + bean.getCuAmt() + "','" + bean.getCuTotal() + "','" + bean.getCuDisc() + "','" + bean.getCuRedule() + "',"
                    + "'" + bean.getCuPayment() + "','" + bean.getCuTextCode() + "','" + bean.getCuTextComment() + "')";
            String sql1 = "select * from tempcupon where r_index='" + bean.getR_Index() + "' ";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if (rs.next()) {
                String sqlDel = "delete from tempcupon where r_index='" + bean.getR_Index() + "' ";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sqlDel);
                stmt1.close();
            }

            rs.close();

            //insert data
            Statement stmt1 = mysql.getConnection().createStatement();
            stmt1.executeUpdate(sql);
            stmt1.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }
    }

    public boolean moveToTCupon(TempCuponBean tempcupon, String B_Refno) {
        TCuponControl tCon = new TCuponControl();
        TCuponBean tBean = new TCuponBean();
        tBean.setR_Index(B_Refno + "/" + tempcupon.getTerminal());
        tBean.setTerminal(tempcupon.getTerminal());
        tBean.setR_Refno(B_Refno);
        tBean.setCashier(tempcupon.getCashier());
        tBean.setTime(tempcupon.getTime());
        tBean.setCuCode(tempcupon.getCuCode());
        tBean.setCuQuan(tempcupon.getCuQuan());
        tBean.setCuAmt(tempcupon.getCuAmt());
        tBean.setRefund("");
        tBean.setCuTextCode("");
        tBean.setCuTextComment("");

        return tCon.saveTCupon(tBean);
    }
}
