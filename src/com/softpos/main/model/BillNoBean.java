package com.softpos.main.model;

import java.util.Date;

public class BillNoBean {
    private String B_Refno = "0";
     private double B_CuponDiscAmt;
     private String B_Ontime;
     private String B_LoginTime;
     private Date B_OnDate;
     private Date B_PostDate;
     private String B_Table;
     private String B_MacNo;
     private String B_Cashier;
     private int B_Cust = 0;
     private String B_ETD;
     private double B_Total = 0.00;
     private double B_Food = 0.00;
     private double B_Drink = 0.00;
     private double B_Product = 0.00;
     private double B_Service = 0.00;
     private double B_ServiceAmt = 0.00;
     private double B_ItemDiscAmt = 0.00;
     private String B_FastDisc;
     private double B_FastDiscAmt = 0.00;
     private String B_EmpDisc;
     private double B_EmpDiscAmt = 0.00;
     private String B_TrainDisc;
     private double B_TrainDiscAmt = 0.00;
     private String B_MemDisc;
     private double B_MemDiscAmt = 0.00;
     private String B_SubDisc;
     private double B_SubDiscAmt = 0.00;
     private double B_SubDiscBath = 0.00;
     private double B_ProDiscAmt = 0.00;
     private double B_SpaDiscAmt = 0.00;
     private double B_AdjAmt = 0.00;
     private double B_PreDisAmt = 0.00;
     private double B_NetTotal = 0.00;
     private double B_NetFood = 0.00;
     private double B_NetDrink = 0.00;
     private double B_NetProduct = 0.00;
     private double B_NetVat = 0.00;
     private double B_NetNonVat = 0.00;
     private double B_Vat = 0.00;
     private double B_PayAmt = 0.00;
     private double B_Cash = 0.00;
     private double B_GiftVoucher = 0.00;
     private double B_Earnest = 0.00;
     private double B_Ton = 0.00;
     private String B_CrCode1;
     private String B_CardNo1;
     private String B_AppCode1;
     private double B_CrCharge1 = 0.00;
     private double B_CrChargeAmt1 = 0.00;
     private double B_CrAmt1 = 0.00;
     private String B_AccrCode;
     private double B_AccrAmt = 0.00;
     private int B_AccrCr = 0;
     private String B_MemCode;
     private String B_MemName;
     private Date B_MemBegin;
     private Date B_MemEnd;
     private double B_MemCurSum = 0.00;
     private String B_Void = "-";
     private String B_VoidUser;
     private String B_VoidTime;
     private int B_BillCopy = 0;
     private int B_PrnCnt = 0;
     private String B_PrnTime1;
     private String B_PrnTime2;
     private String B_InvNo;
     private String B_InvType;
     private String B_Bran;
     private String B_BranName;
     private String B_Tel;
     private String B_RecTime;
     private String MStamp;
     private String MScore;
     private String CurStamp;
     private String StampRate;
     private String B_ChkBill = "N";
     private String B_ChkBillTime = "00:00:00";
     private String B_CashTime = "00:00:00";
     private String B_WaitTime = "00:00:00";
     private double B_SumScore = 0;
     private String B_CrBank;
     private double B_CrCardAmt = 0.00;
     private double B_CrCurPoint = 0.00;
     private double B_CrSumPoint = 0.00;
//     private double B_Entertain;
     private double B_Entertain1 = 0.00;
     private double B_VoucherDiscAmt = 0.00;
     private double B_VoucherOver = 0.00;
     private double B_NetDiff = 0.00;
     private double B_SumSetDiscAmt = 0.00;
     private double B_DetailFood = 0.00;
     private double B_DetailDrink = 0.00;
     private double B_DetailProduct = 0.00;
     private String B_KicQue;
     private String B_ROUNDCLOSE = "N";
     private String Employ = "";
     private String B_CuponName = "";
     
     
//    public double getB_Entertain() {
//        return B_Entertain;
//    }
//
//    public void setB_Entertain(double B_Entertain) {
//        this.B_Entertain = B_Entertain;
//    }
    public double getB_Entertain1() {
        return B_Entertain1;
    }

    public void setB_Entertain1(double B_Entertain1) {
        this.B_Entertain1 = B_Entertain1;
    }

    public double getB_VoucherDiscAmt() {
        return B_VoucherDiscAmt;
    }

    public void setB_VoucherDiscAmt(double B_VoucherDiscAmt) {
        this.B_VoucherDiscAmt = B_VoucherDiscAmt;
    }

