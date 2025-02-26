import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Viewer extends JPanel {
    private final JTable resourceStats;
    private JTable consumerStats;
    private JTable producerStats;
    final private String[] RESOURCE_COLUMN_NAMES =
            {"Resource ID", "Quantity", "MinQ", "MaxQ", "Consumers num", "Producers num"};
    final private String[] CONSUMER_COLUMN_NAMES =
            {"Consumer ID", "Bound Resource", "Status", "Start Delay", "Consume Delay",
                    "Current Cycle", "Total Cycles","Processing Time","Start Time", "End Time"};
    final private String[] PRODUCER_COLUMN_NAMES =
            {"Producer ID", "Bound Resource", "Status", "Start Delay", "Produce Delay",
                    "Current Cycle", "Max Cycles", "Processing Time", "Start Time", "End Time"};
    public Viewer(){
        // crear tablas para mostrar estadísticas
        resourceStats = createTableStats(RESOURCE_COLUMN_NAMES);
        consumerStats = createTableStats(CONSUMER_COLUMN_NAMES);
        producerStats = createTableStats(PRODUCER_COLUMN_NAMES);

        // crear etiquetas
        JLabel resourceLabel = labelFactory("Resource Statistics");
        JLabel consumerLabel = labelFactory("Consumer Statistics");
        JLabel producerLabel = labelFactory("Producer Statistics");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // posicionar tablas
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(resourceStats), gbc);
        gbc.gridy = 3;
        this.add(new JScrollPane(consumerStats), gbc);
        gbc.gridy = 5;
        this.add(new JScrollPane(producerStats), gbc);
        // insertar labels
        gbc.gridy = 0;
        gbc.weighty = 0.05f;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(resourceLabel, gbc);
        gbc.gridy = 2;
        this.add(consumerLabel, gbc);
        gbc.gridy = 4;
        this.add(producerLabel, gbc);
        System.out.println("Viewer creado");
    }
    private JTable createTableStats(String[] columnNames){
        return new JTable(new DefaultTableModel(new Object[][]{}, columnNames));
    }
    public void populateResources(Object[][] data){
        DefaultTableModel model = (DefaultTableModel) resourceStats.getModel();
        model.setRowCount(0);
        if (data.length == 0) {
            model.addRow(new Object[model.getColumnCount()]);
        }
        for (Object[] row : data) {
            model.addRow(row);
        }
    }  public void populateConsumers(Object[][] data){
        DefaultTableModel model = (DefaultTableModel) consumerStats.getModel();
        model.setRowCount(0);
        if (data.length == 0) {
            model.addRow(new Object[model.getColumnCount()]);
        }
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
    public void populateProducers(Object[][] data){
        DefaultTableModel model = (DefaultTableModel) producerStats.getModel();
        model.setRowCount(0);
        if (data.length == 0) {
            model.addRow(new Object[model.getColumnCount()]);
        }
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
    public void deleteRows(){
        DefaultTableModel model = (DefaultTableModel) resourceStats.getModel();
        model.setRowCount(0);
        model = (DefaultTableModel) consumerStats.getModel();
        model.setRowCount(0);
        model = (DefaultTableModel) producerStats.getModel();
        model.setRowCount(0);
    }
    private JLabel labelFactory(String text){
        JLabel label = new JLabel();
        label.setText(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
