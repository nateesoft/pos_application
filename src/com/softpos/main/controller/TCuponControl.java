package com.softpos.main.controller;

import com.softpos.main.model.TCuponBean;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.softpos.main.utils.MSG;

public class TCuponControl {

    public ArrayList<TCuponBean> listTCupon() {
        ArrayList<TCuponBean> listBean = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from t_cupon";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TCuponBean bean = new TCuponBean();
                bean.setR_Index(rs.getString("R_Index"));
                bean.setR_Refno(rs.getString("R_Refno"));
                bean.setTerminal(rs.getString("Terminal"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTime(rs.getString("Time"));
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuQuan(rs.getInt("CuQuan"));
                bean.setCuAmt(rs.getFloat("CuAmt"));
                bean.setRefund(rs.getString("Refund"));
                bean.setCuTextCode(rs.getString("CuTextCode"));
                bean.setCuTextComment(rs.getString("CuTextComment"));

                listBean.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return listBean;
    }

    public ArrayList<TCuponBean> listTCupon(String R_Index) {
        ArrayList<TCuponBean> listBean = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from t_cupon "
                    + "where R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TCuponBean bean = new TCuponBean();

                bean.setR_Index(rs.getString("R_Index"));
                bean.setR_Refno(rs.getString("R_Refno"));
                bean.setTerminal(rs.getString("Terminal"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTime(rs.getString("Time"));
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuQuan(rs.getInt("CuQuan"));
                bean.setCuAmt(rs.getFloat("CuAmt"));
                bean.setRefund(rs.getString("Refund"));
                bean.setCuTextCode(rs.getString("CuTextCode"));
                bean.setCuTextComment(rs.getString("CuTextComment"));

                listBean.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return listBean;
    }

    public TCuponBean getTCupon(String R_Index) {
        TCuponBean bean = new TCuponBean();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from t_cupon "
                    + "where R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setR_Index(rs.getString("R_Index"));
                bean.setR_Refno(rs.getString("R_Refno"));
                bean.setTerminal(rs.getString("Terminal"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTime(rs.getString("Time"));
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuQuan(rs.getInt("CuQuan"));
                bean.setCuAmt(rs.getFloat("CuAmt"));
                bean.setRefund(rs.getString("Refund"));
                bean.setCuTextCode(rs.getString("CuTextCode"));
                bean.setCuTextComment(rs.getString("CuTextComment"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return bean;
    }

    public boolean saveTCupon(TCuponBean bean) {
        boolean isResult = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        if (bean.getTerminal() == null || bean.getCuCode() == null) {
            bean.setTerminal("");
            bean.setCuCode("");
        } else {
            try {
                String sql = "insert into t_cupon "
                        + "(R_Index,R_Refno,Terminal,Cashier,Time,CuCode,CuQuan,"
                        + "CuAmt,Refund,CuTextCode,CuTextComment)  "
                        + "values('" + bean.getR_Index() + "','" + bean.getR_Refno() + "',"
                        + "'" + bean.getTerminal() + "','" + bean.getCashier() + "','" + bean.getTime() + "',"
                        + "'" + bean.getCuCode() + "','" + bean.getCuQuan() + "','" + bean.getCuAmt() + "',"
                        + "'" + bean.getRefund() + "','" + bean.getCuTextCode() + "','" + bean.getCuTextComment() + "')";

                String sqlChk = "select * from t_cupon where R_Index='" + bean.getR_Index() + "'";

                Statement stmt1 = mysql.getConnection().createStatement();
                ResultSet rs = stmt1.executeQuery(sqlChk);

                if (rs.next()) {
                    isResult = updateTCupon(bean);
                } else {
                    Statement stmt = mysql.getConnection().createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                    isResult = true;
                }

                rs.close();
                stmt1.close();
            } catch (SQLException e) {
                MSG.ERR(e.getMessage());
                
                isResult = false;

            } finally {
                mysql.close();
            }

        }

        return isResult;
    }

    public boolean updateTCupon(TCuponBean bean) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update t_cupon "
                    + "set R_Index='" + bean.getR_Index() + "', "
                    + "R_Refno='" + bean.getR_Refno() + "', "
                    + "Terminal='" + bean.getTerminal() + "', "
                    + "Cashier='" + bean.getCashier() + "', "
                    + "Time='" + bean.getTime() + "', "
                    + "CuCode='" + bean.getCuCode() + "', "
                    + "CuQuan='" + bean.getCuQuan() + "', "
                    + "CuAmt='" + bean.getCuAmt() + "', "
                    + "Refund='" + bean.getRefund() + "', "
                    + "CuTextCode='" + bean.getCuTextCode() + "', "
                    + "CuTextComment='" + bean.getCuTextComment() + "' "
                    + "where R_Index='" + bean.getR_Index() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return false;
    }
}
