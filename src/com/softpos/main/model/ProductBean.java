package com.softpos.main.model;

import java.util.Date;

public class ProductBean {

    private String PCode;
    private String PFix = "F";
    private String PReferent;
    private String PAccNo;
    private String PGroup;
    private String PVender;
    private String PType = "1";
    private String PNormal = "C";
    private String PRemark;
    private String PDiscount = "Y";
    private String PService = "Y";
    private String PStatus = "P";
    private String PStock = "Y";
    private String PSet = "N";
    private String PVat = "V";
    private String PDesc;
    private String PUnit1;
    private int PPack1 = 0;
    private String PArea;
    private String PKic = "0";
    private double PPrice11 = 0.00;
    private double PPrice12 = 0.00;
    private double PPrice13 = 0.00;
    private double PPrice14 = 0.00;
    private double PPrice15 = 0.00;
    private String PPromotion1;
    private String PPromotion2;
    private String PPromotion3;
    private double PMax = 0.0000;
    private double PMin = 0.0000;
    private String PBUnit;
    private double PBPack = 0.0000;
    private double PLCost = 0.0000;
    private double PSCost = 0.0000;
    private double PACost = 0.0000;
    private String PLink1;
    private String PLink2;
    private Date PLastUpdate;
    private String PLastTime = "00:00:00";
    private String PUserUpdate;
    private Date PLastSale;
    private String PBarCode;
    private String PActive = "Y";
    private String PSPVat = "N";
    private double PSPVatAmt = 0.00;
    private String POSStk = "0";
    private String MSStk;
    private double PTimeCharge = 0.00;
    private String POrder = "0";
    private String PFoodType = "0";
    private Date PChkDate;
    private int PPackOld = 0;
    private String PDesc2;
    private String PselectItem;
    private double PselectNum;
    private String PShowOption = "N";
    private String PChkTime = "N";
    private double PTime = 0;
    private String PCashCardPay = "N";
    private String PSelectShow = "N";
    private String PEDesc = "";
    public static final String FOOD = "1";
    public static final String DRINK = "2";
    public static final String PRODUCT = "3";
    
    public String PPathName;

