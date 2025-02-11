import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyView extends JFrame implements ActionListener, Runnable {
    public ConfigurationDTO getConfiguration() {
        return configurationPanel.getConfiguration();
    }

    private enum State {
        RUNNING, STOPPED, CREATED
    }
    private State state = State.STOPPED;
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
        controlPanel.getPlay().addActionListener(this);
        controlPanel.getCancel().addActionListener(this);
        System.out.println("MyView creado");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.75;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        add(configurationPanel, constraints);
        constraints.gridy = 1;
        constraints.weighty = 0;
        add(controlPanel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.5;
        constraints.weighty = 1;
        constraints.gridheight = 2;
        add(dataPanel, constraints);
        constraints.weightx = 4;
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(viewer, constraints);
        this.setSize(800, 630);
        this.setTitle("UML Ejemplo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var boton = e.getSource();
        if (boton instanceof JButton) {
            if (boton instanceof Play){
                if (state == State.STOPPED){
                    state = State.RUNNING;
                    father.play();
                    Thread coger = new Thread(this);
                    coger.start();
                }
            }
            else if(boton instanceof Stop){
                if(((Stop) boton).getText().equals("Stop")){
                    if (state == State.RUNNING){
                        state = State.STOPPED;
                        father.stop();
                        controlPanel.toogleStopButton();
                    }
                }
                else{
                    dataPanel.updateData(new int[]{0, 0, 0, 0, 0, 0, 0, 0});
                    viewer.deleteRows();
                    father.clear();
                    state = State.STOPPED;
                    controlPanel.toogleStopButton();
                }

            }
        }
    }

    @Override
    public void run() {
        try {
            while (state == State.RUNNING) {
                refreshStatistics();
                Thread.sleep(100);
            }
            // espera para actualizar las estadisticas al final
           Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            refreshStatistics();
        }
    }
    private void populateIndividualStats(){
        StatisticsDTO statisticsDTO = father.getStatisticsDTO();
        viewer.populateResources(statisticsDTO.getResourceTypesData());
        viewer.populateProducers(statisticsDTO.getProducersData());
        viewer.populateConsumers(statisticsDTO.getConsumersData());
    }
    private void refreshStatistics(){
        StatisticsDTO statisticsDTO = father.getStatisticsDTO();
        int[] data = {statisticsDTO.getTotalResourceTypes(), statisticsDTO.getTotalProducers(),
                statisticsDTO.getTotalConsumers(), statisticsDTO.getTotalResourceQuantity(),
                statisticsDTO.getActiveThreads(), statisticsDTO.getIDLEthreads(),
                statisticsDTO.getTotalProduced(), statisticsDTO.getTotalConsumed()};
        dataPanel.updateData(data);
        populateIndividualStats();
    }
}
