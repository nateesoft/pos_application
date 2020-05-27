package com.softpos.main.program;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sun.natee.project.util.ThaiUtil;
import util.MSG;

public class ControlMenu {

    private final ArrayList<CompanyMenu> companyMenu;
    private int size;

    public ControlMenu() {
        companyMenu = new ArrayList<CompanyMenu>();
        size = 0;
    }

    public CompanyMenu getMenu(int index) {

        return getAllMenu().get(index);
    }

    public CompanyMenu getMenu(String head) {
        int index = 0;
        if (head.equals("A")) {
            index = 0;
        } else if (head.equals("B")) {
            index = 1;
        } else if (head.equals("C")) {
            index = 2;
        } else if (head.equals("D")) {
            index = 3;
        }

        ArrayList<CompanyMenu> cpm = getAllMenu();
        if (cpm.isEmpty()) {
            return new CompanyMenu();
        } else {
            return cpm.get(index);
        }
    }

    //sample A01
    public ArrayList<ProductBean> getMenuItem(String item) {
        ArrayList<ProductBean> dataProduct = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select p.PCode, PGroup, PDesc, PUnit1, PPrice11, PPrice12, PPrice13,"
                    + "PPrice14, PPrice15, Code_Type, PPathName "
                    + "from menusetup m, product p "
                    + "where m.PCode=p.PCode AND Code_ID like '" + item + "E%' "
                    + "and PActive='Y' and PFix='F' "
                    + "group by PCode";
            System.out.println(sql);
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
                product.setPPathName(rs.getString("PPathName"));

                product.setPDesc2(getMenuItemAt(product.getPCode()));

                dataProduct.add(product);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return dataProduct;
    }

    public ArrayList<ProductBean> getMenuItem2(String item) {
        ArrayList<ProductBean> dataProduct = new ArrayList<>();
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select p.PCode, PGroup, PDesc, PUnit1, PPrice11, PPrice12, PPrice13,"
                    + "PPrice14, PPrice15, Code_Type, PPathName "
                    + "from menusetup m, product p "
                    + "where m.PCode=p.PCode "
                    + "group by PCode";
            System.out.println(sql);
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
                product.setPPathName(rs.getString("PPathName"));

                dataProduct.add(product);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return dataProduct;
    }

    public ArrayList<MenuSetup> menuAt(String index) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        String sql = "select * from menusetup where Code_ID like '" + index + "%' "
                + "and length(Code_ID)=3 group by Code_Id order by Code_Id";
        ArrayList<MenuSetup> menuAll = new ArrayList<>();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MenuSetup ms = new MenuSetup();
                ms.setCode_ID(rs.getString("Code_ID"));
                ms.setPCode(rs.getString("PCode"));
                ms.setCode_Type(rs.getString("Code_Type"));
                ms.setShortName(ThaiUtil.ASCII2Unicode(rs.getString("ShortName")));
                ms.setPPathName(rs.getString("PPathName"));

                menuAll.add(ms);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            MSG.ERR(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            mysql.close();
        }

