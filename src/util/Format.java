package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Format {
    private Format(){
        throw new AssertionError();
    }
    public final static SimpleDateFormat dateFmtYMD = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    public final static SimpleDateFormat dateFmtDMY = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public final static SimpleDateFormat dateFmtSql = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public final static SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    public final static DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");
    public final static DecimalFormat integerFmt = new DecimalFormat("##,###,##0");
    public final static SimpleDateFormat dateShowFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public final static SimpleDateFormat dateSqlFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public final static SimpleDateFormat timeSqlFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    public final static DecimalFormat double2Fmt = new DecimalFormat("##,###,##0.00");
    public final static DecimalFormat double3Fmt = new DecimalFormat("##,###,##0.000");
    
    public final static SimpleDateFormat datetimeSqlFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
}
