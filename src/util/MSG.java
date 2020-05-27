package util;

import java.awt.Component;
import java.awt.HeadlessException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MSG {

    private MSG() {
        throw new AssertionError();
    }

    public static void ERR(String error) {
        try {
            URL imgURL = MSG.class.getResource("/util/error64.png");
            JOptionPane.showMessageDialog(null, error, "Error Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    public static void WAR(String warning) {
        try {
            URL imgURL = MSG.class.getResource("/util/warning64.png");
            JOptionPane.showMessageDialog(null, warning, "Warning Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, warning);
        }
    }

    public static void NOTICE(String notice) {
        try {
            URL imgURL = MSG.class.getResource("/util/notice64.png");
            JOptionPane.showMessageDialog(null, notice, "Notice Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, notice);
        }
    }

    public static boolean CONF(String confirm) {
        boolean success = false;
        try {
            URL imgURL = MSG.class.getResource("/util/warning64.png");
            int rs = JOptionPane.showConfirmDialog(null, confirm, "Confirm Dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(imgURL));
            if (rs == JOptionPane.YES_OPTION) {
                success = true;
            }
        } catch (HeadlessException ex) {
            int rs = JOptionPane.showConfirmDialog(null, confirm);
            if (rs == JOptionPane.YES_OPTION) {
                success = true;
            }
        }
        return success;
    }

    public static void ERR_MSG(Component com, String error) {
        try {
            URL imgURL = MSG.class.getResource("/util/error64.png");
            JOptionPane.showMessageDialog(com, error, "Error Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(com, error);
        }
    }

    public static void WAR_MSG(Component com, String warning) {
        try {
            URL imgURL = MSG.class.getResource("/util/warning64.png");
            JOptionPane.showMessageDialog(com, warning, "Warning Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(com, warning);
        }
    }

    public static void NOTICE_MSG(Component com, String notice) {
        try {
            URL imgURL = MSG.class.getResource("/util/notice64.png");
            JOptionPane.showMessageDialog(com, notice, "Notice Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(com, notice);
        }
    }

    public static boolean CONF_MSG(Component com, String confirm) {
        boolean success = false;
        try {
            URL imgURL = MSG.class.getResource("/util/warning64.png");
            int rs = JOptionPane.showConfirmDialog(com, confirm, "Confirm Dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(imgURL));
            if (rs == JOptionPane.YES_OPTION) {
                success = true;
            }
        } catch (HeadlessException ex) {
            int rs = JOptionPane.showConfirmDialog(com, confirm);
            if (rs == JOptionPane.YES_OPTION) {
                success = true;
            }
        }
        return success;
    }
    
    public static void ERR(Component com, String error) {
        try {
            URL imgURL = MSG.class.getResource("/util/error64.png");
            JOptionPane.showMessageDialog(com, error, "Error Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(com, error);
        }
    }

    public static void WAR(Component com, String warning) {
        try {
            URL imgURL = MSG.class.getResource("/util/warning64.png");
            JOptionPane.showMessageDialog(com, warning, "Warning Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(com, warning);
        }
    }

    public static void NOTICE(Component com, String notice) {
        try {
            URL imgURL = MSG.class.getResource("/util/notice64.png");
            JOptionPane.showMessageDialog(com, notice, "Notice Message", JOptionPane.OK_OPTION, new ImageIcon(imgURL));
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(com, notice);
        }
    }

    public static boolean CONF(Component com, String confirm) {
        boolean success = false;
        try {
            URL imgURL = MSG.class.getResource("/util/warning64.png");
            int rs = JOptionPane.showConfirmDialog(com, confirm, "Confirm Dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(imgURL));
            if (rs == JOptionPane.YES_OPTION) {
                success = true;
            }
        } catch (HeadlessException ex) {
            int rs = JOptionPane.showConfirmDialog(com, confirm);
            if (rs == JOptionPane.YES_OPTION) {
                success = true;
            }
        }
        return success;
    }

}
