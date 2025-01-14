import javax.swing.*;

public class MyView extends JFrame {
    private Controls controls;
    private Viewer viewer;
    public MyView(){
        controls = new Controls();
        viewer = new Viewer();
        System.out.println("MyView creado");
        this.add(viewer);
        this.add(controls);
        setLayout(null);
        this.setSize(400, 300);
        this.setTitle("UML Ejemplo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
