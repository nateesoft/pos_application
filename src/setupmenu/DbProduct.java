package setupmenu;

import java.util.List;
import java.util.Map;

public class DbProduct extends DatabaseInfo {

    public DbProduct() {
        super();
    }
    
    @Override
    public List<Map<String,Object>> getAllData(){
        return du.queryList("SELECT * FROM product WHERE pactive = 'Y' and pfix='F' ORDER BY pcode");
    }
    public Map<String,Object> getAtPk(String pcode){
        return du.querySingle("SELECT * FROM product WHERE pcode=? AND pactive = 'Y' and PFix='F'",pcode);
    }
    public List<Map<String,Object>> getAtPgroup(String pgroup){
        return du.queryList("SELECT * FROM product WHERE pgroup=? AND pactive = 'Y' and pfix='F' ORDER BY pcode", pgroup);
    }
    
    public boolean seekAtPk(String pcode){
        boolean success = false;
        Map<String,Object> data = getAtPk(pcode);
        if(data != null){
            if(!data.isEmpty()){
                success = true;
            }
        }
        return success;
    }
}
