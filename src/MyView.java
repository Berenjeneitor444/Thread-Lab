import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyView extends JFrame implements ActionListener, Runnable {
    private final ControlPanel controlPanel;
    private final Viewer viewer;
    private final DataPanel dataPanel;
    private final ConfigurationPanel configurationPanel;
    private final MyController father;

    public MyView(MyController father) {
        this.father = father;
        controlPanel = new ControlPanel();
        viewer = new Viewer();
        dataPanel = new DataPanel();
        configurationPanel = new ConfigurationPanel();
        controlPanel.getoK().addActionListener(this);
        controlPanel.getCancel().addActionListener(this);
        controlPanel.getHilo().addActionListener(this);
        System.out.println("MyView creado");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 4;
        constraints.gridy = 0;

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridheight = 4;
        constraints.gridwidth = 4;
        this.getContentPane().add(viewer, constraints);
        constraints.gridx = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 3;
        this.add(dataPanel, constraints);
        constraints.gridx = 2;
        this.add(configurationPanel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.1;
        constraints.weightx = 0.1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(controlPanel, constraints);
        this.setSize(800, 630);
        this.setTitle("UML Ejemplo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread coger = new Thread(this);
        coger.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String botonTexto;
        var boton = e.getSource();
        if (boton instanceof JButton) {
            botonTexto = ((JButton) boton).getText();
            JOptionPane.showMessageDialog(
                    MyView.this,
                    "Has presionado " + botonTexto,
                    "Button Pressed",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @Override
    public void run() {
        try{
            int contador = 0;
            while (contador < 1000) {
                Thread.sleep(100);
                int counter = father.getCounter();
                dataPanel.getData().setValueAt(counter, 0, 1);
                dataPanel.getData().setValueAt(counter, 1, 1);
                dataPanel.getData().setValueAt(counter, 2, 1);
                contador++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
