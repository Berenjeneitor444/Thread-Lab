import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class MyModel {
    private enum State {
        RUNNING, ENDED, STOPPED
    }

    private int counter = 0;
    private final ArrayList<Consumer> consumers = new ArrayList<>();
    private final ArrayList<Producer> producers = new ArrayList<>();
    private final ArrayList<Resource> resources = new ArrayList<>();
    private ConfigurationDTO configurationDTO;
    private MyController father;
    private final Random random = new Random();
    private State state = State.STOPPED;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }


    public MyModel(MyController father) {
        this.father = father;
        configurationDTO = new ConfigurationDTO(6, 6, 3,
                0, 100, 1, 5);
        initializeSimulation();
        System.out.println("MyModel creado");
    }

    public void play() {
        if (state == State.STOPPED){

        }
    }

    public void stop() {
    }
    private void initializeSimulation(){
        for (int i=0; i<configurationDTO.getResourcesNumber(); i++){
            resources.add(new Resource(0, configurationDTO.getResourcesGeneralMax(), configurationDTO.getResourcesGeneralMin(),
                    "Resource "+i));
        }
        for (int i=0; i<configurationDTO.getProducersNumbers(); i++){
            producers.add(new Producer(this,"Producer "+i,
                    resources.get(random.nextInt(configurationDTO.getResourcesNumber()+1)),
                    configurationDTO.getProducerDelay(),random.nextInt(10)));
        }
        for (int i=0; i<configurationDTO.getConsumerNumbers(); i++){
            consumers.add(new Consumer(this,"Consumer "+i,
                    resources.get(random.nextInt(configurationDTO.getResourcesNumber()+1)),
                    configurationDTO.getConsumerDelay(),random.nextInt(10)));
        }
    }
}
