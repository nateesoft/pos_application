package com.softpos.main.program;

import java.io.File;

import java.io.IOException;
import java.util.StringTokenizer;

public class DirectoryUtility {

    public boolean createDir(File file) throws IOException {
        boolean success = file.mkdir();
//        System.out.println(file.getCanonicalPath() + " Create. ==> " + success);
        return success;
    }

    public boolean deleteDir(File file) throws IOException {
        boolean success = file.delete();
//        System.out.println(file.getCanonicalPath() + " Delete. ==> " + success);
        return success;
    }

    public File getFileAndCreateDir(String pathFile) throws Exception {
        StringTokenizer st = new StringTokenizer(pathFile.trim(), "/");
        String path = "";
        File f = null;
        //System.out.println("Token "+st.countTokens());        
        int countToken = st.countTokens();
        for (int i = 0; st.hasMoreTokens(); i++) {
            if (i < countToken - 1) {
                path += "/" + st.nextToken();
                f = new File(path);
                if (!f.exists()) {
                    createDir(f);
                }
                //System.out.println(i + " , " + path);                
            } else {
                st.nextToken();
            }
        }
        File file = new File(pathFile);
        //System.out.println(file.getCanonicalPath() + " === " + file.exists());
        return file;
    }
}
