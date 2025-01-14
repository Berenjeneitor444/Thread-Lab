import javax.swing.*;

public class Controls extends JPanel {
    private OK oK;
    private Cancel cancel;
    public Controls(){
        oK = new OK();
        cancel = new Cancel();
        this.add(cancel);
        this.add(oK);
        this.setBounds(0,200,400,100);
        this.setVisible(true);
        System.out.println("Controls creado");
    }
}
