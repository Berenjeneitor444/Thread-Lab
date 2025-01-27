import java.awt.*;

public class MyController {
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
    public void play(){}
    public void stop(){}
}
