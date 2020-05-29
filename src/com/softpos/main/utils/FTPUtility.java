package com.softpos.main.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtility {
    private FTPClient ftp;
    
    public static void main(String[] args) {
        FTPUtility ftp = new FTPUtility();
        ftp.connect("localhost", "fgordering", "bor_ftp", 21);
        ftp.download("C:rss/rss.txt", "C:/tmp/rss.txt");
    }
    public boolean connect(String server, String user, String pass, int port) {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftp.setDefaultPort(port);
        ftp.setDataTimeout(1000 * 15);

        try {
            int reply;
            ftp.connect(server);

            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        try {
            if (!ftp.login(user, pass)) {
                ftp.logout();
                ftp = null;
                
                return false;
            }
        } catch (IOException e) {
            MSG.ERR(null, e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean download(String remoteFile, String localFile) {
        OutputStream output = null;
        InputStream input = null;
        boolean success = false;
        try {
            long max = 0;
            try {
                FTPFile[] files = ftp.listFiles(remoteFile);

                for (int i = 0; i < files.length; i++) {
                    max = files[i].getSize();
                }
                if (max == 0) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalActiveMode();

            DirectoryUtility du = new DirectoryUtility();
            File localF = du.getFileAndCreateDir(localFile);
            output = new FileOutputStream(localF);
            input = ftp.retrieveFileStream(remoteFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
                output.flush();
            }
            success = true;            
            return success;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean upload(String localFile, String remoteFile) {
        InputStream input = null;
        OutputStream output = null;
        boolean success = false;
        try {
            if (true) {
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
            }
            ftp.enterLocalPassiveMode();
            File f = new File(localFile);
            if (!f.exists()) {
                return false;
            }
            input = new FileInputStream(localFile);
            output = ftp.storeFileStream(remoteFile);

            if (output == null) {                
                return false;
            }

            byte[] buffer = new byte[1024];
            int len;

            f = new File(localFile);

            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
                output.flush();
            }
            success = true;
            return success;

        } catch (IOException e) {
            return false;
        }
    }    
}
