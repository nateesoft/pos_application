package util;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JTableUtility {

    public JTableUtility() {

    }

    public static void initDefaultTable(JTable table) {
        try {

            //Setting column size
            table.setShowGrid(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setRowSelectionAllowed(true);
            table.setBackground(Color.WHITE);
            table.setGridColor(Color.LIGHT_GRAY);

            JTableHeader header = table.getTableHeader();
            header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
            table.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
            table.setRowHeight(25);

        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

    }

    public static void initColumnSizeTable(JTable table, int[] colSize) {
        try {

            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

            //Setting column size
            TableColumn column = null;
            for (int i = 0; i < colSize.length; i++) {
                column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(colSize[i]);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void initColumnSizeTable(JTable table, int[] colSize, int resizeMode) {
        try {

            table.setAutoResizeMode(resizeMode);

            //Setting column size
            TableColumn column = null;
            for (int i = 0; i < colSize.length; i++) {
                column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(colSize[i]);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void initHorizontalAlignmentTable(JTable table, int[] leading, int[] center, int[] right) {
        try {

            TableColumnModel tcm = table.getColumnModel();

            DefaultTableCellRenderer d;

            int ncolumn = table.getColumnCount();
            d = new DefaultTableCellRenderer();
            d.setHorizontalAlignment(SwingConstants.LEADING);
            for (int i = 0; i < ncolumn; i++) {
                tcm.getColumn(i).setCellRenderer(d);
            }

            if (leading != null) {
                if (leading.length != 0) {
                    d = new DefaultTableCellRenderer();
                    d.setHorizontalAlignment(SwingConstants.LEADING);
                    for (int index : leading) {
                        tcm.getColumn(index).setCellRenderer(d);
                    }
                }
            }

            if (center != null) {
                if (center.length != 0) {
                    d = new DefaultTableCellRenderer();
                    d.setHorizontalAlignment(SwingConstants.CENTER);
                    for (int index : center) {
                        tcm.getColumn(index).setCellRenderer(d);
                    }
                }
            }

            if (right != null) {
                if (right.length != 0) {
                    d = new DefaultTableCellRenderer();
                    d.setHorizontalAlignment(SwingConstants.RIGHT);
                    for (int index : right) {
                        tcm.getColumn(index).setCellRenderer(d);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean resetTableModel(TableModel model) {
        boolean success = false;
        try {

            if (model instanceof DefaultTableModel) {
                DefaultTableModel dtm = (DefaultTableModel) model;
                int nrow = dtm.getRowCount();
                for (int i = 0; i < nrow; i++) {
                    dtm.removeRow(0);
                }
                success = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return success;
    }
}
