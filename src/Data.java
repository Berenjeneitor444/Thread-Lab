import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Data extends JTable {
    private final DataModel model = new DataModel();
    public Data() {
        super();
        this.setModel(model);
        this.getColumnModel().getColumn(0).setPreferredWidth(120);
        this.getColumnModel().getColumn(1).setPreferredWidth(30);
        this.setRowHeight(22);
        this.setFillsViewportHeight(true);
    }
    public void updateData(int[] newData){
        for (int i = 0; i < newData.length; i++) {
            this.setValueAt(newData[i], i, 1);
        }
    }
    // modelo con la estructura de la tabla
    static class DataModel extends AbstractTableModel{
        private final String[] columnNames = {"Data", "Value"};
        private final Object[][] data = {
                {"Total ResourceTypes", 0},
                {"Total Producers", 0},
                {"Total Consumers", 0},
                {"Total Resource Quantity", 0},
                {"Active Threads", 0},
                {"IDLE Threads", 0},
                {"Total Produced Products", 0},
                {"Total Consumed Products", 0}
        };
        @Override
        public int getRowCount() {
            return data.length;
        }
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            data[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}
