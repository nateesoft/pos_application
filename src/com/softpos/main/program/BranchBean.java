package com.softpos.main.program;

import java.util.Date;

public class BranchBean {

    private String Code = "XXX";
    private String Name = "New Branch";
    private String AddressNo;
    private String Locality;
    private String SubProvince;
    private String Province;
    private String Post;
    private String Tel_No;
    private String Fax_No;
    private String E_Mail;
    private String Manager;
    private String Location_Area;
    private double Ser_Area = 0.00;
    private double Cou_Area = 0.00;
    private double Kic_Area = 0.00;
    private double Tot_Area = 0.00;
    private double Cost = 0.00;
    private double Charge = 0.00;
    private String FlageCost = "N";
    private double Gp = 0.00;
    private String FlageGp = "N";
    private String Remark;
    private double ArBillNo = 1;
    private double EarneatBillNo = 1;
    private double ReturnBillNo = 1;
    private Date PrintAutoSumDate;
    private String SaveOrder = "N";
    private String SaveOrderCopy = "N";
    private String SaveOrderChk = "N";
    private String KIC1 = "N";
    private String KIC2 = "N";
    private String KIC3 = "N";
    private String KIC4 = "N";
    private String KIC5 = "N";
    private String KIC6 = "N";
    private String KIC7 = "N";
    private String KIC8 = "N";
    private String KIC9 = "N";
    private String SmartCard = "N";
    private String GetFile;
    private String RetFile;
    private String PointFile;
    private int CntLoop = 1;
    private double InvNo = 1;
    private double InvCashNo = 1;
    private double InvCash = 1;
    private String InvActive = "Y";
    private String CreditAct;
    private String PromotionGP;
    private int LockTime = 0;
    private int KicItemNo = 0;
    private String PT1;
    private String PT2;
    private String PT3;
    private String PT4;
    private String PT5;
    private int PONO = 1;
    private String PrintKicForm = "1";
    private String PrintInvForm = "1";
    private String PSelectStk = "P";
    private String PStkChk = "N";
    private String PMinStkChk = "N";
    private double RoundUpTime = 0;
    private String GiftStatusChk = "N";
    private String KICCopy1 = "6";
    private String KICCopy2 = "6";
    private String KICCopy3 = "6";
    private String KICCopy4 = "6";
    private String KICCopy5 = "6";
    private String KICCopy6 = "6";
    private String KICCopy7 = "6";
    private String KICCopy8 = "6";
    private String KICCopy9 = "6";
    private String KICChk1 = "N";
    private String KICChk2 = "N";
    private String KICChk3 = "N";
    private String KICChk4 = "N";
    private String KICChk5 = "N";
    private String KICChk6 = "N";
    private String KICChk7 = "N";
    private String KICChk8 = "N";
    private String KICChk9 = "N";
    private String UpdateBranchPoint = "Y";
    private String KicName1;
    private String KicName2;
    private String KicName3;
    private String KicName4;
    private String KicName5;
    private String KicName6;
    private String KicName7;
    private String KicName8;
    private String KicName9;
    private String KicPrintOnReceipt1 = "N";
    private String KicPrintOnReceipt2 = "N";
    private String KicPrintOnReceipt3 = "N";
    private String KicPrintOnReceipt4 = "N";
    private String KicPrintOnReceipt5 = "N";
    private String KicPrintOnReceipt6 = "N";
    private String KicPrintOnReceipt7 = "N";
    private String KicPrintOnReceipt8 = "N";
    private String KicPrintOnReceipt9 = "N";
    private int KicQue = 0;
    private String Kic10 = "N";
    private String Kic11 = "N";
    private String Kic12 = "N";
    private String Kic13 = "N";
    private String Kic14 = "N";
    private String Kic15 = "N";
    private String Kic16 = "N";
    private String Kic17 = "N";
    private String Kic18 = "N";
    private String Kic19 = "N";
    private String Kic20 = "N";
    private String KicCopy10 = "1";
    private String KicCopy11 = "1";
    private String KicCopy12 = "1";
    private String KicCopy13 = "1";
    private String KicCopy14 = "1";
    private String KicCopy15 = "1";
    private String KicCopy16 = "1";
    private String KicCopy17 = "1";
    private String KicCopy18 = "1";
    private String KicCopy19 = "1";
    private String KicCopy20 = "1";
    private String KicChk10 = "N";
    private String KicChk11 = "N";
    private String KicChk12 = "N";
    private String KicChk13 = "N";
    private String KicChk14 = "N";
    private String KicChk15 = "N";
    private String KicChk16 = "N";
    private String KicChk17 = "N";
    private String KicChk18 = "N";
    private String KicChk19 = "N";
    private String KicChk20 = "N";
    
