public class Producer implements Runnable{


    private enum State {
        RUNNING, ENDED, STOPPED;
    }
    //private startTime;
    //private stopTime;
    private final MyModel father;
    private String name;
    private Resource resource;
    private int delay;
    private State state;
    private int startDelay;

    public Producer(MyModel father, String name, Resource resource, int delay, int startDelay){
        this.father = father;
        this.name = name;
        this.state = State.STOPPED;
        this.resource = resource;
        this.delay = delay;
        this.startDelay = startDelay;
        System.out.println("Producer creado");
    }
    public void run(){
        try {
            Thread.sleep(startDelay);
            while (state == State.RUNNING) {
                Thread.sleep(delay);
                resource.addResource();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void produce(){
        this.state = State.RUNNING;
        Thread producir = new Thread(this);
        producir.start();
    }
}
