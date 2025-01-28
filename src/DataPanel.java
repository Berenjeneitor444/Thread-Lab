import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private Data data;

    public Data getData() {
        return data;
    }

    public DataPanel(){
        data = new Data();
        this.add(data);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        System.out.println("DataViewer Creado");
    }
}