    public String getPCode() {
        return PCode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public String getPFix() {
        return PFix;
    }

    public void setPFix(String PFix) {
        this.PFix = PFix;
    }

    public String getPReferent() {
        return PReferent;
    }

    public void setPReferent(String PReferent) {
        this.PReferent = PReferent;
    }

    public String getPAccNo() {
        return PAccNo;
    }

    public void setPAccNo(String PAccNo) {
        this.PAccNo = PAccNo;
    }

    public String getPGroup() {
        return PGroup;
    }

    public void setPGroup(String PGroup) {
        this.PGroup = PGroup;
    }

    public String getPVender() {
        return PVender;
    }

    public void setPVender(String PVender) {
        this.PVender = PVender;
    }

    public String getPType() {
        return PType;
    }

    public void setPType(String PType) {
        this.PType = PType;
    }

    public String getPNormal() {
        return PNormal;
    }

    public void setPNormal(String PNormal) {
        this.PNormal = PNormal;
    }

    public String getPRemark() {
        return PRemark;
    }

    public void setPRemark(String PRemark) {
        this.PRemark = PRemark;
    }

    public String getPDiscount() {
        return PDiscount;
    }

    public void setPDiscount(String PDiscount) {
        this.PDiscount = PDiscount;
    }

    public String getPService() {
        return PService;
    }

    public void setPService(String PService) {
        this.PService = PService;
    }

    public String getPStatus() {
        return PStatus;
    }

    public void setPStatus(String PStatus) {
        this.PStatus = PStatus;
    }

    public String getPStock() {
        return PStock;
    }

    public void setPStock(String PStock) {
        this.PStock = PStock;
    }

    public String getPSet() {
        return PSet;
    }

    public void setPSet(String PSet) {
        this.PSet = PSet;
    }

    public String getPVat() {
        return PVat;
    }

    public void setPVat(String PVat) {
        this.PVat = PVat;
    }

    public String getPDesc() {
        return PDesc;
    }

    public void setPDesc(String PDesc) {
        this.PDesc = PDesc;
    }

    public String getPUnit1() {
        return PUnit1;
    }

    public void setPUnit1(String PUnit1) {
        this.PUnit1 = PUnit1;
    }

    public int getPPack1() {
        return PPack1;
    }

    public void setPPack1(int PPack1) {
        this.PPack1 = PPack1;
    }

    public String getPArea() {
        return PArea;
    }

    public void setPArea(String PArea) {
        this.PArea = PArea;
    }

    public String getPKic() {
        return PKic;
    }

    public void setPKic(String PKic) {
        this.PKic = PKic;
    }

    public double getPPrice11() {
        return PPrice11;
    }

    public void setPPrice11(double PPrice11) {
        this.PPrice11 = PPrice11;
    }

    public double getPPrice12() {
        return PPrice12;
    }

    public void setPPrice12(double PPrice12) {
        this.PPrice12 = PPrice12;
    }

    public double getPPrice13() {
        return PPrice13;
    }

    public void setPPrice13(double PPrice13) {
        this.PPrice13 = PPrice13;
    }

    public double getPPrice14() {
        return PPrice14;
    }

    public void setPPrice14(double PPrice14) {
        this.PPrice14 = PPrice14;
    }

    public double getPPrice15() {
        return PPrice15;
    }

    public void setPPrice15(double PPrice15) {
        this.PPrice15 = PPrice15;
    }

    public String getPPromotion1() {
        return PPromotion1;
    }

    public void setPPromotion1(String PPromotion1) {
        this.PPromotion1 = PPromotion1;
    }

    public String getPPromotion2() {
        return PPromotion2;
    }

    public void setPPromotion2(String PPromotion2) {
        this.PPromotion2 = PPromotion2;
    }

    public String getPPromotion3() {
        return PPromotion3;
    }

    public void setPPromotion3(String PPromotion3) {
        this.PPromotion3 = PPromotion3;
    }

    public double getPMax() {
        return PMax;
    }

    public void setPMax(double PMax) {
        this.PMax = PMax;
    }

    public double getPMin() {
        return PMin;
    }

    public void setPMin(double PMin) {
        this.PMin = PMin;
    }

    public String getPBUnit() {
        return PBUnit;
    }

    public void setPBUnit(String PBUnit) {
        this.PBUnit = PBUnit;
    }

    public double getPBPack() {
        return PBPack;
    }

    public void setPBPack(double PBPack) {
        this.PBPack = PBPack;
    }

    public double getPLCost() {
        return PLCost;
    }

    public void setPLCost(double PLCost) {
        this.PLCost = PLCost;
    }

    public double getPSCost() {
        return PSCost;
    }

    public void setPSCost(double PSCost) {
        this.PSCost = PSCost;
    }

    public double getPACost() {
        return PACost;
    }

    public void setPACost(double PACost) {
        this.PACost = PACost;
    }

    public String getPLink1() {
        return PLink1;
    }

    public void setPLink1(String PLink1) {
        this.PLink1 = PLink1;
    }

    public String getPLink2() {
        return PLink2;
    }

    public void setPLink2(String PLink2) {
        this.PLink2 = PLink2;
    }

    public Date getPLastUpdate() {
        return PLastUpdate;
    }

    public void setPLastUpdate(Date PLastUpdate) {
        this.PLastUpdate = PLastUpdate;
    }

    public String getPLastTime() {
        return PLastTime;
    }

    public void setPLastTime(String PLastTime) {
        this.PLastTime = PLastTime;
    }

    public String getPUserUpdate() {
        return PUserUpdate;
    }

    public void setPUserUpdate(String PUserUpdate) {
        this.PUserUpdate = PUserUpdate;
    }

    public Date getPLastSale() {
        return PLastSale;
    }

    public void setPLastSale(Date PLastSale) {
        this.PLastSale = PLastSale;
    }

    public String getPBarCode() {
        return PBarCode;
    }

    public void setPBarCode(String PBarCode) {
        this.PBarCode = PBarCode;
    }

    public String getPActive() {
        return PActive;
    }

    public void setPActive(String PActive) {
        this.PActive = PActive;
    }

    public String getPSPVat() {
        return PSPVat;
    }

    public void setPSPVat(String PSPVat) {
        this.PSPVat = PSPVat;
    }

    public double getPSPVatAmt() {
        return PSPVatAmt;
    }

    public void setPSPVatAmt(double PSPVatAmt) {
        this.PSPVatAmt = PSPVatAmt;
    }

    public String getPOSStk() {
        return POSStk;
    }

    public void setPOSStk(String POSStk) {
        this.POSStk = POSStk;
    }

    public String getMSStk() {
        return MSStk;
    }

    public void setMSStk(String MSStk) {
        this.MSStk = MSStk;
    }

    public double getPTimeCharge() {
        return PTimeCharge;
    }

    public void setPTimeCharge(double PTimeCharge) {
        this.PTimeCharge = PTimeCharge;
    }

    public String getPOrder() {
        return POrder;
    }

    public void setPOrder(String POrder) {
        this.POrder = POrder;
    }

    public String getPFoodType() {
        return PFoodType;
    }

    public void setPFoodType(String PFoodType) {
        this.PFoodType = PFoodType;
    }

    public Date getPChkDate() {
        return PChkDate;
    }

    public void setPChkDate(Date PChkDate) {
        this.PChkDate = PChkDate;
    }

    public int getPPackOld() {
        return PPackOld;
    }

    public void setPPackOld(int PPackOld) {
        this.PPackOld = PPackOld;
    }

    public String getPDesc2() {
        return PDesc2;
    }

    public void setPDesc2(String PDesc2) {
        this.PDesc2 = PDesc2;
    }

    public String getPselectItem() {
        return PselectItem;
    }

    public void setPselectItem(String PselectItem) {
        this.PselectItem = PselectItem;
    }

    public double getPselectNum() {
        return PselectNum;
    }

    public void setPselectNum(double PselectNum) {
        this.PselectNum = PselectNum;
    }

    public String getPShowOption() {
        return PShowOption;
    }

    public void setPShowOption(String PShowOption) {
        this.PShowOption = PShowOption;
    }

    public String getPChkTime() {
        return PChkTime;
    }

    public void setPChkTime(String PChkTime) {
        this.PChkTime = PChkTime;
    }

    public double getPTime() {
        return PTime;
    }

    public void setPTime(double PTime) {
        this.PTime = PTime;
    }

    public String getPCashCardPay() {
        return PCashCardPay;
    }

    public void setPCashCardPay(String PCashCardPay) {
        this.PCashCardPay = PCashCardPay;
    }

    public String getPSelectShow() {
        return PSelectShow;
    }

    public void setPSelectShow(String PSelectShow) {
        this.PSelectShow = PSelectShow;
    }

    public void setPPathName(String PPathName) {
        this.PPathName = PPathName;
    }
    
    public String getPPathName(){
        return this.PPathName;
    }
    public void setPEDesc(String PEDesc) {
        this.PEDesc = PEDesc;
    }
    
    public String getPEDesc(){
        return this.PEDesc;
    }

}
