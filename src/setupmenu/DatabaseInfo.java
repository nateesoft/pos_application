package setupmenu;

import database.MySQLConnect_Bak;
import java.util.List;
import java.util.Map;
import util.DatabaseUtility;

public abstract class DatabaseInfo {
    public static DatabaseUtility du;

    public DatabaseInfo() {
        if(du == null){
            du = new DatabaseUtility(MySQLConnect_Bak.con);
        }
    }
    
    public abstract List<Map<String,Object>> getAllData();
    
}
