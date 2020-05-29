package com.softpos.main.controller;

import database.MySQLConnect;
import java.sql.ResultSet;
import com.softpos.main.model.CustomerBean;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class CustomerConrol {

    public CustomerBean getCustomer(String custCode) {
        CustomerBean bean = new CustomerBean();
        String sql = "select * from customer where sp_code='" + custCode + "'";
        try {
            MySQLConnect c = new MySQLConnect();
            c.open();
            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                bean.setSp_code(rs.getString("sp_code"));
                bean.setSp_Desc(ThaiUtil.ASCII2Unicode(rs.getString("sp_Desc")));
                bean.setSp_Addr1(ThaiUtil.ASCII2Unicode(rs.getString("sp_Addr1")));
                bean.setSp_Addr2(ThaiUtil.ASCII2Unicode(rs.getString("sp_Addr2")));
                bean.setSp_zip(rs.getString("sp_zip"));
                bean.setTel(rs.getString("tel"));
                bean.setFax(rs.getString("fax"));
                bean.setContack(ThaiUtil.ASCII2Unicode(rs.getString("Contack")));
                bean.setRemark(ThaiUtil.ASCII2Unicode(rs.getString("Remark")));
                bean.setRemark2(ThaiUtil.ASCII2Unicode(rs.getString("Remark2")));
            }
            c.close();
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }
        return bean;

    }
}
