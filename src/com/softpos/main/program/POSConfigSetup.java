package com.softpos.main.program;

public class POSConfigSetup {

    private String P_Terminal;
    private double P_Vat = 0.00;
    private double P_Service = 0.00;
    private String P_ServiceType = "N";
    private String P_VatPrn = "Y";
    private String P_VatType;
    private int P_BillCopy = 0;
    private String P_BillCopyOne = "N";
    private String P_DefaultSaleType = "E";
    private String P_EmpUse = "Y";
    private String P_CodePrn = "N";
    private String P_CheckBillBeforCash = "N";
    private String P_PrintDetailOnRecp = "Y";
    private String P_PrintSum = "N";
    private String P_PrintRecpMessage;
    private String P_MemDisc = "00/00/00";
    private String P_MemDiscChk = "N/N/N";
    private String P_MemDiscGet = "Y";
    private String P_MemDiscMax = "00/00/00";
    private String P_FastDisc = "00/00/00";
    private String P_FastDiscChk = "N/N/N";
    private String P_FastDiscGet = "Y";
    private String P_FastDiscMax = "00/00/00";
    private String P_EmpDisc = "00/00/00";
    private String P_EmpDiscChk = "N/N/N";
    private String P_EmpDiscGet = "Y";
    private String P_EmpDiscMax = "00/00/00";
    private String P_TrainDisc = "00/00/00";
    private String P_TrainDiscChk = "N/N/N";
    private String P_TrainDiscGet = "Y";
    private String P_TrainDiscMax = "00/00/00";
    private String P_SubDisc = "00/00/00";
    private String P_SubDiscChk = "N/N/N";
    private String P_SubDiscGet = "Y";
    private String P_SubDiscMax = "00/00/00";
    private String P_DiscBathChk = "N";
    private int P_DiscBathMax = 0;
    private String P_PromotionChk = "Y/Y/Y";
    private String P_SpacialChk = "Y/Y/Y";
    private String P_DiscRound = "F";
    private String P_ServiceRound = "F";
    private String P_SerChkBySaleType = "Y/Y/Y/Y/Y";
    private String P_DiscChkBySaleType = "Y/Y/Y/Y/Y";
    private String P_MemberSystem = "N";
    private String KicSum = "N";
    private String KicCopy = "1";
    private String P_PrintByItemType = "N";
    private String P_PrintTotalSumItemType = "N";
    private String P_PrintTotalSumNormalType = "N";
    private String P_PrintTotalSumGroup = "N";
    private String WTime = "00:04";
    private String LTime = "00:08";
    private String P_PrintProductValue = "N";
    private int P_LimitTime = 0;
    private int P_RefreshTime = 1;
    private String P_SaleDecimal = "N";
    private String P_PayBahtRound = "F";
    private String P_PrintAdjust = "N";
    private String P_PrintNetAdj = "N";
    private String P_ShowKicQue = "N";
    private int P_DefAddTime = 0;
    private String P_BillLang = "T";
    
    public static POSConfigSetup Bean(){
        PosControl pos = new PosControl();
        
        return pos.getData();
    }

    public String getP_Terminal() {
        return P_Terminal;
    }

    public void setP_Terminal(String P_Terminal) {
        this.P_Terminal = P_Terminal;
    }

    public double getP_Vat() {
        return P_Vat;
    }

    public void setP_Vat(double P_Vat) {
        this.P_Vat = P_Vat;
    }

    public double getP_Service() {
        return P_Service;
    }

    public void setP_Service(double P_Service) {
        this.P_Service = P_Service;
    }

    public String getP_ServiceType() {
        return P_ServiceType;
    }

    public void setP_ServiceType(String P_ServiceType) {
        this.P_ServiceType = P_ServiceType;
    }

    public String getP_VatPrn() {
        return P_VatPrn;
    }

    public void setP_VatPrn(String P_VatPrn) {
        this.P_VatPrn = P_VatPrn;
    }

    public String getP_VatType() {
        return P_VatType;
    }