    public double getB_VoucherOver() {
        return B_VoucherOver;
    }

    public void setB_VoucherOver(double B_VoucherOver) {
        this.B_VoucherOver = B_VoucherOver;
    }

    public double getB_SumSetDiscAmt() {
        return B_SumSetDiscAmt;
    }

    public void setB_SumSetDiscAmt(double B_SumSetDiscAmt) {
        this.B_SumSetDiscAmt = B_SumSetDiscAmt;
    }

    public double getB_DetailFood() {
        return B_DetailFood;
    }

    public void setB_DetailFood(double B_DetailFood) {
        this.B_DetailFood = B_DetailFood;
    }

    public double getB_DetailDrink() {
        return B_DetailDrink;
    }

    public void setB_DetailDrink(double B_DetailDrink) {
        this.B_DetailDrink = B_DetailDrink;
    }

    public double getB_DetailProduct() {
        return B_DetailProduct;
    }

    public void setB_DetailProduct(double B_DetailProduct) {
        this.B_DetailProduct = B_DetailProduct;
    }

    public String getB_ROUNDCLOSE() {
        return B_ROUNDCLOSE;
    }

    public void setB_ROUNDCLOSE(String B_ROUNDCLOSE) {
        this.B_ROUNDCLOSE = B_ROUNDCLOSE;
    }

    public String getB_Refno() {
        return B_Refno;
    }

    public void setB_Refno(String B_Refno) {
        this.B_Refno = B_Refno;
    }

    public double getB_CuponDiscAmt() {
        return B_CuponDiscAmt;
    }

    public void setB_CuponDiscAmt(double B_CuponDiscAmt) {
        this.B_CuponDiscAmt = B_CuponDiscAmt;
    }

    public String getB_Ontime() {
        return B_Ontime;
    }

    public void setB_Ontime(String B_Ontime) {
        this.B_Ontime = B_Ontime;
    }

    public String getB_LoginTime() {
        return B_LoginTime;
    }

    public void setB_LoginTime(String B_LoginTime) {
        this.B_LoginTime = B_LoginTime;
    }

    public Date getB_OnDate() {
        return B_OnDate;
    }

    public void setB_OnDate(Date B_OnDate) {
        this.B_OnDate = B_OnDate;
    }

    public Date getB_PostDate() {
        return B_PostDate;
    }

    public void setB_PostDate(Date B_PostDate) {
        this.B_PostDate = B_PostDate;
    }

    public String getB_Table() {
        return B_Table;
    }

    public void setB_Table(String B_Table) {
        this.B_Table = B_Table;
    }

    public String getB_MacNo() {
        return B_MacNo;
    }

    public void setB_MacNo(String B_MacNo) {
        this.B_MacNo = B_MacNo;
    }

    public String getB_Cashier() {
        return B_Cashier;
    }

    public void setB_Cashier(String B_Cashier) {
        this.B_Cashier = B_Cashier;
    }

    public int getB_Cust() {
        return B_Cust;
    }

    public void setB_Cust(int B_Cust) {
        this.B_Cust = B_Cust;
    }

    public String getB_ETD() {
        return B_ETD;
    }

    public void setB_ETD(String B_ETD) {
        this.B_ETD = B_ETD;
    }

    public double getB_Total() {
        return B_Total;
    }

    public void setB_Total(double B_Total) {
        this.B_Total = B_Total;
    }

    public double getB_Food() {
        return B_Food;
    }

    public void setB_Food(double B_Food) {
        this.B_Food = B_Food;
    }

    public double getB_Drink() {
        return B_Drink;
    }

    public void setB_Drink(double B_Drink) {
        this.B_Drink = B_Drink;
    }

    public double getB_Product() {
        return B_Product;
    }

    public void setB_Product(double B_Product) {
        this.B_Product = B_Product;
    }

    public double getB_Service() {
        return B_Service;
    }

    public void setB_Service(double B_Service) {
        this.B_Service = B_Service;
    }

    public double getB_ServiceAmt() {
        return B_ServiceAmt;
    }

    public void setB_ServiceAmt(double B_ServiceAmt) {
        this.B_ServiceAmt = B_ServiceAmt;
    }

    public double getB_ItemDiscAmt() {
        return B_ItemDiscAmt;
    }

    public void setB_ItemDiscAmt(double B_ItemDiscAmt) {
        this.B_ItemDiscAmt = B_ItemDiscAmt;
    }

    public String getB_FastDisc() {
        return B_FastDisc;
    }

