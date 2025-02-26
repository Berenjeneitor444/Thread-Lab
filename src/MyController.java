import java.awt.*;

public class MyController {
    public enum State {
        RUNNING, STOPPED, IDLE
    }
    private MyView myView;
    private MyModel myModel;

    public MyController() {
        myView = new MyView(this);
        myModel = new MyModel(this);

    }

    public MyView getMyView() {
        return myView;
    }

    public MyModel getMyModel() {
        return myModel;
    }
    public StatisticsDTO getStatisticsDTO(){
        return myModel.getStatisticsDTO();
    }
    public void play(){
        myModel.play();
    }
    public void stop(){
        myModel.stop();
    }
    public ConfigurationDTO getConfiguration(){
        return myView.getConfiguration();
    }

    public void clear() {
        myModel.clear();
    }
}