    public static BranchBean Bean(){
        return new BranchControl().getData();
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddressNo() {
        return AddressNo;
    }

    public void setAddressNo(String AddressNo) {
        this.AddressNo = AddressNo;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String Locality) {
        this.Locality = Locality;
    }

    public String getSubProvince() {
        return SubProvince;
    }

    public void setSubProvince(String SubProvince) {
        this.SubProvince = SubProvince;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String Post) {
        this.Post = Post;
    }

    public String getTel_No() {
        return Tel_No;
    }

    public void setTel_No(String Tel_No) {
        this.Tel_No = Tel_No;
    }

    public String getFax_No() {
        return Fax_No;
    }

    public void setFax_No(String Fax_No) {
        this.Fax_No = Fax_No;
    }

    public String getE_Mail() {
        return E_Mail;
    }

    public void setE_Mail(String E_Mail) {
        this.E_Mail = E_Mail;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String Manager) {
        this.Manager = Manager;
    }

    public String getLocation_Area() {
        return Location_Area;
    }

    public void setLocation_Area(String Location_Area) {
        this.Location_Area = Location_Area;
    }

    public double getSer_Area() {
        return Ser_Area;
    }

    public void setSer_Area(double Ser_Area) {
        this.Ser_Area = Ser_Area;
    }

    public double getCou_Area() {
        return Cou_Area;
    }

    public void setCou_Area(double Cou_Area) {
        this.Cou_Area = Cou_Area;
    }

    public double getKic_Area() {
        return Kic_Area;
    }

    public void setKic_Area(double Kic_Area) {
        this.Kic_Area = Kic_Area;
    }

    public double getTot_Area() {
        return Tot_Area;
    }

    public void setTot_Area(double Tot_Area) {
        this.Tot_Area = Tot_Area;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    public double getCharge() {
        return Charge;
    }

    public void setCharge(double Charge) {
        this.Charge = Charge;
    }

    public String getFlageCost() {
        return FlageCost;
    }

    public void setFlageCost(String FlageCost) {
        this.FlageCost = FlageCost;
    }

    public double getGp() {
        return Gp;
    }

    public void setGp(double Gp) {
        this.Gp = Gp;
    }

    public String getFlageGp() {
        return FlageGp;
    }

    public void setFlageGp(String FlageGp) {
        this.FlageGp = FlageGp;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public double getArBillNo() {
        return ArBillNo;
    }

    public void setArBillNo(double ArBillNo) {
        this.ArBillNo = ArBillNo;
    }

    public double getEarneatBillNo() {
        return EarneatBillNo;
    }

    public void setEarneatBillNo(double EarneatBillNo) {
        this.EarneatBillNo = EarneatBillNo;
    }

    public double getReturnBillNo() {
        return ReturnBillNo;
    }

    public void setReturnBillNo(double ReturnBillNo) {
        this.ReturnBillNo = ReturnBillNo;
    }

    public Date getPrintAutoSumDate() {
        return PrintAutoSumDate;
    }

    public void setPrintAutoSumDate(Date PrintAutoSumDate) {
        this.PrintAutoSumDate = PrintAutoSumDate;
    }

    public String getSaveOrder() {
        return SaveOrder;
    }

    public void setSaveOrder(String SaveOrder) {
        this.SaveOrder = SaveOrder;
    }

    public String getSaveOrderCopy() {
        return SaveOrderCopy;
    }

    public void setSaveOrderCopy(String SaveOrderCopy) {
        this.SaveOrderCopy = SaveOrderCopy;
    }

    public String getSaveOrderChk() {
        return SaveOrderChk;
    }

    public void setSaveOrderChk(String SaveOrderChk) {
        this.SaveOrderChk = SaveOrderChk;
    }

    public String getKIC1() {
        return KIC1;
    }

    public void setKIC1(String KIC1) {
        this.KIC1 = KIC1;
    }

    public String getKIC2() {
        return KIC2;
    }

    public void setKIC2(String KIC2) {
        this.KIC2 = KIC2;
    }

    public String getKIC3() {
        return KIC3;
    }

    public void setKIC3(String KIC3) {
        this.KIC3 = KIC3;
    }

    public String getKIC4() {
        return KIC4;
    }

    public void setKIC4(String KIC4) {
        this.KIC4 = KIC4;
    }

    public String getKIC5() {
        return KIC5;
    }

    public void setKIC5(String KIC5) {
        this.KIC5 = KIC5;
    }

    public String getKIC6() {
        return KIC6;
    }

    public void setKIC6(String KIC6) {
        this.KIC6 = KIC6;
    }

    public String getKIC7() {
        return KIC7;
    }

    public void setKIC7(String KIC7) {
        this.KIC7 = KIC7;
    }

    public String getKIC8() {
        return KIC8;
    }

    public void setKIC8(String KIC8) {
        this.KIC8 = KIC8;
    }

    public String getKIC9() {
        return KIC9;
    }

    public void setKIC9(String KIC9) {
        this.KIC9 = KIC9;
    }

    public String getSmartCard() {
        return SmartCard;
    }

    public void setSmartCard(String SmartCard) {
        this.SmartCard = SmartCard;
    }

    public String getGetFile() {
        return GetFile;
    }

    public void setGetFile(String GetFile) {
        this.GetFile = GetFile;
    }

    public String getRetFile() {
        return RetFile;
    }

    public void setRetFile(String RetFile) {
        this.RetFile = RetFile;
    }

    public String getPointFile() {
        return PointFile;
    }

    public void setPointFile(String PointFile) {
        this.PointFile = PointFile;
    }

    public int getCntLoop() {
        return CntLoop;
    }

    public void setCntLoop(int CntLoop) {
        this.CntLoop = CntLoop;
    }

    public double getInvNo() {
        return InvNo;
    }

    public void setInvNo(double InvNo) {
        this.InvNo = InvNo;
    }

    public double getInvCashNo() {
        return InvCashNo;
    }

    public void setInvCashNo(double InvCashNo) {
        this.InvCashNo = InvCashNo;
    }

    public double getInvCash() {
        return InvCash;
    }

    public void setInvCash(double InvCash) {
        this.InvCash = InvCash;
    }

    public String getInvActive() {
        return InvActive;
    }

    public void setInvActive(String InvActive) {
        this.InvActive = InvActive;
    }

    public String getCreditAct() {
        return CreditAct;
    }

    public void setCreditAct(String CreditAct) {
        this.CreditAct = CreditAct;
    }

    public String getPromotionGP() {
        return PromotionGP;
    }

    public void setPromotionGP(String PromotionGP) {
        this.PromotionGP = PromotionGP;
    }

    public int getLockTime() {
        return LockTime;
    }

    public void setLockTime(int LockTime) {
        this.LockTime = LockTime;
    }

    public int getKicItemNo() {
        return KicItemNo;
    }

    public void setKicItemNo(int KicItemNo) {
        this.KicItemNo = KicItemNo;
    }

    public String getPT1() {
        return PT1;
    }

    public void setPT1(String PT1) {
        this.PT1 = PT1;
    }

    public String getPT2() {
        return PT2;
    }

    public void setPT2(String PT2) {
        this.PT2 = PT2;
    }

    public String getPT3() {
        return PT3;
    }

    public void setPT3(String PT3) {
        this.PT3 = PT3;
    }

    public String getPT4() {
        return PT4;
    }

    public void setPT4(String PT4) {
        this.PT4 = PT4;
    }

    public String getPT5() {
        return PT5;
    }

    public void setPT5(String PT5) {
        this.PT5 = PT5;
    }

    public int getPONO() {
        return PONO;
    }

    public void setPONO(int PONO) {
        this.PONO = PONO;
    }

    public String getPrintKicForm() {
        return PrintKicForm;
    }

    public void setPrintKicForm(String PrintKicForm) {
        this.PrintKicForm = PrintKicForm;
    }

    public String getPrintInvForm() {
        return PrintInvForm;
    }

    public void setPrintInvForm(String PrintInvForm) {
        this.PrintInvForm = PrintInvForm;
    }

    public String getPSelectStk() {
        return PSelectStk;
    }

    public void setPSelectStk(String PSelectStk) {
        this.PSelectStk = PSelectStk;
    }

    public String getPStkChk() {
        return PStkChk;
    }

    public void setPStkChk(String PStkChk) {
        this.PStkChk = PStkChk;
    }

    public String getPMinStkChk() {
        return PMinStkChk;
    }

    public void setPMinStkChk(String PMinStkChk) {
        this.PMinStkChk = PMinStkChk;
    }

    public double getRoundUpTime() {
        return RoundUpTime;
    }

    public void setRoundUpTime(double RoundUpTime) {
        this.RoundUpTime = RoundUpTime;
    }

    public String getGiftStatusChk() {
        return GiftStatusChk;
    }

    public void setGiftStatusChk(String GiftStatusChk) {
        this.GiftStatusChk = GiftStatusChk;
    }

    public String getKICCopy1() {
        return KICCopy1;
    }

    public void setKICCopy1(String KICCopy1) {
        this.KICCopy1 = KICCopy1;
    }

    public String getKICCopy2() {
        return KICCopy2;
    }

    public void setKICCopy2(String KICCopy2) {
        this.KICCopy2 = KICCopy2;
    }

    public String getKICCopy3() {
        return KICCopy3;
    }

    public void setKICCopy3(String KICCopy3) {
        this.KICCopy3 = KICCopy3;
    }

    public String getKICCopy4() {
        return KICCopy4;
    }

    public void setKICCopy4(String KICCopy4) {
        this.KICCopy4 = KICCopy4;
    }

    public String getKICCopy5() {
        return KICCopy5;
    }

    public void setKICCopy5(String KICCopy5) {
        this.KICCopy5 = KICCopy5;
    }

    public String getKICCopy6() {
        return KICCopy6;
    }

    public void setKICCopy6(String KICCopy6) {
        this.KICCopy6 = KICCopy6;
    }

    public String getKICCopy7() {
        return KICCopy7;
    }

    public void setKICCopy7(String KICCopy7) {
        this.KICCopy7 = KICCopy7;
    }

    public String getKICCopy8() {
        return KICCopy8;
    }

    public void setKICCopy8(String KICCopy8) {
        this.KICCopy8 = KICCopy8;
    }

    public String getKICCopy9() {
        return KICCopy9;
    }

    public void setKICCopy9(String KICCopy9) {
        this.KICCopy9 = KICCopy9;
    }

    public String getKICChk1() {
        return KICChk1;
    }

    public void setKICChk1(String KICChk1) {
        this.KICChk1 = KICChk1;
    }

    public String getKICChk2() {
        return KICChk2;
    }

    public void setKICChk2(String KICChk2) {
        this.KICChk2 = KICChk2;
    }

    public String getKICChk3() {
        return KICChk3;
    }

    public void setKICChk3(String KICChk3) {
        this.KICChk3 = KICChk3;
    }

    public String getKICChk4() {
        return KICChk4;
    }

    public void setKICChk4(String KICChk4) {
        this.KICChk4 = KICChk4;
    }

    public String getKICChk5() {
        return KICChk5;
    }

    public void setKICChk5(String KICChk5) {
        this.KICChk5 = KICChk5;
    }

    public String getKICChk6() {
        return KICChk6;
    }

    public void setKICChk6(String KICChk6) {
        this.KICChk6 = KICChk6;
    }

    public String getKICChk7() {
        return KICChk7;
    }

    public void setKICChk7(String KICChk7) {
        this.KICChk7 = KICChk7;
    }

    public String getKICChk8() {
        return KICChk8;
    }

    public void setKICChk8(String KICChk8) {
        this.KICChk8 = KICChk8;
    }

    public String getKICChk9() {
        return KICChk9;
    }

    public void setKICChk9(String KICChk9) {
        this.KICChk9 = KICChk9;
    }

    public String getUpdateBranchPoint() {
        return UpdateBranchPoint;
    }

    public void setUpdateBranchPoint(String UpdateBranchPoint) {
        this.UpdateBranchPoint = UpdateBranchPoint;
    }

    public String getKicName1() {
        return KicName1;
    }

    public void setKicName1(String KicName1) {
        this.KicName1 = KicName1;
    }

    public String getKicName2() {
        return KicName2;
    }

    public void setKicName2(String KicName2) {
        this.KicName2 = KicName2;
    }

    public String getKicName3() {
        return KicName3;
    }

    public void setKicName3(String KicName3) {
        this.KicName3 = KicName3;
    }

    public String getKicName4() {
        return KicName4;
    }

    public void setKicName4(String KicName4) {
        this.KicName4 = KicName4;
    }

    public String getKicName5() {
        return KicName5;
    }

    public void setKicName5(String KicName5) {
        this.KicName5 = KicName5;
    }

    public String getKicName6() {
        return KicName6;
    }

    public void setKicName6(String KicName6) {
        this.KicName6 = KicName6;
    }

    public String getKicName7() {
        return KicName7;
    }

    public void setKicName7(String KicName7) {
        this.KicName7 = KicName7;
    }

    public String getKicName8() {
        return KicName8;
    }

    public void setKicName8(String KicName8) {
        this.KicName8 = KicName8;
    }

    public String getKicName9() {
        return KicName9;
    }

    public void setKicName9(String KicName9) {
        this.KicName9 = KicName9;
    }

    public String getKicPrintOnReceipt1() {
        return KicPrintOnReceipt1;
    }

    public void setKicPrintOnReceipt1(String KicPrintOnReceipt1) {
        this.KicPrintOnReceipt1 = KicPrintOnReceipt1;
    }

    public String getKicPrintOnReceipt2() {
        return KicPrintOnReceipt2;
    }

    public void setKicPrintOnReceipt2(String KicPrintOnReceipt2) {
        this.KicPrintOnReceipt2 = KicPrintOnReceipt2;
    }

    public String getKicPrintOnReceipt3() {
        return KicPrintOnReceipt3;
    }

    public void setKicPrintOnReceipt3(String KicPrintOnReceipt3) {
        this.KicPrintOnReceipt3 = KicPrintOnReceipt3;
    }

    public String getKicPrintOnReceipt4() {
        return KicPrintOnReceipt4;
    }

    public void setKicPrintOnReceipt4(String KicPrintOnReceipt4) {
        this.KicPrintOnReceipt4 = KicPrintOnReceipt4;
    }

    public String getKicPrintOnReceipt5() {
        return KicPrintOnReceipt5;
    }

    public void setKicPrintOnReceipt5(String KicPrintOnReceipt5) {
        this.KicPrintOnReceipt5 = KicPrintOnReceipt5;
    }

    public String getKicPrintOnReceipt6() {
        return KicPrintOnReceipt6;
    }

    public void setKicPrintOnReceipt6(String KicPrintOnReceipt6) {
        this.KicPrintOnReceipt6 = KicPrintOnReceipt6;
    }

    public String getKicPrintOnReceipt7() {
        return KicPrintOnReceipt7;
    }

    public void setKicPrintOnReceipt7(String KicPrintOnReceipt7) {
        this.KicPrintOnReceipt7 = KicPrintOnReceipt7;
    }

    public String getKicPrintOnReceipt8() {
        return KicPrintOnReceipt8;
    }

    public void setKicPrintOnReceipt8(String KicPrintOnReceipt8) {
        this.KicPrintOnReceipt8 = KicPrintOnReceipt8;
    }

    public String getKicPrintOnReceipt9() {
        return KicPrintOnReceipt9;
    }

    public void setKicPrintOnReceipt9(String KicPrintOnReceipt9) {
        this.KicPrintOnReceipt9 = KicPrintOnReceipt9;
    }

    public int getKicQue() {
        return KicQue;
    }

    public void setKicQue(int KicQue) {
        this.KicQue = KicQue;
    }

    public String getKic10() {
        return Kic10;
    }

    public void setKic10(String Kic10) {
        this.Kic10 = Kic10;
    }

    public String getKic11() {
        return Kic11;
    }

    public void setKic11(String Kic11) {
        this.Kic11 = Kic11;
    }

    public String getKic12() {
        return Kic12;
    }

    public void setKic12(String Kic12) {
        this.Kic12 = Kic12;
    }

    public String getKic13() {
        return Kic13;
    }

    public void setKic13(String Kic13) {
        this.Kic13 = Kic13;
    }

    public String getKic14() {
        return Kic14;
    }

    public void setKic14(String Kic14) {
        this.Kic14 = Kic14;
    }

    public String getKic15() {
        return Kic15;
    }

    public void setKic15(String Kic15) {
        this.Kic15 = Kic15;
    }

    public String getKic16() {
        return Kic16;
    }

    public void setKic16(String Kic16) {
        this.Kic16 = Kic16;
    }

    public String getKic17() {
        return Kic17;
    }

    public void setKic17(String Kic17) {
        this.Kic17 = Kic17;
    }

    public String getKic18() {
        return Kic18;
    }

    public void setKic18(String Kic18) {
        this.Kic18 = Kic18;
    }

    public String getKic19() {
        return Kic19;
    }

    public void setKic19(String Kic19) {
        this.Kic19 = Kic19;
    }

    public String getKic20() {
        return Kic20;
    }

    public void setKic20(String Kic20) {
        this.Kic20 = Kic20;
    }

    public String getKicCopy10() {
        return KicCopy10;
    }

    public void setKicCopy10(String KicCopy10) {
        this.KicCopy10 = KicCopy10;
    }

    public String getKicCopy11() {
        return KicCopy11;
    }

    public void setKicCopy11(String KicCopy11) {
        this.KicCopy11 = KicCopy11;
    }

    public String getKicCopy12() {
        return KicCopy12;
    }

    public void setKicCopy12(String KicCopy12) {
        this.KicCopy12 = KicCopy12;
    }

    public String getKicCopy13() {
        return KicCopy13;
    }

    public void setKicCopy13(String KicCopy13) {
        this.KicCopy13 = KicCopy13;
    }

    public String getKicCopy14() {
        return KicCopy14;
    }

    public void setKicCopy14(String KicCopy14) {
        this.KicCopy14 = KicCopy14;
    }

    public String getKicCopy15() {
        return KicCopy15;
    }

    public void setKicCopy15(String KicCopy15) {
        this.KicCopy15 = KicCopy15;
    }

    public String getKicCopy16() {
        return KicCopy16;
    }

    public void setKicCopy16(String KicCopy16) {
        this.KicCopy16 = KicCopy16;
    }

    public String getKicCopy17() {
        return KicCopy17;
    }

    public void setKicCopy17(String KicCopy17) {
        this.KicCopy17 = KicCopy17;
    }

    public String getKicCopy18() {
        return KicCopy18;
    }

    public void setKicCopy18(String KicCopy18) {
        this.KicCopy18 = KicCopy18;
    }

    public String getKicCopy19() {
        return KicCopy19;
    }

    public void setKicCopy19(String KicCopy19) {
        this.KicCopy19 = KicCopy19;
    }

    public String getKicCopy20() {
        return KicCopy20;
    }

    public void setKicCopy20(String KicCopy20) {
        this.KicCopy20 = KicCopy20;
    }

    public String getKicChk10() {
        return KicChk10;
    }

    public void setKicChk10(String KicChk10) {
        this.KicChk10 = KicChk10;
    }

    public String getKicChk11() {
        return KicChk11;
    }

    public void setKicChk11(String KicChk11) {
        this.KicChk11 = KicChk11;
    }

    public String getKicChk12() {
        return KicChk12;
    }

    public void setKicChk12(String KicChk12) {
        this.KicChk12 = KicChk12;
    }

    public String getKicChk13() {
        return KicChk13;
    }

    public void setKicChk13(String KicChk13) {
        this.KicChk13 = KicChk13;
    }

    public String getKicChk14() {
        return KicChk14;
    }

    public void setKicChk14(String KicChk14) {
        this.KicChk14 = KicChk14;
    }

    public String getKicChk15() {
        return KicChk15;
    }

    public void setKicChk15(String KicChk15) {
        this.KicChk15 = KicChk15;
    }

    public String getKicChk16() {
        return KicChk16;
    }

    public void setKicChk16(String KicChk16) {
        this.KicChk16 = KicChk16;
    }

    public String getKicChk17() {
        return KicChk17;
    }

    public void setKicChk17(String KicChk17) {
        this.KicChk17 = KicChk17;
    }

    public String getKicChk18() {
        return KicChk18;
    }

    public void setKicChk18(String KicChk18) {
        this.KicChk18 = KicChk18;
    }

    public String getKicChk19() {
        return KicChk19;
    }

    public void setKicChk19(String KicChk19) {
        this.KicChk19 = KicChk19;
    }

    public String getKicChk20() {
        return KicChk20;
    }

    public void setKicChk20(String KicChk20) {
        this.KicChk20 = KicChk20;
    }
    
    
}
