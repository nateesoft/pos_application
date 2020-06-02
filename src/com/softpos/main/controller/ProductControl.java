package com.softpos.main.controller;

import com.softpos.main.model.ProductBean;
import com.softpos.database.util.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sun.natee.project.util.ThaiUtil;
import com.softpos.main.utils.MSG;

public class ProductControl {

    private ArrayList<ProductBean> dataProduct = null;
    public static final int PRODUCT_NOT_FOUND = 0;
    public static final int PRODUCT_ACTIVE = 1;
    public static final int PRODUCT_NOT_ACTIVE = 2;

    public ProductControl() {
        dataProduct = new ArrayList<>();
    }

    public ProductBean getData(String PCode) {
        String sql = "select * "
                + "from product "
                + "where PCode='" + PCode + "'";
        ProductBean productBean = new ProductBean();
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                productBean.setPCode(rs.getString("PCode"));
                productBean.setPFix(rs.getString("PFix"));
                productBean.setPReferent(rs.getString("PReferent"));
                productBean.setPAccNo(rs.getString("PAccNo"));
                productBean.setPGroup(rs.getString("PGroup"));
                productBean.setPVender(rs.getString("PVender"));
                productBean.setPType(rs.getString("PType"));
                productBean.setPNormal(rs.getString("PNormal"));
                productBean.setPRemark(rs.getString("PRemark"));
                productBean.setPDiscount(rs.getString("PDiscount"));
                productBean.setPService(rs.getString("PService"));
                productBean.setPStatus(rs.getString("PStatus"));
                productBean.setPStock(rs.getString("PStock"));
                productBean.setPSet(rs.getString("PSet"));
                productBean.setPVat(rs.getString("PVat"));
                productBean.setPDesc(ThaiUtil.ASCII2Unicode(rs.getString("PDesc")));
                productBean.setPUnit1(ThaiUtil.ASCII2Unicode(rs.getString("PUnit1")));
                productBean.setPPack1(rs.getInt("PPack1"));
                productBean.setPArea(rs.getString("PArea"));
                productBean.setPKic(rs.getString("PKic"));
                productBean.setPPrice11(rs.getFloat("PPrice11"));
                productBean.setPPrice12(rs.getFloat("PPrice12"));
                productBean.setPPrice13(rs.getFloat("PPrice13"));
                productBean.setPPrice14(rs.getFloat("PPrice14"));
                productBean.setPPrice15(rs.getFloat("PPrice15"));
                productBean.setPPromotion1(rs.getString("PPromotion1"));
                productBean.setPPromotion2(rs.getString("PPromotion2"));
                productBean.setPPromotion3(rs.getString("PPromotion3"));
                productBean.setPMax(rs.getFloat("PMax"));
                productBean.setPMin(rs.getFloat("PMin"));
                productBean.setPBUnit(rs.getString("PBUnit"));
                productBean.setPBPack(rs.getFloat("PBPack"));
                productBean.setPLCost(rs.getFloat("PLCost"));
                productBean.setPSCost(rs.getFloat("PSCost"));
                productBean.setPACost(rs.getFloat("PACost"));
                productBean.setPLink1(rs.getString("PLink1"));
                productBean.setPLink2(rs.getString("PLink2"));
                productBean.setPLastTime(rs.getString("PLastTime"));
                productBean.setPUserUpdate(rs.getString("PUserUpdate"));
                productBean.setPBarCode(rs.getString("PBarCode"));
                productBean.setPActive(rs.getString("PActive"));
                productBean.setPSPVat(rs.getString("PSPVat"));
                productBean.setPSPVatAmt(rs.getFloat("PSPVatAmt"));
                productBean.setPOSStk(rs.getString("POSStk"));
                productBean.setMSStk(rs.getString("MSStk"));
                productBean.setPTimeCharge(rs.getFloat("PTimeCharge"));
                productBean.setPOrder(rs.getString("POrder"));
                productBean.setPFoodType(rs.getString("PFoodType"));
                productBean.setPPackOld(rs.getInt("PPackOld"));
                productBean.setPDesc2(rs.getString("PDesc2"));
                productBean.setPselectItem(rs.getString("PselectItem"));
                productBean.setPselectNum(rs.getFloat("PselectNum"));
                productBean.setPShowOption(rs.getString("PShowOption"));
                productBean.setPEDesc(rs.getString("PEDesc"));
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return productBean;
    }

