package test;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestJDialog {

    public JPanel getPanelImage(JPanel pnButton, JLabel lbCust, JLabel lbTable, JButton btnIcon) {                
        //config layout
        pnButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbCust.setFont(new java.awt.Font("Tahoma", 1, 10));
        lbCust.setForeground(new java.awt.Color(0, 0, 204));
        lbCust.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCust.setText("C(0) T(1,500)");
        pnButton.add(lbCust, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, 30));

        lbTable.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbTable.setForeground(new java.awt.Color(0, 0, 204));
        lbTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTable.setText("TABLE: 101");
        pnButton.add(lbTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 80, 30));

        btnIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/table_void.png")));
        btnIcon.setRequestFocusEnabled(false);
        
        pnButton.add(btnIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

        return pnButton;
    }

    public static void main(String[] args) {
        TestJDialog jd = new TestJDialog();
        JPanel pnButton = new JPanel();
        JLabel lbCust = new javax.swing.JLabel();
        JLabel lbTable = new javax.swing.JLabel();
        JButton btnIcon = new javax.swing.JButton();

        JPanel pn = jd.getPanelImage(pnButton, lbCust, lbTable, btnIcon);
        
        JDialog jd1 = new JDialog(new JFrame(), true);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jd1.getContentPane());
        jd1.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jd1.pack();        
        jd1.setVisible(true);
    }
}
