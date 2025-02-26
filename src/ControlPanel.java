import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public Play getPlay() {
        return play;
    }
    public Stop getCancel() {
        return stop;
    }
    private Play play;
    private Stop stop;
    private Hilo hilo;

    public ControlPanel(){
        play = new Play();
        stop = new Stop();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 5, 0, 5);
        this.add(stop, constraints);

        constraints.gridx = 0;
        this.add(play, constraints);

        this.setVisible(true);
    }
    public void toogleStopButton(){
        if (stop.getText().equals("Stop")){
            stop.setText("Clear");
        }
        else{
            stop.setText("Stop");
        }
    }
}
