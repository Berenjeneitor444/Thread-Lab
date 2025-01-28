import java.util.Date;

public class Consumer implements Runnable {
    private enum State {
        RUNNING, ENDED, STOPPED
    }

    private Date startTime;
    private Date stopTime;
    private final MyModel father;
    private String name;
    private Resource resource;
    private int delay;
    private int startDelay;
    private State state;

    public Consumer(MyModel father, String name, Resource resource, int delay, int startDelay) {
        this.father = father;
        this.name = name;
        this.state = State.STOPPED;
        this.resource = resource;
        this.delay = delay;
        this.startDelay = startDelay;
        System.out.println("Consumer creado");
    }

    public void run() {
        try {
            Thread.sleep(startDelay);
            while (state == State.RUNNING) {
                Thread.sleep(delay);
                resource.removeResource();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        this.state = State.RUNNING;
        Thread consumir = new Thread(this);
        consumir.start();
    }
}