    public void setB_FastDisc(String B_FastDisc) {
        this.B_FastDisc = B_FastDisc;
    }

    public double getB_FastDiscAmt() {
        return B_FastDiscAmt;
    }

    public void setB_FastDiscAmt(double B_FastDiscAmt) {
        this.B_FastDiscAmt = B_FastDiscAmt;
    }

    public String getB_EmpDisc() {
        return B_EmpDisc;
    }

    public void setB_EmpDisc(String B_EmpDisc) {
        this.B_EmpDisc = B_EmpDisc;
    }

    public double getB_EmpDiscAmt() {
        return B_EmpDiscAmt;
    }

    public void setB_EmpDiscAmt(double B_EmpDiscAmt) {
        this.B_EmpDiscAmt = B_EmpDiscAmt;
    }

    public String getB_TrainDisc() {
        return B_TrainDisc;
    }

    public void setB_TrainDisc(String B_TrainDisc) {
        this.B_TrainDisc = B_TrainDisc;
    }

    public double getB_TrainDiscAmt() {
        return B_TrainDiscAmt;
    }

    public void setB_TrainDiscAmt(double B_TrainDiscAmt) {
        this.B_TrainDiscAmt = B_TrainDiscAmt;
    }

    public String getB_MemDisc() {
        return B_MemDisc;
    }

    public void setB_MemDisc(String B_MemDisc) {
        this.B_MemDisc = B_MemDisc;
    }

    public double getB_MemDiscAmt() {
        return B_MemDiscAmt;
    }

    public void setB_MemDiscAmt(double B_MemDiscAmt) {
        this.B_MemDiscAmt = B_MemDiscAmt;
    }

    public String getB_SubDisc() {
        return B_SubDisc;
    }

    public void setB_SubDisc(String B_SubDisc) {
        this.B_SubDisc = B_SubDisc;
    }

    public double getB_SubDiscAmt() {
        return B_SubDiscAmt;
    }

    public void setB_SubDiscAmt(double B_SubDiscAmt) {
        this.B_SubDiscAmt = B_SubDiscAmt;
    }

    public double getB_SubDiscBath() {
        return B_SubDiscBath;
    }

    public void setB_SubDiscBath(double B_SubDiscBath) {
        this.B_SubDiscBath = B_SubDiscBath;
    }

    public double getB_ProDiscAmt() {
        return B_ProDiscAmt;
    }

    public void setB_ProDiscAmt(double B_ProDiscAmt) {
        this.B_ProDiscAmt = B_ProDiscAmt;
    }

    public double getB_SpaDiscAmt() {
        return B_SpaDiscAmt;
    }

    public void setB_SpaDiscAmt(double B_SpaDiscAmt) {
        this.B_SpaDiscAmt = B_SpaDiscAmt;
    }

    public double getB_AdjAmt() {
        return B_AdjAmt;
    }

    public void setB_AdjAmt(double B_AdjAmt) {
        this.B_AdjAmt = B_AdjAmt;
    }

    public double getB_PreDisAmt() {
        return B_PreDisAmt;
    }

    public void setB_PreDisAmt(double B_PreDisAmt) {
        this.B_PreDisAmt = B_PreDisAmt;
    }

    public double getB_NetTotal() {
        return B_NetTotal;
    }

    public void setB_NetTotal(double B_NetTotal) {
        this.B_NetTotal = B_NetTotal;
    }

    public double getB_NetFood() {
        return B_NetFood;
    }

    public void setB_NetFood(double B_NetFood) {
        this.B_NetFood = B_NetFood;
    }

    public double getB_NetDrink() {
        return B_NetDrink;
    }

    public void setB_NetDrink(double B_NetDrink) {
        this.B_NetDrink = B_NetDrink;
    }

    public double getB_NetProduct() {
        return B_NetProduct;
    }

    public void setB_NetProduct(double B_NetProduct) {
        this.B_NetProduct = B_NetProduct;
    }

    public double getB_NetVat() {
        return B_NetVat;
    }

    public void setB_NetVat(double B_NetVat) {
        this.B_NetVat = B_NetVat;
    }

    public double getB_NetNonVat() {
        return B_NetNonVat;
    }

    public void setB_NetNonVat(double B_NetNonVat) {
        this.B_NetNonVat = B_NetNonVat;
    }

    public double getB_Vat() {
        return B_Vat;
    }

    public void setB_Vat(double B_Vat) {
        this.B_Vat = B_Vat;
    }

    public double getB_PayAmt() {
        return B_PayAmt;
    }

