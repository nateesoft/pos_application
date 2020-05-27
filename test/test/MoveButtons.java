package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class MoveButtons {

    public static void main(String[] args) {
        new MoveButtons();
    }

    public MoveButtons() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (IllegalAccessException ex) {
                } catch (InstantiationException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JButton button;

        public TestPane() {
            setLayout(new GridLayout(4, 4));
            button = new JButton("...");
            add(button);
            for (int index = 0; index < (4 * 4) - 1; index++) {
                JPanel panel = new JPanel();
                panel.setBorder(new LineBorder(Color.GRAY));
                add(panel);
            }

            requestFocus();

            InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

            am.put("left", new MoveAction(-1));
            am.put("right", new MoveAction(1));
            am.put("down", new MoveAction(4));
            am.put("up", new MoveAction(-4));
        }

        public class MoveAction extends AbstractAction {

            private int delta;

            public MoveAction(int delta) {
                this.delta = delta;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getComponentZOrder(button);
                index += delta;
                if (index < 0) {
                    index = 0;
                } else if (index >= getComponentCount()) {
                    index = getComponentCount() - 1;
                }
                setComponentZOrder(button, index);
                revalidate();
            }
        }
    }
}
