import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;

public class MyView extends JFrame implements ActionListener{
    private ControlPanel controlPanel;
    private Viewer viewer;

    public MyView(){

        controlPanel = new ControlPanel();
        viewer = new Viewer();
        controlPanel.getoK().addActionListener(this);
        controlPanel.getCancel().addActionListener(this);
        System.out.println("MyView creado");
        setLayout(new GridBagLayout());
        GridBagConstraints constraintsViewer = new GridBagConstraints();
        constraintsViewer.gridx = 0;
        constraintsViewer.gridy = 0;
        constraintsViewer.gridheight = 3;
        constraintsViewer.gridwidth = 3;
        constraintsViewer.fill = GridBagConstraints.BOTH;
        constraintsViewer.weightx = 1;
        constraintsViewer.weighty= 1;
        GridBagConstraints constraintsControlPane = new GridBagConstraints();
        constraintsControlPane.gridx = 0;
        constraintsControlPane.gridy = 3;
        constraintsControlPane.gridheight = 1;
        constraintsControlPane.gridwidth = 3;
        constraintsControlPane.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(viewer, constraintsViewer);
        this.add(controlPanel, constraintsControlPane);
        this.setSize(650,480);
        this.setTitle("UML Ejemplo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String botonTexto;
        var boton = e.getSource();
        if (boton instanceof JButton){
            botonTexto = ((JButton) boton).getText();
            JOptionPane.showMessageDialog(
                    MyView.this,
                    "Has presionado " + botonTexto,
                    "Button Pressed",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