    public void setP_VatType(String P_VatType) {
        this.P_VatType = P_VatType;
    }

    public int getP_BillCopy() {
        return P_BillCopy;
    }

    public void setP_BillCopy(int P_BillCopy) {
        this.P_BillCopy = P_BillCopy;
    }

    public String getP_BillCopyOne() {
        return P_BillCopyOne;
    }

    public void setP_BillCopyOne(String P_BillCopyOne) {
        this.P_BillCopyOne = P_BillCopyOne;
    }

    public String getP_DefaultSaleType() {
        return P_DefaultSaleType;
    }

    public void setP_DefaultSaleType(String P_DefaultSaleType) {
        this.P_DefaultSaleType = P_DefaultSaleType;
    }

    public String getP_EmpUse() {
        return P_EmpUse;
    }

    public void setP_EmpUse(String P_EmpUse) {
        this.P_EmpUse = P_EmpUse;
    }

    public String getP_CodePrn() {
        return P_CodePrn;
    }

    public void setP_CodePrn(String P_CodePrn) {
        this.P_CodePrn = P_CodePrn;
    }

    public String getP_CheckBillBeforCash() {
        return P_CheckBillBeforCash;
    }

    public void setP_CheckBillBeforCash(String P_CheckBillBeforCash) {
        this.P_CheckBillBeforCash = P_CheckBillBeforCash;
    }

    public String getP_PrintDetailOnRecp() {
        return P_PrintDetailOnRecp;
    }

    public void setP_PrintDetailOnRecp(String P_PrintDetailOnRecp) {
        this.P_PrintDetailOnRecp = P_PrintDetailOnRecp;
    }

    public String getP_PrintSum() {
        return P_PrintSum;
    }

    public void setP_PrintSum(String P_PrintSum) {
        this.P_PrintSum = P_PrintSum;
    }

    public String getP_PrintRecpMessage() {
        return P_PrintRecpMessage;
    }

    public void setP_PrintRecpMessage(String P_PrintRecpMessage) {
        this.P_PrintRecpMessage = P_PrintRecpMessage;
    }

    public String getP_MemDisc() {
        return P_MemDisc;
    }

    public void setP_MemDisc(String P_MemDisc) {
        this.P_MemDisc = P_MemDisc;
    }

    public String getP_MemDiscChk() {
        return P_MemDiscChk;
    }

    public void setP_MemDiscChk(String P_MemDiscChk) {
        this.P_MemDiscChk = P_MemDiscChk;
    }

    public String getP_MemDiscGet() {
        return P_MemDiscGet;
    }

    public void setP_MemDiscGet(String P_MemDiscGet) {
        this.P_MemDiscGet = P_MemDiscGet;
    }

    public String getP_MemDiscMax() {
        return P_MemDiscMax;
    }

    public void setP_MemDiscMax(String P_MemDiscMax) {
        this.P_MemDiscMax = P_MemDiscMax;
    }

    public String getP_FastDisc() {
        return P_FastDisc;
    }

    public void setP_FastDisc(String P_FastDisc) {
        this.P_FastDisc = P_FastDisc;
    }

    public String getP_FastDiscChk() {
        return P_FastDiscChk;
    }

    public void setP_FastDiscChk(String P_FastDiscChk) {
        this.P_FastDiscChk = P_FastDiscChk;
    }

    public String getP_FastDiscGet() {
        return P_FastDiscGet;
    }

    public void setP_FastDiscGet(String P_FastDiscGet) {
        this.P_FastDiscGet = P_FastDiscGet;
    }

    public String getP_FastDiscMax() {
        return P_FastDiscMax;
    }

    public void setP_FastDiscMax(String P_FastDiscMax) {
        this.P_FastDiscMax = P_FastDiscMax;
    }

    public String getP_EmpDisc() {
        return P_EmpDisc;
    }

    public void setP_EmpDisc(String P_EmpDisc) {
        this.P_EmpDisc = P_EmpDisc;
    }

    public String getP_EmpDiscChk() {
        return P_EmpDiscChk;
    }

