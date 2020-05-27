package util;

import directory_utility.DirectoryUtility;
import java.awt.Frame;
import java.io.File;

public class ZipUnzip extends ZipUtility {

    public boolean zip(String sourcePath, String destinationPath) {
        try {
            File srcFile = new File(sourcePath.trim());
            if (!srcFile.exists()) {
                MSG.ERR(new Frame(), "Can not find file or directory : \n" + sourcePath);
                return false;
            }

            DirectoryUtility du = new DirectoryUtility();
            File desFile = du.getFileAndCreateDir(destinationPath.trim());

            if (srcFile.isFile()) {
                zipFile(srcFile, desFile);
            } else if (srcFile.isDirectory()) {
                zipDirectory(srcFile, desFile);
            }

            return true;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
            return false;
        }
    }

    public boolean unzip(String zipPath, String extractToPath) {
        try {
            File srcFile = new File(zipPath.trim());
            if (!srcFile.exists()) {
                MSG.ERR(new Frame(), "Can not find file or directory : \n" + zipPath);
                return false;
            }

            DirectoryUtility du = new DirectoryUtility();
            File desFile = du.getFileAndCreateDir(extractToPath.trim());

            unzip(srcFile, desFile);

            return true;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
            return false;
        }
    }
}
