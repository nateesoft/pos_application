package com.softpos.main.model;

import java.util.ArrayList;
import java.util.Date;

public class CuponBean {

    private String CuCode;//
    private int setCuQTY;
    private String CuName;//
    private Date CuBegin;//
    private Date CuEnd;//
    private String CuStrDay;//
    private String CuType = "C";//
    private String CuADisc = "0";//
    private double CuADiscBath = 0.00;//
    private String CuBDisc = "0";//
    private double CuBDiscBath = 0.00;//
    private String CuPLUList;//
    private String CuPLU1;//
    private String CuPLU2;//
    private String CuPLU3;//
    private String CuPLU4;//
    private String CuPLU5;//
    private String CuPLU6;//
    private String CuPLU7;//
    private String CuPLU8;//
    private String CuPLU9;//
    private String CuPLU10;//
    private double CuDisc = 0.00;//
    private double CuDiscBath = 0.00;//
    private String ChkMember = "N";//
    private double CuDisc2 = 0.00;//
    private double CuDiscBath2 = 0.00;//
    private double CuDisc3 = 0.00;//
    private double CuDiscBath3 = 0.00;//
    private double CuDisc1;//
    private double CuDiscBath1;//
    private String CuSelectDisc;//
    private double CuEDiscount;//
    private double CuEPayment;//

    private ArrayList<CuponlistBean> listBean;

    public CuponBean() {
        listBean = new ArrayList<>();
    }

    public String getCuCode() {
        return CuCode;
    }

    public void setCuCode(String CuCode) {
        this.CuCode = CuCode;
    }

    public int getSetCuQTY() {
        return setCuQTY;
    }
    //public void setCuQTY(int setCuQTY)
    public void setCuQTY(int parseInt) {
    //public void setSetCuQTY(int setCuQTY) {
        this.setCuQTY = setCuQTY;
    }

    public String getCuName() {
        return CuName;
    }

    public void setCuName(String CuName) {
        this.CuName = CuName;
    }

    public Date getCuBegin() {
        return CuBegin;
    }

    public void setCuBegin(Date CuBegin) {
        this.CuBegin = CuBegin;
    }

    public Date getCuEnd() {
        return CuEnd;
    }

    public void setCuEnd(Date CuEnd) {
        this.CuEnd = CuEnd;
    }

    public String getCuStrDay() {
        return CuStrDay;
    }

    public void setCuStrDay(String CuStrDay) {
        this.CuStrDay = CuStrDay;
    }

    public String getCuType() {
        return CuType;
    }

    public void setCuType(String CuType) {
        this.CuType = CuType;
    }

    public String getCuADisc() {
        return CuADisc;
    }

    public void setCuADisc(String CuADisc) {
        this.CuADisc = CuADisc;
    }

    public double getCuADiscBath() {
        return CuADiscBath;
    }

    public void setCuADiscBath(double CuADiscBath) {
        this.CuADiscBath = CuADiscBath;
    }

    public String getCuBDisc() {
        return CuBDisc;
    }

    public void setCuBDisc(String CuBDisc) {
        this.CuBDisc = CuBDisc;
    }

    public double getCuBDiscBath() {
        return CuBDiscBath;
    }

    public void setCuBDiscBath(double CuBDiscBath) {
        this.CuBDiscBath = CuBDiscBath;
    }

    public String getCuPLUList() {
        return CuPLUList;
    }

    public void setCuPLUList(String CuPLUList) {
        this.CuPLUList = CuPLUList;
    }

    public String getCuPLU1() {
        return CuPLU1;
    }

    public void setCuPLU1(String CuPLU1) {
        this.CuPLU1 = CuPLU1;
    }

    public String getCuPLU2() {
        return CuPLU2;
    }

    public void setCuPLU2(String CuPLU2) {
        this.CuPLU2 = CuPLU2;
    }

    public String getCuPLU3() {
        return CuPLU3;
    }

    public void setCuPLU3(String CuPLU3) {
        this.CuPLU3 = CuPLU3;
    }

    public String getCuPLU4() {
        return CuPLU4;
    }

    public void setCuPLU4(String CuPLU4) {
        this.CuPLU4 = CuPLU4;
    }

    public String getCuPLU5() {
        return CuPLU5;
    }

    public void setCuPLU5(String CuPLU5) {
        this.CuPLU5 = CuPLU5;
    }

    public String getCuPLU6() {
        return CuPLU6;
    }

    public void setCuPLU6(String CuPLU6) {
        this.CuPLU6 = CuPLU6;
    }

    public String getCuPLU7() {
        return CuPLU7;
    }

    public void setCuPLU7(String CuPLU7) {
        this.CuPLU7 = CuPLU7;
    }

    public String getCuPLU8() {
        return CuPLU8;
    }

    public void setCuPLU8(String CuPLU8) {
        this.CuPLU8 = CuPLU8;
    }

    public String getCuPLU9() {
        return CuPLU9;
    }

    public void setCuPLU9(String CuPLU9) {
        this.CuPLU9 = CuPLU9;
    }

    public String getCuPLU10() {
        return CuPLU10;
    }

    public void setCuPLU10(String CuPLU10) {
        this.CuPLU10 = CuPLU10;
    }

    public double getCuDisc() {
        return CuDisc;
    }

    public void setCuDisc(double CuDisc) {
        this.CuDisc = CuDisc;
    }

    public double getCuDiscBath() {
        return CuDiscBath;
    }

    public void setCuDiscBath(double CuDiscBath) {
        this.CuDiscBath = CuDiscBath;
    }

    public String getChkMember() {
        return ChkMember;
    }

    public void setChkMember(String ChkMember) {
        this.ChkMember = ChkMember;
    }

    public double getCuDisc2() {
        return CuDisc2;
    }

    public void setCuDisc2(double CuDisc2) {
        this.CuDisc2 = CuDisc2;
    }

    public double getCuDiscBath2() {
        return CuDiscBath2;
    }

    public void setCuDiscBath2(double CuDiscBath2) {
        this.CuDiscBath2 = CuDiscBath2;
    }

    public double getCuDisc3() {
        return CuDisc3;
    }

    public void setCuDisc3(double CuDisc3) {
        this.CuDisc3 = CuDisc3;
    }

    public double getCuDiscBath3() {
        return CuDiscBath3;
    }

    public void setCuDiscBath3(double CuDiscBath3) {
        this.CuDiscBath3 = CuDiscBath3;
    }

    public double getCuDisc1() {
        return CuDisc1;
    }

    public void setCuDisc1(double CuDisc1) {
        this.CuDisc1 = CuDisc1;
    }

    public double getCuDiscBath1() {
        return CuDiscBath1;
    }

    public void setCuDiscBath1(double CuDiscBath1) {
        this.CuDiscBath1 = CuDiscBath1;
    }

    public String getCuSelectDisc() {
        return CuSelectDisc;
    }

    public void setCuSelectDisc(String CuSelectDisc) {
        this.CuSelectDisc = CuSelectDisc;
    }

    public double getCuEDiscount() {
        return CuEDiscount;
    }

    public void setCuEDiscount(double CuEDiscount) {
        this.CuEDiscount = CuEDiscount;
    }

    public double getCuEPayment() {
        return CuEPayment;
    }

    public void setCuEPayment(double CuEPayment) {
        this.CuEPayment = CuEPayment;
    }

    public ArrayList<CuponlistBean> getListBean() {
        return listBean;
    }

    public void setListBean(ArrayList<CuponlistBean> listBean) {
        this.listBean = listBean;
    }
}