    public void setB_PayAmt(double B_PayAmt) {
        this.B_PayAmt = B_PayAmt;
    }

    public double getB_Cash() {
        return B_Cash;
    }

    public void setB_Cash(double B_Cash) {
        this.B_Cash = B_Cash;
    }

    public double getB_GiftVoucher() {
        return B_GiftVoucher;
    }

    public void setB_GiftVoucher(double B_GiftVoucher) {
        this.B_GiftVoucher = B_GiftVoucher;
    }

    public double getB_Earnest() {
        return B_Earnest;
    }

    public void setB_Earnest(double B_Earnest) {
        this.B_Earnest = B_Earnest;
    }

    public double getB_Ton() {
        return B_Ton;
    }

    public void setB_Ton(double B_Ton) {
        this.B_Ton = B_Ton;
    }

    public String getB_CrCode1() {
        return B_CrCode1;
    }

    public void setB_CrCode1(String B_CrCode1) {
        this.B_CrCode1 = B_CrCode1;
    }

    public String getB_CardNo1() {
        return B_CardNo1;
    }

    public void setB_CardNo1(String B_CardNo1) {
        this.B_CardNo1 = B_CardNo1;
    }

    public String getB_AppCode1() {
        return B_AppCode1;
    }

    public void setB_AppCode1(String B_AppCode1) {
        this.B_AppCode1 = B_AppCode1;
    }

    public double getB_CrCharge1() {
        return B_CrCharge1;
    }

    public void setB_CrCharge1(double B_CrCharge1) {
        this.B_CrCharge1 = B_CrCharge1;
    }

    public double getB_CrChargeAmt1() {
        return B_CrChargeAmt1;
    }

    public void setB_CrChargeAmt1(double B_CrChargeAmt1) {
        this.B_CrChargeAmt1 = B_CrChargeAmt1;
    }

    public double getB_CrAmt1() {
        return B_CrAmt1;
    }

    public void setB_CrAmt1(double B_CrAmt1) {
        this.B_CrAmt1 = B_CrAmt1;
    }

    public String getB_AccrCode() {
        return B_AccrCode;
    }

    public void setB_AccrCode(String B_AccrCode) {
        this.B_AccrCode = B_AccrCode;
    }

    public double getB_AccrAmt() {
        return B_AccrAmt;
    }

    public void setB_AccrAmt(double B_AccrAmt) {
        this.B_AccrAmt = B_AccrAmt;
    }

    public int getB_AccrCr() {
        return B_AccrCr;
    }

    public void setB_AccrCr(int B_AccrCr) {
        this.B_AccrCr = B_AccrCr;
    }

    public String getB_MemCode() {
        return B_MemCode;
    }

    public void setB_MemCode(String B_MemCode) {
        this.B_MemCode = B_MemCode;
    }

    public String getB_MemName() {
        return B_MemName;
    }

    public void setB_MemName(String B_MemName) {
        this.B_MemName = B_MemName;
    }

    public Date getB_MemBegin() {
        return B_MemBegin;
    }

    public void setB_MemBegin(Date B_MemBegin) {
        this.B_MemBegin = B_MemBegin;
    }

    public Date getB_MemEnd() {
        return B_MemEnd;
    }

    public void setB_MemEnd(Date B_MemEnd) {
        this.B_MemEnd = B_MemEnd;
    }

    public double getB_MemCurSum() {
        return B_MemCurSum;
    }

    public void setB_MemCurSum(double B_MemCurSum) {
        this.B_MemCurSum = B_MemCurSum;
    }

    public String getB_Void() {
        return B_Void;
    }

    public void setB_Void(String B_Void) {
        this.B_Void = B_Void;
    }

    public String getB_VoidUser() {
        return B_VoidUser;
    }

    public void setB_VoidUser(String B_VoidUser) {
        this.B_VoidUser = B_VoidUser;
    }

    public String getB_VoidTime() {
        return B_VoidTime;
    }

    public void setB_VoidTime(String B_VoidTime) {
        this.B_VoidTime = B_VoidTime;
    }

    public int getB_BillCopy() {
        return B_BillCopy;
    }

    public void setB_BillCopy(int B_BillCopy) {
        this.B_BillCopy = B_BillCopy;
    }

    public int getB_PrnCnt() {
        return B_PrnCnt;
    }

    public void setB_PrnCnt(int B_PrnCnt) {
        this.B_PrnCnt = B_PrnCnt;
    }

    public String getB_PrnTime1() {
        return B_PrnTime1;
    }

