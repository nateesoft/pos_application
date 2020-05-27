package soft.virtual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

public class KeyBoardDialog extends javax.swing.JDialog {

    private String dataEnglish1;
    private String dataEnglish2;

    private String dataThai1;
    private String dataThai2;
    public static String TEXT_INPUT;

    public KeyBoardDialog(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        initComponents();

        loadData();
        tbControl.setSelectedIndex(index);
    }
    
    public void get(JTextField txtField, int index){
        KeyBoardDialog key = new KeyBoardDialog(null, true, index);
        key.setVisible(true);
        String temp = KeyBoardDialog.TEXT_INPUT;
        if(!temp.equals("")){
            txtField.setText(temp);
        }
    }
    
    public static void get(JTextField txtField){
        KeyBoardDialog key = new KeyBoardDialog(null, true, 0);
        key.setVisible(true);
        String temp = KeyBoardDialog.TEXT_INPUT;
        if(!temp.equals("")){
            txtField.setText(temp);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbControl = new javax.swing.JTabbedPane();
        pnThai1 = new javax.swing.JPanel();
        pnThai2 = new javax.swing.JPanel();
        pnEng1 = new javax.swing.JPanel();
        pnEng2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        txtShow = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Virtual Keyboard");
        setAlwaysOnTop(true);
        setUndecorated(true);

        tbControl.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tbControl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbControl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbControlKeyPressed(evt);
            }
        });

        pnThai1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        pnThai1.setLayout(new java.awt.GridLayout(4, 14));
        tbControl.addTab("ภาษาไทย1", pnThai1);

        pnThai2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        pnThai2.setLayout(new java.awt.GridLayout(4, 13));
        tbControl.addTab("ภาษาไทย2", pnThai2);

        pnEng1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        pnEng1.setLayout(new java.awt.GridLayout(4, 13));
        tbControl.addTab("ภาษาอังกฤษ1", pnEng1);

        pnEng2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        pnEng2.setLayout(new java.awt.GridLayout(4, 13));
        tbControl.addTab("ภาษาอังกฤษ2", pnEng2);

        jPanel1.setLayout(new java.awt.GridLayout(5, 4));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton1.setText("กลับ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton2.setText("/");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton3.setText("*");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton5.setText("7");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton6.setText("8");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton7.setText("9");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton8.setText("+");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton9.setText("4");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton10.setText("5");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton11.setText("6");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton12.setText("CE");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton13.setText("1");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13);

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton14.setText("2");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton15.setText("3");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15);

        jButton16.setBackground(new java.awt.Color(51, 153, 0));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton16.setText("OK");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16);

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton17.setText("0");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton17);

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton18.setText("_");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton18);

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton19.setText(".");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton19);

        jButton20.setBackground(new java.awt.Color(255, 102, 0));
        jButton20.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton20.setText("[x]");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton20);

        tbControl.addTab("Number", jPanel1);

        txtShow.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtShow.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtShow.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtShowMouseClicked(evt);
            }
        });
        txtShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtShowKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbControl, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtShow)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtShow)
                .addGap(0, 0, 0)
                .addComponent(tbControl, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1000, 418));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbControlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbControlKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            if (tbControl.getSelectedIndex() == 0) {
                tbControl.setSelectedIndex(1);
            } else if (tbControl.getSelectedIndex() == 1) {
                tbControl.setSelectedIndex(0);
            } else if (tbControl.getSelectedIndex() == 2) {
                tbControl.setSelectedIndex(3);
            } else if (tbControl.getSelectedIndex() == 3) {
                tbControl.setSelectedIndex(2);
            }
        }
    }//GEN-LAST:event_tbControlKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tbControl.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        inputKey(jButton2.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inputKey(jButton3.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        inputKey(jButton6.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        inputKey(jButton7.getText());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        inputKey(jButton8.getText());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        inputKey(jButton11.getText());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        inputKey(jButton12.getText());
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        inputKey(jButton13.getText());
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        inputKey(jButton14.getText());
    }//GEN-LAST:event_jButton14ActionPerformed

    private void txtShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShowKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            TEXT_INPUT = "";
            dispose();
        }
    }//GEN-LAST:event_txtShowKeyPressed

    private void txtShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtShowMouseClicked
        if (!txtShow.getText().trim().equals("")) {
            txtShow.setText(txtShow.getText() + " ");
        }
    }//GEN-LAST:event_txtShowMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        TEXT_INPUT = txtShow.getText();
        dispose();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        inputKey(jButton17.getText());
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        inputKey(jButton18.getText());
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        inputKey(jButton19.getText());
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        TEXT_INPUT = "";
        txtShow.setText("");
        dispose();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        inputKey(jButton4.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        inputKey(jButton5.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        inputKey(jButton9.getText());
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        inputKey(jButton10.getText());
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        inputKey(jButton15.getText());
    }//GEN-LAST:event_jButton15ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnEng1;
    private javax.swing.JPanel pnEng2;
    private javax.swing.JPanel pnThai1;
    private javax.swing.JPanel pnThai2;
    private javax.swing.JTabbedPane tbControl;
    private javax.swing.JTextField txtShow;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        dataEnglish1 = "1234567890-=/  qwertyuiop[]   asdfghjkl;'    zxcvbnm,./ ";
        dataEnglish2 = "!@#$%^&*()_+|  QWERTYUIOP{}   ASDFGHJKL:\"    ZXCVBNM<>? ";

        dataThai1 = "ๅ/-ภถุึคตจขชฃ  ๆไำพะัีรนยบล   ฟหกดเ้่าสวง    ผปแอิืทมใฝ ";
        dataThai2 = "+๑๒๓๔ู฿๕๖๗๘๙ฅ  ๐\"ฎฑธํ๊ณฯญฐ,   ฤฆฏโฌ็๋ษศซ.    ()ฉฮฺ์?ฒฬฦ ";

        int total = 56;

        for (int i = 0; i < total; i++) {
            String str = dataEnglish1.charAt(i) + "";
            final JButton btn = new JButton(str);
            btn.setFont(new Font("Tahoma", Font.BOLD, 40));

            if (i == 13) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("<=");
                btn.setBackground(Color.RED);
            } else if (i == 14 || i == 28 || i == 29 || i == 43 || i == 44) {
                btn.setEnabled(false);
            } else if (i == 27) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("OK");
                btn.setBackground(Color.GREEN);
            } else if (i == 41) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("CE");
                btn.setBackground(Color.ORANGE);
            } else if (i == 42) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("UP");
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.WHITE);
            } else if (i == 55) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 22));
                btn.setText("[X]");
                btn.setBackground(Color.BLACK);
                btn.setForeground(Color.WHITE);
            }

            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    inputKey(btn.getText());
                }
            });
            pnEng1.add(btn);
        }

        for (int i = 0; i < total; i++) {
            String str = dataEnglish2.charAt(i) + "";
            final JButton btn = new JButton(str);
            btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 36));

            if (i == 13) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("<=");
                btn.setBackground(Color.RED);
            } else if (i == 14 || i == 28 || i == 29 || i == 43 || i == 44) {
                btn.setEnabled(false);
            } else if (i == 27) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("OK");
                btn.setBackground(Color.GREEN);
            } else if (i == 41) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("CE");
                btn.setBackground(Color.ORANGE);
            } else if (i == 42) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("UP");
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.WHITE);
            } else if (i == 55) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 22));
                btn.setText("[X]");
                btn.setBackground(Color.BLACK);
                btn.setForeground(Color.WHITE);
            }

            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    inputKey(btn.getText());
                }
            });

            pnEng2.add(btn);
        }

        for (int i = 0; i < total; i++) {
            String str = dataThai1.charAt(i) + "";
            final JButton btn = new JButton(str);
            btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 38));

            if (i == 13) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("<=");
                btn.setBackground(Color.RED);
            } else if (i == 14 || i == 28 || i == 29 || i == 43 || i == 44) {
                btn.setEnabled(false);
            } else if (i == 27) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("OK");
                btn.setBackground(Color.GREEN);
            } else if (i == 41) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("CE");
                btn.setBackground(Color.ORANGE);
            } else if (i == 42) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("UP");
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.WHITE);
            } else if (i == 55) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 22));
                btn.setText("[X]");
                btn.setBackground(Color.BLACK);
                btn.setForeground(Color.WHITE);
            }

            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    inputKey(btn.getText());
                }
            });

            pnThai1.add(btn);
        }

        for (int i = 0; i < total; i++) {
            String str = dataThai2.charAt(i) + "";
            final JButton btn = new JButton(str);
            btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 38));

            if (i == 13) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("<=");
                btn.setBackground(Color.RED);
            } else if (i == 14 || i == 28 || i == 29 || i == 43 || i == 44) {
                btn.setEnabled(false);
            } else if (i == 27) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("OK");
                btn.setBackground(Color.GREEN);
            } else if (i == 41) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("CE");
                btn.setBackground(Color.ORANGE);
            } else if (i == 42) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
                btn.setText("UP");
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.WHITE);
            } else if (i == 55) {
                btn.setFont(new java.awt.Font("Tahoma", Font.BOLD, 22));
                btn.setText("[X]");
                btn.setBackground(Color.BLACK);
                btn.setForeground(Color.WHITE);
            }

            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    inputKey(btn.getText());
                }
            });

            pnThai2.add(btn);
        }
    }

    private void inputKey(String text) {
        String tempText = txtShow.getText();
        if (text.equalsIgnoreCase("<=")) {
            if (!tempText.trim().equals("")) {
                tempText = tempText.substring(0, tempText.length() - 1);
                txtShow.setText(tempText);
            }
        } else if (text.equalsIgnoreCase("OK")) {
            TEXT_INPUT = txtShow.getText();
            dispose();
        } else if (text.equalsIgnoreCase("CE")) {
            TEXT_INPUT = "";
            txtShow.setText("");
        } else if (text.equalsIgnoreCase("Space")) {
            txtShow.setText(tempText + " ");
        } else if (text.equalsIgnoreCase("UP")) {
            if (tbControl.getSelectedIndex() == 0) {
                tbControl.setSelectedIndex(1);
            } else if (tbControl.getSelectedIndex() == 1) {
                tbControl.setSelectedIndex(0);
            } else if (tbControl.getSelectedIndex() == 2) {
                tbControl.setSelectedIndex(3);
            } else if (tbControl.getSelectedIndex() == 3) {
                tbControl.setSelectedIndex(2);
            } else {
                tbControl.setSelectedIndex(0);
            }
        } else if (text.equalsIgnoreCase("[X]")) {
            TEXT_INPUT = "";
            txtShow.setText("");
            dispose();
        } else {
            txtShow.setText(tempText + text);
            int tbSelected = tbControl.getSelectedIndex();
            if (tbSelected == 1) {
                tbControl.setSelectedIndex(0);
            } else if (tbSelected == 3) {
                tbControl.setSelectedIndex(2);
            } else if(tbSelected==4){
                
            } else {
                tbControl.setSelectedIndex(0);
            }
        }

        txtShow.requestFocus();
    }
}
