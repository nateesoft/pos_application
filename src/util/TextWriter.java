package util;

import directory_utility.DirectoryUtility;
import java.io.FileWriter;
import java.io.IOException;
import util.MSG;

public class TextWriter {

    public static void writeToText(String pathFile, String data) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new DirectoryUtility().getFileAndCreateDir(pathFile), true);

            writer.write(data);
            writer.write(13);
            writer.write(10);

        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                MSG.ERR(null, e.getMessage());
            }
        }
    }

}
