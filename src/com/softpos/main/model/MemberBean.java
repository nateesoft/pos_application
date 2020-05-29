package com.softpos.main.model;

import com.softpos.main.controller.BranchControl;
import com.softpos.main.controller.PosControl;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Statement;
import util.DateConvert;
import util.MSG;

public class MemberBean {

    private String Member_Code;
    private String Member_TypeCode = "00";
    private String Member_BranchCode = "000";
    private String Member_NameThai;
    private String Member_NameEng;
    private String Member_Gender = "M";
    private String Member_Status = "S";
    private String Member_NationCode = "00";
    private String Member_OccupationCode = "0";
    private String Member_IncomeCode = "0";
    private String Member_EducationCode = "000";
    private String Member_Company;
    private String Member_AddressNo;
    private String Member_Building;
    private String Member_AddressSoi;
    private String Member_AddressStreet;
    private String Member_AddressSubDistrict;
    private String Member_AddressDistrict;
    private String Member_Province;
    private String Member_PostalCode;
    private String Member_HomeTel;
    private String Member_Fax;
    private String Member_Email;
    private Date Member_Brithday;
    private Date Member_AppliedDate;
    private Date Member_ExpiredDate;
    private String Member_DiscountRate = "00/00/00";
    private String Member_SpouseName;
    private String Member_Food;
    private double Member_TotalPurchase = 0.00;
    private String Member_Remark1;
    private String Member_Remark2;
    private String Member_Mobile;
    private String Member_ReceiveInformation = "M";
    private String Member_HobbySetCode;
    private Date Member_LastDateService;
    private double Member_ServiceCount = 0;
    private Date Member_PointExpiredDate;
    private double Member_TotalScore = 0;
    private String Member_TitleNameThai;
    private String Member_SurnameThai;
    private String Member_CompanyAddressNo;
    private String Member_CompanyBuilding;
    private String Member_CompanySoi;
    private String Member_CompanyStreet;
    private String Member_CompanySubDistrict;
    private String Member_CompanyDistrict;
    private String Member_CompanyProvince;
    private String Member_CompanyPostalCode;
    private String Member_CompanyTel;
    private String Member_CompanyFax;
    private String Member_Active = "Y";
    private String Member_UsedTitle = "Y";
    private String Member_MailTo = "0";
    private String Member_PrintLabel = "N";
    private String Member_UnknowBirth = "N";
    private String Employee_CodeCreate = "000000";
    private Date Employee_CreateDate;
    private String Employee_CreateTime = "00:00:00";
    private String Employee_CodeModify = "000000";
    private Date Employee_ModifyDate;
    private String Employee_ModifyTime = "00:00:00";
    private String CardPro_Code;
    private String Member_SMSBirthDayCheck = "Y";
    private String Member_SMSPromotionCheck = "Y";
    private String Member_SMSActivityCheck = "Y";
    private String Member_SMSNewsCheck = "Y";
    private int Member_SMSBranchCheck = 3;
    private String Member_BranchNearHourse = "000";
    private String Member_BranchNearOffice = "000";
    private String Member_BranchReques = "000";
    private String Member_PriceChk = "1";
    private int Member_CurPoint = 0;

    public int getMember_CurPoint() {
        return Member_CurPoint;
    }

    public void setMember_CurPoint(int Member_CurPoint) {
        this.Member_CurPoint = Member_CurPoint;
    }

