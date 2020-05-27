/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author NuAmMie
 */
public class TestSpitCustomer {
    public static void main(String[] args) {
        String TableNo = "101(2)";
        String T1 = TableNo.substring(0,TableNo.indexOf("("));
        System.out.println(T1);
    }
    
    
}
