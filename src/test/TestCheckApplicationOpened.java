/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;

/**
 *
 * @author Dell-Softpos
 */
public class TestCheckApplicationOpened {

    public static void main(String[] args) {
        //  the file we want to check
        String fileName = "D:\\CPS_Restaurant650\\SoftPOSRestaurant.jar";
        File file = new File(fileName);

        // try to rename the file with the same name
        File sameFileName = new File(fileName);

        if (file.renameTo(sameFileName)) {
            // if the file is renamed
            System.out.println("file is closed");
        } else {
            // if the file didnt accept the renaming operation
            System.out.println("file is opened");
        }
    }
}
