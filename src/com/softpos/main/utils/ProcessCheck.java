package com.softpos.main.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessCheck {

    private ProcessCheck() {
        throw new AssertionError();
    }

    public static boolean isRunning(String project) {
        int count = 0;
        boolean run = false;
        Runtime rt = Runtime.getRuntime();
        try {
            java.lang.Process p = rt.exec("ps aux");
            InputStreamReader in = new InputStreamReader(p.getInputStream());
            BufferedReader read = new BufferedReader(in);
            String text;
            while((text = read.readLine())!=null){
                if(text.indexOf(project)!=-1){
                    count++;
                }
            }
            read.close();
            in.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if(count > 1){
            run = true;
        }
        return run;
    }

    public static void showRunning(String project) {
        Runtime rt = Runtime.getRuntime();
        try {
            java.lang.Process p = rt.exec("ps aux");
            InputStreamReader in = new InputStreamReader(p.getInputStream());
            BufferedReader read = new BufferedReader(in);
            String text;
            while((text = read.readLine())!=null){
                if(text.indexOf(project)!=-1){
                    System.out.println(text);
                }
            }
            read.close();
            in.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