    public ArrayList<ProductBean> searchAllProductBy(String PCode) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            PCode = ThaiUtil.Unicode2ASCII(PCode);
            String sql = "select * from product "
                    + "where PCode like '%" + PCode + "%' "
                    + "or PDesc like '%" + PCode + "%'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            ProductBean p;
            while (rs.next()) {
                p = new ProductBean();
                p.setPCode(rs.getString("PCode"));
                p.setPFix(rs.getString("PFix"));
                p.setPReferent(rs.getString("PReferent"));
                p.setPAccNo(rs.getString("PAccNo"));
                p.setPGroup(rs.getString("PGroup"));
                p.setPVender(rs.getString("PVender"));
                p.setPType(rs.getString("PType"));
                p.setPNormal(rs.getString("PNormal"));
                p.setPRemark(rs.getString("PRemark"));
                p.setPDiscount(rs.getString("PDiscount"));
                p.setPService(rs.getString("PService"));
                p.setPStatus(rs.getString("PStatus"));
                p.setPStock(rs.getString("PStock"));
                p.setPSet(rs.getString("PSet"));
                p.setPVat(rs.getString("PVat"));
                p.setPDesc(ThaiUtil.ASCII2Unicode(rs.getString("PDesc")));
                p.setPUnit1(ThaiUtil.ASCII2Unicode(rs.getString("PUnit1")));
                p.setPPack1(rs.getInt("PPack1"));
                p.setPArea(rs.getString("PArea"));
                p.setPKic(rs.getString("PKic"));
                p.setPPrice11(rs.getFloat("PPrice11"));
                p.setPPrice12(rs.getFloat("PPrice12"));
                p.setPPrice13(rs.getFloat("PPrice13"));
                p.setPPrice14(rs.getFloat("PPrice14"));
                p.setPPrice15(rs.getFloat("PPrice15"));
                p.setPPromotion1(rs.getString("PPromotion1"));
                p.setPPromotion2(rs.getString("PPromotion2"));
                p.setPPromotion3(rs.getString("PPromotion3"));
                p.setPMax(rs.getFloat("PMax"));
                p.setPMin(rs.getFloat("PMin"));
                p.setPBUnit(rs.getString("PBUnit"));
                p.setPBPack(rs.getFloat("PBPack"));
                p.setPLCost(rs.getFloat("PLCost"));
                p.setPSCost(rs.getFloat("PSCost"));
                p.setPACost(rs.getFloat("PACost"));
                p.setPLink1(rs.getString("PLink1"));
                p.setPLink2(rs.getString("PLink2"));
                p.setPLastTime(rs.getString("PLastTime"));
                p.setPUserUpdate(rs.getString("PUserUpdate"));
                p.setPBarCode(rs.getString("PBarCode"));
                p.setPActive(rs.getString("PActive"));
                p.setPSPVat(rs.getString("PSPVat"));
                p.setPSPVatAmt(rs.getFloat("PSPVatAmt"));
                p.setPOSStk(rs.getString("POSStk"));
                p.setMSStk(rs.getString("MSStk"));
                p.setPTimeCharge(rs.getFloat("PTimeCharge"));
                p.setPOrder(rs.getString("POrder"));
                p.setPFoodType(rs.getString("PFoodType"));
                p.setPPackOld(rs.getInt("PPackOld"));
                p.setPDesc2(rs.getString("PDesc2"));
                p.setPselectItem(rs.getString("PselectItem"));
                p.setPselectNum(rs.getFloat("PselectNum"));
                p.setPShowOption(rs.getString("PShowOption"));
                p.setPEDesc(rs.getString("PEName"));

                dataProduct.add(p);
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return dataProduct;
    }

