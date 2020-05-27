package test;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import com.softpos.login.Login;

public class ListObject {
    
    public void listComponents(Object obj){
        if(obj instanceof JDialog){
            JDialog dialog = (JDialog)obj;
            for(int i=0;i<dialog.getComponentCount();i++){
                Object obj2 = dialog.getComponent(i);
                listComponents(obj2);
            }
        }else if(obj instanceof JRootPane){
            JRootPane root = (JRootPane)obj;
            for(int i=0;i<root.getComponentCount();i++){
                Object obj2 = root.getComponent(i);
                listComponents(obj2);
            }
        }else if(obj instanceof JPanel){
            JPanel panel = (JPanel)obj;
            for(int i=0;i<panel.getComponentCount();i++){
                Object obj2 = panel.getComponent(i);
                listComponents(obj2);
            }
        }else if(obj instanceof JButton){
            JButton button = (JButton)obj;
            System.out.println(button.getText());
        }else if(obj instanceof JLayeredPane){
            JLayeredPane layer = (JLayeredPane)obj;
            for(int i=0;i<layer.getComponentCount();i++){
                Object obj2 = layer.getComponent(i);
                listComponents(obj2);
            }
        }
    }
    
    public static void main(String[] args) {
        Login login = new Login(null, true);
        ListObject list = new ListObject();
        list.listComponents(login);
    }
    
}
