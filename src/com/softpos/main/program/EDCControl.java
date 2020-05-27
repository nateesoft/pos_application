package com.softpos.main.program;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class EDCControl extends JDialog implements SerialPortEventListener {

    SerialPort serialPort;
    InputStream inputStream;
    OutputStream outputStream;
    Enumeration portList;
    CommPortIdentifier portId;
    boolean OpenStatus = false;
    //String PortName = "";
    Thread readThread;
    String StrRead;
    String ResultStr = "";
    String WriteStr = "";
    String CardCode = "";
    String CustName = "";
    String AppCode = "";
    String CardType = "";
    Boolean ProcessError = false;
    Boolean ProcessFinish = false;
    //Double Amount = 0.0 ;
    /**
     * Creates new form EDCControl
     */
    public EDCControl(java.awt.Frame parent, boolean modal, String PortName, Double Amount) {
        super(parent, modal);
        initComponents();
        WriteStr = "";
        CardCode = "";
        CustName = "";
        AppCode = "";
        CardType = "";
        ProcessError = false;
        ProcessFinish = false;
        CrMsg.setText("กรุณารูดบัติเครดิต..ที่เครื่องอนุมัติบัตรเครดิต") ;
        ProcessEDC(PortName, Amount);
    }

    public boolean OpenPort(String PortName) {
        OpenStatus = false;
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(PortName)) {
                    //if (portId.getName().equals("/dev/term/a")) 
                    try {
                        serialPort = (SerialPort) portId.open("SimpleWriteApp", 1000);
                    } catch (PortInUseException e) {
                    }

                    try {
                        outputStream = serialPort.getOutputStream();
                        inputStream = serialPort.getInputStream();
                    } catch (IOException e) {
                    }

                    try {
                        serialPort.setSerialPortParams(9600,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                        //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

                        serialPort.setInputBufferSize(2048);
                        serialPort.setOutputBufferSize(2048);
                        OpenStatus = true;

                    } catch (UnsupportedCommOperationException e) {
                    }
                }
            }
        }
        return OpenStatus;
    }

    public void closePort() {
        // If port is alread closed just return.
        if (!OpenStatus) {
            return;
        }
        // Check to make sure sPort has reference to avoid a NPE.

        if (serialPort != null) {
            try {
                // close the i/o streams.
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                System.err.print(e.getMessage());
            }
            // Close the port.
            serialPort.close();
            // Remove the ownership listener.
            //portId.removePortOwnershipListener(this);
        }
        OpenStatus = false;
    }

    private void ProcessEDC(String PortName, Double Amount) {
        int StrLength = 0;
        String ReadStr = "";
        String Msg = "";
        int intAmount = (int) (Amount * 100);
        String StrAmount = PUtility.Addzero(Integer.toString(intAmount), 12);
        String STX = "\u0002\u0000\u0035";
        String ETX = "\u0003";
        Msg = "60000000001020000" + "\u001C" + "40" + "\u0000\u0012" + StrAmount + "\u001C";
        WriteStr = STX + Msg + ETX;
        //WriteStr = toJAVA(WriteStr);
        StrLength = WriteStr.length();
        byte byte1;
        byte byte2;
        byte tempbyte;
        char tempch = WriteStr.charAt(1);
        byte1 = (byte) tempch;
        for (int i = 2; i < StrLength; i++) {
            char ch = WriteStr.charAt(i);
            byte2 = (byte) ch;
            byte1 = (byte) (byte1 ^ byte2);
        }
        tempbyte = byte1;
        char ch = (char) tempbyte;
        WriteStr = WriteStr + ch;
        if (OpenPort(PortName)) {
            serialPort.notifyOnDataAvailable(true);
            try {
                serialPort.addEventListener(this);
            } catch (TooManyListenersException e) {
            }

            try {
                inputStream = serialPort.getInputStream();
            } catch (IOException e) {
            }
            byte[] readBuffer = new byte[2048];
            try {
                while (inputStream.available() > 0) {
                    int numBytes = inputStream.read(readBuffer);
                }
                StrRead = new String(readBuffer);
//                System.out.print(new String(readBuffer));
            } catch (IOException e) {
            }
            byte chz = (byte) StrRead.charAt(0);
            int TimeOut = 0;
            Boolean OutLoop = false;
            while ((StrRead.equals("") | (chz != 6)) & (!OutLoop)) {
                try {
                    outputStream.write(WriteStr.getBytes());
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                chz = (byte) StrRead.charAt(0);
                TimeOut++;
                if (TimeOut > 3000) {
                    OutLoop = true;
                }
            }
            if (TimeOut > 30000) {
                JOptionPane.showMessageDialog(this, "กรุณาตรวจสอบสถานะเครื่อง EDC ...");
                closePort();
                ChkResult();
            }
            ResultStr = "";

        } else {
            JOptionPane.showMessageDialog(this, "Port " + PortName + " ไม่สามารถใช้งานได้...กรุณาตรวจสอบ");
            ChkResult();
        }
    }

    public boolean GetError() {
        return ProcessError;
    }

    public void bntExitOK() {
        ProcessFinish = true;
        ProcessError = true;
        this.dispose();
    }

    public void Test() {
        String TempStr = ResultStr.toString();
        String T1 = TempStr.substring(4, 20);
        String T2 = TempStr.substring(129, 255);
//        System.out.println("TempStr:" + TempStr);
//        System.out.println("T1:" + T1);
//        System.out.println("T2:" + T2);
    }

    public void ChkResult() {
        String T02, TD0, T03, T04, T65, T16, TD1, T31, T50, TD3, TD4, TD5 = "";
        String T30 = "";
        String T01 = "";
        String TD2 = "";
        String ChkError = ResultStr.substring(3, 5);
        //JOptionPane.showMessageDialog(this, ChkError);
        if (ChkError.equals("Y6")) {
            CardCode = "";
            AppCode = "";
            CustName = "";
            CardType = "";
            ProcessFinish = true;
            ProcessError = true;
            SendAck();
            closePort();
            this.dispose();
        }
        int TotalLength = ResultStr.length();
        int Cur = 0;
        String Resp_Code = ResultStr.substring(17, 19);
        if (Resp_Code.equals("00")) {
            if (ResultStr.length() > 256) {
                TotalLength = ResultStr.length();
                Cur = 0;
                String TempText = "";
                while (Cur < TotalLength) {
                    byte ChkText = (byte) ResultStr.charAt(Cur);
                    TempText = TempText + (char) ChkText;
                    if (ChkText == 28) {
                        if (TempText.substring(0, 2).equals("02")) {
                            T02 = TempText.substring(3, TempText.length() - 1);
                        } else if (TempText.substring(0, 2).equals("D0")) {
                            TD0 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("03")) {
                            T03 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("04")) {
                            T04 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("01")) {
                            T01 = TempText.substring(3, TempText.length() - 1);
                        } else if (TempText.substring(0, 2).equals("65")) {
                            T65 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("16")) {
                            T16 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("D1")) {
                            TD1 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("30")) {
                            T30 = TempText.substring(3, TempText.length() - 1);
                        } else if (TempText.substring(0, 2).equals("31")) {
                            T31 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("50")) {
                            T50 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("D3")) {
                            TD3 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("D4")) {
                            TD4 = TempText.substring(2);
                        } else if (TempText.substring(0, 2).equals("D5")) {
                            TD5 = TempText.substring(3, TempText.length() - 1);
                        } else if (TempText.substring(0, 2).equals("D2")) {
                            TD2 = TempText.substring(3, TempText.length() - 1);
                        }
                        TempText = "";
                    }
                    Cur++;
                }

                CardCode = T30;
                AppCode = T01;
                CustName = TD5;
                CardType = TD2;
                if (!AppCode.equals("")) {
                    ProcessFinish = true;
                    ProcessError = false;
                } else {
                    ProcessFinish = true;
                    ProcessError = true;
                }
                SendAck();
                closePort();
                this.dispose();
            }
        } else {
            CardCode = "";
            AppCode = "";
            CustName = "";
            CardType = "";
            ProcessFinish = true;
            ProcessError = true;
            SendAck();
            closePort();
            this.dispose();
        }

    }

    public void SendAck() {
        String Ack = "\u0006";
        try {
            outputStream.write(Ack.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[1024];
                int numBytes = 0;
                try {
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);
                    }
                    StrRead = new String(readBuffer);
                    int i = 0;
                    int totbyteread = StrRead.length();
                    while (i < totbyteread) {
                        byte ch = (byte) readBuffer[i];
                        if (ch != 0) {
                            ResultStr = ResultStr + (char) ch;
                        }
                        i++;
                    }
                    if (ResultStr.length() > 20) {
                        ChkResult();
                    }
                } catch (IOException e) {
                }
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CrMsg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.white);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(244, 43, 22));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, java.awt.Color.lightGray));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        CrMsg.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        CrMsg.setForeground(java.awt.Color.white);
        CrMsg.setText("กรุณารูดบัตรเครดิต...ที่เครื่องอนุมัติบัตรเครดิต");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CrMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CrMsg)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(543, 200, 519, 54);
    }// </editor-fold>//GEN-END:initComponents

private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        //bntExitOK() ;
    }
}//GEN-LAST:event_formKeyPressed

private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed

}//GEN-LAST:event_jPanel1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardCode = "";
        AppCode = "";
        CustName = "";
        CardType = "";
        ProcessFinish = true;
        ProcessError = true;
        //SendAck();
        closePort();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CrMsg;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