    public void setB_PrnTime1(String B_PrnTime1) {
        this.B_PrnTime1 = B_PrnTime1;
    }

    public String getB_PrnTime2() {
        return B_PrnTime2;
    }

    public void setB_PrnTime2(String B_PrnTime2) {
        this.B_PrnTime2 = B_PrnTime2;
    }

    public String getB_InvNo() {
        return B_InvNo;
    }

    public void setB_InvNo(String B_InvNo) {
        this.B_InvNo = B_InvNo;
    }

    public String getB_InvType() {
        return B_InvType;
    }

    public void setB_InvType(String B_InvType) {
        this.B_InvType = B_InvType;
    }

    public String getB_Bran() {
        return B_Bran;
    }

    public void setB_Bran(String B_Bran) {
        this.B_Bran = B_Bran;
    }

    public String getB_BranName() {
        return B_BranName;
    }

    public void setB_BranName(String B_BranName) {
        this.B_BranName = B_BranName;
    }

    public String getB_Tel() {
        return B_Tel;
    }

    public void setB_Tel(String B_Tel) {
        this.B_Tel = B_Tel;
    }

    public String getB_RecTime() {
        return B_RecTime;
    }

    public void setB_RecTime(String B_RecTime) {
        this.B_RecTime = B_RecTime;
    }

    public String getMStamp() {
        return MStamp;
    }

    public void setMStamp(String MStamp) {
        this.MStamp = MStamp;
    }

    public String getMScore() {
        return MScore;
    }

    public void setMScore(String MScore) {
        this.MScore = MScore;
    }

    public String getCurStamp() {
        return CurStamp;
    }

    public void setCurStamp(String CurStamp) {
        this.CurStamp = CurStamp;
    }

    public String getStampRate() {
        return StampRate;
    }

    public void setStampRate(String StampRate) {
        this.StampRate = StampRate;
    }

    public String getB_ChkBill() {
        return B_ChkBill;
    }

    public void setB_ChkBill(String B_ChkBill) {
        this.B_ChkBill = B_ChkBill;
    }

    public String getB_ChkBillTime() {
        return B_ChkBillTime;
    }

    public void setB_ChkBillTime(String B_ChkBillTime) {
        this.B_ChkBillTime = B_ChkBillTime;
    }

    public String getB_CashTime() {
        return B_CashTime;
    }

    public void setB_CashTime(String B_CashTime) {
        this.B_CashTime = B_CashTime;
    }

    public String getB_WaitTime() {
        return B_WaitTime;
    }

    public void setB_WaitTime(String B_WaitTime) {
        this.B_WaitTime = B_WaitTime;
    }

    public double getB_SumScore() {
        return B_SumScore;
    }

    public void setB_SumScore(double B_SumScore) {
        this.B_SumScore = B_SumScore;
    }

    public String getB_CrBank() {
        return B_CrBank;
    }

    public void setB_CrBank(String B_CrBank) {
        this.B_CrBank = B_CrBank;
    }

    public double getB_CrCardAmt() {
        return B_CrCardAmt;
    }

    public void setB_CrCardAmt(double B_CrCardAmt) {
        this.B_CrCardAmt = B_CrCardAmt;
    }

    public double getB_CrCurPoint() {
        return B_CrCurPoint;
    }

    public void setB_CrCurPoint(double B_CrCurPoint) {
        this.B_CrCurPoint = B_CrCurPoint;
    }

    public double getB_CrSumPoint() {
        return B_CrSumPoint;
    }

    public void setB_CrSumPoint(double B_CrSumPoint) {
        this.B_CrSumPoint = B_CrSumPoint;
    }

    public String getB_KicQue() {
        return B_KicQue;
    }

    public void setB_KicQue(String B_KicQue) {
        this.B_KicQue = B_KicQue;
    }

    public double getB_NetDiff() {
        return B_NetDiff;
    }

    public void setB_NetDiff(double B_NetDiff) {
        this.B_NetDiff = B_NetDiff;
    }
//    public double getB_NetDiff() {
//        return B_NetDiff;
//    }
//
//    public void setB_NetDiff(double B_getB_CashPayMent) {
//        this.B_NetDiff = B_NetDiff;
//    }
    public String get_Employ() {
        return Employ;
    }

    public void set_Employ(String Employ) {
        this.Employ = Employ;
    }
    public String get_B_CuponName() {
        return B_CuponName;
    }

    public void set_B_CuponName(String B_CuponName) {
        this.B_CuponName = B_CuponName;
    }
     
}
