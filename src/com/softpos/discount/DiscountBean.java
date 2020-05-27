package com.softpos.discount;

import com.softpos.cupon.CuponBean;

public class DiscountBean {

    private double festDiscount = 0;
    private double empDiscount = 0;
    private double memDiscount = 0;
    private double trainDiscount = 0;
    private double cuponDiscount = 0;
    private double bahtDiscount = 0;
    private double cuponSpecialDiscount = 0;
    
    private String strFestDiscount = "00/00/00";
    private String strEmpDiscount = "00/00/00";
    private String strMemDiscount = "00/00/00";
    private String strTrainDiscount = "00/00/00";
    private String strCuponDiscount = "00/00/00";
    
    private CuponBean cuponBean = new CuponBean();

    public String getStrFestDiscount() {
        return strFestDiscount;
    }

    public void setStrFestDiscount(String strFestDiscount) {
        this.strFestDiscount = strFestDiscount;
    }

    public String getStrEmpDiscount() {
        return strEmpDiscount;
    }

    public void setStrEmpDiscount(String strEmpDiscount) {
        this.strEmpDiscount = strEmpDiscount;
    }

    public String getStrMemDiscount() {
        return strMemDiscount;
    }

    public void setStrMemDiscount(String strMemDiscount) {
        this.strMemDiscount = strMemDiscount;
    }

    public String getStrTrainDiscount() {
        return strTrainDiscount;
    }

    public void setStrTrainDiscount(String strTrainDiscount) {
        this.strTrainDiscount = strTrainDiscount;
    }

    public String getStrCuponDiscount() {
        return strCuponDiscount;
    }

    public void setStrCuponDiscount(String strCuponDiscount) {
        this.strCuponDiscount = strCuponDiscount;
    }

    public double getTotalDiscount() {
        return festDiscount + empDiscount + memDiscount + trainDiscount + cuponDiscount + bahtDiscount + cuponSpecialDiscount;
    }

    public double getCuponSpecialDiscount() {
        return cuponSpecialDiscount;
    }

    public void setCuponSpecialDiscount(double cuponSpecialDiscount) {
        this.cuponSpecialDiscount = cuponSpecialDiscount;
    }

    public double getFestDiscount() {
        return festDiscount;
    }

    public void setFestDiscount(double festDiscount) {
        this.festDiscount = festDiscount;
    }

    public double getEmpDiscount() {
        return empDiscount;
    }

    public void setEmpDiscount(double empDiscount) {
        this.empDiscount = empDiscount;
    }

    public double getMemDiscount() {
        return memDiscount;
    }

    public void setMemDiscount(double memDiscount) {
        this.memDiscount = memDiscount;
    }

    public double getTrainDiscount() {
        return trainDiscount;
    }

    public void setTrainDiscount(double trainDiscount) {
        this.trainDiscount = trainDiscount;
    }

    public double getCuponDiscount() {
        return cuponDiscount;
    }

    public void setCuponDiscount(double cuponDiscount) {
        this.cuponDiscount = cuponDiscount;
    }

    public double getBahtDiscount() {
        return bahtDiscount;
    }

    public void setBahtDiscount(double bahtDiscount) {
        this.bahtDiscount = bahtDiscount;
    }

    public CuponBean getCuponBean() {
        return cuponBean;
    }

    public void setCuponBean(CuponBean cuponBean) {
        this.cuponBean = cuponBean;
    }

}
