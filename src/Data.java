import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Data extends JTable {
    public Data() {
        super(new DefaultTableModel(
                new Object[][]{
                        {"Total ResourceTypes", 0},
                        {"Total Producers", 0},
                        {"Total Consumers", 0},
                        {"Total Resource Quantity", 0},
                        {"Active Threads", 0},
                        {"IDLE Threads", 0},
                        {"Total Produced Products", 0},
                        {"Total Consumed Products", 0}
                },
                new String[]{"Data", "Value"}
        ));
        this.getColumnModel().getColumn(0).setPreferredWidth(120);
        this.getColumnModel().getColumn(1).setPreferredWidth(30);

        System.out.println("Data Creado");
        this.setFillsViewportHeight(true);
    }
    public void updateData(int[] newData){
        for (int i = 0; i < newData.length; i++) {
            this.setValueAt(newData[i], i, 1);
        }
    }
}
