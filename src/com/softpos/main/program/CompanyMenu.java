package com.softpos.main.program;

import java.util.ArrayList;

public class CompanyMenu {
    
    private String headName;
    private ArrayList<MenuSetup> menuSetupData;
    public static final String TYPE_PRODUCT = "P";
    public static final String TYPE_GROUP = "S";
    
    public CompanyMenu(){
        menuSetupData = new ArrayList<MenuSetup>();
    }
    
    public void addMenuSetup(MenuSetup menu){
        menuSetupData.add(menu);
    }
    
    public ArrayList<MenuSetup> getAllMenuSetup(){
        return menuSetupData;
    }
    
    public MenuSetup getMenuSetup(int index){
        return menuSetupData.get(index);
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }
    
}
