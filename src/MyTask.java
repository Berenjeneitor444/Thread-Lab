public class MyTask {
    private MyView myView;
    private MyModel myModel;
    public MyTask(){
        myView = new MyView();
        myModel = new MyModel();
        System.out.println("MyTask creado");
    }
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        myTask.myView.setVisible(true);
    }
}