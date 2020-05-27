package com.softpos.main.program;

public class FinalcialRec {

    public String MacNo1 = "";
    public String Macno2 = "";
    public String Cashier1 = "";
    public String Cashier2 = "";
    public Double Dept_Sum = 0.0;
    public Double Service = 0.0;
    public int ServiceCnt = 0;
    public Double Charge = 0.0;
    public int ChargeCnt = 0;

    public Double Vip_Disc = 0.0;  //As Mem_Disc
    public int Vip_DiscCnt = 0;

    public Double Emp_Disc = 0.0;
    public int Emp_DiscCnt = 0;

    public Double Fast_Disc = 0.0;
    public int Fast_DiscCnt = 0;

    public Double Train_Disc = 0.0;
    public int Train_DiscCnt = 0;

    public Double Sub_Disc = 0.0;
    public int Sub_DiscCnt = 0;

    public Double Gen_Refund = 0.0;
    public int Gen_RefundCnt = 0;

    public Double Cupon_Disc = 0.0;
    public int Cupon_DiscCnt = 0;

    public Double Promotion = 0.0;
    public int PromotionCnt = 0;

    public Double Spacial = 0.0;
    public int SpacialCnt = 0;

    public Double Item_Disc = 0.0;
    public int Item_DiscCnt = 0;

    public Double Net_Sale = 0.0;
    public Double Cash = 0.0;
    public int CashCnt = 0;
    public Double Gift = 0.0;
    public int GiftCnt = 0;
    public Double Earnest = 0.0;
    public int EarnestCnt = 0;
    public Double ArPayment = 0.0;
    public int ArPaymentCnt = 0;
    public Double Credit_Card = 0.0;
    public int Credit_CardCnt = 0;
    public Double Paid_In = 0.0;
    public int Paid_InCnt = 0;
    public Double Paid_Out = 0.0;
    public int Paid_OutCnt = 0;
    public Double Cash_In_Draw = 0.0;
    public Double SaleVat = 0.0;
    public Double SaleNonVat = 0.0;
    public Double VatAmt = 0.0;
    public int CntBill = 0;
    public String StBill = "";
    public String SpBill = "";
    public int CntBillVoid = 0;
    public Double AmtBillVoid = 0.0;
    public Double VoidValue = 0.0;
    public int CntVoid = 0;
    public Double Eat_In_Amt = 0.0;
    public Double Take_AwayAmt = 0.0;
    public Double DeliveryAmt = 0.0;
    public Double PintoAmt = 0.0;
    public Double WholeAmt = 0.0;
    public Double Food = 0.0;
    public Double Drink = 0.0;
    public Double Product = 0.0;
    public int Customer = 0;
    public int Eat_In_Cnt = 0;
    public int Take_AwayCnt = 0;
    public int DeliveryCnt = 0;
    public int PintoCnt = 0;
    public int WholeCnt = 0;
    public int Eat_In_Cust = 0;
    public int Take_AwayCust = 0;
    public int DeliveryCust = 0;
    public int PintoCust = 0;
    public int WholeCust = 0;
    public int NMist = 0;
    public Double Mist = 0.0;
    public Double Eat_In_Net = 0.0;
    public Double Take_AwayNet = 0.0;
    public Double DeliveryNet = 0.0;
    public Double PintoNet = 0.0;
    public Double WholeNet = 0.0;
    public Double Entertain = 0.0;
    public Double BillEntertain = 0.0;
    public Double B_NetDiff = 0.0;

    public void ClearRec() {
        MacNo1 = "";
        Macno2 = "";
        Cashier1 = "";
        Cashier2 = "";
        Dept_Sum = 0.0;
        Service = 0.0;
        ServiceCnt = 0;
        Charge = 0.0;
        ChargeCnt = 0;

        Vip_Disc = 0.0;  //As Mem_Disc
        Vip_DiscCnt = 0;

        Emp_Disc = 0.0;
        Emp_DiscCnt = 0;

        Fast_Disc = 0.0;
        Fast_DiscCnt = 0;

        Train_Disc = 0.0;
        Train_DiscCnt = 0;

        Sub_Disc = 0.0;
        Sub_DiscCnt = 0;

        Gen_Refund = 0.0;
        Gen_RefundCnt = 0;

        Cupon_Disc = 0.0;
        Cupon_DiscCnt = 0;

        Promotion = 0.0;
        PromotionCnt = 0;

        Spacial = 0.0;
        SpacialCnt = 0;

        Item_Disc = 0.0;
        Item_DiscCnt = 0;

        Net_Sale = 0.0;
        Cash = 0.0;
        CashCnt = 0;
        Gift = 0.0;
        GiftCnt = 0;
        Earnest = 0.0;
        EarnestCnt = 0;
        ArPayment = 0.0;
        ArPaymentCnt = 0;
        Credit_Card = 0.0;
        Credit_CardCnt = 0;
        Paid_In = 0.0;
        Paid_InCnt = 0;
        Paid_Out = 0.0;
        Paid_OutCnt = 0;
        Cash_In_Draw = 0.0;
        SaleVat = 0.0;
        SaleNonVat = 0.0;
        VatAmt = 0.0;
        CntBill = 0;
        StBill = "";
        SpBill = "";
        CntBillVoid = 0;
        AmtBillVoid = 0.0;
        VoidValue = 0.0;
        CntVoid = 0;
        Eat_In_Amt = 0.0;
        Take_AwayAmt = 0.0;
        DeliveryAmt = 0.0;
        PintoAmt = 0.0;
        WholeAmt = 0.0;
        Food = 0.0;
        Drink = 0.0;
        Product = 0.0;
        Customer = 0;
        Eat_In_Cnt = 0;
        Take_AwayCnt = 0;
        DeliveryCnt = 0;
        PintoCnt = 0;
        WholeCnt = 0;
        Eat_In_Cust = 0;
        Take_AwayCust = 0;
        DeliveryCust = 0;
        PintoCust = 0;
        WholeCust = 0;
        NMist = 0;
        Mist = 0.0;
        Eat_In_Net = 0.0;
        Take_AwayNet = 0.0;
        DeliveryNet = 0.0;
        PintoNet = 0.0;
        WholeNet = 0.0;
        Entertain = 0.0;
        BillEntertain = 0.0;
        B_NetDiff = 0.00;
    }
}
