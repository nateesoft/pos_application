package com.softpos.main.program;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class TableFileControl {

    public static final int TABLE_READY = 1;
    public static final int TABLE_NOT_ACTIVE = 2;
    public static final int TABLE_NOT_SETUP = 0;
    public static final int TABLE_EXIST_DATA = 3;
    public static final int TABLE_EXIST_DATA_IS_ACTIVE = 4;

    public TableFileControl() {
    }

    public void saveTableFile(TableFileBean tableBean) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "insert into tablefile "
                    + "(Tcode,SoneCode,TLoginDate,MacNo,Cashier,TLoginTime,TCurTime,TCustomer,"
                    + "TItem,TAmount,TOnAct,Service,ServiceAmt,EmpDisc,EmpDiscAmt,FastDisc,"
                    + "FastDiscAmt,TrainDisc,TrainDiscAmt,MemDisc,MemDiscAmt,SubDisc,SubDiscAmt,"
                    + "DiscBath,ProDiscAmt,SpaDiscAmt,CuponDiscAmt,ItemDiscAmt,MemCode,MemCurAmt,"
                    + "MemName,MemBegin,MemEnd,Food,Drink,Product,NetTotal,PrintTotal,PrintChkBill,"
                    + "PrintCnt,PrintTime1,PrintTime2,ChkBill,ChkBillTime,StkCode1,StkCode2,TDesk,"
                    + "TUser,TPause)"
                    + "values('" + tableBean.getTcode() + "','" + tableBean.getSoneCode() + "','"
                    + tableBean.getTLoginDate() + "','" + tableBean.getMacNo() + "','"
                    + tableBean.getCashier() + "','" + tableBean.getTLoginTime() + "','"
                    + tableBean.getTCurTime() + "','" + tableBean.getTCustomer() + "','"
                    + tableBean.getTItem() + "','" + tableBean.getTAmount() + "','" + tableBean.getTOnAct() + "','"
                    + tableBean.getService() + "','" + tableBean.getServiceAmt() + "','"
                    + tableBean.getEmpDisc() + "','" + tableBean.getEmpDiscAmt() + "','"
                    + tableBean.getFastDisc() + "','" + tableBean.getFastDiscAmt() + "','"
                    + tableBean.getTrainDisc() + "','" + tableBean.getTrainDiscAmt() + "','"
                    + tableBean.getMemDisc() + "','" + tableBean.getMemDiscAmt() + "','"
                    + tableBean.getSubDisc() + "','" + tableBean.getSubDiscAmt() + "','"
                    + tableBean.getDiscBath() + "','" + tableBean.getProDiscAmt() + "','"
                    + tableBean.getSpaDiscAmt() + "','" + tableBean.getCuponDiscAmt() + "','"
                    + tableBean.getItemDiscAmt() + "','" + tableBean.getMemCode() + "','"
                    + tableBean.getMemCurAmt() + "','" + tableBean.getMemName() + "','"
                    + tableBean.getMemBegin() + "','" + tableBean.getMemEnd() + "','"
                    + tableBean.getFood() + "','" + tableBean.getDrink() + "','" + tableBean.getProduct() + "','"
                    + tableBean.getNetTotal() + "','" + tableBean.getPrintTotal() + "','"
                    + tableBean.getPrintChkBill() + "','" + tableBean.getPrintCnt() + "','"
                    + tableBean.getPrintTime1() + "','" + tableBean.getPrintTime2() + "','"
                    + tableBean.getChkBill() + "','" + tableBean.getChkBillTime() + "','"
                    + tableBean.getStkCode1() + "','" + tableBean.getStkCode2() + "','"
                    + tableBean.getTDesk() + "','" + tableBean.getTUser() + "','" + tableBean.getTPause() + "')";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void updateTableFile(TableFileBean tableFile) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile set "
                    + "Tcode='" + tableFile.getTcode() + "', SoneCode='" + tableFile.getSoneCode() + "', TLoginDate='"
                    + tableFile.getTLoginDate() + "', MacNo='" + tableFile.getMacNo() + "', Cashier='"
                    + tableFile.getCashier() + "', TLoginTime='" + tableFile.getTLoginTime() + "', TCurTime='"
                    + tableFile.getTCurTime() + "', TCustomer='" + tableFile.getTCustomer() + "', TItem='"
                    + tableFile.getTItem() + "', TAmount='" + tableFile.getTAmount() + "', TOnAct='"
                    + tableFile.getTOnAct() + "', Service='" + tableFile.getService() + "', ServiceAmt='"
                    + tableFile.getServiceAmt() + "', EmpDisc='" + tableFile.getEmpDisc() + "', EmpDiscAmt='"
                    + tableFile.getEmpDiscAmt() + "', FastDisc='" + tableFile.getFastDisc() + "', FastDiscAmt='"
                    + tableFile.getFastDiscAmt() + "', TrainDisc='" + tableFile.getTrainDisc() + "', TrainDiscAmt='"
                    + tableFile.getTrainDiscAmt() + "', MemDisc='" + tableFile.getMemDisc() + "', MemDiscAmt='"
                    + tableFile.getMemDiscAmt() + "', SubDisc='" + tableFile.getSubDisc() + "', SubDiscAmt='"
                    + tableFile.getSubDiscAmt() + "', DiscBath='" + tableFile.getDiscBath() + "', ProDiscAmt='"
                    + tableFile.getProDiscAmt() + "', SpaDiscAmt='" + tableFile.getSpaDiscAmt() + "', CuponDiscAmt='"
                    + tableFile.getCuponDiscAmt() + "', MemCode='"
                    + tableFile.getMemCode() + "', MemCurAmt='" + tableFile.getMemCurAmt() + "', MemName='"
                    + tableFile.getMemName() + "', MemBegin='" + tableFile.getMemBegin() + "', MemEnd='"
                    + tableFile.getMemEnd() + "', Food='" + tableFile.getFood() + "', Drink='"
                    + tableFile.getDrink() + "', Product='" + tableFile.getProduct() + "', NetTotal='"
                    + tableFile.getNetTotal() + "', PrintTotal='" + tableFile.getPrintTotal() + "', PrintChkBill='"
                    + tableFile.getPrintChkBill() + "', PrintCnt='" + tableFile.getPrintCnt() + "', PrintTime1='"
                    + tableFile.getPrintTime1() + "', PrintTime2='" + tableFile.getPrintTime2() + "', ChkBill='"
                    + tableFile.getChkBill() + "', ChkBillTime='" + tableFile.getChkBillTime() + "', TDesk='"
                    + tableFile.getTDesk() + "', TUser='" + tableFile.getTUser() + "', TPause='' "
                    + tableFile.getTPause() + "' "
                    + "where TCode='" + tableFile + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public int checkTableRead(String tableNo, String user) {
        int result;
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * "
                    + "from tablefile "
                    + "where TCode='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String TActive;
            int TItem;
            String TUser;
            String TOnAct;
            if (rs.next()) {
                //table no is exist
                TActive = "Y";//rs.getString("TActive");
                if (TActive.equalsIgnoreCase("Y")) {
                    TItem = rs.getInt("TItem");
                    TOnAct = rs.getString("TOnAct");
                    TUser = rs.getString("TUser");

                    if (TItem > 0) {
                        if (TOnAct.equalsIgnoreCase("Y")) {
                            if (TUser.equals(user)) {
                                result = TABLE_EXIST_DATA;
                            } else {
                                result = TABLE_EXIST_DATA_IS_ACTIVE;
                            }
                        } else {
                            result = TABLE_EXIST_DATA;
                        }
                    } else {
                        result = TABLE_READY;
                    }
                } else {
                    result = TABLE_NOT_ACTIVE;
                }
            } else {
                result = TABLE_NOT_SETUP;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            result = TABLE_NOT_SETUP;
        } finally {
            mysql.close();
        }

        return result;
    }

    public ArrayList<TableFileBean> getALlHoldTable() {
        ArrayList<TableFileBean> allTable = new ArrayList<>();
        String sql = "select * from tablefile where TAmount>0";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TableFileBean bean = new TableFileBean();
                bean.setTcode(rs.getString("Tcode"));
                bean.setSoneCode(rs.getString("SoneCode"));
                bean.setMacNo(rs.getString("MacNo"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTLoginTime(rs.getString("TLoginTime"));
                bean.setTCurTime(rs.getString("TCurTime"));
                bean.setTCustomer(rs.getInt("TCustomer"));
                bean.setTItem(rs.getInt("TItem"));
                bean.setTAmount(rs.getFloat("TAmount"));
                bean.setTOnAct(rs.getString("TOnAct"));
                bean.setService(rs.getFloat("Service"));
                bean.setServiceAmt(rs.getFloat("ServiceAmt"));
                bean.setEmpDisc(rs.getString("EmpDisc"));
                bean.setEmpDiscAmt(rs.getFloat("EmpDiscAmt"));
                bean.setFastDisc(rs.getString("FastDisc"));
                bean.setFastDiscAmt(rs.getFloat("FastDiscAmt"));
                bean.setTrainDisc(rs.getString("TrainDisc"));
                bean.setTrainDiscAmt(rs.getFloat("TrainDiscAmt"));
                bean.setMemDisc(rs.getString("MemDisc"));
                bean.setMemDiscAmt(rs.getFloat("MemDiscAmt"));
                bean.setSubDisc(rs.getString("SubDisc"));
                bean.setSubDiscAmt(rs.getFloat("SubDiscAmt"));
                bean.setDiscBath(rs.getFloat("DiscBath"));
                bean.setProDiscAmt(rs.getFloat("ProDiscAmt"));
                bean.setSpaDiscAmt(rs.getFloat("SpaDiscAmt"));
                bean.setCuponDiscAmt(rs.getFloat("CuponDiscAmt"));
                bean.setItemDiscAmt(rs.getFloat("ItemDiscAmt"));
                bean.setMemCode(rs.getString("MemCode"));
                bean.setMemCurAmt(rs.getFloat("MemCurAmt"));
                bean.setMemName(rs.getString("MemName"));
                bean.setFood(rs.getFloat("Food"));
                bean.setDrink(rs.getFloat("Drink"));
                bean.setProduct(rs.getFloat("Product"));
                bean.setNetTotal(rs.getFloat("NetTotal"));
                bean.setPrintTotal(rs.getFloat("PrintTotal"));
                bean.setPrintChkBill(rs.getString("PrintChkBill"));
                bean.setPrintCnt(rs.getInt("PrintCnt"));
                bean.setPrintTime1(rs.getString("PrintTime1"));
                bean.setPrintTime2(rs.getString("PrintTime2"));
                bean.setChkBill(rs.getString("ChkBill"));
                bean.setChkBillTime(rs.getString("ChkBillTime"));
                bean.setStkCode1(rs.getString("StkCode1"));
                bean.setStkCode2(rs.getString("StkCode2"));
                bean.setTDesk(rs.getInt("TDesk"));
                bean.setTUser(rs.getString("TUser"));
                bean.setTPause(rs.getString("TPause"));

                try {
                    bean.setTLoginDate(rs.getDate("TLoginDate"));
                    //bean.setMemBegin(rs.getDate("MemBegin"));
                    //bean.setMemEnd(rs.getDate("MemEnd"));
                } catch (SQLException e) {
                    System.out.println("Error Date: " + e.getMessage());
                }

                allTable.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return allTable;
    }

    public ArrayList<TableFileBean> getALlTable() {
        ArrayList<TableFileBean> allTable = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from tablefile";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TableFileBean bean = new TableFileBean();
                bean.setTcode(rs.getString("Tcode"));
                bean.setSoneCode(rs.getString("SoneCode"));
                bean.setMacNo(rs.getString("MacNo"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTLoginTime(rs.getString("TLoginTime"));
                bean.setTCurTime(rs.getString("TCurTime"));
                bean.setTCustomer(rs.getInt("TCustomer"));
                bean.setTItem(rs.getInt("TItem"));
                bean.setTAmount(rs.getFloat("TAmount"));
                bean.setTOnAct(rs.getString("TOnAct"));
                bean.setService(rs.getFloat("Service"));
                bean.setServiceAmt(rs.getFloat("ServiceAmt"));
                bean.setEmpDisc(rs.getString("EmpDisc"));
                bean.setEmpDiscAmt(rs.getFloat("EmpDiscAmt"));
                bean.setFastDisc(rs.getString("FastDisc"));
                bean.setFastDiscAmt(rs.getFloat("FastDiscAmt"));
                bean.setTrainDisc(rs.getString("TrainDisc"));
                bean.setTrainDiscAmt(rs.getFloat("TrainDiscAmt"));
                bean.setMemDisc(rs.getString("MemDisc"));
                bean.setMemDiscAmt(rs.getFloat("MemDiscAmt"));
                bean.setSubDisc(rs.getString("SubDisc"));
                bean.setSubDiscAmt(rs.getFloat("SubDiscAmt"));
                bean.setDiscBath(rs.getFloat("DiscBath"));
                bean.setProDiscAmt(rs.getFloat("ProDiscAmt"));
                bean.setSpaDiscAmt(rs.getFloat("SpaDiscAmt"));
                bean.setCuponDiscAmt(rs.getFloat("CuponDiscAmt"));
                bean.setItemDiscAmt(rs.getFloat("ItemDiscAmt"));
                bean.setMemCode(rs.getString("MemCode"));
                bean.setMemCurAmt(rs.getFloat("MemCurAmt"));
                bean.setMemName(rs.getString("MemName"));
                bean.setFood(rs.getFloat("Food"));
                bean.setDrink(rs.getFloat("Drink"));
                bean.setProduct(rs.getFloat("Product"));
                bean.setNetTotal(rs.getFloat("NetTotal"));
                bean.setPrintTotal(rs.getFloat("PrintTotal"));
                bean.setPrintChkBill(rs.getString("PrintChkBill"));
                bean.setPrintCnt(rs.getInt("PrintCnt"));
                bean.setPrintTime1(rs.getString("PrintTime1"));
                bean.setPrintTime2(rs.getString("PrintTime2"));
                bean.setChkBill(rs.getString("ChkBill"));
                bean.setChkBillTime(rs.getString("ChkBillTime"));
                bean.setStkCode1(rs.getString("StkCode1"));
                bean.setStkCode2(rs.getString("StkCode2"));
                bean.setTDesk(rs.getInt("TDesk"));
                bean.setTUser(rs.getString("TUser"));
                bean.setTPause(rs.getString("TPause"));

                try {
                    bean.setTLoginDate(rs.getDate("TLoginDate"));
                    bean.setMemBegin(rs.getDate("MemBegin"));
                    bean.setMemEnd(rs.getDate("MemEnd"));
                } catch (SQLException e) {
                    System.out.println("Error Date: " + e.getMessage());
                }

                allTable.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return allTable;
    }

    public ArrayList<TableSetup> getTable(String TCode) {
        ArrayList<TableSetup> allTable = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select t2.Code_ID, t1.TCode from tablefile t1 , tablesetup t2 "
                    + "where t1.TCode=t2.TCode "
                    + "and Code_ID like '" + TCode + "%' "
                    + "order by Code_ID";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TableSetup bean = new TableSetup();
                bean.setCode_ID(rs.getString("Code_ID"));
                bean.setTCode(rs.getString("TCode"));

                allTable.add(bean);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return allTable;
    }

    public TableFileBean getData(String table) {
        TableFileBean bean = new TableFileBean();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * "
                    + "from tablefile "
                    + "where Tcode='" + table + "' limit 1";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean.setTcode(rs.getString("Tcode"));
                bean.setSoneCode(rs.getString("SoneCode"));
                bean.setMacNo(rs.getString("MacNo"));
                bean.setCashier(rs.getString("Cashier"));
                bean.setTLoginTime(rs.getString("TLoginTime"));
                bean.setTCurTime(rs.getString("TCurTime"));
                bean.setTCustomer(rs.getInt("TCustomer"));
                bean.setTItem(rs.getInt("TItem"));
                bean.setTAmount(rs.getFloat("TAmount"));
                System.out.print(rs.getFloat("TAmount"));
                bean.setTOnAct(rs.getString("TOnAct"));
                bean.setService(rs.getFloat("Service"));
                bean.setServiceAmt(rs.getFloat("ServiceAmt"));
                bean.setEmpDisc(rs.getString("EmpDisc"));
                bean.setEmpDiscAmt(rs.getFloat("EmpDiscAmt"));
                bean.setFastDisc(rs.getString("FastDisc"));
                bean.setFastDiscAmt(rs.getFloat("FastDiscAmt"));
                bean.setTrainDisc(rs.getString("TrainDisc"));
                bean.setTrainDiscAmt(rs.getFloat("TrainDiscAmt"));
                
                bean.setMemDisc(rs.getString("MemDisc"));
                bean.setMemDiscAmt(rs.getFloat("MemDiscAmt"));
                bean.setSubDisc(rs.getString("SubDisc"));
                bean.setSubDiscAmt(rs.getFloat("SubDiscAmt"));
                bean.setDiscBath(rs.getFloat("DiscBath"));
                bean.setProDiscAmt(rs.getFloat("ProDiscAmt"));
                bean.setSpaDiscAmt(rs.getFloat("SpaDiscAmt"));
                bean.setCuponDiscAmt(rs.getFloat("CuponDiscAmt"));
                bean.setItemDiscAmt(rs.getFloat("ItemDiscAmt"));
                bean.setMemCode(rs.getString("MemCode"));
                if (bean.getMemCode() == null) {
                    bean.setMemCode("");
                }
                bean.setMemCurAmt(rs.getFloat("MemCurAmt"));
                bean.setMemName(ThaiUtil.ASCII2Unicode(rs.getString("MemName")));
                if (bean.getMemName() == null) {
                    bean.setMemName("");
                }
                bean.setFood(rs.getFloat("Food"));
                bean.setDrink(rs.getFloat("Drink"));
                bean.setProduct(rs.getFloat("Product"));

                bean.setNetTotal(rs.getFloat("NetTotal"));
                bean.setPrintTotal(rs.getFloat("PrintTotal"));
                bean.setPrintChkBill(rs.getString("PrintChkBill"));
                bean.setPrintCnt(rs.getInt("PrintCnt"));
                bean.setPrintTime1(rs.getString("PrintTime1"));
                bean.setPrintTime2(rs.getString("PrintTime2"));
                bean.setChkBill(rs.getString("ChkBill"));
                bean.setChkBillTime(rs.getString("ChkBillTime"));
                bean.setStkCode1(rs.getString("StkCode1"));
                bean.setStkCode2(rs.getString("StkCode2"));
                bean.setTDesk(rs.getInt("TDesk"));
                bean.setTUser(rs.getString("TUser"));
                bean.setTPause(rs.getString("TPause"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return bean;
    }

    public void setDefaultTableFile(String table) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        //clear = update to default not delete
        String sql = "update tablefile set "
                + "MacNo='', Cashier='', TLoginTime='00:00:00', TCurTime='00:00', TCustomer='0', TItem='0', TAmount='0.00', TOnAct='N', "
                + "Service='0.00', ServiceAmt='0.00', EmpDisc='', EmpDiscAmt='0.00', FastDisc='', FastDiscAmt='0.00', TrainDisc='', "
                + "TrainDiscAmt='0.00', MemDisc='', MemDiscAmt='0.00', SubDisc='', SubDiscAmt='0.00', DiscBath='0.00', ProDiscAmt='0.00', "
                + "SpaDiscAmt='0.00', CuponDiscAmt='0.00', ItemDiscAmt='0.00', MemCode='', MemCurAmt='0.00', MemName='', "
                + "Food='0.00', Drink='0.00', Product='0.00', NetTotal='0.00', PrintTotal='0.00', PrintChkBill='N', "
                + "PrintCnt='0', PrintTime1='', PrintTime2='', ChkBill='N', ChkBillTime='00:00:00', StkCode2='', TDesk='0', "
                + "TUser='', TPause='N' "
                + "where Tcode='" + table + "'";
        try {
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            if (table.indexOf("-") != -1) {
                sql = "delete from tablefile where Tcode='" + table + "'";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sql);
                stmt1.close();
            }
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void updateTableActive(String table, String customer, String emp) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile set "
                    + "TOnAct='Y',"
                    + "TLoginDate=now(),"
                    + "TLoginTime=curtime(),"
                    + "TCustomer='" + customer + "',"
                    + "TUser='" + emp + "',"
                    + "TCurTime=curtime() "
                    + "where tcode='" + table + "' "
                    + "and tOnAct='N'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            PromotionControl proControl = new PromotionControl();
            proControl.updatePromotion(table);

            //คำนวณค่าบริการ + คำนวณภาษีมูลค่าเพิ่ม
            ServiceControl serviceControl = new ServiceControl();
            serviceControl.updateService(table);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public void updateMacno(String table, String username) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile set "
                    + "MacNo='" + Value.MACNO + "',"
                    + "TUser='" + username + "' "
                    + "where Tcode='" + table + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public void createNewTableSplit(TableFileBean table, String newTable) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String chk = "select * from tablefile where tcode='" + newTable + "'";
            Statement stmt = mysql.getConnection().createStatement();
            if (stmt.executeQuery(chk).next()) {
                // มีตารางในระบบแล้ว
                System.out.println("มีตาราง " + newTable + " ในระบบแล้ว");
            } else {
                String sql = "insert into tablefile (Tcode,SoneCode,StkCode1,StkCode2,TLoginTime, TCurTime, TLoginDate, MemBegin, MemEnd) "
                        + "value('" + newTable + "','" + table.getSoneCode() + "','" + table.getStkCode1() + "',' ',curtime(), curtime(), curdate(),'1899-12-30','1899-12-30');";
                Statement stmt1 = mysql.getConnection().createStatement();
                stmt1.executeUpdate(sql);
                stmt1.close();
            }

            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public String getSplitTable(String tableNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from tablefile where Tcode='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String[] data = rs.getString("Tcode").split("-");
                if (data.length <= 1) {
                    return tableNo;
                } else {
                    int index = 0;
                    try {
                        index = Integer.parseInt(data[1]) + 1;
                    } catch (NumberFormatException e) {
                        index = 1;
                    }
                    rs.close();
                    stmt.close();
                    return getSplitTable(data[0] + "-" + index);
                }
            } else {
                rs.close();
                stmt.close();
                return tableNo;
            }
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            return tableNo;
        } finally {
            mysql.close();
        }
    }

    public static void updateTableFile(String tableNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            double TAmount = 0.00;
            BalanceControl bControl = new BalanceControl();
            ArrayList<BalanceBean> dataBean = bControl.getAllBalance(tableNo);
            for (int i = 0; i < dataBean.size(); i++) {
                BalanceBean bean = (BalanceBean) dataBean.get(i);
                if (!bean.getR_Void().equals("V")) {
                    TAmount += bean.getR_Total();
                }
            }

            //update tablefile
            String sql = "update tablefile "
                    + "set TAmount='" + TAmount + "' "
                    + "where Tcode='" + tableNo + "'";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            //คำนวณโปรโมชัน + ค่าบริการ และคำนวณภาษีมูลค่าเพิ่ม
            PromotionControl proControl = new PromotionControl();
            proControl.updatePromotion(tableNo);

            //คำนวณค่าบริการ + คำนวณภาษีมูลค่าเพิ่ม
            ServiceControl serviceControl = new ServiceControl();
            serviceControl.updateService(tableNo);

        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public boolean checkTableOpened(String tableNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select Cashier from tablefile "
                    + "where TOnact='Y' "
                    + "and tcode='" + tableNo + "';";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                rs.close();
                stmt.close();
                return true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }

        return false;
    }

    public void createNewTable(String tableTemp) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate("delete from tablefile where tcode='" + tableTemp + "';");
            stmt.executeUpdate("insert into tablefile(Tcode) values('" + tableTemp + "');");

            stmt.close();
            setDefaultTableFile(tableTemp);
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    void updateTableNotActive(String TABLE_NO) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile set tonact='N' where tcode='" + TABLE_NO + "';";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    void updateTableActive(String TABLE_2) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "update tablefile set tonact='Y' where tcode='" + TABLE_2 + "';";
            Statement stmt = mysql.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public int getItemCount(String tableNo) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        int countItem = 0;
        try {
            String sql = "select r_linkindex from balance "
                    + "where r_table='" + tableNo + "' "
                    + "and r_linkindex<>'' "
                    + "group by R_LinkIndex";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                countItem++;
            }

            rs.close();

            String sql1 = "select r_index from balance "
                    + "where r_table='" + tableNo + "' "
                    + "and r_linkindex=''";
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                countItem++;
            }

            rs.close();
            String sql2 = "select r_index from balance "
                    + "where r_table='" + tableNo + "' "
                    + "and r_linkindex is null";
            rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                countItem++;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return countItem;
    }

}
