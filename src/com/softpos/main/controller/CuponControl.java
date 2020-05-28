package com.softpos.main.controller;

import com.softpos.main.model.CuponBean;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sun.natee.project.util.DateFormat;
import util.MSG;

public class CuponControl {

    public ArrayList<CuponBean> listCupon() {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        ArrayList<CuponBean> listBean = new ArrayList<>();
        try {
            String sql = "select * from cupon";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CuponBean bean = new CuponBean();
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuName(rs.getString("CuName"));
                bean.setCuBegin(rs.getDate("CuBegin"));
                bean.setCuEnd(rs.getDate("CuEnd"));
                bean.setCuStrDay(rs.getString("CuStrDay"));
                bean.setCuType(rs.getString("CuType"));
                bean.setCuADisc(rs.getString("CuADisc"));
                bean.setCuADiscBath(rs.getFloat("CuADiscBath"));
                bean.setCuBDisc(rs.getString("CuBDisc"));
                bean.setCuBDiscBath(rs.getFloat("CuBDiscBath"));
                bean.setCuPLUList(rs.getString("CuPLUList"));
                bean.setCuPLU1(rs.getString("CuPLU1"));
                bean.setCuPLU2(rs.getString("CuPLU2"));
                bean.setCuPLU3(rs.getString("CuPLU3"));
                bean.setCuPLU4(rs.getString("CuPLU4"));
                bean.setCuPLU5(rs.getString("CuPLU5"));
                bean.setCuPLU6(rs.getString("CuPLU6"));
                bean.setCuPLU7(rs.getString("CuPLU7"));
                bean.setCuPLU8(rs.getString("CuPLU8"));
                bean.setCuPLU9(rs.getString("CuPLU9"));
                bean.setCuPLU10(rs.getString("CuPLU10"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuDiscBath(rs.getFloat("CuDiscBath"));
                bean.setChkMember(rs.getString("ChkMember"));
                bean.setCuDisc2(rs.getFloat("CuDisc2"));
                bean.setCuDiscBath2(rs.getFloat("CuDiscBath2"));
                bean.setCuDisc3(rs.getFloat("CuDisc3"));
                bean.setCuDiscBath3(rs.getFloat("CuDiscBath3"));
                bean.setCuDisc1(rs.getFloat("CuDisc1"));
                bean.setCuDiscBath1(rs.getFloat("CuDiscBath1"));
                bean.setCuSelectDisc(rs.getString("CuSelectDisc"));
                bean.setCuEDiscount(rs.getFloat("CuEDiscount"));
                bean.setCuEPayment(rs.getFloat("CuEPayment"));

                listBean.add(bean);
            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally{
            mysql.close();
        }

        return listBean;
    }

    public ArrayList<CuponBean> listCupon(String CuCode) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        ArrayList<CuponBean> listBean = new ArrayList<>();
        try {
            String sql = "select * from cupon where CuCode='" + CuCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CuponBean bean = new CuponBean();
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuName(rs.getString("CuName"));
                bean.setCuBegin(rs.getDate("CuBegin"));
                bean.setCuEnd(rs.getDate("CuEnd"));
                bean.setCuStrDay(rs.getString("CuStrDay"));
                bean.setCuType(rs.getString("CuType"));
                bean.setCuADisc(rs.getString("CuADisc"));
                bean.setCuADiscBath(rs.getFloat("CuADiscBath"));
                bean.setCuBDisc(rs.getString("CuBDisc"));
                bean.setCuBDiscBath(rs.getFloat("CuBDiscBath"));
                bean.setCuPLUList(rs.getString("CuPLUList"));
                bean.setCuPLU1(rs.getString("CuPLU1"));
                bean.setCuPLU2(rs.getString("CuPLU2"));
                bean.setCuPLU3(rs.getString("CuPLU3"));
                bean.setCuPLU4(rs.getString("CuPLU4"));
                bean.setCuPLU5(rs.getString("CuPLU5"));
                bean.setCuPLU6(rs.getString("CuPLU6"));
                bean.setCuPLU7(rs.getString("CuPLU7"));
                bean.setCuPLU8(rs.getString("CuPLU8"));
                bean.setCuPLU9(rs.getString("CuPLU9"));
                bean.setCuPLU10(rs.getString("CuPLU10"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuDiscBath(rs.getFloat("CuDiscBath"));
                bean.setChkMember(rs.getString("ChkMember"));
                bean.setCuDisc2(rs.getFloat("CuDisc2"));
                bean.setCuDiscBath2(rs.getFloat("CuDiscBath2"));
                bean.setCuDisc3(rs.getFloat("CuDisc3"));
                bean.setCuDiscBath3(rs.getFloat("CuDiscBath3"));
                bean.setCuDisc1(rs.getFloat("CuDisc1"));
                bean.setCuDiscBath1(rs.getFloat("CuDiscBath1"));
                bean.setCuSelectDisc(rs.getString("CuSelectDisc"));
                bean.setCuEDiscount(rs.getFloat("CuEDiscount"));
                bean.setCuEPayment(rs.getFloat("CuEPayment"));

                listBean.add(bean);
            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally{
            mysql.close();
        }

        return listBean;
    }

    public CuponBean getCupon(String CuCode) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        CuponBean bean = new CuponBean();
        try {
            String sql = "select * from cupon where CuCode='" + CuCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuName(rs.getString("CuName"));
                bean.setCuBegin(rs.getDate("CuBegin"));
                bean.setCuEnd(rs.getDate("CuEnd"));
                bean.setCuStrDay(rs.getString("CuStrDay"));
                bean.setCuType(rs.getString("CuType"));
                bean.setCuADisc(rs.getString("CuADisc"));
                bean.setCuADiscBath(rs.getFloat("CuADiscBath"));
                bean.setCuBDisc(rs.getString("CuBDisc"));
                bean.setCuBDiscBath(rs.getFloat("CuBDiscBath"));
                bean.setCuPLUList(rs.getString("CuPLUList"));
                bean.setCuPLU1(rs.getString("CuPLU1"));
                bean.setCuPLU2(rs.getString("CuPLU2"));
                bean.setCuPLU3(rs.getString("CuPLU3"));
                bean.setCuPLU4(rs.getString("CuPLU4"));
                bean.setCuPLU5(rs.getString("CuPLU5"));
                bean.setCuPLU6(rs.getString("CuPLU6"));
                bean.setCuPLU7(rs.getString("CuPLU7"));
                bean.setCuPLU8(rs.getString("CuPLU8"));
                bean.setCuPLU9(rs.getString("CuPLU9"));
                bean.setCuPLU10(rs.getString("CuPLU10"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuDiscBath(rs.getFloat("CuDiscBath"));
                bean.setChkMember(rs.getString("ChkMember"));
                bean.setCuDisc2(rs.getFloat("CuDisc2"));
                bean.setCuDiscBath2(rs.getFloat("CuDiscBath2"));
                bean.setCuDisc3(rs.getFloat("CuDisc3"));
                bean.setCuDiscBath3(rs.getFloat("CuDiscBath3"));
                bean.setCuDisc1(rs.getFloat("CuDisc1"));
                bean.setCuDiscBath1(rs.getFloat("CuDiscBath1"));
                bean.setCuSelectDisc(rs.getString("CuSelectDisc"));
                bean.setCuEDiscount(rs.getFloat("CuEDiscount"));
                bean.setCuEPayment(rs.getFloat("CuEPayment"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally{
            mysql.close();
        }

        return bean;
    }

    public void saveCupon(CuponBean bean) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        try {
            String sql = "insert into cupon (CuCode,CuName,CuBegin,CuEnd,CuStrDay,CuType,CuADisc,"
                    + "CuADiscBath,CuBDisc,CuBDiscBath,CuPLUList,CuPLU1,CuPLU2,CuPLU3,CuPLU4,CuPLU5,"
                    + "CuPLU6,CuPLU7,CuPLU8,CuPLU9,CuPLU10,CuDisc,CuDiscBath,ChkMember,CuDisc2,CuDiscBath2,"
                    + "CuDisc3,CuDiscBath3,CuDisc1,CuDiscBath1,CuSelectDisc,CuEDiscount,CuEPayment)  "
                    + "values('" + bean.getCuCode() + "','" + bean.getCuName() + "','" + bean.getCuBegin() + "',"
                    + "'" + bean.getCuEnd() + "','" + bean.getCuStrDay() + "','" + bean.getCuType() + "',"
                    + "'" + bean.getCuADisc() + "','" + bean.getCuADiscBath() + "','" + bean.getCuBDisc() + "',"
                    + "'" + bean.getCuBDiscBath() + "','" + bean.getCuPLUList() + "','" + bean.getCuPLU1() + "',"
                    + "'" + bean.getCuPLU2() + "','" + bean.getCuPLU3() + "','" + bean.getCuPLU4() + "',"
                    + "'" + bean.getCuPLU5() + "','" + bean.getCuPLU6() + "','" + bean.getCuPLU7() + "',"
                    + "'" + bean.getCuPLU8() + "','" + bean.getCuPLU9() + "','" + bean.getCuPLU10() + "',"
                    + "'" + bean.getCuDisc() + "','" + bean.getCuDiscBath() + "','" + bean.getChkMember() + "',"
                    + "'" + bean.getCuDisc2() + "','" + bean.getCuDiscBath2() + "','" + bean.getCuDisc3() + "',"
                    + "'" + bean.getCuDiscBath3() + "','" + bean.getCuDisc1() + "',"
                    + "'" + bean.getCuDiscBath1() + "','" + bean.getCuSelectDisc() + "',"
                    + "'" + bean.getCuEDiscount() + "','" + bean.getCuEPayment() + "')";
            String sqlChk = "select * from cupon where CuCode='" + bean.getCuCode() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sqlChk);
            if (rs.next()) {
                updateCupon(bean);
            } else {
                stmt.executeUpdate(sql);
            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally{
            mysql.close();
        }
    }

    public void updateCupon(CuponBean bean) {
        /*** OPEN CONNECTION ***/
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        
        try {
            String sql = "update cupon set CuCode='" + bean.getCuCode() + "', "
                    + "CuName='" + bean.getCuName() + "', "
                    + "CuBegin='" + DateFormat.getMySQL_Date(bean.getCuBegin()) + "', "
                    + "CuEnd='" + DateFormat.getMySQL_Date(bean.getCuEnd()) + "', "
                    + "CuStrDay='" + bean.getCuStrDay() + "', CuType='" + bean.getCuType() + "', "
                    + "CuADisc='" + bean.getCuADisc() + "', CuADiscBath='" + bean.getCuADiscBath() + "', "
                    + "CuBDisc='" + bean.getCuBDisc() + "', CuBDiscBath='" + bean.getCuBDiscBath() + "', "
                    + "CuPLUList='" + bean.getCuPLUList() + "', CuPLU1='" + bean.getCuPLU1() + "', "
                    + "CuPLU2='" + bean.getCuPLU2() + "', CuPLU3='" + bean.getCuPLU3() + "', "
                    + "CuPLU4='" + bean.getCuPLU4() + "', CuPLU5='" + bean.getCuPLU5() + "', "
                    + "CuPLU6='" + bean.getCuPLU6() + "', CuPLU7='" + bean.getCuPLU7() + "', "
                    + "CuPLU8='" + bean.getCuPLU8() + "', CuPLU9='" + bean.getCuPLU9() + "', "
                    + "CuPLU10='" + bean.getCuPLU10() + "', CuDisc='" + bean.getCuDisc() + "', "
                    + "CuDiscBath='" + bean.getCuDiscBath() + "', ChkMember='" + bean.getChkMember() + "', "
                    + "CuDisc2='" + bean.getCuDisc2() + "', CuDiscBath2='" + bean.getCuDiscBath2() + "', "
                    + "CuDisc3='" + bean.getCuDisc3() + "', CuDiscBath3='" + bean.getCuDiscBath3() + "', "
                    + "CuDisc1='" + bean.getCuDisc1() + "', CuDiscBath1='" + bean.getCuDiscBath1() + "', "
                    + "CuSelectDisc='" + bean.getCuSelectDisc() + "', "
                    + "CuEDiscount='" + bean.getCuEDiscount() + "', CuEPayment='" + bean.getCuEPayment() + "' "
                    + "where CuCode='" + bean.getCuCode() + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally{
            mysql.close();
        }
    }
}
