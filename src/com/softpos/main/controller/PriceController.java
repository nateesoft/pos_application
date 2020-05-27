package com.softpos.main.controller;

import com.softpos.main.program.ProductBean;

public class PriceController {
    
    public static double getPrice(String etdType, ProductBean productBean) {
        switch(etdType) {
            case "E":
                return productBean.getPPrice11();
            case "T":
                return productBean.getPPrice12();
            case "D":
                return productBean.getPPrice13();
            case "P":
                return productBean.getPPrice14();
            case "W":
                return productBean.getPPrice15();
            default:
                return productBean.getPPrice11();
        }
    }
}
