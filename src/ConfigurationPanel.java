import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private ConfigurationDTO configurationDTO;
    private JTable table;
    public ConfigurationPanel(){
        this.configurationDTO = new ConfigurationDTO();
        table = new JTable(new ConfigurationTableModel());
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.setRowHeight(20);
        table.setFillsViewportHeight(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(table), gbc);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
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
                {"Life Cycle enabled", String.valueOf(configurationDTO.isLifeCycleEnabled())},
                {"Min Cicles", String.valueOf(configurationDTO.getMinCycles())},
                {"Max Cicles", String.valueOf(configurationDTO.getMaxCycles())},
                {"**Thread Safety Settings**", null},
                {"Guarded Blocks Enabled", String.valueOf(configurationDTO.isGuardedBlocksEnabled())},
                {"Stock Protection Enabled", String.valueOf(configurationDTO.isStockProtectionEnabled())},
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
            data[rowIndex][columnIndex] = String.valueOf(aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }
}

