package com.softpos.webapp.promotion;

public class TPromotionBean {

    private String R_Index;
    private String R_RefNo;
    private String Terminal;
    private String Cashier;
    private String PrCode;
    private String PrType;
    private String PCode;
    private double PDisc = 0.00;
    private double PDiscBath = 0.00;
    private double PPrice = 0.00;
    private double PQty = 0.00;
    private double PrTotalAmt = 0.00;
    private double PrAmt = 0.00;
    private String Flage = "-";

    public String getR_Index() {
        return R_Index;
    }

    public void setR_Index(String R_Index) {
        this.R_Index = R_Index;
    }

    public String getR_RefNo() {
        return R_RefNo;
    }

    public void setR_RefNo(String R_RefNo) {
        this.R_RefNo = R_RefNo;
    }

    public String getTerminal() {
        return Terminal;
    }

    public void setTerminal(String Terminal) {
        this.Terminal = Terminal;
    }

    public String getCashier() {
        return Cashier;
    }

    public void setCashier(String Cashier) {
        this.Cashier = Cashier;
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

    public double getPDisc() {
        return PDisc;
    }

    public void setPDisc(double PDisc) {
        this.PDisc = PDisc;
    }

    public double getPDiscBath() {
        return PDiscBath;
    }

    public void setPDiscBath(double PDiscBath) {
        this.PDiscBath = PDiscBath;
    }

    public double getPPrice() {
        return PPrice;
    }

    public void setPPrice(double PPrice) {
        this.PPrice = PPrice;
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

    public String getFlage() {
        return Flage;
    }

    public void setFlage(String Flage) {
        this.Flage = Flage;
    }

}
