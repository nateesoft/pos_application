package util;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class DirectoryUtility {

    private DirectoryUtility(){
        throw new AssertionError();
    }
    
    public static boolean createDir(File file) throws IOException {
        boolean success = file.mkdir();
//        System.out.println(file.getCanonicalPath() + " Create. ==> " + success);
        return success;
    }

    public static boolean deleteDir(File file) throws IOException {
        boolean success = file.delete();
//        System.out.println(file.getCanonicalPath() + " Delete. ==> " + success);
        return success;
    }

    public static File getFileAndCreateDir(String pathFile) {
        try {
            StringTokenizer st = new StringTokenizer(pathFile.trim(), "/");
            String path = "";
            File f = null;
            int countToken = st.countTokens();
            for (int i = 0; st.hasMoreTokens(); i++) {
                if (i < countToken - 1) {
                    path += "/" + st.nextToken();
                    f = new File(path);
                    if (!f.exists()) {
                        createDir(f);
                    }
                } else {
                    st.nextToken();
                }
            }
            File file = new File(pathFile);
            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector<File> getFilesInDirectory(String path) {
        try {
            Vector<File> file = new Vector<File>();
            File pathName = new File(path);
            System.out.println("Path " + pathName.getPath());
            String[] fileNames = pathName.list();
            for (int i = 0; i < fileNames.length; i++) {
                File f = new File(pathName.getPath(), fileNames[i]);
                if (f.isDirectory()) {
                } else if (f.isFile()) {
                    file.add(f.getCanonicalFile());
                    System.out.println(f.getCanonicalPath());
                }
            }
            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    public static Vector<File> getAllFilesInDirectory(String path) {
        try {
            files = new Vector<File>();
            findAllFilesInDirectory(path);
            return files;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Vector<File> files;

    private static void findAllFilesInDirectory(String path){
        try {
            File pathName = new File(path);
            String[] fileNames = pathName.list();
            for (int i = 0; i < fileNames.length; i++) {
                File f = new File(pathName.getPath(), fileNames[i]);
                if (f.isDirectory()) {
                    findAllFilesInDirectory(f.getPath());
                } else if (f.isFile()) {
                    files.add(f.getCanonicalFile());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
  
}
