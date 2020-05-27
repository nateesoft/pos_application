package com.softpos.main.program;

public class POSHWSetup {

    private String Terminal;
    private String OnAct = "N";
    private String MacNo;
    private double ReceNo1 = 0;
    private String SaleType = "E";
    private String TStock;
    private String TSone;
    private String Heading1;
    private String Heading2;
    private String Heading3;
    private String Heading4;
    private String Footting1;
    private String Footting2;
    private String Footting3;
    private String DRPort = "NONE";
    private String DRType = "1";
    private String DRCOM;
    private String DISPort = "NONE";
    private String DISType = "1";
    private String DISCOM;
    private String PRNPort = "NONE";
    private String PRNTYPE = "1";
    private String PRNCOM;
    private String PRNThaiLevel = "Y";
    private String KIC1Port = "NONE";
    private String KIC1Type = "1";
    private String KIC1Com;
    private String KIC1ThaiLevel = "Y";
    private String KIC2Port = "NONE";
    private String KIC2Type = "1";
    private String KIC2Com;
    private String KIC2ThaiLevel = "Y";
    private String KIC3Port = "NONE";
    private String KIC3Type = "1";
    private String KIC3Com;
    private String KIC3ThaiLevel = "Y";
    private String KIC4Port = "NONE";
    private String KIC4Type = "1";
    private String KIC4Com;
    private String KIC4ThaiLevel = "Y";
    private String EJounal = "N";
    private String EJDailyPath;
    private String EJBackupPath;
    private int PrnRate = 0;
    private int DrRate = 0;
    private int DisRate = 0;
    private String EDCPort = "NONE";
    private String SMPBank;
    private String MenuItemList = "N";
    private String UseFloorPlan = "N";
    private String TakeOrderChk = "N";
    private String RFIDPort = "NONE";
    
    public static POSHWSetup Bean(String MacNo){
        PosControl pos = new PosControl();
        
        return pos.getData(MacNo);
    }

    public String getTerminal() {
        return Terminal;
    }

    public void setTerminal(String Terminal) {
        this.Terminal = Terminal;
    }

    public String getOnAct() {
        return OnAct;
    }

    public void setOnAct(String OnAct) {
        this.OnAct = OnAct;
    }

    public String getMacNo() {
        return MacNo;
    }

    public void setMacNo(String MacNo) {
        this.MacNo = MacNo;
    }

    public double getReceNo1() {
        return ReceNo1;
    }

    public void setReceNo1(double ReceNo1) {
        this.ReceNo1 = ReceNo1;
    }

    public String getSaleType() {
        return SaleType;
    }

    public void setSaleType(String SaleType) {
        this.SaleType = SaleType;
    }

    public String getTStock() {
        return TStock;
    }

    public void setTStock(String TStock) {
        this.TStock = TStock;
    }

    public String getTSone() {
        return TSone;
    }

    public void setTSone(String TSone) {
        this.TSone = TSone;
    }

    public String getHeading1() {
        return Heading1;
    }

    public void setHeading1(String Heading1) {
        this.Heading1 = Heading1;
    }

    public String getHeading2() {
        return Heading2;
    }

    public void setHeading2(String Heading2) {
        this.Heading2 = Heading2;
    }

    public String getHeading3() {
        return Heading3;
    }

    public void setHeading3(String Heading3) {
        this.Heading3 = Heading3;
    }

    public String getHeading4() {
        return Heading4;
    }

    public void setHeading4(String Heading4) {
        this.Heading4 = Heading4;
    }

    public String getFootting1() {
        return Footting1;
    }

    public void setFootting1(String Footting1) {
        this.Footting1 = Footting1;
    }

    public String getFootting2() {
        return Footting2;
    }

    public void setFootting2(String Footting2) {
        this.Footting2 = Footting2;
    }
    public String getFootting3() {
        return Footting3;
    }

    public void setFootting3(String Footting3) {
        this.Footting3 = Footting3;
    }

    public String getDRPort() {
        return DRPort;
    }

    public void setDRPort(String DRPort) {
        this.DRPort = DRPort;
    }

    public String getDRType() {
        return DRType;
    }

    public void setDRType(String DRType) {
        this.DRType = DRType;
    }

    public String getDRCOM() {
        return DRCOM;
    }

    public void setDRCOM(String DRCOM) {
        this.DRCOM = DRCOM;
    }

    public String getDISPort() {
        return DISPort;
    }

    public void setDISPort(String DISPort) {
        this.DISPort = DISPort;
    }

    public String getDISType() {
        return DISType;
    }

    public void setDISType(String DISType) {
        this.DISType = DISType;
    }

    public String getDISCOM() {
        return DISCOM;
    }

    public void setDISCOM(String DISCOM) {
        this.DISCOM = DISCOM;
    }

    public String getPRNPort() {
        return PRNPort;
    }

    public void setPRNPort(String PRNPort) {
        this.PRNPort = PRNPort;
    }

    public String getPRNTYPE() {
        return PRNTYPE;
    }

    public void setPRNTYPE(String PRNTYPE) {
        this.PRNTYPE = PRNTYPE;
    }

    public String getPRNCOM() {
        return PRNCOM;
    }

    public void setPRNCOM(String PRNCOM) {
        this.PRNCOM = PRNCOM;
    }

