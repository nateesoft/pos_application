package com.softpos.main.program;

import com.softpos.member.MemberBean;
import com.softpos.member.MemberControl;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import sun.natee.project.util.DateFormat;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class BalanceControl_1 {

    private BalanceBean balanceCurrent;

    public BalanceControl_1() {
        balanceCurrent = new BalanceBean();
    }

    public String getLastIndex(String tableNo) {
        String tempIndex = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select max(R_Index) R_Index "
                    + "from balance "
                    + "where r_table='" + tableNo + "';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                tempIndex = rs.getString("R_Index");
            } else {
                tempIndex = "";
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return tempIndex;
    }

    public boolean saveBalance(BalanceBean bb) {
        try {
            ProductControl pc = new ProductControl();
            BalanceBean bean = new BalanceBean();

            //### Save to balance ###
            bean.setR_ETD(bb.getR_ETD());//set default E
            bean.setR_Table(bb.getR_Table());
            bean.setR_Emp(bb.getR_Emp());
            bean.setR_PluCode(bb.getR_PluCode());
            bean.setR_Quan(bb.getR_Quan());
            bean.setR_PrQuan(0);
            bean.setMacno(Value.MACNO);

            bean.setR_KicPrint(bb.getR_KicPrint());
            bean.setR_Pause(bb.getR_Pause());

            /*สำหรับคำนวณโปรโมชัน*/
            bean.setR_PrType("");//set default ''
            bean.setR_PrCode("");//set default ''
            bean.setR_PrDisc(0);
            bean.setR_PrBath(0);
            bean.setR_PrAmt(0);
            bean.setR_PrCuType("");//set default ''
            bean.setR_PrCuCode("");
            bean.setR_PrChkType("");

            // สำหรับส่วนลดสมาชิก            
            bean.setR_PrSubType(bb.getR_PrSubType());
            bean.setR_PrSubCode(bb.getR_PrSubCode());
            bean.setR_PrSubQuan(bb.getR_PrSubQuan());
            bean.setR_PrSubDisc(bb.getR_PrSubDisc());
            bean.setR_PrSubBath(bb.getR_PrSubBath());
            bean.setR_PrSubAmt(bb.getR_PrSubAmt());
            bean.setR_QuanCanDisc(bb.getR_QuanCanDisc());

            bean.setR_Void("");
            bean.setR_Serve("");
            bean.setR_PrintOK("Y");
            bean.setPosStk("0");
            bean.setR_Order("1");
            bean.setR_MemSum("N");
            bean.setR_MoveItem("N");
            bean.setR_MovePrint("N");
            //bill.setR_CardPay("N");
            bean.setR_Pickup("P");
            bean.setR_KicOK("");

            bean.setR_CallWait("");
            bean.setR_VoidPause("");

            bean.setR_Opt1(bb.getR_Opt1());
            bean.setR_Opt2(bb.getR_Opt2());
            bean.setR_Opt3(bb.getR_Opt3());
            bean.setR_Opt4(bb.getR_Opt4());
            bean.setR_Opt5(bb.getR_Opt5());
            bean.setR_Opt6(bb.getR_Opt6());
            bean.setR_Opt7(bb.getR_Opt7());
            bean.setR_Opt8(bb.getR_Opt8());
            bean.setR_Opt9(bb.getR_Opt9());

            bean.setVoidMSG(bb.getVoidMSG());

            bean.setR_MoveFrom("");
            bean.setR_MoveUser("");

            bean.setCashier(bb.getCashier());

            bean.setR_Status("Y");
            bean.setR_Void("");
            bean.setR_VoidUser("");
            bean.setR_VoidTime("");
            bean.setR_MoveFlag("0");

            ProductBean product = pc.getData(bb.getR_PluCode());
            bean.setR_Index(bb.getR_Index());//หมายเลขโต๊ะ/ลำดับที่รายการอาหาร
            bean.setR_Table(bb.getR_Table());//หมายเลขโต๊ะ
            //bean.setR_Date(Calendar.getInstance().getTime());
            //bean.setR_Time("16:04:14");
//            bean.setMacno(bb.getMacno());//หมายเลขเครื่อง
            bean.setCashier(bb.getCashier());//รหัส login
            bean.setR_Emp(bb.getR_Emp());//รหัสบริกร
            bean.setR_PluCode(product.getPCode());//รหัสสินค้า
            bean.setR_PName(product.getPDesc());//ชื่อสินค้า
            bean.setR_Unit(ThaiUtil.Unicode2ASCII(product.getPUnit1()));//หน่วยนับ
            bean.setR_Group(product.getPGroup());//กลุ่มสินค้า
            bean.setR_Status(product.getPStatus());//การคิดราคาขาย (P=อัตโนมัติ(Plu), D=กำหนดเองตามป้าย(SDP), T=คำนวณตามเวลา(Time))
            bean.setR_Normal(product.getPNormal());//สถานะสินค้า (N=Normal, C=Consign, S=Special)
            bean.setR_Discount(product.getPDiscount());//สามารถให้ส่วนลดได้
            bean.setR_Service(product.getPService());//คิดค่าบริการ
            bean.setR_Stock(product.getPStock());//จัดทำสต็อกหรือไม่
            bean.setR_Set(product.getPSet());//เป็นสินค้าชุดหรือไม่
            bean.setR_Vat(product.getPVat());//คิดภาษีหรือไม่
            bean.setR_Type(product.getPType());//ประเภทสินค้า
            bean.setR_Quan(bb.getR_Quan());//จำนวนที่สั่ง
            bean.setR_Price(bb.getR_Price());//ราคาสินค้า
            bean.setR_Total(bb.getR_Quan() * bb.getR_Price());//ราคารวม            
            bean.setR_Kic(product.getPKic());

            //Add new Field
//            bean.setStkCode(product.getMSStk());
            bean.setStkCode(bb.getStkCode());
            bean.setPosStk(product.getPOSStk());

            if (bb.getR_LinkIndex() == null) {
                bean.setR_LinkIndex("");
            } else {
                bean.setR_LinkIndex(bb.getR_LinkIndex());
            }

            bean.setR_Date(bb.getR_Date());
            bean.setR_Time(bb.getR_Time());

            //copy balance bean
            balanceCurrent = bean;

            return saveBillNoSQL(bean);

        } catch (Exception e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean moveBalanceAll(String table, BalanceBean bean) {
        try {
            BalanceBean bill = bean;

            //### Save to balance ###
            bill.setR_Table(table);
            String tempRIndex = bill.getR_Index();
            bill.setR_Index(getIndexBalance(bill.getR_Table()));
            bill.setR_Time(bean.getR_Time());
            bill.setR_Date(bean.getR_Date());
            bill.setR_MoveFrom(tempRIndex);

            //copy balance bean
            balanceCurrent = bill;

            return saveBillNoSQL(bill);

        } catch (Exception e) {
            MSG.ERR(e.getMessage());
            return false;
        }
    }

    public BalanceBean getCurrentBalance() {
        return balanceCurrent;
    }

    private boolean saveBillNoSQL(BalanceBean bean) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            if (bean.getR_Date() == null) {
                bean.setR_Date(new Date());
            }
            if (bean.getR_Time() == null) {
                SimpleDateFormat sss = new SimpleDateFormat("HH:mm:ss");
                bean.setR_Time(sss.format(new Date()));
            }
            String sqlInsert = "insert into balance "
                    + "(R_Index,R_Table,R_Date,R_Time,Macno,"
                    + "Cashier,R_Emp,R_PluCode,R_PName,R_Unit,"
                    + "R_Group,R_Status,R_Normal,R_Discount,R_Service,"
                    + "R_Stock,R_Set,R_Vat,R_Type,R_ETD,R_Quan,R_Price,"
                    + "R_Total,R_PrType,R_PrCode,R_PrDisc,R_PrBath,"
                    + "R_PrAmt,R_DiscBath,R_PrCuType,R_PrCuQuan,R_PrCuAmt,"
                    + "R_Redule,R_Kic,R_KicPrint,R_Void,R_VoidUser,R_VoidTime,"
                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,R_Opt7,R_Opt8,"
                    + "R_Opt9,R_PrCuCode,R_Serve,R_PrintOK,R_KicOK,StkCode,"
                    + "PosStk,R_PrChkType,R_PrQuan,R_PrSubType,R_PrSubCode,"
                    + "R_PrSubQuan,R_PrSubDisc,R_PrSubBath,R_PrSubAmt,R_PrSubAdj,"
                    + "R_PrCuDisc,R_PrCuBath,R_PrCuAdj,R_QuanCanDisc,R_Order,R_PItemNo,"
                    + "R_PKicQue,R_MemSum,R_MoveItem,R_MoveFrom,R_MoveUser,R_MoveFlag,"
                    + "R_MovePrint,R_Pause,R_PrVcType,R_PrVcCode,R_LinkIndex,R_VoidPause,R_SPIndex,VoidMSG) "
                    + "values('" + bean.getR_Index() + "','" + bean.getR_Table() + "',"
                    + "'" + DateFormat.getMySQL_Date(bean.getR_Date()) + "','" + bean.getR_Time() + "','"
                    + bean.getMacno() + "','" + bean.getCashier() + "','"
                    + bean.getR_Emp() + "','" + bean.getR_PluCode() + "','" + ThaiUtil.Unicode2ASCII(bean.getR_PName()) + "','"
                    + bean.getR_Unit() + "','" + bean.getR_Group() + "','" + bean.getR_Status() + "','"
                    + bean.getR_Normal() + "','" + bean.getR_Discount() + "','" + bean.getR_Service() + "','"
                    + bean.getR_Stock() + "','" + bean.getR_Set() + "','" + bean.getR_Vat() + "','"
                    + bean.getR_Type() + "','" + bean.getR_ETD() + "','" + bean.getR_Quan() + "','"
                    + bean.getR_Price() + "','" + bean.getR_Total() + "','" + bean.getR_PrType() + "','"
                    + bean.getR_PrCode() + "','" + bean.getR_PrDisc() + "','" + bean.getR_PrBath() + "','"
                    + bean.getR_PrAmt() + "','" + bean.getR_DiscBath() + "','" + bean.getR_PrCuType() + "','"
                    + bean.getR_PrCuQuan() + "','" + bean.getR_PrCuAmt() + "','" + bean.getR_Redule() + "','"
                    + bean.getR_Kic() + "','" + bean.getR_KicPrint() + "','" + bean.getR_Void() + "','"
                    + bean.getR_VoidUser() + "','" + bean.getR_VoidTime() + "','" + ThaiUtil.Unicode2ASCII(bean.getR_Opt1()) + "','"
                    + ThaiUtil.Unicode2ASCII(bean.getR_Opt2()) + "','" + ThaiUtil.Unicode2ASCII(bean.getR_Opt3()) + "','" + ThaiUtil.Unicode2ASCII(bean.getR_Opt4()) + "','"
                    + ThaiUtil.Unicode2ASCII(bean.getR_Opt5()) + "','" + ThaiUtil.Unicode2ASCII(bean.getR_Opt6()) + "','" + ThaiUtil.Unicode2ASCII(bean.getR_Opt7()) + "','"
                    + ThaiUtil.Unicode2ASCII(bean.getR_Opt8()) + "','" + ThaiUtil.Unicode2ASCII(bean.getR_Opt9()) + "','" + bean.getR_PrCuCode() + "','"
                    + bean.getR_Serve() + "','" + bean.getR_PrintOK() + "','" + bean.getR_KicOK() + "','"
                    + bean.getStkCode() + "','" + bean.getPosStk() + "','" + bean.getR_PrChkType() + "','"
                    + bean.getR_PrQuan() + "','" + bean.getR_PrSubType() + "','" + bean.getR_PrSubCode() + "','"
                    + bean.getR_PrSubQuan() + "','" + bean.getR_PrSubDisc() + "','" + bean.getR_PrSubBath() + "','"
                    + bean.getR_PrSubAmt() + "','" + bean.getR_PrSubAdj() + "','" + bean.getR_PrCuDisc() + "','"
                    + bean.getR_PrCuBath() + "','" + bean.getR_PrCuAdj() + "','" + bean.getR_QuanCanDisc() + "','"
                    + bean.getR_Order() + "','" + bean.getR_PItemNo() + "','" + bean.getR_PKicQue() + "','"
                    + bean.getR_MemSum() + "','" + bean.getR_MoveItem() + "','" + bean.getR_MoveFrom() + "','"
                    + bean.getR_MoveUser() + "','" + bean.getR_MoveFlag() + "','" + bean.getR_MovePrint() + "','"
                    + bean.getR_Pause() + "','','','" + bean.getR_LinkIndex() + "','','" + bean.getR_Index() + "','" + bean.getVoidMSG() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            int iUpdate = stmt.executeUpdate(sqlInsert);

            //clear Option file...
            Value.ClearOPT();
            stmt.close();
            return iUpdate > 0;
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.close();
        }
    }

    public ArrayList<BalanceBean> getAllBalance(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);

                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
                balanceBean.setR_LinkIndex(rs.getString("R_LinkIndex"));

                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }

                beanData.add(balanceBean);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public ArrayList<BalanceBean> getAllBalanceNoVoid(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' and r_void<>'V'"
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);

                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
                balanceBean.setR_LinkIndex(rs.getString("R_LinkIndex"));
                if (!balanceBean.getR_Opt1().equals("")) {
                    balanceBean.setR_Opt1("    *** " + rs.getString("R_Opt1"));
                }
                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }

                beanData.add(balanceBean);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public ArrayList<BalanceBean> getBalanceIndex(String R_Index) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from balance where R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
                balanceBean.setR_LinkIndex(rs.getString("R_LinkIndex"));

                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    MSG.ERR(e.getMessage());
                }

                beanData.add(balanceBean);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return beanData;
    }

    public ArrayList<BalanceBean> getAllBalancePromotion(String table) {
        ArrayList<BalanceBean> beanData = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from balance "
                    + "where R_Table='" + table + "' "
                    + "and R_Discount='Y' "
                    + "and R_Void <> 'V' "
                    + "group by R_PluCode "
                    + "order by R_PluCode, R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(rs.getString("R_Unit"));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);

                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));

                try {
                    balanceBean.setR_Date(rs.getDate("R_Date"));
                } catch (SQLException e) {
                    MSG.ERR(null, e.getMessage());
                }

                //balanceBean.setR_CashCard(""+rs.getFloat("R_CashCard"));
                beanData.add(balanceBean);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        }

        return beanData;
    }

    public void updateTableHold(String table, String emp) {
        String date_default = "1899-12-30";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile set "
                    + "TOnAct='N',"
                    + "TCurTime=curtime(),"
                    + "Service='" + POSConfigSetup.Bean().getP_Service() + "',"
                    + "MemBegin='" + date_default + "',"
                    + "MemEnd='" + date_default + "' "
                    + "where tcode='" + table + "' "
                    + "and tOnAct='Y'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

    public String getIndexBalance(String R_Table) {
        String index = R_Table + "/001";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select max(R_Index) R_Index "
                    + "from balance "
                    + "where R_Table = '" + R_Table + "' "
                    + "order by R_Index";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String R_Index;
            boolean notfound = false;
            while (rs.next()) {
                notfound = true;
                R_Index = rs.getString("R_Index");

                if (R_Index == null) {
                    break;
                }

                String[] data = R_Index.split("/");
                int id;
                try {
                    id = Integer.parseInt(data[1]);
                    id = id + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }

                if (id < 10) {
                    index = R_Table + "/00" + id;
                } else if (id < 100) {
                    index = R_Table + "/0" + id;
                } else if (id < 1000) {
                    index = R_Table + "/" + id;
                }
            }
            if (!notfound) {
                //not found data
                index = R_Table + "/001";
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            index = R_Table + "/001";
        } finally {
            mysql.close();
        }

        return index;
    }

    public void setDefaultBalance(String table) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        String sql = "delete from balance "
                + "where R_Table='" + table + "'";
        try {
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

    public boolean checkQuantity(String table, String R_PluCode, double R_Quan) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select sum(R_Quan) R_Quan "
                    + "from balance "
                    + "where R_PluCode='" + R_PluCode + "' "
                    + "and R_Table='" + table + "' "
                    + "and R_Void<>'V' "
                    + "group by R_PluCode";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            double quan = R_Quan;
            if (rs.next()) {
                quan = rs.getDouble("R_Quan");
                quan += R_Quan;
            }
            rs.close();
            stmt.close();
            return quan >= 0;
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.close();
        }
    }

    public BalanceBean getProduct(String PCode, String R_Index) {
        BalanceBean balanceBean = new BalanceBean();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from balance "
                    + "where R_PluCode='" + PCode + "' "
                    + "and R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(ThaiUtil.ASCII2Unicode(rs.getString("R_Unit")));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return balanceBean;
    }

    public BalanceBean getBalanceIndex(String Table, String R_Index) {
        BalanceBean balanceBean = null;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from balance "
                    + "where R_Table='" + Table + "' "
                    + "and R_Index='" + R_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Date(rs.getDate("R_Date"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(ThaiUtil.ASCII2Unicode(rs.getString("R_Unit")));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));

                balanceBean.setR_LinkIndex(rs.getString("R_LinkIndex"));
                balanceBean.setR_SPIndex(rs.getString("R_SPIndex"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            mysql.close();
        }

        return balanceBean;
    }

    public ArrayList<BalanceBean> getBalanceIndexVoid(String Table) {
        ArrayList<BalanceBean> list = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * "
                    + "from balance "
                    + "where r_table='" + Table + "' "
                    + "and r_void='V' "
                    + "and r_pause='Y' "
                    + "and r_kicprint=''";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BalanceBean balanceBean = new BalanceBean();
                balanceBean.setR_Index(rs.getString("R_Index"));
                balanceBean.setR_Table(rs.getString("R_Table"));
                balanceBean.setR_Time(rs.getString("R_Time"));
                balanceBean.setMacno(rs.getString("Macno"));
                balanceBean.setCashier(rs.getString("Cashier"));
                balanceBean.setR_Emp(rs.getString("R_Emp"));
                balanceBean.setR_PluCode(rs.getString("R_PluCode"));
                balanceBean.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                balanceBean.setR_Unit(ThaiUtil.ASCII2Unicode(rs.getString("R_Unit")));
                balanceBean.setR_Group(rs.getString("R_Group"));
                balanceBean.setR_Status(rs.getString("R_Status"));
                balanceBean.setR_Normal(rs.getString("R_Normal"));
                balanceBean.setR_Discount(rs.getString("R_Discount"));
                balanceBean.setR_Service(rs.getString("R_Service"));
                balanceBean.setR_Stock(rs.getString("R_Stock"));
                balanceBean.setR_Set(rs.getString("R_Set"));
                balanceBean.setR_Vat(rs.getString("R_Vat"));
                balanceBean.setR_Type(rs.getString("R_Type"));
                balanceBean.setR_ETD(rs.getString("R_ETD"));
                balanceBean.setR_Quan(rs.getFloat("R_Quan"));
                balanceBean.setR_Price(rs.getFloat("R_Price"));
                balanceBean.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                balanceBean.setR_PrType(R_PrType);
                balanceBean.setR_PrCode(rs.getString("R_PrCode"));
                balanceBean.setR_PrDisc(rs.getFloat("R_PrDisc"));
                balanceBean.setR_PrBath(rs.getFloat("R_PrBath"));
                balanceBean.setR_PrAmt(rs.getFloat("R_PrAmt"));
                balanceBean.setR_DiscBath(rs.getFloat("R_DiscBath"));
                balanceBean.setR_PrCuType(rs.getString("R_PrCuType"));
                balanceBean.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                balanceBean.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                balanceBean.setR_Redule(rs.getFloat("R_Redule"));
                balanceBean.setR_Kic(rs.getString("R_Kic"));
                balanceBean.setR_KicPrint(rs.getString("R_KicPrint"));
                balanceBean.setR_Void(rs.getString("R_Void"));
                balanceBean.setR_VoidUser(rs.getString("R_VoidUser"));
                balanceBean.setR_VoidTime(rs.getString("R_VoidTime"));
                balanceBean.setR_Opt1(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt1")));
                balanceBean.setR_Opt2(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt2")));
                balanceBean.setR_Opt3(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt3")));
                balanceBean.setR_Opt4(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt4")));
                balanceBean.setR_Opt5(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt5")));
                balanceBean.setR_Opt6(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt6")));
                balanceBean.setR_Opt7(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt7")));
                balanceBean.setR_Opt8(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt8")));
                balanceBean.setR_Opt9(ThaiUtil.ASCII2Unicode(rs.getString("R_Opt9")));
                balanceBean.setVoidMSG(ThaiUtil.ASCII2Unicode(rs.getString("VoidMSG")));
                balanceBean.setR_PrCuCode(rs.getString("R_PrCuCode"));
                balanceBean.setR_Serve(rs.getString("R_Serve"));
                balanceBean.setR_PrintOK(rs.getString("R_PrintOK"));
                balanceBean.setR_KicOK(rs.getString("R_KicOK"));
                balanceBean.setStkCode(rs.getString("StkCode"));
                balanceBean.setPosStk(rs.getString("PosStk"));
                balanceBean.setR_PrChkType(rs.getString("R_PrChkType"));
                balanceBean.setR_PrQuan(rs.getFloat("R_PrQuan"));
                balanceBean.setR_PrSubType(rs.getString("R_PrSubType"));
                balanceBean.setR_PrSubCode(rs.getString("R_PrSubCode"));
                balanceBean.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                balanceBean.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                balanceBean.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                balanceBean.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                balanceBean.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                balanceBean.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                balanceBean.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                balanceBean.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                balanceBean.setR_QuanCanDisc(rs.getFloat("R_QuanCanDisc"));
                balanceBean.setR_Order(rs.getString("R_Order"));
                balanceBean.setR_PItemNo(rs.getInt("R_PItemNo"));
                balanceBean.setR_PKicQue(rs.getInt("R_PKicQue"));
                balanceBean.setR_MemSum(rs.getString("R_MemSum"));
                balanceBean.setR_MoveItem(rs.getString("R_MoveItem"));
                balanceBean.setR_MoveFrom(rs.getString("R_MoveFrom"));
                balanceBean.setR_MoveUser(rs.getString("R_MoveUser"));
                balanceBean.setR_MoveFlag(rs.getString("R_MoveFlag"));
                balanceBean.setR_MovePrint(rs.getString("R_MovePrint"));
                balanceBean.setR_Pause(rs.getString("R_Pause"));

                list.add(balanceBean);
            }
            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            mysql.close();
        }

        return list;
    }

    public void holdTableToKichen(String table) {
        kichenPrint(table);
    }

    public void kichenPrint(String table) {

    }

    public String SeekKicItemNo() {
        int KicItemNo = 0;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            String sql = "select * from branch";
            ResultSet rec = stmt.executeQuery(sql);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                KicItemNo = rec.getInt("kicitemno") + 1;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        mysql.open();

        try {
            Statement stmt = mysql.getConnection().createStatement();
            String sql = "update branch set kicitemno =" + KicItemNo;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return "" + KicItemNo;
    }

    public void deleteBalance(String r_Table, String r_PluCode, String r_Index) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from balance "
                    + "where R_Table = '" + r_Table + "' "
                    + "and R_PluCode = '" + r_PluCode + "' "
                    + "and R_Index = '" + r_Index + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

    public void deleteProduct(String table, String PCode, String R_Index) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from balance "
                    + "where R_Index='" + R_Index + "' "
                    + "and R_Table='" + table + "' ";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }
    }

    boolean backupBalance(String tableNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "delete from temp_balance";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);

            sql = "insert into temp_balance select * from balance where R_Table='" + tableNo + "';";
            stmt.executeUpdate(sql);

            stmt.close();

            return true;
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
            try {
                mysql.open();
                Statement stmt = mysql.getConnection().createStatement();
                stmt.executeUpdate("drop table if exists temp_balance;");
                stmt.executeUpdate("create table temp_balance select * from balance limit 0,0;");
                stmt.close();
            } catch (SQLException e1) {
                MSG.ERR(e1.getMessage());
                e1.printStackTrace();
            } finally {
                mysql.close();
            }
        } finally {
            mysql.close();
        }

        return false;
    }

    boolean restoreBalance(String tableNo, String table2) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {

            Statement stmt = mysql.getConnection().createStatement();

            // clear table 1
            String sql = "delete from balance where R_Table='" + tableNo + "'";
            stmt.executeUpdate(sql);

            // clear table 1-1
            sql = "delete from balance where R_Table='" + table2 + "'";
            stmt.executeUpdate(sql);

            sql = "insert into balance select * from temp_balance where R_Table='" + tableNo + "';";
            stmt.executeUpdate(sql);

            sql = "delete from temp_balance";
            stmt.executeUpdate(sql);

            return true;
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return false;
    }

    boolean copyProductTo(String table1, String table2, String R_Index, String PCode) {
        try {
            BalanceControl balanceControl = new BalanceControl();
            BalanceBean bean = balanceControl.getBalanceIndex(table1, R_Index);
            if (bean == null) {
                return false;
            }
//            BalanceBean bean = (BalanceBean)getProduct(PCode, R_Index);
            bean.setR_Index(getIndexBalance(table2));
            bean.setR_Table(table2);
            if (bean.getR_LinkIndex().split("/").length > 1) {
                String getIndex = bean.getR_LinkIndex().split("/")[1];
                bean.setR_LinkIndex(table2 + "/" + getIndex);
//            bean.setR_PluCode(PCode);
            }
            if (saveBalance(bean)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //MSG.ERR(e.getMessage());
        }

        return false;
    }

    public static void updateProSerTable(String table, MemberBean memberBean) {
        //คำนวณโปรโมชัน + ค่าบริการ และคำนวณภาษีมูลค่าเพิ่ม
        PromotionControl proControl = new PromotionControl();
        proControl.updatePromotion(table);

        // ให้ส่วนลดสมาชิก
        MemberControl memControl = new MemberControl();
        memControl.updateMemberDiscount(table, memberBean);

        //คำนวณส่วนลด
        DiscountControl discControl = new DiscountControl();
        discControl.updateDiscount(table);

        //คำนวณค่าบริการ + คำนวณภาษีมูลค่าเพิ่ม
        ServiceControl serviceControl = new ServiceControl();
        serviceControl.updateService(table);
        
    }

}