    public static MemberBean getMember(String MemberCode) {
        MemberBean bean = null;
        MySQLConnect mysql = new MySQLConnect();
        mysql.open();
        try {
            String sql = "select * from " + Value.db_member + ".memmaster "
                    + "where member_code='" + MemberCode + "'";
            Statement stmt = mysql.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bean = new MemberBean();
//                bean.setMember_Code(rs.getString("Member_Code"));
//                bean.setMember_TypeCode(rs.getString("Member_TypeCode"));
//                bean.setMember_BranchCode(rs.getString("Member_BranchCode"));
//                bean.setMember_NameThai(ThaiUtil.ASCII2Unicode(rs.getString("Member_NameThai")));
//                bean.setMember_NameEng(rs.getString("Member_NameEng"));
//                bean.setMember_Gender(rs.getString("Member_Gender"));
//                bean.setMember_Status(rs.getString("Member_Status"));
//                bean.setMember_NationCode(rs.getString("Member_NationCode"));
//                bean.setMember_OccupationCode(rs.getString("Member_OccupationCode"));
//                bean.setMember_IncomeCode(rs.getString("Member_IncomeCode"));
//                bean.setMember_EducationCode(rs.getString("Member_EducationCode"));
//                bean.setMember_Company(rs.getString("Member_Company"));
//                bean.setMember_AddressNo(rs.getString("Member_AddressNo"));
//                bean.setMember_Building(rs.getString("Member_Building"));
//                bean.setMember_AddressSoi(rs.getString("Member_AddressSoi"));
//                bean.setMember_AddressStreet(rs.getString("Member_AddressStreet"));
//                bean.setMember_AddressSubDistrict(rs.getString("Member_AddressSubDistrict"));
//                bean.setMember_AddressDistrict(rs.getString("Member_AddressDistrict"));
//                bean.setMember_Province(rs.getString("Member_Province"));
//                bean.setMember_PostalCode(rs.getString("Member_PostalCode"));
//                bean.setMember_HomeTel(ThaiUtil.ASCII2Unicode(rs.getString("Member_HomeTel")));
//                bean.setMember_Fax(rs.getString("Member_Fax"));
//                bean.setMember_Email(rs.getString("Member_Email"));
//                bean.setMember_DiscountRate(rs.getString("Member_DiscountRate"));
//                bean.setMember_SpouseName(rs.getString("Member_SpouseName"));
//                bean.setMember_Food(rs.getString("Member_Food"));
//                bean.setMember_TotalPurchase(rs.getFloat("Member_TotalPurchase"));
//                bean.setMember_Remark1(rs.getString("Member_Remark1"));
//                bean.setMember_Remark2(rs.getString("Member_Remark2"));
//                bean.setMember_Mobile(rs.getString("Member_Mobile"));
//                bean.setMember_ReceiveInformation(rs.getString("Member_ReceiveInformation"));
//                bean.setMember_HobbySetCode(rs.getString("Member_HobbySetCode"));
//                bean.setMember_ServiceCount(rs.getFloat("Member_ServiceCount"));
//                bean.setMember_TotalScore(rs.getDouble("Member_TotalScore"));
//                try {
//                    bean.setMember_LastDateService(rs.getDate("Member_LastDateService"));
//                } catch (SQLException e) {
//                }
//
//                try {
//                    bean.setMember_PointExpiredDate(rs.getDate("Member_PointExpiredDate"));
//                } catch (SQLException e) {
//                }
//
//                try {
//                    bean.setMember_Brithday(rs.getDate("Member_Brithday"));
//                } catch (SQLException e) {
//                }
//
//                try {
//                    bean.setMember_AppliedDate(rs.getDate("Member_AppliedDate"));
//                } catch (SQLException e) {
//                }
//
//                try {
//                    bean.setMember_ExpiredDate(rs.getDate("Member_ExpiredDate"));
//                } catch (SQLException e) {
//                }
//
//                try {
//                    bean.setEmployee_CreateDate(rs.getDate("Employee_CreateDate"));
//                } catch (SQLException e) {
//                }
//
//                try {
//                    bean.setEmployee_ModifyDate(rs.getDate("Employee_ModifyDate"));
//                } catch (SQLException e) {
//                }
//
//                bean.setMember_TotalScore(rs.getFloat("Member_TotalScore"));
//                bean.setMember_TitleNameThai(ThaiUtil.ASCII2Unicode(rs.getString("Member_TitleNameThai")));
//                bean.setMember_SurnameThai(ThaiUtil.ASCII2Unicode(rs.getString("Member_SurnameThai")));
//                bean.setMember_CompanyAddressNo(rs.getString("Member_CompanyAddressNo"));
//                bean.setMember_CompanyBuilding(rs.getString("Member_CompanyBuilding"));
//                bean.setMember_CompanySoi(rs.getString("Member_CompanySoi"));
//                bean.setMember_CompanyStreet(rs.getString("Member_CompanyStreet"));
//                bean.setMember_CompanySubDistrict(rs.getString("Member_CompanySubDistrict"));
//                bean.setMember_CompanyDistrict(rs.getString("Member_CompanyDistrict"));
//                bean.setMember_CompanyProvince(rs.getString("Member_CompanyProvince"));
//                bean.setMember_CompanyPostalCode(rs.getString("Member_CompanyPostalCode"));
//                bean.setMember_CompanyTel(rs.getString("Member_CompanyTel"));
//                bean.setMember_CompanyFax(rs.getString("Member_CompanyFax"));
//                bean.setMember_Active(rs.getString("Member_Active"));
//                bean.setMember_UsedTitle(rs.getString("Member_UsedTitle"));
//                bean.setMember_MailTo(rs.getString("Member_MailTo"));
//                bean.setMember_PrintLabel(rs.getString("Member_PrintLabel"));
//                bean.setMember_UnknowBirth(rs.getString("Member_UnknowBirth"));
//                bean.setEmployee_CodeCreate(rs.getString("Employee_CodeCreate"));
//                bean.setEmployee_CreateTime(rs.getString("Employee_CreateTime"));
//                bean.setEmployee_CodeModify(rs.getString("Employee_CodeModify"));
//                bean.setEmployee_ModifyTime(rs.getString("Employee_ModifyTime"));
                // bean.setCardPro_Code(rs.getString("CardPro_Code"));
                //bean.setMember_SMSBirthDayCheck(rs.getString("Member_SMSBirthDayCheck"));
                // bean.setMember_SMSPromotionCheck(rs.getString("Member_SMSPromotionCheck"));
                //bean.setMember_SMSActivityCheck(rs.getString("Member_SMSActivityCheck"));
                //bean.setMember_SMSNewsCheck(rs.getString("Member_SMSNewsCheck"));
                //bean.setMember_SMSBranchCheck(rs.getInt("Member_SMSBranchCheck"));
                // bean.setMember_BranchNearHourse(rs.getString("Member_BranchNearHourse"));
                //bean.setMember_BranchNearOffice(rs.getString("Member_BranchNearOffice"));
                //bean.setMember_BranchReques(rs.getString("Member_BranchReques"));
                //bean.setMember_PriceChk(rs.getString("Member_PriceChk"));
            }

            rs.close();
        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            
        } finally {
            mysql.close();
        }

