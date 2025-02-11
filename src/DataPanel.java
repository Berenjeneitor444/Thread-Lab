import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private final Data data;

    public DataPanel(){
        data = new Data();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(new JScrollPane(data), gbc);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        System.out.println("DataViewer Creado");
    }
    public void updateData(int[] newData){
        data.updateData(newData);
    }

}
