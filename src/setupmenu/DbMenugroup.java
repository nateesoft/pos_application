package setupmenu;

import java.util.List;
import java.util.Map;

public class DbMenugroup extends DatabaseInfo {
    private static String sql_getAllData;
    private static String sql_getAtCodeId;
    private static String sql_delCodeId;
    private static String sql_addNewData;
    private static String sql_seekNewData;
    private static String sql_delPcode;

    static {
        sql_getAllData = "SELECT * FROM menugrou";
        sql_getAtCodeId = "SELECT * FROM menugroup m LEFT JOIN product p ON (m.pcode = p.pcode) WHERE code_id=?";
        sql_delCodeId = "DELETE FROM menugroup" +
                " WHERE code_id = ?";
        sql_delPcode = "DELETE FROM menugroup" +
                " WHERE code_id = ? AND pcode = ?";
        sql_addNewData = "INSERT INTO menugroup (code_id, pcode)" +
                " VALUES(? , ?)";
        sql_seekNewData = "SELECT * From menugroup WHERE code_id=? AND pcode=?";
       
    }

    public DbMenugroup() {
        super();
    }

    @Override
    public List<Map<String, Object>> getAllData() {
        return du.queryList(sql_getAllData);
    }

    public List<Map<String, Object>> getAtCodeId(String codeId) {
        return du.queryList(sql_getAtCodeId,codeId);
    }
    
    public int deleteCodeId(String codeId){
        return du.executeUpdate(sql_delCodeId, codeId);
    }
    public int deletePcode(String codeId, String pcode){
        return du.executeUpdate(sql_delPcode, codeId, pcode);
    }
    
    public int addNewData(String code_id,String pcode){
//        System.out.println("Add New Data : "+code_id+" , "+pcode);
        return du.executeUpdate(sql_addNewData, code_id,pcode);
    }
    
    public boolean seekNewData(String code_id, String pcode){
        boolean success = false;
        Map<String,Object> data = du.querySingle(sql_seekNewData, code_id, pcode);
        if(data != null){
            if(!data.isEmpty()){
                success = true;
            }
        }
        return success;
    }

}
