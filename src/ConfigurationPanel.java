import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ConfigurationPanel extends JPanel{
    private ConfigurationDTO configurationDTO;
    private JTable table;

    public ConfigurationPanel(){
        this.configurationDTO = new ConfigurationDTO();
        table = new JTable(new ConfigurationTableModel());
        table.setDefaultRenderer(Object.class, new ConfigurationTableRenderer());
        table.setDefaultEditor(Object.class, new ConfigurationTableEditor());
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.setRowHeight(22);
        table.setFillsViewportHeight(true);
        // titulo
        JLabel label = new JLabel("Thread Lab Configuration");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        // posicionamiento
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(table), gbc);
        gbc.gridy = 0;
        gbc.weighty = 0.05f;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(label, gbc);
    }

    public ConfigurationDTO getConfiguration() {
        Object[] values = new Object[16];
        // variable para saber el siguiente index a insertar del array
        int indexArray = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 1) == null) continue;
            values[indexArray] = table.getValueAt(i, 1);
            indexArray++;
        }
        configurationDTO = new ConfigurationDTO(values);
        return configurationDTO;
    }


    static class ConfigurationTableRenderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            // decorar la tabla

            // filas de titulos
            if(row == 0 || row == 4 || row == 8 || row == 12 || row == 15 || row == 19){
                setBackground(Color.BLUE);
                setForeground(Color.WHITE);
                setFont(getFont().deriveFont(Font.BOLD));
            }
            // filas normales
            else{
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
                setFont(getFont().deriveFont(Font.PLAIN));
            }
            // configuracion checkbox
            if ((row == 16 || row == 20 || row == 21) && column == 1){
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(Boolean.TRUE.equals(value));
                return checkBox;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    static class ConfigurationTableEditor extends AbstractCellEditor implements TableCellEditor {
        private final JCheckBox checkBox = new JCheckBox();
        private final JTextField textField = new JTextField();
        private Component currentEditor;

        public ConfigurationTableEditor() {
            // notifica que ha cambiado el valor
            checkBox.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Object getCellEditorValue() {
            if (currentEditor == checkBox) {
                return checkBox.isSelected();
            } else if (currentEditor == textField) {
                return textField.getText();
            }
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
                currentEditor = checkBox;
            } else {
                textField.setText(value == null ? null : value.toString());
                currentEditor = textField;
            }
            return currentEditor;
        }
    }
    class ConfigurationTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Parameter", "Value"};
        private final Object[][] data = {
                {"**Resource Parameters**", null},
                {"Number of ResourceTypes", String.valueOf(configurationDTO.getResourcesNumber())},
                {"Min General Resources", String.valueOf(configurationDTO.getResourcesGeneralMin())},
                {"Max General Resources", String.valueOf(configurationDTO.getResourcesGeneralMax())},
                {"**Producer Parameters**", null},
                {"Number of Producers", String.valueOf(configurationDTO.getProducersNumbers())},
                {"Producer Delay Min", String.valueOf(configurationDTO.getProducerDelayMin())},
                {"Producer Delay Max", String.valueOf(configurationDTO.getProducerDelayMax())},
                {"**Consumer Parameters**", null},
                {"Number of Consumers", String.valueOf(configurationDTO.getConsumerNumbers())},
                {"Consumer Delay Min", String.valueOf(configurationDTO.getConsumerDelayMin())},
                {"Consumer Delay Max", String.valueOf(configurationDTO.getConsumerDelayMax())},
                {"**Start Delay Parameters**", null},
                {"Start Delay Min (ms)", String.valueOf(configurationDTO.getStartDelayMin())},
                {"Start Delay Max (ms)", String.valueOf(configurationDTO.getStartDelayMax())},
                {"**Thread Lifetime Parameters**", null},
                {"Life Cycle enabled", configurationDTO.isLifeCycleEnabled()},
                {"Min Cicles", String.valueOf(configurationDTO.getMinCycles())},
                {"Max Cicles", String.valueOf(configurationDTO.getMaxCycles())},
                {"**Thread Safety Settings**", null},
                {"Guarded Blocks Enabled", configurationDTO.isGuardedBlocksEnabled()},
                {"Stock Protection Enabled", configurationDTO.isStockProtectionEnabled()}
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
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return (data[rowIndex][columnIndex] != null && columnIndex == 1);
        }
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            data[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }
}

