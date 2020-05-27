package com.softpos.main.program;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import java.util.Vector;

public class SerialComm implements Runnable, SerialPortEventListener {

    CommPortIdentifier portId;
    InputStream inputStream;
    SerialPort serialPort;
    Thread readThread;
    String StrRead = "";

    public SerialComm() {
        super();

        try {
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (PortInUseException e) {
        }
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true);
        try {
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        }
        readThread = new Thread(this);
        readThread.start();

    }

    /**
     * SerialComm constructor comment.
     */
    public SerialComm(String comm) {
        super();

        try {
            portId = CommPortIdentifier.getPortIdentifier(comm);
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (Exception e) {
        }
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {

        }
        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true);
        try {
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {

        }

    }

    /**
     * Insert the method's description here. Creation date: (3/22/2001 4:17:38
     * PM)
     */
    public void close() {

        serialPort.close();

    }

    /**
     * Insert the method's description here. Creation date: (3/20/2001 1:23:49
     * PM)
     *
     * @param args java.lang.String[]
     */
    public static void main(String[] args) {

        SerialComm reader = new SerialComm("/dev/ttyUSB0");
    }

    /**
     * When an object implementing interface
     * <code>Runnable</code> is used to create a thread, starting the thread
     * causes the object's
     * <code>run</code> method to be called in that separately executing thread.
     * <p>
     * The general contract of the method
     * <code>run</code> is that it may take any action whatsoever.
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        }

    }

    @Override
    public void serialEvent(SerialPortEvent event) {

        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }

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
                byte[] readBuffer = new byte[20];
                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(readBuffer);
                    }
                    StrRead = new String(readBuffer);
//                System.out.print(new String(readBuffer));
                } catch (IOException e) {
                }
                break;
        }
    }
    Vector booleanValue;
    Vector comports;
    Vector explicacio;
    String port;

    /**
     * SerialComm constructor comment.
     */
    public SerialComm(String comm, Vector com, Vector exp, Vector bool) {
        super();

        comports = com;
        explicacio = exp;
        booleanValue = bool;
        port = comm;

        try {
            portId = CommPortIdentifier.getPortIdentifier(comm);
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (Exception e) {
        }
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true);
        try {
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        }

    }
}