public class ThreadLab {
    private MyController myController;
    public ThreadLab(){

        System.out.println("MyTask creado");
    }
    public static void main(String[] args) {
        ThreadLab threadLab = new ThreadLab();
        threadLab.myController = new MyController();
        threadLab.myController.getMyView().setVisible(true);
    }
}