    public void setP_EmpDiscChk(String P_EmpDiscChk) {
        this.P_EmpDiscChk = P_EmpDiscChk;
    }

    public String getP_EmpDiscGet() {
        return P_EmpDiscGet;
    }

    public void setP_EmpDiscGet(String P_EmpDiscGet) {
        this.P_EmpDiscGet = P_EmpDiscGet;
    }

    public String getP_EmpDiscMax() {
        return P_EmpDiscMax;
    }

    public void setP_EmpDiscMax(String P_EmpDiscMax) {
        this.P_EmpDiscMax = P_EmpDiscMax;
    }

    public String getP_TrainDisc() {
        return P_TrainDisc;
    }

    public void setP_TrainDisc(String P_TrainDisc) {
        this.P_TrainDisc = P_TrainDisc;
    }

    public String getP_TrainDiscChk() {
        return P_TrainDiscChk;
    }

    public void setP_TrainDiscChk(String P_TrainDiscChk) {
        this.P_TrainDiscChk = P_TrainDiscChk;
    }

    public String getP_TrainDiscGet() {
        return P_TrainDiscGet;
    }

    public void setP_TrainDiscGet(String P_TrainDiscGet) {
        this.P_TrainDiscGet = P_TrainDiscGet;
    }

    public String getP_TrainDiscMax() {
        return P_TrainDiscMax;
    }

    public void setP_TrainDiscMax(String P_TrainDiscMax) {
        this.P_TrainDiscMax = P_TrainDiscMax;
    }

    public String getP_SubDisc() {
        return P_SubDisc;
    }

    public void setP_SubDisc(String P_SubDisc) {
        this.P_SubDisc = P_SubDisc;
    }

    public String getP_SubDiscChk() {
        return P_SubDiscChk;
    }

    public void setP_SubDiscChk(String P_SubDiscChk) {
        this.P_SubDiscChk = P_SubDiscChk;
    }

    public String getP_SubDiscGet() {
        return P_SubDiscGet;
    }

    public void setP_SubDiscGet(String P_SubDiscGet) {
        this.P_SubDiscGet = P_SubDiscGet;
    }

    public String getP_SubDiscMax() {
        return P_SubDiscMax;
    }

    public void setP_SubDiscMax(String P_SubDiscMax) {
        this.P_SubDiscMax = P_SubDiscMax;
    }

    public String getP_DiscBathChk() {
        return P_DiscBathChk;
    }

    public void setP_DiscBathChk(String P_DiscBathChk) {
        this.P_DiscBathChk = P_DiscBathChk;
    }

    public int getP_DiscBathMax() {
        return P_DiscBathMax;
    }

    public void setP_DiscBathMax(int P_DiscBathMax) {
        this.P_DiscBathMax = P_DiscBathMax;
    }

    public String getP_PromotionChk() {
        return P_PromotionChk;
    }

    public void setP_PromotionChk(String P_PromotionChk) {
        this.P_PromotionChk = P_PromotionChk;
    }

    public String getP_SpacialChk() {
        return P_SpacialChk;
    }

    public void setP_SpacialChk(String P_SpacialChk) {
        this.P_SpacialChk = P_SpacialChk;
    }

    public String getP_DiscRound() {
        return P_DiscRound;
    }

    public void setP_DiscRound(String P_DiscRound) {
        this.P_DiscRound = P_DiscRound;
    }

    public String getP_ServiceRound() {
        return P_ServiceRound;
    }

    public void setP_ServiceRound(String P_ServiceRound) {
        this.P_ServiceRound = P_ServiceRound;
    }

    public String getP_SerChkBySaleType() {
        return P_SerChkBySaleType;
    }

    public void setP_SerChkBySaleType(String P_SerChkBySaleType) {
        this.P_SerChkBySaleType = P_SerChkBySaleType;
    }

    public String getP_DiscChkBySaleType() {
        return P_DiscChkBySaleType;
    }

