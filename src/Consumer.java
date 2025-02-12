import java.util.Date;
import java.util.Random;

public class Consumer implements Runnable {

    public void stop() {
        state = MyController.State.STOPPED;
    }
    private Date startTime;
    private Date stopTime;
    private final MyModel father;
    private String id;
    private ResourceType resourceType;
    private int delay;
    private int startDelay;
    private long processingTime;
    private MyController.State state;
    private boolean lifeCicle;
    private int totalCycles;
    private boolean guardedBlocksEnabled;

    private int currentCycle = 0;
    public Consumer(MyModel father, String name, int delay, int startDelay,
                    boolean lifeCicle, int minCycles, int maxCycles,
                    boolean guardedBlocksEnabled) {
        this.father = father;
        this.id = name;
        this.state = MyController.State.STOPPED;
        this.delay = delay;
        this.startDelay = startDelay;
        this.guardedBlocksEnabled = guardedBlocksEnabled;
        if (lifeCicle) {
            Random random = new Random();
            this.lifeCicle = true;
            this.totalCycles = random.nextInt(minCycles, maxCycles + 1);
        } else {
            this.lifeCicle = false;
        }
        father.incrementTotalConsumers();
        System.out.println("Consumer creado");
    }
    public void run() {
        try {
            Thread.sleep(startDelay);
            if (lifeCicle) {
                while (state == MyController.State.RUNNING) {
                    if(currentCycle >= totalCycles){
                        stop();
                    }
                    Thread.sleep(delay);
                    if (guardedBlocksEnabled) {
                        resourceType.decrementSync(this);
                    } else {
                        resourceType.decrement(this);
                    }
                    // calculo el tiempo procesado
                    processingTime = new Date().getTime() - startTime.getTime();
                    currentCycle++;
                    if (father.isMustStop()) {
                        stop();
                    }
                }
            }
            else {
                while (state == MyController.State.RUNNING) {
                    Thread.sleep(delay);
                    if (guardedBlocksEnabled) {
                        resourceType.decrementSync(this);
                    } else {
                        resourceType.decrement(this);
                    }
                    // calculo el tiempo procesado
                    processingTime = new Date().getTime() - startTime.getTime();
                    currentCycle++;
                    if (father.isMustStop()) {
                        stop();
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            father.decrementActiveThreads();
            stopTime = new Date();
            System.out.println("Consumer " + id + " finalizado");
        }
    }

    public void consume() {
        if (startTime == null) {
            startTime = new Date();
        }
        this.state = MyController.State.RUNNING;
        Thread consumir = new Thread(this);
        father.incrementActiveThreads();
        consumir.start();
    }

    public void setResourceType(ResourceType resourceType){
        resourceType.incrementConsumersNum();
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType(){
        return resourceType;
    }

    public String getId() {
        return id;
    }

    public void turnIDLE() {
        state = MyController.State.IDLE;
    }
    public void turnRUNNING() {
        state = MyController.State.RUNNING;
    }
    public long getProcessingTime() {
        return processingTime;
    }

    public MyController.State getState() {
        return state;
    }

    public int getStartDelay() {
        return startDelay;
    }

    public int getDelay() {
        return delay;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }

    public int getTotalCycles() {
        return totalCycles;
    }

    public boolean isLifeCicle() {
        return lifeCicle;
    }
}
