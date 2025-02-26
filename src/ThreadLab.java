public class ThreadLab {
    private MyController myController;
    public ThreadLab(){
        myController = new MyController();
    }
    public static void main(String[] args) {
        ThreadLab threadLab = new ThreadLab();

        threadLab.myController.getMyView().setVisible(true);
    }
}