        return menuAll;
    }

    public ArrayList<MenuSetup> menuItemAt(String Code_ID) {
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        String sql = "select * from menusetup where Code_ID = '" + Code_ID + "' group by Code_ID";
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String Code_Type = rs.getString("Code_Type");
                String PCode = rs.getString("PCode");

                if (Code_Type.equals("S")) {
                    sql = "select Code_ID, PCode, ShortName from menusetup where Code_ID like '" + Code_ID + "%' and Code_Type='P' group by Code_ID";
                } else if (Code_Type.equals("P")) {
                    sql = "";
                } else if (Code_Type.equals("D")) {
                    sql = "select PCode, PDesc ShortName from product where PGroup='" + PCode + "'";
                } else if (Code_Type.equals("G")) {
                    sql = "select Code_ID, p.PCode, PDesc ShortName from menugroup m inner join product p on m.PCode=p.PCode where Code_ID='" + Code_ID + "'";
                }
            }

            rs.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            e.printStackTrace();
        }

        ArrayList<MenuSetup> menuAll = new ArrayList<>();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String prefix = "TEST";
            int count = 0;
            while (rs.next()) {
                count++;
                MenuSetup ms = new MenuSetup();

                String CID;
                try {
                    CID = rs.getString("Code_ID");
                } catch (SQLException e) {
                    CID = prefix + count;
                }

                ms.setCode_ID(CID);
                ms.setPCode(rs.getString("PCode"));
                ms.setShortName(ThaiUtil.ASCII2Unicode(rs.getString("ShortName")));

                menuAll.add(ms);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            MSG.ERR(null, ex.getMessage());
            ex.printStackTrace();
        }

        mysql.close();

        return menuAll;
    }

    public ArrayList<CompanyMenu> getAllMenu() {
        String sqlHead = "select head1, head2, head3, head4 from company";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sqlHead);
            if (rs != null) {
                if (rs.next()) {
                    String head1 = ThaiUtil.ASCII2Unicode(rs.getString("head1"));
                    String head2 = ThaiUtil.ASCII2Unicode(rs.getString("head2"));
                    String head3 = ThaiUtil.ASCII2Unicode(rs.getString("head3"));
                    String head4 = ThaiUtil.ASCII2Unicode(rs.getString("head4"));
                    String sql;
                    CompanyMenu headMenu;
                    String[] head = (head1 + "," + head2 + "," + head3 + "," + head4).split(",");
                    String[] mmenu = ("A,B,C,D").split(",");
                    int index = 0;

                    for (String h : head) {
                        if (h != null) {
                            headMenu = new CompanyMenu();
                            headMenu.setHeadName(h.trim());

                            sql = "select * from menusetup "
                                    + "where code_id like '" + mmenu[index] + "%' "
                                    + "and Code_Type='" + CompanyMenu.TYPE_GROUP + "' "
                                    + "group by Code_ID";
                            Statement stmt1 = mysql.getConnection().createStatement();
                            ResultSet rs1 = stmt1.executeQuery(sql);
                            while (rs1.next()) {
                                MenuSetup menu = new MenuSetup();
                                menu.setCode_ID(rs1.getString("Code_ID"));
                                menu.setCode_Type(rs1.getString("Code_Type"));
                                menu.setPCode(rs1.getString("PCode"));
                                menu.setShortName(ThaiUtil.ASCII2Unicode(rs1.getString("ShortName")));
                                menu.setPPathName(ThaiUtil.ASCII2Unicode(rs1.getString("PPathName")));

                                String sqlProduct = "select * from menusetup "
                                        + "where Code_Id like '" + menu.getCode_ID() + "%' "
                                        + "and Code_Type='" + CompanyMenu.TYPE_PRODUCT + "' "
                                        + "and shortName<>'' "
                                        + "group by Code_ID";
                                Statement stmt2 = mysql.getConnection().createStatement();
                                ResultSet rs2 = stmt2.executeQuery(sqlProduct);
                                while (rs2.next()) {
                                    ProductBean product = new ProductBean();
                                    product.setPCode(rs2.getString("Code_ID"));
                                    product.setPDesc(ThaiUtil.ASCII2Unicode(rs2.getString("ShortName")));

                                    menu.addProduct(product);
                                }

                                rs2.close();
                                stmt2.close();

                                headMenu.addMenuSetup(menu);
                            }
                            rs1.close();
                            stmt1.close();

                            companyMenu.add(headMenu);
                            size++;
                        }
                        index++;
                    }

                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.close();
        }

        return companyMenu;
    }

    public int size() {
        return size;
    }

    public String[] getData(String menuHead) {
        ArrayList<MenuSetup> listMenu = menuAt(menuHead);
        String[] data = new String[28];
        for (int a1 = 0; a1 < data.length; a1++) {
            data[a1] = "";
        }
        for (int a = 0; a < listMenu.size(); a++) {
            MenuSetup m = (MenuSetup) listMenu.get(a);
            int index = Integer.parseInt(m.getCode_ID().substring(1, m.getCode_ID().length()));
            data[index - 1] = m.getShortName();
        }

        return data;
    }

    public ArrayList<String> getDataArray(String menuHead) {
        ArrayList<MenuSetup> listMenu = menuAt(menuHead);
        ArrayList<String> dataArray = new ArrayList<String>();
        for (int a1 = 0; a1 < 28; a1++) {
            //data[a1] = "";
            dataArray.add("");
        }
        for (int a = 0; a < listMenu.size(); a++) {
            MenuSetup m = (MenuSetup) listMenu.get(a);
            int index = Integer.parseInt(m.getCode_ID().substring(1, m.getCode_ID().length()));

            //Sub Menu
            if (m.getCode_Type().equals("S")) {
                dataArray.set(index - 1, "(S) " + m.getShortName());
            } //Plu Code
            else if (m.getCode_Type().equals("P")) {
                dataArray.set(index - 1, "(P) " + m.getShortName());
            } //Group
            else if (m.getCode_Type().equals("G")) {
                dataArray.set(index - 1, "(G) " + m.getShortName());
            } //Dept
            else if (m.getCode_Type().equals("D")) {
                dataArray.set(index - 1, "(D) " + m.getShortName());
            }
        }

        return dataArray;
    }

    public ArrayList<MenuSetup> getDataMenuSetup(String menuHead) {
        ArrayList<MenuSetup> listMenu = menuAt(menuHead);
        ArrayList<MenuSetup> dataArray = new ArrayList<MenuSetup>();
        for (int a1 = 0; a1 < 28; a1++) {
            //data[a1] = "";
            dataArray.add(new MenuSetup("", "", "", "", ""));
        }
        for (int a = 0; a < listMenu.size(); a++) {
            MenuSetup m = (MenuSetup) listMenu.get(a);
            int index = Integer.parseInt(m.getCode_ID().substring(1, m.getCode_ID().length()));

            dataArray.set(index - 1, m);
        }

        return dataArray;
    }

    public ArrayList<MenuSetup> getDataMenuItem(String Code_ID) {
        ArrayList<MenuSetup> listMenu = menuItemAt(Code_ID);

        return listMenu;
    }

    public String getMenuItemAt(String pCode) {
        String menuAt = "";
        /**
         * * OPEN CONNECTION **
         */
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select MenuItem from menulist where PLUCode='" + pCode + "' and MenuActive='Y'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(sql);
            if (rs.next()) {
                menuAt = rs.getString("MenuItem");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            e.printStackTrace();
        } finally{
            mysql.close();
        }

        return menuAt;
    }

}
