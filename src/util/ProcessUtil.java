package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Natee Sungthongngam
 * @date 15/08/2009
 * @version 1.0
 */
public class ProcessUtil {

    private ProcessUtil() {
        throw new AssertionError();
    }

    /**
     * เช็คสถานะการทำงานของโปรแกรม ว่าถูกใช้งานอยู่หรือไม่
     * @param String - ชื่อโปรแกรมที่ต้องการตรวจสอบ
     * @return - คืนค่าเป็น true หรือ false
     */
    public static boolean isRunning(String project) {
        try {
            boolean run;
            if (OSValidator.isWindows()) {
                System.err.println("Windows");
                run = OSWindowsRunning(project);
            } else if (OSValidator.isLinux()) {
                System.err.println("Linux");
                run = OSLinuxRunning(project);
            } else if (OSValidator.isUnix()) {
                System.err.println("Unix");
                run = OSUnixRunning(project);
            } else if (OSValidator.isMac()) {
                System.err.println("Mac");
                run = OSMacRunning(project);
            } else {
                System.err.println("Unknow Operation System!!!");
                run = false;
            }
            return run;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean OSMacRunning(String project) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static boolean OSUnixRunning(String project) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static boolean OSLinuxRunning(String project) {
        try {
            boolean run = false;
            int count = 0;
            Runtime rt = Runtime.getRuntime();
            java.lang.Process p = rt.exec("ps aux");
            InputStreamReader in = new InputStreamReader(p.getInputStream());
            BufferedReader read = new BufferedReader(in);
            String text;
            while ((text = read.readLine()) != null) {
                if (text.indexOf(project) != -1) {
                    count++;
                }
            }
            read.close();
            in.close();
            if (count > 1) {
                run = true;
            }
            return run;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean OSWindowsRunning(String project) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * แสดง ProcessUtil การทำงานที่กำลังรันอยู่
     */
    public static void showRunning() {
        try {
            if (OSValidator.isWindows()) {
                System.err.println("Windows");
                OSWindowsShowRunning();
            } else if (OSValidator.isLinux()) {
                System.err.println("Linux");
                OSLinuxShowRunning();
            } else if (OSValidator.isUnix()) {
                System.err.println("Unix");
                OSUnixShowRunning();
            } else if (OSValidator.isMac()) {
                System.err.println("Mac");
                OSMacShowRunning();
            } else {
                System.err.println("Unknow Operation System!!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void OSLinuxShowRunning() {
        try {
            Runtime rt = Runtime.getRuntime();
            try {
                java.lang.Process p = rt.exec("ps aux");
                InputStreamReader in = new InputStreamReader(p.getInputStream());
                BufferedReader read = new BufferedReader(in);
                String text;
                while ((text = read.readLine()) != null) {
                    System.out.println(text);
                }
                read.close();
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void OSMacShowRunning() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static void OSUnixShowRunning() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static void OSWindowsShowRunning() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public static void main(String[] args) {
        ProcessUtil.showRunning();
    }
}