    public String getPRNThaiLevel() {
        return PRNThaiLevel;
    }

    public void setPRNThaiLevel(String PRNThaiLevel) {
        this.PRNThaiLevel = PRNThaiLevel;
    }

    public String getKIC1Port() {
        return KIC1Port;
    }

    public void setKIC1Port(String KIC1Port) {
        this.KIC1Port = KIC1Port;
    }

    public String getKIC1Type() {
        return KIC1Type;
    }

    public void setKIC1Type(String KIC1Type) {
        this.KIC1Type = KIC1Type;
    }

    public String getKIC1Com() {
        return KIC1Com;
    }

    public void setKIC1Com(String KIC1Com) {
        this.KIC1Com = KIC1Com;
    }

    public String getKIC1ThaiLevel() {
        return KIC1ThaiLevel;
    }

    public void setKIC1ThaiLevel(String KIC1ThaiLevel) {
        this.KIC1ThaiLevel = KIC1ThaiLevel;
    }

    public String getKIC2Port() {
        return KIC2Port;
    }

    public void setKIC2Port(String KIC2Port) {
        this.KIC2Port = KIC2Port;
    }

    public String getKIC2Type() {
        return KIC2Type;
    }

    public void setKIC2Type(String KIC2Type) {
        this.KIC2Type = KIC2Type;
    }

    public String getKIC2Com() {
        return KIC2Com;
    }

    public void setKIC2Com(String KIC2Com) {
        this.KIC2Com = KIC2Com;
    }

    public String getKIC2ThaiLevel() {
        return KIC2ThaiLevel;
    }

    public void setKIC2ThaiLevel(String KIC2ThaiLevel) {
        this.KIC2ThaiLevel = KIC2ThaiLevel;
    }

    public String getKIC3Port() {
        return KIC3Port;
    }

    public void setKIC3Port(String KIC3Port) {
        this.KIC3Port = KIC3Port;
    }

    public String getKIC3Type() {
        return KIC3Type;
    }

    public void setKIC3Type(String KIC3Type) {
        this.KIC3Type = KIC3Type;
    }

    public String getKIC3Com() {
        return KIC3Com;
    }

    public void setKIC3Com(String KIC3Com) {
        this.KIC3Com = KIC3Com;
    }

    public String getKIC3ThaiLevel() {
        return KIC3ThaiLevel;
    }

    public void setKIC3ThaiLevel(String KIC3ThaiLevel) {
        this.KIC3ThaiLevel = KIC3ThaiLevel;
    }

    public String getKIC4Port() {
        return KIC4Port;
    }

    public void setKIC4Port(String KIC4Port) {
        this.KIC4Port = KIC4Port;
    }

    public String getKIC4Type() {
        return KIC4Type;
    }

    public void setKIC4Type(String KIC4Type) {
        this.KIC4Type = KIC4Type;
    }

    public String getKIC4Com() {
        return KIC4Com;
    }

    public void setKIC4Com(String KIC4Com) {
        this.KIC4Com = KIC4Com;
    }

    public String getKIC4ThaiLevel() {
        return KIC4ThaiLevel;
    }

    public void setKIC4ThaiLevel(String KIC4ThaiLevel) {
        this.KIC4ThaiLevel = KIC4ThaiLevel;
    }

    public String getEJounal() {
        return EJounal;
    }

    public void setEJounal(String EJounal) {
        this.EJounal = EJounal;
    }

    public String getEJDailyPath() {
        return EJDailyPath;
    }

    public void setEJDailyPath(String EJDailyPath) {
        this.EJDailyPath = EJDailyPath;
    }

    public String getEJBackupPath() {
        return EJBackupPath;
    }

    public void setEJBackupPath(String EJBackupPath) {
        this.EJBackupPath = EJBackupPath;
    }

    public int getPrnRate() {
        return PrnRate;
    }

    public void setPrnRate(int PrnRate) {
        this.PrnRate = PrnRate;
    }

    public int getDrRate() {
        return DrRate;
    }

    public void setDrRate(int DrRate) {
        this.DrRate = DrRate;
    }

    public int getDisRate() {
        return DisRate;
    }

    public void setDisRate(int DisRate) {
        this.DisRate = DisRate;
    }

    public String getEDCPort() {
        return EDCPort;
    }

    public void setEDCPort(String EDCPort) {
        this.EDCPort = EDCPort;
    }

    public String getSMPBank() {
        return SMPBank;
    }

    public void setSMPBank(String SMPBank) {
        this.SMPBank = SMPBank;
    }

    public String getMenuItemList() {
        return MenuItemList;
    }

    public void setMenuItemList(String MenuItemList) {
        this.MenuItemList = MenuItemList;
    }

    public String getUseFloorPlan() {
        return UseFloorPlan;
    }

    public void setUseFloorPlan(String UseFloorPlan) {
        this.UseFloorPlan = UseFloorPlan;
    }

    public String getTakeOrderChk() {
        return TakeOrderChk;
    }

    public void setTakeOrderChk(String TakeOrderChk) {
        this.TakeOrderChk = TakeOrderChk;
    }

    public String getRFIDPort() {
        return RFIDPort;
    }

    public void setRFIDPort(String RFIDPort) {
        this.RFIDPort = RFIDPort;
    }
    
    
}
