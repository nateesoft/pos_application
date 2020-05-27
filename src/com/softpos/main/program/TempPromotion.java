package com.softpos.main.program;

public class TempPromotion {

    private String TableNo;
    private String PrCode;
    private String PrType;
    private String PCode;
    private double PQty = 0.000;
    private double PrTotalAmt = 0.00;
    private double PrAmt = 0.00;

    public String getTableNo() {
        return TableNo;
    }

    public void setTableNo(String TableNo) {
        this.TableNo = TableNo;
    }

    public String getPrCode() {
        return PrCode;
    }

    public void setPrCode(String PrCode) {
        this.PrCode = PrCode;
    }

    public String getPrType() {
        return PrType;
    }

    public void setPrType(String PrType) {
        this.PrType = PrType;
    }

    public String getPCode() {
        return PCode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public double getPQty() {
        return PQty;
    }

    public void setPQty(double PQty) {
        this.PQty = PQty;
    }

    public double getPrTotalAmt() {
        return PrTotalAmt;
    }

    public void setPrTotalAmt(double PrTotalAmt) {
        this.PrTotalAmt = PrTotalAmt;
    }

    public double getPrAmt() {
        return PrAmt;
    }

    public void setPrAmt(double PrAmt) {
        this.PrAmt = PrAmt;
    }

}
