package setupmenu;

import javax.swing.JButton;

public class MainButtonBean {

    private String name;
    private JButton button;

    public String getName() {
        return name;
    }

    public MainButtonBean(String name, JButton button) {
        this.name = name;
        this.button = button;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
