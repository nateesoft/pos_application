package setupmenu;

import java.util.List;
import java.util.Map;

public class DbMenusetup extends DatabaseInfo {

    private static String sql_getAllData;
    private static String sql_getAtCodeIdAndDetail;
    private static String sql_getAtCodeId;
    private static String sql_delCodeId;
    private static String sql_delSubCodeId;
    private static String sql_update;
    private static String sql_insert;
    private static String sql_seekAtPk;

    static {
        sql_getAllData = "SELECT * FROM menusetup";

        sql_getAtCodeIdAndDetail = "SELECT * "
                + " FROM menusetup m "
                + " LEFT JOIN product p ON m.pcode=p.pcode"
                + " WHERE code_id = ?";

        sql_getAtCodeId = "SELECT * FROM menusetup WHERE code_id LIKE ?";

        sql_delCodeId = "DELETE FROM menusetup"
                + " WHERE code_id = ?";

        sql_delSubCodeId = "DELETE FROM menusetup"
                + " WHERE ( SubString(code_id,0,2) = SubString(?,0,2) )";

        sql_update = "UPDATE menusetup SET"
                + " code_type = ?,"
                + " pcode = ?,"
                + " shortname = ?,"
                + " ppathname = ? "
                + " WHERE code_id = ?";

        sql_insert = "INSERT INTO menusetup (code_id,code_type,pcode,shortname,ppathname)"
                + " VALUES (?,?,?,?,?)";

        sql_seekAtPk = "SELECT * FROM menusetup WHERE code_id=?";
    }

    @Override
    public List<Map<String, Object>> getAllData() {
        return du.queryList(sql_getAllData);
    }

    public List<Map<String, Object>> getAtCodeIdAndDetail(String codeId) {
        return du.queryList(sql_getAtCodeIdAndDetail, codeId);
    }

    public Map<String, Object> getAtCodeId(String codeId) {
        //JOptionPane.showMessageDialog(null, "dddd");
        return du.querySingle(sql_getAtCodeId, codeId);
    }

    public int deleteCodeId(String codeId) {
        return du.executeUpdate(sql_delCodeId, codeId);
    }

    public int deleteSubCodeId(String codeId) {
        return du.executeUpdate(sql_delSubCodeId, codeId);
    }

    public int update(String codeId, char codeType, String pcode, String shortname, String ppathname, String pcolor) {
        return du.executeUpdate(sql_update, String.valueOf(codeType), pcode, shortname, ppathname, codeId);
    }

    public boolean seekAtPk(String codeId) {
        boolean success = false;
        Map<String, Object> data = du.querySingle(sql_seekAtPk, codeId);
        if (data != null) {
            if (!data.isEmpty()) {
                success = true;
            }
        }
        return success;
    }

    public int insert(String codeId, char codeType, String pcode, String shortname, String ppathname, String pcolor) {
        int rs = 0;

        if (!seekAtPk(codeId)) {
            rs = du.executeUpdate(sql_insert, codeId, String.valueOf(codeType), pcode, shortname, ppathname);
        }

        return rs;
    }

}