        return bean;
    }

    public String getMember_Code() {
        return Member_Code;
    }

    public void setMember_Code(String Member_Code) {
        this.Member_Code = Member_Code;
    }

    public String getMember_TypeCode() {
        return Member_TypeCode;
    }

    public void setMember_TypeCode(String Member_TypeCode) {
        this.Member_TypeCode = Member_TypeCode;
    }

    public String getMember_BranchCode() {
        return Member_BranchCode;
    }

    public void setMember_BranchCode(String Member_BranchCode) {
        this.Member_BranchCode = Member_BranchCode;
    }

    public String getMember_NameThai() {
        return Member_NameThai;
    }

    public void setMember_NameThai(String Member_NameThai) {
        this.Member_NameThai = Member_NameThai;
    }

    public String getMember_NameEng() {
        return Member_NameEng;
    }

    public void setMember_NameEng(String Member_NameEng) {
        this.Member_NameEng = Member_NameEng;
    }

    public String getMember_Gender() {
        return Member_Gender;
    }

    public void setMember_Gender(String Member_Gender) {
        this.Member_Gender = Member_Gender;
    }

    public String getMember_Status() {
        return Member_Status;
    }

    public void setMember_Status(String Member_Status) {
        this.Member_Status = Member_Status;
    }

    public String getMember_NationCode() {
        return Member_NationCode;
    }

    public void setMember_NationCode(String Member_NationCode) {
        this.Member_NationCode = Member_NationCode;
    }

    public String getMember_OccupationCode() {
        return Member_OccupationCode;
    }

    public void setMember_OccupationCode(String Member_OccupationCode) {
        this.Member_OccupationCode = Member_OccupationCode;
    }

    public String getMember_IncomeCode() {
        return Member_IncomeCode;
    }

    public void setMember_IncomeCode(String Member_IncomeCode) {
        this.Member_IncomeCode = Member_IncomeCode;
    }

    public String getMember_EducationCode() {
        return Member_EducationCode;
    }

    public void setMember_EducationCode(String Member_EducationCode) {
        this.Member_EducationCode = Member_EducationCode;
    }

    public String getMember_Company() {
        return Member_Company;
    }

    public void setMember_Company(String Member_Company) {
        this.Member_Company = Member_Company;
    }

    public String getMember_AddressNo() {
        return Member_AddressNo;
    }

    public void setMember_AddressNo(String Member_AddressNo) {
        this.Member_AddressNo = Member_AddressNo;
    }

    public String getMember_Building() {
        return Member_Building;
    }

    public void setMember_Building(String Member_Building) {
        this.Member_Building = Member_Building;
    }

    public String getMember_AddressSoi() {
        return Member_AddressSoi;
    }

    public void setMember_AddressSoi(String Member_AddressSoi) {
        this.Member_AddressSoi = Member_AddressSoi;
    }

    public String getMember_AddressStreet() {
        return Member_AddressStreet;
    }

    public void setMember_AddressStreet(String Member_AddressStreet) {
        this.Member_AddressStreet = Member_AddressStreet;
    }

    public String getMember_AddressSubDistrict() {
        return Member_AddressSubDistrict;
    }

    public void setMember_AddressSubDistrict(String Member_AddressSubDistrict) {
        this.Member_AddressSubDistrict = Member_AddressSubDistrict;
    }

    public String getMember_AddressDistrict() {
        return Member_AddressDistrict;
    }

    public void setMember_AddressDistrict(String Member_AddressDistrict) {
        this.Member_AddressDistrict = Member_AddressDistrict;
    }

    public String getMember_Province() {
        return Member_Province;
    }

    public void setMember_Province(String Member_Province) {
        this.Member_Province = Member_Province;
    }

    public String getMember_PostalCode() {
        return Member_PostalCode;
    }

    public void setMember_PostalCode(String Member_PostalCode) {
        this.Member_PostalCode = Member_PostalCode;
    }

    public String getMember_HomeTel() {
        return Member_HomeTel;
    }

    public void setMember_HomeTel(String Member_HomeTel) {
        this.Member_HomeTel = Member_HomeTel;
    }

    public String getMember_Fax() {
        return Member_Fax;
    }

    public void setMember_Fax(String Member_Fax) {
        this.Member_Fax = Member_Fax;
    }

    public String getMember_Email() {
        return Member_Email;
    }

    public void setMember_Email(String Member_Email) {
        this.Member_Email = Member_Email;
    }

    public Date getMember_Brithday() {
        return Member_Brithday;
    }

    public void setMember_Brithday(Date Member_Brithday) {
        this.Member_Brithday = Member_Brithday;
    }

    public Date getMember_AppliedDate() {
        return Member_AppliedDate;
    }

    public void setMember_AppliedDate(Date Member_AppliedDate) {
        this.Member_AppliedDate = Member_AppliedDate;
    }

    public Date getMember_ExpiredDate() {
        return Member_ExpiredDate;
    }

    public void setMember_ExpiredDate(Date Member_ExpiredDate) {
        this.Member_ExpiredDate = Member_ExpiredDate;
    }

    public String getMember_DiscountRate() {
        return Member_DiscountRate;
    }

    public void setMember_DiscountRate(String Member_DiscountRate) {
        this.Member_DiscountRate = Member_DiscountRate;
    }

    public String getMember_SpouseName() {
        return Member_SpouseName;
    }

    public void setMember_SpouseName(String Member_SpouseName) {
        this.Member_SpouseName = Member_SpouseName;
    }

    public String getMember_Food() {
        return Member_Food;
    }

    public void setMember_Food(String Member_Food) {
        this.Member_Food = Member_Food;
    }

    public double getMember_TotalPurchase() {
        return Member_TotalPurchase;
    }

    public void setMember_TotalPurchase(double Member_TotalPurchase) {
        this.Member_TotalPurchase = Member_TotalPurchase;
    }

    public String getMember_Remark1() {
        return Member_Remark1;
    }

    public void setMember_Remark1(String Member_Remark1) {
        this.Member_Remark1 = Member_Remark1;
    }

    public String getMember_Remark2() {
        return Member_Remark2;
    }

    public void setMember_Remark2(String Member_Remark2) {
        this.Member_Remark2 = Member_Remark2;
    }

    public String getMember_Mobile() {
        return Member_Mobile;
    }

    public void setMember_Mobile(String Member_Mobile) {
        this.Member_Mobile = Member_Mobile;
    }

    public String getMember_ReceiveInformation() {
        return Member_ReceiveInformation;
    }

    public void setMember_ReceiveInformation(String Member_ReceiveInformation) {
        this.Member_ReceiveInformation = Member_ReceiveInformation;
    }

    public String getMember_HobbySetCode() {
        return Member_HobbySetCode;
    }

    public void setMember_HobbySetCode(String Member_HobbySetCode) {
        this.Member_HobbySetCode = Member_HobbySetCode;
    }

    public Date getMember_LastDateService() {
        return Member_LastDateService;
    }

    public void setMember_LastDateService(Date Member_LastDateService) {
        this.Member_LastDateService = Member_LastDateService;
    }

    public double getMember_ServiceCount() {
        return Member_ServiceCount;
    }

    public void setMember_ServiceCount(double Member_ServiceCount) {
        this.Member_ServiceCount = Member_ServiceCount;
    }

    public Date getMember_PointExpiredDate() {
        return Member_PointExpiredDate;
    }

    public void setMember_PointExpiredDate(Date Member_PointExpiredDate) {
        this.Member_PointExpiredDate = Member_PointExpiredDate;
    }

    public double getMember_TotalScore() {
        return Member_TotalScore;
    }

    public void setMember_TotalScore(double Member_TotalScore) {
        this.Member_TotalScore = Member_TotalScore;
    }

    public String getMember_TitleNameThai() {
        return Member_TitleNameThai;
    }

    public void setMember_TitleNameThai(String Member_TitleNameThai) {
        this.Member_TitleNameThai = Member_TitleNameThai;
    }

    public String getMember_SurnameThai() {
        return Member_SurnameThai;
    }

    public void setMember_SurnameThai(String Member_SurnameThai) {
        this.Member_SurnameThai = Member_SurnameThai;
    }

    public String getMember_CompanyAddressNo() {
        return Member_CompanyAddressNo;
    }

    public void setMember_CompanyAddressNo(String Member_CompanyAddressNo) {
        this.Member_CompanyAddressNo = Member_CompanyAddressNo;
    }

    public String getMember_CompanyBuilding() {
        return Member_CompanyBuilding;
    }

    public void setMember_CompanyBuilding(String Member_CompanyBuilding) {
        this.Member_CompanyBuilding = Member_CompanyBuilding;
    }

    public String getMember_CompanySoi() {
        return Member_CompanySoi;
    }

    public void setMember_CompanySoi(String Member_CompanySoi) {
        this.Member_CompanySoi = Member_CompanySoi;
    }

    public String getMember_CompanyStreet() {
        return Member_CompanyStreet;
    }

    public void setMember_CompanyStreet(String Member_CompanyStreet) {
        this.Member_CompanyStreet = Member_CompanyStreet;
    }

    public String getMember_CompanySubDistrict() {
        return Member_CompanySubDistrict;
    }

    public void setMember_CompanySubDistrict(String Member_CompanySubDistrict) {
        this.Member_CompanySubDistrict = Member_CompanySubDistrict;
    }

    public String getMember_CompanyDistrict() {
        return Member_CompanyDistrict;
    }

    public void setMember_CompanyDistrict(String Member_CompanyDistrict) {
        this.Member_CompanyDistrict = Member_CompanyDistrict;
    }

    public String getMember_CompanyProvince() {
        return Member_CompanyProvince;
    }

    public void setMember_CompanyProvince(String Member_CompanyProvince) {
        this.Member_CompanyProvince = Member_CompanyProvince;
    }

    public String getMember_CompanyPostalCode() {
        return Member_CompanyPostalCode;
    }

    public void setMember_CompanyPostalCode(String Member_CompanyPostalCode) {
        this.Member_CompanyPostalCode = Member_CompanyPostalCode;
    }

    public String getMember_CompanyTel() {
        return Member_CompanyTel;
    }

    public void setMember_CompanyTel(String Member_CompanyTel) {
        this.Member_CompanyTel = Member_CompanyTel;
    }

    public String getMember_CompanyFax() {
        return Member_CompanyFax;
    }

    public void setMember_CompanyFax(String Member_CompanyFax) {
        this.Member_CompanyFax = Member_CompanyFax;
    }

    public String getMember_Active() {
        return Member_Active;
    }

    public void setMember_Active(String Member_Active) {
        this.Member_Active = Member_Active;
    }

    public String getMember_UsedTitle() {
        return Member_UsedTitle;
    }

    public void setMember_UsedTitle(String Member_UsedTitle) {
        this.Member_UsedTitle = Member_UsedTitle;
    }

    public String getMember_MailTo() {
        return Member_MailTo;
    }

    public void setMember_MailTo(String Member_MailTo) {
        this.Member_MailTo = Member_MailTo;
    }

    public String getMember_PrintLabel() {
        return Member_PrintLabel;
    }

    public void setMember_PrintLabel(String Member_PrintLabel) {
        this.Member_PrintLabel = Member_PrintLabel;
    }

    public String getMember_UnknowBirth() {
        return Member_UnknowBirth;
    }

    public void setMember_UnknowBirth(String Member_UnknowBirth) {
        this.Member_UnknowBirth = Member_UnknowBirth;
    }

    public String getEmployee_CodeCreate() {
        return Employee_CodeCreate;
    }

    public void setEmployee_CodeCreate(String Employee_CodeCreate) {
        this.Employee_CodeCreate = Employee_CodeCreate;
    }

    public Date getEmployee_CreateDate() {
        return Employee_CreateDate;
    }

    public void setEmployee_CreateDate(Date Employee_CreateDate) {
        this.Employee_CreateDate = Employee_CreateDate;
    }

    public String getEmployee_CreateTime() {
        return Employee_CreateTime;
    }

    public void setEmployee_CreateTime(String Employee_CreateTime) {
        this.Employee_CreateTime = Employee_CreateTime;
    }

    public String getEmployee_CodeModify() {
        return Employee_CodeModify;
    }

    public void setEmployee_CodeModify(String Employee_CodeModify) {
        this.Employee_CodeModify = Employee_CodeModify;
    }

    public Date getEmployee_ModifyDate() {
        return Employee_ModifyDate;
    }

    public void setEmployee_ModifyDate(Date Employee_ModifyDate) {
        this.Employee_ModifyDate = Employee_ModifyDate;
    }

    public String getEmployee_ModifyTime() {
        return Employee_ModifyTime;
    }

    public void setEmployee_ModifyTime(String Employee_ModifyTime) {
        this.Employee_ModifyTime = Employee_ModifyTime;
    }

    public String getCardPro_Code() {
        return CardPro_Code;
    }

    public void setCardPro_Code(String CardPro_Code) {
        this.CardPro_Code = CardPro_Code;
    }

    public String getMember_SMSBirthDayCheck() {
        return Member_SMSBirthDayCheck;
    }

    public void setMember_SMSBirthDayCheck(String Member_SMSBirthDayCheck) {
        this.Member_SMSBirthDayCheck = Member_SMSBirthDayCheck;
    }

    public String getMember_SMSPromotionCheck() {
        return Member_SMSPromotionCheck;
    }

    public void setMember_SMSPromotionCheck(String Member_SMSPromotionCheck) {
        this.Member_SMSPromotionCheck = Member_SMSPromotionCheck;
    }

    public String getMember_SMSActivityCheck() {
        return Member_SMSActivityCheck;
    }

    public void setMember_SMSActivityCheck(String Member_SMSActivityCheck) {
        this.Member_SMSActivityCheck = Member_SMSActivityCheck;
    }

    public String getMember_SMSNewsCheck() {
        return Member_SMSNewsCheck;
    }

    public void setMember_SMSNewsCheck(String Member_SMSNewsCheck) {
        this.Member_SMSNewsCheck = Member_SMSNewsCheck;
    }

    public int getMember_SMSBranchCheck() {
        return Member_SMSBranchCheck;
    }

    public void setMember_SMSBranchCheck(int Member_SMSBranchCheck) {
        this.Member_SMSBranchCheck = Member_SMSBranchCheck;
    }

    public String getMember_BranchNearHourse() {
        return Member_BranchNearHourse;
    }

    public void setMember_BranchNearHourse(String Member_BranchNearHourse) {
        this.Member_BranchNearHourse = Member_BranchNearHourse;
    }

    public String getMember_BranchNearOffice() {
        return Member_BranchNearOffice;
    }

    public void setMember_BranchNearOffice(String Member_BranchNearOffice) {
        this.Member_BranchNearOffice = Member_BranchNearOffice;
    }

    public String getMember_BranchReques() {
        return Member_BranchReques;
    }

    public void setMember_BranchReques(String Member_BranchReques) {
        this.Member_BranchReques = Member_BranchReques;
    }

    public String getMember_PriceChk() {
        return Member_PriceChk;
    }

    public void setMember_PriceChk(String Member_PriceChk) {
        this.Member_PriceChk = Member_PriceChk;
    }

    public void MemberPaymentScore(String Member_Code) {
        try {
            MySQLConnect c = new MySQLConnect();
            DateConvert dc = new DateConvert();
            PosControl posControl = new PosControl();
            CompanyBean CompanyBean = new CompanyBean();
            CompanyBean = posControl.getCompany();
            BranchControl branch = new BranchControl();
            BranchBean BranchBean = branch.getBranch();
            
            c.open();
            //เช็คการคิดแต้มสำหรับสาขา
            if (BranchBean.getCode().equals("001")) {
            String sql = "select * from " + Value.db_member + ".pointtype";
            ResultSet rs = c.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                //CheckExpire Date Point วันที่หมดอายุเก็บแต้มสะสมในประเภทสะสมแต้ม CRM
                String Point_FinishDateService = rs.getString("Point_FinishDateService");
                int pointDateExpire = dc.getCheckExpireDate(Point_FinishDateService);
                if (pointDateExpire >= 0) {
                    //Time Active
                    int timeActive = dc.getCheckExpireTime(rs.getString(""), rs.getString(""));
                    if (timeActive >= 0) {
                        int memberExpire = dc.getCheckExpireDate(Member_PointExpiredDate + "");
                        //CheckExpire Member วันที่หมดอายุสะสมแต้มของสมาชิกคนนั้นๆ
                        if (memberExpire >= 0) {
                            //CheckDate Can Use Point เช็ค Point Type ว่าใช้วันไหนได้บ้าง
                            String[] strs = rs.getString("Point_TypeDateService").split(",");
                            for (String data : strs) {
                                //CheckExpire Time1 Point1 
                                //CheckExpire Time2 Point2
                                //CheckExpire Time3 Point3
                            }
                            //CalPoint
                            this.Member_CurPoint = 0;

                        }
                    }

                }

            }
            rs.close();
            c.close();
            } else {
                this.Member_CurPoint = 0;
            }
        } catch (Exception e) {
            MSG.NOTICE(e.toString());
        }
    }

//    public static void main(String[] args) {
//        MemberBean bean = new MemberBean();
//        bean.getMemberPaymentScore("9990200006661");
//    }
}
