package com.softpos.main.program;

import java.util.ArrayList;
import java.util.Date;

public class TableFileBean {

    private String Tcode;
    private String SoneCode="";
    private Date TLoginDate;
    private String MacNo;
    private String Cashier;
    private String TLoginTime;
    private String TCurTime;
    private int TCustomer = 1;
    private int TItem = 0;
    private double TAmount = 0.00;
    private String TOnAct = "N";
    private double Service = 0.00;
    private double ServiceAmt = 0.00;
    private String EmpDisc;
    private double EmpDiscAmt = 0.00;
    private String FastDisc;
    private double FastDiscAmt = 0.00;
    private String TrainDisc;
    private double TrainDiscAmt = 0.00;
    private String MemDisc;
    private double MemDiscAmt = 0.00;
    private String SubDisc;
    private double SubDiscAmt = 0.00;
    private double DiscBath = 0.00;
    private double ProDiscAmt = 0.00;
    private double SpaDiscAmt = 0.00;
    private double CuponDiscAmt = 0.00;
    private double ItemDiscAmt = 0.00;
    private String MemCode;
    private double MemCurAmt = 0.00;
    private String MemName="";
    private Date MemBegin;
    private Date MemEnd;
    private double Food = 0.00;
    private double Drink = 0.00;
    private double Product = 0.00;
    private double NetTotal = 0.00;
    private double PrintTotal = 0.00;
    private String PrintChkBill = "N";
    private int PrintCnt = 0;
    private String PrintTime1;
    private String PrintTime2;
    private String ChkBill = "N";
    private String ChkBillTime = "00:00:00";
    private String StkCode1="";
    private String StkCode2="";
    private int TDesk = 0;
    private String TUser;
    private String TPause = "N";
    private String TType = "0";
    private String TActive = "Y";
    private String TFinishTime;
    private String TTableIsOn = "N";
    private String TAutoClose = "Y";
    private ArrayList<BalanceBean> dataBalance;
    
    public TableFileBean(){
        dataBalance = new ArrayList<BalanceBean>();
    }
    
    public void addBalance(BalanceBean bb){
        dataBalance.add(bb);
    }
    
    public BalanceBean getBalance(int i){
        return dataBalance.get(i);
    }
    
    public ArrayList<BalanceBean> getAllBalance(){
        return dataBalance;
    }
    
    public int size(){
        return dataBalance.size();
    }

    public String getTcode() {
        return Tcode;
    }

    public void setTcode(String Tcode) {
        this.Tcode = Tcode;
    }

    public String getSoneCode() {
        return SoneCode;
    }

    public void setSoneCode(String SoneCode) {
        this.SoneCode = SoneCode;
    }

    public Date getTLoginDate() {
        return TLoginDate;
    }

    public void setTLoginDate(Date TLoginDate) {
        this.TLoginDate = TLoginDate;
    }

    public String getMacNo() {
        return MacNo;
    }

    public void setMacNo(String MacNo) {
        this.MacNo = MacNo;
    }

    public String getCashier() {
        return Cashier;
    }

    public void setCashier(String Cashier) {
        this.Cashier = Cashier;
    }

    public String getTLoginTime() {
        return TLoginTime;
    }

    public void setTLoginTime(String TLoginTime) {
        this.TLoginTime = TLoginTime;
    }

    public String getTCurTime() {
        return TCurTime;
    }

    public void setTCurTime(String TCurTime) {
        this.TCurTime = TCurTime;
    }

    public int getTCustomer() {
        return TCustomer;
    }

    public void setTCustomer(int TCustomer) {
        this.TCustomer = TCustomer;
    }

    public int getTItem() {
        return TItem;
    }

    public void setTItem(int TItem) {
        this.TItem = TItem;
    }

    public double getTAmount() {
        return TAmount;
    }

    public void setTAmount(double TAmount) {
        this.TAmount = TAmount;
    }

    public String getTOnAct() {
        return TOnAct;
    }

    public void setTOnAct(String TOnAct) {
        this.TOnAct = TOnAct;
    }

    public double getService() {
        return Service;
    }

    public void setService(double Service) {
        this.Service = Service;
    }

    public double getServiceAmt() {
        return ServiceAmt;
    }

    public void setServiceAmt(double ServiceAmt) {
        this.ServiceAmt = ServiceAmt;
    }

    public String getEmpDisc() {
        return EmpDisc;
    }

    public void setEmpDisc(String EmpDisc) {
        this.EmpDisc = EmpDisc;
    }

    public double getEmpDiscAmt() {
        return EmpDiscAmt;
    }

    public void setEmpDiscAmt(double EmpDiscAmt) {
        this.EmpDiscAmt = EmpDiscAmt;
    }

    public String getFastDisc() {
        return FastDisc;
    }

    public void setFastDisc(String FastDisc) {
        this.FastDisc = FastDisc;
    }

    public double getFastDiscAmt() {
        return FastDiscAmt;
    }

    public void setFastDiscAmt(double FastDiscAmt) {
        this.FastDiscAmt = FastDiscAmt;
    }

    public String getTrainDisc() {
        return TrainDisc;
    }

    public void setTrainDisc(String TrainDisc) {
        this.TrainDisc = TrainDisc;
    }

    public double getTrainDiscAmt() {
        return TrainDiscAmt;
    }

    public void setTrainDiscAmt(double TrainDiscAmt) {
        this.TrainDiscAmt = TrainDiscAmt;
    }

    public String getMemDisc() {
        return MemDisc;
    }

    public void setMemDisc(String MemDisc) {
        this.MemDisc = MemDisc;
    }

