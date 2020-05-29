package com.softpos.main.controller;

import database.MySQLConnect;
import com.softpos.main.model.POSConfigSetup;
import com.softpos.main.model.TableFileBean;
import java.sql.SQLException;
import java.sql.Statement;
import util.MSG;

public class VatControl {

    private String vatType = "";
    private String tableNo = "";
    TableFileControl tfControl = null;
    TableFileBean tBean = null;
    private double Vat = 0.00;

    public VatControl(String tableNo) {

        this.tableNo = tableNo;
        vatType = POSConfigSetup.Bean().getP_VatType();
        Vat = POSConfigSetup.Bean().getP_Vat();
        tfControl = new TableFileControl();
        tBean = tfControl.getData(tableNo);
    }

    public void updateVat() {
        if (vatType.equals("I")) {
            updateVatInclude();
        } else if (vatType.equals("E")) {
            updateVatExclude();
        }
    }

    public void updateVatInclude() {
        System.out.println("Include Vat.");

        double TAmount = tBean.getTAmount();
        double ServiceAmt = tBean.getServiceAmt();
        double NetTotal = TAmount + ServiceAmt;

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile "
                    + "set NetTotal='" + NetTotal + "' "
                    + "where TCode='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }
    }

    public void updateVatExclude() {
        System.out.println("Exclude Vat.");

        double TAmount = tBean.getTAmount();
        double ServiceAmt = tBean.getServiceAmt();
        double total = TAmount + ServiceAmt;
        double NetTotal = (total * Vat / 100) + total;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile "
                    + "set NetTotal='" + NetTotal + "' "
                    + "where TCode='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        }
    }
}
