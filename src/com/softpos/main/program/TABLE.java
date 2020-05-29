package com.softpos.main.program;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class TABLE {
    
    public static JTable getDefaultTableFont(JTable table){
        JTableHeader tHead = table.getTableHeader();
        tHead.setFont(new Font("Norasi", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setFont(new Font("Norasi", Font.PLAIN, 14));
        table.setShowGrid(true);
        
        return table;
    }
    
    public static JTable center(JTable table, int row, int col){
        TableColumnModel tcm = table.getColumnModel();
        TableCellRenderer r = table.getCellRenderer(row, col);
        tcm.getColumn(6).setCellRenderer(r);
        
        return table;
    }
}
