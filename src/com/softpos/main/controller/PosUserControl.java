package com.softpos.main.controller;

import com.softpos.main.model.PosUserBean;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.MSG;

public class PosUserControl {
    
    public static PosUserBean getData(String username){
        PosUserBean bean = new PosUserBean();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from posuser where username=''";
            Statement stmt = mysql.getConnection().createStatement();
ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                bean.setUserName(rs.getString("UserName"));
                bean.setPassword(rs.getString("Password"));
                bean.setName(rs.getString("Name"));
                bean.setUserGroup(rs.getString("UserGroup"));
                bean.setOnACT(rs.getString("OnACT"));
                bean.setMacNo(rs.getString("MacNo"));
                bean.setSale1(rs.getString("Sale1"));
                bean.setSale2(rs.getString("Sale2"));
                bean.setSale3(rs.getString("Sale3"));
                bean.setSale4(rs.getString("Sale4"));
                bean.setSale5(rs.getString("Sale5"));
                bean.setSale6(rs.getString("Sale6"));
                bean.setSale7(rs.getString("Sale7"));
                bean.setSale8(rs.getString("Sale8"));
                bean.setSale9(rs.getString("Sale9"));
                bean.setSale10(rs.getString("Sale10"));
                bean.setSale11(rs.getString("Sale11"));
                bean.setSale12(rs.getString("Sale12"));
                bean.setSale13(rs.getString("Sale13"));
                bean.setSale14(rs.getString("Sale14"));
                bean.setSale15(rs.getString("Sale15"));
                bean.setSale16(rs.getString("Sale16"));
                bean.setSale17(rs.getString("Sale17"));
                bean.setSale18(rs.getString("Sale18"));
                bean.setSale19(rs.getString("Sale19"));
                bean.setSale20(rs.getString("Sale20"));
                bean.setSale21(rs.getString("Sale21"));
                bean.setSale22(rs.getString("Sale22"));
                bean.setSale23(rs.getString("Sale23"));
                bean.setSale24(rs.getString("Sale24"));
                bean.setSale25(rs.getString("Sale25"));
                bean.setSale26(rs.getString("Sale26"));
                bean.setSale27(rs.getString("Sale27"));
                bean.setSale28(rs.getString("Sale28"));
                bean.setSale29(rs.getString("Sale29"));
                bean.setSale30(rs.getString("Sale30"));
                bean.setSale31(rs.getString("Sale31"));
                bean.setSale32(rs.getString("Sale32"));
                bean.setSale33(rs.getString("Sale33"));
                bean.setSale34(rs.getString("Sale34"));
                bean.setSale35(rs.getString("Sale35"));
                bean.setSale36(rs.getString("Sale36"));
                bean.setCont0(rs.getString("Cont0"));
                bean.setCont1(rs.getString("Cont1"));
                bean.setCont2(rs.getString("Cont2"));
                bean.setCont3(rs.getString("Cont3"));
                bean.setCont4(rs.getString("Cont4"));
                bean.setCont5(rs.getString("Cont5"));
                bean.setCont6(rs.getString("Cont6"));
                bean.setCont7(rs.getString("Cont7"));
                bean.setCont8(rs.getString("Cont8"));
                bean.setCont9(rs.getString("Cont9"));
                bean.setCont10(rs.getString("Cont10"));
                bean.setCont11(rs.getString("Cont11"));
                bean.setCont12(rs.getString("Cont12"));
                bean.setCont13(rs.getString("Cont13"));
                bean.setCont14(rs.getString("Cont14"));
                bean.setCont15(rs.getString("Cont15"));
                bean.setStock0(rs.getString("Stock0"));
                bean.setStock0_1(rs.getString("Stock0_1"));
                bean.setStock1(rs.getString("Stock1"));
                bean.setStock2(rs.getString("Stock2"));
                bean.setStock3(rs.getString("Stock3"));
                bean.setStock4(rs.getString("Stock4"));
                bean.setStock5(rs.getString("Stock5"));
                bean.setStock6(rs.getString("Stock6"));
                bean.setStock7(rs.getString("Stock7"));
                bean.setStock8(rs.getString("Stock8"));
                bean.setStock9(rs.getString("Stock9"));
                bean.setStock10(rs.getString("Stock10"));
                bean.setStock11(rs.getString("Stock11"));
                bean.setStock12(rs.getString("Stock12"));
                bean.setStock13(rs.getString("Stock13"));
                bean.setStock14(rs.getString("Stock14"));
                bean.setStock15(rs.getString("Stock15"));
                bean.setStock16(rs.getString("Stock16"));
                bean.setStock17(rs.getString("Stock17"));
                bean.setStock18(rs.getString("Stock18"));
                bean.setStock19(rs.getString("Stock19"));
                bean.setStock20(rs.getString("Stock20"));
                bean.setStock21(rs.getString("Stock21"));
                bean.setStock22(rs.getString("Stock22"));
                bean.setStock23(rs.getString("Stock23"));
                bean.setStock24(rs.getString("Stock24"));
                bean.setStock25(rs.getString("Stock25"));
                bean.setStock26(rs.getString("Stock26"));
                bean.setStock27(rs.getString("Stock27"));
                bean.setStock28(rs.getString("Stock28"));
                bean.setStock29(rs.getString("Stock29"));
                bean.setStock30(rs.getString("Stock30"));
                bean.setStock31(rs.getString("Stock31"));
                bean.setStock32(rs.getString("Stock32"));
                bean.setStock33(rs.getString("Stock33"));
                bean.setStock34(rs.getString("Stock34"));
                bean.setStock35(rs.getString("Stock35"));
                bean.setStock36(rs.getString("Stock36"));
                bean.setStock37(rs.getString("Stock37"));
                bean.setStock38(rs.getString("Stock38"));
                bean.setStock39(rs.getString("Stock39"));
                bean.setStock40(rs.getString("Stock40"));
                bean.setStock41(rs.getString("Stock41"));
                bean.setStock42(rs.getString("Stock42"));
                bean.setStock43(rs.getString("Stock43"));
                bean.setStock44(rs.getString("Stock44"));
                bean.setStock45(rs.getString("Stock45"));
                bean.setStock46(rs.getString("Stock46"));
                bean.setStock47(rs.getString("Stock47"));
                bean.setStock48(rs.getString("Stock48"));
                bean.setStock49(rs.getString("Stock49"));
                bean.setStock50(rs.getString("Stock50"));
                bean.setStock51(rs.getString("Stock51"));
                bean.setStock52(rs.getString("Stock52"));
                bean.setStock53(rs.getString("Stock53"));
                bean.setStock54(rs.getString("Stock54"));
                bean.setStock55(rs.getString("Stock55"));
                bean.setStock56(rs.getString("Stock56"));
                bean.setStock57(rs.getString("Stock57"));
                bean.setStock58(rs.getString("Stock58"));
                bean.setStock59(rs.getString("Stock59"));
                bean.setStock60(rs.getString("Stock60"));
                bean.setStock61(rs.getString("Stock61"));
                bean.setStock62(rs.getString("Stock62"));
                bean.setStock63(rs.getString("Stock63"));
                bean.setStock64(rs.getString("Stock64"));
                bean.setStock65(rs.getString("Stock65"));
                bean.setStock66(rs.getString("Stock66"));
                bean.setStock67(rs.getString("Stock67"));
                bean.setStock68(rs.getString("Stock68"));
                bean.setStock69(rs.getString("Stock69"));
                bean.setStock70(rs.getString("Stock70"));
                bean.setStock71(rs.getString("Stock71"));
                bean.setStock72(rs.getString("Stock72"));
                bean.setStock73(rs.getString("Stock73"));
                bean.setStock74(rs.getString("Stock74"));
                bean.setCont16(rs.getString("Cont16"));
                bean.setCont17(rs.getString("Cont17"));
                bean.setCont18(rs.getString("Cont18"));
                bean.setCont19(rs.getString("Cont19"));
                bean.setCont20(rs.getString("Cont20"));
                bean.setCont21(rs.getString("Cont21"));
                bean.setCont22(rs.getString("Cont22"));
                bean.setCont23(rs.getString("Cont23"));
                bean.setCont24(rs.getString("Cont24"));
                bean.setCont25(rs.getString("Cont25"));
                bean.setCont26(rs.getString("Cont26"));
                bean.setCont27(rs.getString("Cont27"));
                bean.setCont28(rs.getString("Cont28"));
                bean.setCont29(rs.getString("Cont29"));
                bean.setCont30(rs.getString("Cont30"));
                bean.setCont31(rs.getString("Cont31"));
                bean.setCont32(rs.getString("Cont32"));
                bean.setCont33(rs.getString("Cont33"));
                bean.setCont34(rs.getString("Cont34"));
                bean.setCont35(rs.getString("Cont35"));
                bean.setCont36(rs.getString("Cont36"));
                bean.setCont37(rs.getString("Cont37"));
                bean.setCont38(rs.getString("Cont38"));
                bean.setCont39(rs.getString("Cont39"));
                bean.setCont40(rs.getString("Cont40"));
                bean.setCont41(rs.getString("Cont41"));
                bean.setCont42(rs.getString("Cont42"));
                bean.setCont43(rs.getString("Cont43"));
                bean.setCont44(rs.getString("Cont44"));
                bean.setCont45(rs.getString("Cont45"));
                bean.setCont46(rs.getString("Cont46"));
                bean.setCont47(rs.getString("Cont47"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
        }finally{
            mysql.close();
        }
        
        return bean;
    }
}
