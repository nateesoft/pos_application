package setupmenu;

import java.util.List;
import java.util.Map;

public class DbGroupfile extends DatabaseInfo {
    private static String sql_getAllData ;
    private static String sql_getAtPk ;
    private static String sql_seekAtPk;
    static {
        sql_getAllData = "SELECT * FROM groupfile";
        sql_getAtPk = "SELECT * FROM groupfile WHERE groupcode=?";
        sql_seekAtPk = "SELECT * FROM groupfile WHERE groupcode=?";
    }

    public DbGroupfile() {
        super();
    }
    
    
    public List<Map<String,Object>> getAllData(){
//        dbUtility.dbconnect();
//        du.setConnect(dbUtility.con);
        List<Map<String,Object>> result = du.queryList(sql_getAllData);
//        du.close();
        return result;
    }
    public Map<String,Object> getAtPk(String groupcode){
//        dbUtility.dbconnect();
//        du.setConnect(dbUtility.con);
        Map<String,Object> result = du.querySingle(sql_getAtPk,groupcode);
//        du.close();
        return result;
    }
    
    public boolean seekAtPk(String groupcode){
        boolean success = false;
        Map<String,Object> data = du.querySingle(sql_seekAtPk, groupcode);
        if(data != null){
            if(!data.isEmpty()){
                success = true;
            }
        }
        return success;
    }
}
