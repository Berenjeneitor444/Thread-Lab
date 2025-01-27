import javax.swing.*;

public class Data extends JTable {
    public Data(){
        super(3,2);
        System.out.println("Data Creado");
        this.setValueAt("Contador A",0,0);
        this.setValueAt("Contador B",1,0);
        this.setValueAt("Contador C",2,0);
        this.setValueAt(0,0,1);
        this.setValueAt(0,1,1);
        this.setValueAt(0,2,1);
    }
}
