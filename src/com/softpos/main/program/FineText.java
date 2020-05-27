package com.softpos.main.program;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class FineText extends javax.swing.JDialog {
    DisplayEJ g;
    String txtinput, txtold;
    String txtMain;
    int index = 0;
    Color blue = new java.awt.Color(121,252,246);
    private final DefaultHighlighter.DefaultHighlightPainter BOX_HighlightPainter = 
            new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

    public FineText(DisplayEJ g, boolean modal) {
        super(g, modal);
       
        initComponents();
        this.setVisible(true);
        this.g = g;

    }

    private void findData() {
        txtinput = tf_txt.getText().trim();
        Highlighter h = g.TextArea.getHighlighter();
        if (!txtinput.equals("")) {
            try {
                if (txtinput.equals(txtold)) {
                    txtold = txtinput;
                    index++;
                } else {
                    txtold = txtinput;
                    index = 0;
                }

                txtMain = g.doc.getText(0, g.doc.getLength());
                index = txtMain.indexOf(txtold, index);
                //  System.out.println("txt >"+index);
                if (index != -1) {
                    
                        g.TextArea.select(index, index + txtold.length());
                        
                        h.removeAllHighlights();
                        h.addHighlight(index, index + txtold.length(), BOX_HighlightPainter);
                    
                }else{
                    h.removeAllHighlights();
                    
                    tf_txt.requestFocus();
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(FineText.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tf_txt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ค้นหาข้อความจาก LOG");
        setUndecorated(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ข้อความ : ");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("ค้นหา");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        tf_txt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_txtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(tf_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    findData();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        findData();
    }
}//GEN-LAST:event_jButton1KeyReleased

private void tf_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_txtKeyReleased
   
    if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        dispose();
    }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        findData();
    }
}//GEN-LAST:event_tf_txtKeyReleased
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_txt;
    // End of variables declaration//GEN-END:variables
}