    public void setP_DiscChkBySaleType(String P_DiscChkBySaleType) {
        this.P_DiscChkBySaleType = P_DiscChkBySaleType;
    }

    public String getP_MemberSystem() {
        return P_MemberSystem;
    }

    public void setP_MemberSystem(String P_MemberSystem) {
        this.P_MemberSystem = P_MemberSystem;
    }

    public String getKicSum() {
        return KicSum;
    }

    public void setKicSum(String KicSum) {
        this.KicSum = KicSum;
    }

    public String getKicCopy() {
        return KicCopy;
    }

    public void setKicCopy(String KicCopy) {
        this.KicCopy = KicCopy;
    }

    public String getP_PrintByItemType() {
        return P_PrintByItemType;
    }

    public void setP_PrintByItemType(String P_PrintByItemType) {
        this.P_PrintByItemType = P_PrintByItemType;
    }

    public String getP_PrintTotalSumItemType() {
        return P_PrintTotalSumItemType;
    }

    public void setP_PrintTotalSumItemType(String P_PrintTotalSumItemType) {
        this.P_PrintTotalSumItemType = P_PrintTotalSumItemType;
    }

    public String getP_PrintTotalSumNormalType() {
        return P_PrintTotalSumNormalType;
    }

    public void setP_PrintTotalSumNormalType(String P_PrintTotalSumNormalType) {
        this.P_PrintTotalSumNormalType = P_PrintTotalSumNormalType;
    }

    public String getP_PrintTotalSumGroup() {
        return P_PrintTotalSumGroup;
    }

    public void setP_PrintTotalSumGroup(String P_PrintTotalSumGroup) {
        this.P_PrintTotalSumGroup = P_PrintTotalSumGroup;
    }

    public String getWTime() {
        return WTime;
    }

    public void setWTime(String WTime) {
        this.WTime = WTime;
    }

    public String getLTime() {
        return LTime;
    }

    public void setLTime(String LTime) {
        this.LTime = LTime;
    }

    public String getP_PrintProductValue() {
        return P_PrintProductValue;
    }

    public void setP_PrintProductValue(String P_PrintProductValue) {
        this.P_PrintProductValue = P_PrintProductValue;
    }

    public int getP_LimitTime() {
        return P_LimitTime;
    }

    public void setP_LimitTime(int P_LimitTime) {
        this.P_LimitTime = P_LimitTime;
    }

    public int getP_RefreshTime() {
        return P_RefreshTime;
    }

    public void setP_RefreshTime(int P_RefreshTime) {
        this.P_RefreshTime = P_RefreshTime;
    }

    public String getP_SaleDecimal() {
        return P_SaleDecimal;
    }

    public void setP_SaleDecimal(String P_SaleDecimal) {
        this.P_SaleDecimal = P_SaleDecimal;
    }

    public String getP_PayBahtRound() {
        return P_PayBahtRound;
    }

    public void setP_PayBahtRound(String P_PayBahtRound) {
        this.P_PayBahtRound = P_PayBahtRound;
    }

    public String getP_PrintAdjust() {
        return P_PrintAdjust;
    }

    public void setP_PrintAdjust(String P_PrintAdjust) {
        this.P_PrintAdjust = P_PrintAdjust;
    }

    public String getP_PrintNetAdj() {
        return P_PrintNetAdj;
    }

    public void setP_PrintNetAdj(String P_PrintNetAdj) {
        this.P_PrintNetAdj = P_PrintNetAdj;
    }

    public String getP_ShowKicQue() {
        return P_ShowKicQue;
    }

    public void setP_ShowKicQue(String P_ShowKicQue) {
        this.P_ShowKicQue = P_ShowKicQue;
    }

    public int getP_DefAddTime() {
        return P_DefAddTime;
    }

    public void setP_DefAddTime(int P_DefAddTime) {
        this.P_DefAddTime = P_DefAddTime;
    }

    public String getP_BillLang() {
        return P_BillLang;
    }

    public void setP_BillLang(String P_BillLang) {
        this.P_BillLang = P_BillLang;
    }
    
    
}
