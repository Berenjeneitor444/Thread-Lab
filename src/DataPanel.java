import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private final Data data;

    public DataPanel(){
        data = new Data();
        JLabel label = new JLabel("General Statistics");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(new JScrollPane(data), gbc);
        gbc.gridy = 0;
        gbc.weighty = 0.05f;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(label, gbc);
    }
    public void updateData(int[] newData){
        data.updateData(newData);
    }

}
