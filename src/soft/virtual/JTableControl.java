
package soft.virtual;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class JTableControl {
    
    public static void alignColumn(JTable tblShowBalance, int col, String align) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        if(align.equals("right")){
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        }else if(align.equals("left")){
            rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        }else if(align.equals("center")){
            rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        }
        
        tblShowBalance.getColumnModel().getColumn(col).setCellRenderer(rightRenderer);
    }
}
