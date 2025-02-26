import java.util.Date;
import java.util.Random;

public class Producer implements Runnable {

    public void turnRUNNING() {
        state = MyController.State.RUNNING;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void stop() {
        state = MyController.State.STOPPED;
    }

    public String getId() {
        return id;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void turnIDLE() {
        state = MyController.State.IDLE;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getDelay() {
        return delay;
    }

    public MyController.State getState() {
        return state;
    }

    public int getStartDelay() {
        return startDelay;
    }

    private Date startTime;
    private Date stopTime;
    private final MyModel father;
    private final String id;
    private ResourceType resourceType;
    private final int delay;
    private MyController.State state;
    private final int startDelay;
    private long processingTime;
    private final boolean lifeCicle;
    private int totalCycles;
    private int currentCycle = 0;
    private boolean guardedBlocksEnabled;

    public Producer(MyModel father, String id, int delay, int startDelay,
                    boolean lifeCicle, int minCycles, int maxCycles, boolean guardedBlocksEnabled) {
        this.father = father;
        this.id = id;
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
        father.incrementTotalProducers();
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
                    if(guardedBlocksEnabled) {
                        resourceType.incrementSync(this);
                    }
                    else {
                        resourceType.increment(this);
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
                    if(guardedBlocksEnabled) {
                        resourceType.incrementSync(this);
                    }
                    else {
                        resourceType.increment(this);
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
        } finally {
            father.decrementActiveThreads();
            stopTime = new Date();
            System.out.println("Producer " + id + " finalizado");
        }
    }


    public void produce() {
        if (startTime == null) {
            startTime = new Date();
        }
        this.state = MyController.State.RUNNING;
        Thread producir = new Thread(this);
        father.incrementActiveThreads();
        producir.start();
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        resourceType.incrementProducersNum();
        this.resourceType = resourceType;
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