    public ArrayList<ProductBean> searchAllProductBy2(String key) {
        ArrayList<ProductBean> arrList = new ArrayList<>();
        if (key.trim().equals("")) {
            return arrList;
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            key = ThaiUtil.Unicode2ASCII(key);
            String sql = "select p.PCode, PGroup, PDesc, PUnit1, PPrice11, PPrice12, PPrice13,"
                    + "PPrice14, PPrice15, Code_Type, PPathName "
                    + "from menusetup m, product p "
                    + "where m.PCode=p.PCode AND PDesc like '%" + key + "%' "
                    + "and PActive='Y' and PFix='F' "
                    + "group by PCode "
                    + "limit 0, 20";

//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                ProductBean product = new ProductBean();
                product.setPCode(rs.getString("PCode"));
                product.setPGroup(rs.getString("PGroup"));
                product.setPDesc(ThaiUtil.ASCII2Unicode(rs.getString("PDesc")));
                product.setPUnit1(ThaiUtil.ASCII2Unicode(rs.getString("PUnit1")));
                product.setPPrice11(rs.getDouble("PPrice11"));
                product.setPPrice12(rs.getDouble("PPrice12"));
                product.setPPrice13(rs.getDouble("PPrice13"));
                product.setPPrice14(rs.getDouble("PPrice14"));
                product.setPPrice15(rs.getDouble("PPrice15"));
                product.setPPathName(rs.getString("PPathName"));
                product.setPDesc2(new MenuOrderControl().getMenuItemAt(product.getPCode()));

                arrList.add(product);
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return arrList;
    }

    public ArrayList<ProductBean> searchAllProductBy3(String key) {
        ArrayList<ProductBean> arrList = new ArrayList<>();
        if (key.trim().equals("")) {
            return arrList;
        }

        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            key = ThaiUtil.Unicode2ASCII(key);
            String sql = "select MenuItem, PCode, PGroup, PDesc, PUnit1, "
                    + "PPrice11, PPrice12, PPrice13, PPrice14, PPrice15 "
                    + "from product p, menulist m "
                    + "where p.PCode=m.PLUCode "
                    + "and PActive='Y' "
                    + "and PFix = 'F' "
                    + "and MenuActive='Y' "
                    + "and MenuItem like '%" + key + "%' "
                    + "order by MenuItem "
                    + "limit 0,20";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductBean product = new ProductBean();
                product.setPCode(rs.getString("PCode"));
                product.setPGroup(rs.getString("PGroup"));
                product.setPDesc(ThaiUtil.ASCII2Unicode(rs.getString("PDesc")));
                product.setPUnit1(ThaiUtil.ASCII2Unicode(rs.getString("PUnit1")));
                product.setPPrice11(rs.getDouble("PPrice11"));
                product.setPPrice12(rs.getDouble("PPrice12"));
                product.setPPrice13(rs.getDouble("PPrice13"));
                product.setPPrice14(rs.getDouble("PPrice14"));
                product.setPPrice15(rs.getDouble("PPrice15"));
                product.setPPathName("");
                product.setPDesc2(rs.getString("MenuItem"));

                arrList.add(product);
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return arrList;
    }

    public ArrayList<ProductBean> getAllProductByGroup(String PGroup) {
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from product where PGroup ='" + PGroup + "'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            ProductBean p;
            while (rs.next()) {
                p = new ProductBean();
                p.setPCode(rs.getString("PCode"));
                p.setPFix(rs.getString("PFix"));
                p.setPReferent(rs.getString("PReferent"));
                p.setPAccNo(rs.getString("PAccNo"));
                p.setPGroup(rs.getString("PGroup"));
                p.setPVender(rs.getString("PVender"));
                p.setPType(rs.getString("PType"));
                p.setPNormal(rs.getString("PNormal"));
                p.setPRemark(rs.getString("PRemark"));
                p.setPDiscount(rs.getString("PDiscount"));
                p.setPService(rs.getString("PService"));
                p.setPStatus(rs.getString("PStatus"));
                p.setPStock(rs.getString("PStock"));
                p.setPSet(rs.getString("PSet"));
                p.setPVat(rs.getString("PVat"));
                p.setPDesc(ThaiUtil.ASCII2Unicode(rs.getString("PDesc")));
                p.setPUnit1(ThaiUtil.ASCII2Unicode(rs.getString("PUnit1")));
                p.setPPack1(rs.getInt("PPack1"));
                p.setPArea(rs.getString("PArea"));
                p.setPKic(rs.getString("PKic"));
                p.setPPrice11(rs.getFloat("PPrice11"));
                p.setPPrice12(rs.getFloat("PPrice12"));
                p.setPPrice13(rs.getFloat("PPrice13"));
                p.setPPrice14(rs.getFloat("PPrice14"));
                p.setPPrice15(rs.getFloat("PPrice15"));
                p.setPPromotion1(rs.getString("PPromotion1"));
                p.setPPromotion2(rs.getString("PPromotion2"));
                p.setPPromotion3(rs.getString("PPromotion3"));
                p.setPMax(rs.getFloat("PMax"));
                p.setPMin(rs.getFloat("PMin"));
                p.setPBUnit(rs.getString("PBUnit"));
                p.setPBPack(rs.getFloat("PBPack"));
                p.setPLCost(rs.getFloat("PLCost"));
                p.setPSCost(rs.getFloat("PSCost"));
                p.setPACost(rs.getFloat("PACost"));
                p.setPLink1(rs.getString("PLink1"));
                p.setPLink2(rs.getString("PLink2"));
                p.setPLastTime(rs.getString("PLastTime"));
                p.setPUserUpdate(rs.getString("PUserUpdate"));
                p.setPBarCode(rs.getString("PBarCode"));
                p.setPActive(rs.getString("PActive"));
                p.setPSPVat(rs.getString("PSPVat"));
                p.setPSPVatAmt(rs.getFloat("PSPVatAmt"));
                p.setPOSStk(rs.getString("POSStk"));
                p.setMSStk(rs.getString("MSStk"));
                p.setPTimeCharge(rs.getFloat("PTimeCharge"));
                p.setPOrder(rs.getString("POrder"));
                p.setPFoodType(rs.getString("PFoodType"));
                p.setPPackOld(rs.getInt("PPackOld"));
                p.setPDesc2(rs.getString("PDesc2"));
                p.setPselectItem(rs.getString("PselectItem"));
                p.setPselectNum(rs.getFloat("PselectNum"));
                p.setPShowOption(rs.getString("PShowOption"));

                dataProduct.add(p);
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return dataProduct;
    }

    public boolean productExist(String PCode) {
        boolean isExist = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select PCode from product where PCode='" + PCode + "'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                isExist = true;
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return isExist;
    }

    public boolean productOutStock(String PCode) {
        boolean isExist = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select PCode from outstocklist where PCode='" + PCode + "'";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                isExist = true;
            }
            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            mysql.close();
        }

        return isExist;
    }

    public void main() {
        ProductControl control = new ProductControl();
        control.searchAllProductBy("เหล้า");
        System.out.println(control.getData("11010340180"));
    }

    public static boolean checkProductItem(String menuCode) {
        boolean isProduct = false;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
//            String sql = "select * from soft_menusetup "
//                    + "where menucode='" + menuCode + "' "
//                    + "and pcode<>'';";
            String sql = "select pcode from soft_menusetup "
                    + "where menucode='" + menuCode + "' "
                    + "and pcode<>'' limit 1;";
//            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = mysql.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                isProduct = true;
            }

            rs.close();
//            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        }finally{
            mysql.close();
        }

        return isProduct;
    }
}