    public double getMemDiscAmt() {
        return MemDiscAmt;
    }

    public void setMemDiscAmt(double MemDiscAmt) {
        this.MemDiscAmt = MemDiscAmt;
    }

    public String getSubDisc() {
        return SubDisc;
    }

    public void setSubDisc(String SubDisc) {
        this.SubDisc = SubDisc;
    }

    public double getSubDiscAmt() {
        return SubDiscAmt;
    }

    public void setSubDiscAmt(double SubDiscAmt) {
        this.SubDiscAmt = SubDiscAmt;
    }

    public double getDiscBath() {
        return DiscBath;
    }

    public void setDiscBath(double DiscBath) {
        this.DiscBath = DiscBath;
    }

    public double getProDiscAmt() {
        return ProDiscAmt;
    }

    public void setProDiscAmt(double ProDiscAmt) {
        this.ProDiscAmt = ProDiscAmt;
    }

    public double getSpaDiscAmt() {
        return SpaDiscAmt;
    }

    public void setSpaDiscAmt(double SpaDiscAmt) {
        this.SpaDiscAmt = SpaDiscAmt;
    }

    public double getCuponDiscAmt() {
        return CuponDiscAmt;
    }

    public void setCuponDiscAmt(double CuponDiscAmt) {
        this.CuponDiscAmt = CuponDiscAmt;
    }

    public double getItemDiscAmt() {
        return ItemDiscAmt;
    }

    public void setItemDiscAmt(double ItemDiscAmt) {
        this.ItemDiscAmt = ItemDiscAmt;
    }

    public String getMemCode() {
        return MemCode;
    }

    public void setMemCode(String MemCode) {
        this.MemCode = MemCode;
    }

    public double getMemCurAmt() {
        return MemCurAmt;
    }

    public void setMemCurAmt(double MemCurAmt) {
        this.MemCurAmt = MemCurAmt;
    }

    public String getMemName() {
        return MemName;
    }

    public void setMemName(String MemName) {
        this.MemName = MemName;
    }

    public Date getMemBegin() {
        return MemBegin;
    }

    public void setMemBegin(Date MemBegin) {
        this.MemBegin = MemBegin;
    }

    public Date getMemEnd() {
        return MemEnd;
    }

    public void setMemEnd(Date MemEnd) {
        this.MemEnd = MemEnd;
    }

    public double getFood() {
        return Food;
    }

    public void setFood(double Food) {
        this.Food = Food;
    }

    public double getDrink() {
        return Drink;
    }

    public void setDrink(double Drink) {
        this.Drink = Drink;
    }

    public double getProduct() {
        return Product;
    }

    public void setProduct(double Product) {
        this.Product = Product;
    }

    public double getNetTotal() {
        return NetTotal;
    }

    public void setNetTotal(double NetTotal) {
        this.NetTotal = NetTotal;
    }

    public double getPrintTotal() {
        return PrintTotal;
    }

    public void setPrintTotal(double PrintTotal) {
        this.PrintTotal = PrintTotal;
    }

    public String getPrintChkBill() {
        return PrintChkBill;
    }

    public void setPrintChkBill(String PrintChkBill) {
        this.PrintChkBill = PrintChkBill;
    }

    public int getPrintCnt() {
        return PrintCnt;
    }

    public void setPrintCnt(int PrintCnt) {
        this.PrintCnt = PrintCnt;
    }

    public String getPrintTime1() {
        return PrintTime1;
    }

    public void setPrintTime1(String PrintTime1) {
        this.PrintTime1 = PrintTime1;
    }

    public String getPrintTime2() {
        return PrintTime2;
    }

    public void setPrintTime2(String PrintTime2) {
        this.PrintTime2 = PrintTime2;
    }

    public String getChkBill() {
        return ChkBill;
    }

    public void setChkBill(String ChkBill) {
        this.ChkBill = ChkBill;
    }

    public String getChkBillTime() {
        return ChkBillTime;
    }

    public void setChkBillTime(String ChkBillTime) {
        this.ChkBillTime = ChkBillTime;
    }

    public String getStkCode1() {
        return StkCode1;
    }

    public void setStkCode1(String StkCode1) {
        this.StkCode1 = StkCode1;
    }

    public String getStkCode2() {
        return StkCode2;
    }

    public void setStkCode2(String StkCode2) {
        this.StkCode2 = StkCode2;
    }

    public int getTDesk() {
        return TDesk;
    }

    public void setTDesk(int TDesk) {
        this.TDesk = TDesk;
    }

    public String getTUser() {
        return TUser;
    }

    public void setTUser(String TUser) {
        this.TUser = TUser;
    }

    public String getTPause() {
        return TPause;
    }

    public void setTPause(String TPause) {
        this.TPause = TPause;
    }

    public String getTType() {
        return TType;
    }

    public void setTType(String TType) {
        this.TType = TType;
    }

    public String getTActive() {
        return TActive;
    }

    public void setTActive(String TActive) {
        this.TActive = TActive;
    }

    public String getTFinishTime() {
        return TFinishTime;
    }

    public void setTFinishTime(String TFinishTime) {
        this.TFinishTime = TFinishTime;
    }

    public String getTTableIsOn() {
        return TTableIsOn;
    }

    public void setTTableIsOn(String TTableIsOn) {
        this.TTableIsOn = TTableIsOn;
    }

    public String getTAutoClose() {
        return TAutoClose;
    }

    public void setTAutoClose(String TAutoClose) {
        this.TAutoClose = TAutoClose;
    }
}