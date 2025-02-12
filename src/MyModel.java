import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MyModel {
    public StatisticsDTO getStatisticsDTO() {
        refreshStatisticsDTOData();
        return statisticsDTO;
    }

    public boolean isMustStop() {
        return mustStop;
    }

    public void clear() {
        consumers.clear();
        producers.clear();
        resourceTypes.clear();
        statisticsDTO = new StatisticsDTO();
        mustStop = false;
        state = MyController.State.STOPPED;
    }

    private final ArrayList<Consumer> consumers = new ArrayList<>();
    private final ArrayList<Producer> producers = new ArrayList<>();
    private final ArrayList<ResourceType> resourceTypes = new ArrayList<>();
    private ConfigurationDTO configurationDTO;
    private MyController father;
    private final Random random = new Random();
    private MyController.State state = MyController.State.STOPPED;
    private StatisticsDTO statisticsDTO;
    private boolean mustStop = false;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");

    public synchronized void incrementTotalResourceTypes() {
        statisticsDTO.incrementTotalResourceTypes();
    }

    public synchronized void incrementTotalProducers() {
        statisticsDTO.incrementTotalProducers();
    }

    public synchronized void incrementTotalConsumers() {
        statisticsDTO.incrementTotalConsumers();
    }

    public synchronized void incrementTotalResourceQuantity() {
        statisticsDTO.incrementTotalResourceQuantity();
    }

    public synchronized void decrementTotalResourceQuantity() {
        statisticsDTO.decrementTotalResourceQuantity();
    }

    public synchronized void incrementIDLEthreads() {
        statisticsDTO.incrementIDLEthreads();
    }

    public synchronized void decrementIDLEthreads() {
        statisticsDTO.decrementIDLEthreads();
    }

    public synchronized void incrementActiveThreads() {
        statisticsDTO.incrementActiveThreads();
    }

    public synchronized void decrementActiveThreads() {
        statisticsDTO.decrementActiveThreads();
    }

    public MyModel(MyController father) {
        this.father = father;
        statisticsDTO = new StatisticsDTO();
        System.out.println("MyModel creado");
    }

    public void play() {
        if (state != MyController.State.RUNNING) {
            state = MyController.State.RUNNING;
            configurationDTO = father.getConfiguration();
            initializeSimulation();
            for (Producer producer : producers) {
                producer.produce();
            }
            for (Consumer consumer : consumers) {
                consumer.consume();
            }
        }
    }

    public void stop() {
        if (state == MyController.State.RUNNING) {
            state = MyController.State.STOPPED;
            synchronized (this) {
                mustStop = true;
            }

            try {
                // damos tiempo antes de despertarlos
                // de esta manera aseguramos que lleguen a estar en IDLE antes de notificarlos y no después
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Despertamos los hilos atascados en IDLE para que mueran
            for (ResourceType rst : resourceTypes) {
                rst.wakeUpIDLEThreads();
            }
        }
    }

    private void initializeSimulation() {
        for (int i = 0; i < configurationDTO.getResourcesNumber(); i++) {
            resourceTypes.add(
                    new ResourceType(
                            this, configurationDTO.getResourcesGeneralMin(), configurationDTO.getResourcesGeneralMax(),
                            "Resource " + i, configurationDTO.isStockProtectionEnabled()));
        }
        for (int i = 0; i < configurationDTO.getProducersNumbers(); i++) {
            Producer producer = new Producer(this, "Producer " + i,
                    random.nextInt(configurationDTO.getProducerDelayMin(), configurationDTO.getProducerDelayMax()),
                    random.nextInt(10), configurationDTO.isLifeCycleEnabled(), configurationDTO.getMinCycles(),
                    configurationDTO.getMaxCycles(), configurationDTO.isGuardedBlocksEnabled());
            producer.setResourceType(resourceTypes.get(random.nextInt(resourceTypes.size())));
            producers.add(producer);
        }
        for (int i = 0; i < configurationDTO.getConsumerNumbers(); i++) {
            Consumer consumer = new Consumer(this, "Consumer " + i,
                    random.nextInt(configurationDTO.getConsumerDelayMin(),
                            configurationDTO.getConsumerDelayMax()), random.nextInt(10),
                    configurationDTO.isLifeCycleEnabled(), configurationDTO.getMinCycles(),
                    configurationDTO.getMaxCycles(), configurationDTO.isGuardedBlocksEnabled());
            consumer.setResourceType(resourceTypes.get(random.nextInt(resourceTypes.size())));
            consumers.add(consumer);
        }
    }

    // solo refresca las array consumer, producers y resourceTypes Data
    private void refreshStatisticsDTOData() {
        Object[][] resourceTypeData = new Object[resourceTypes.size()][6];
        Object[][] producerData = new Object[producers.size()][9];
        Object[][] consumerData = new Object[consumers.size()][9];

        for (int i = 0; i < resourceTypeData.length; i++) {
            ResourceType resourceType = resourceTypes.get(i);
            resourceTypeData[i] = breakDownResourceType(resourceType);
        }

        for (int i = 0; i < producerData.length; i++) {
            Producer producer = producers.get(i);
            producerData[i] = breakDownProducer(producer);
        }

        for (int i = 0; i < consumerData.length; i++) {
            Consumer consumer = consumers.get(i);
            consumerData[i] = breakDownConsumer(consumer);
        }
        statisticsDTO.setResourceTypesData(resourceTypeData);
        statisticsDTO.setProducersData(producerData);
        statisticsDTO.setConsumersData(consumerData);
    }

    private Object[] breakDownResourceType(ResourceType resourceType) {
        Object[] data = new Object[6];
        data[0] = resourceType.getId();
        data[1] = resourceType.getQuantity();
        data[2] = resourceType.getMinQuantity();
        data[3] = resourceType.getMaxQuantity();
        data[4] = resourceType.getConsumersNum();
        data[5] = resourceType.getProducersNum();
        return data;
    }

    private Object[] breakDownProducer(Producer producer) {
        Object[] data = new Object[10];
        data[0] = producer.getId();
        data[1] = producer.getResourceType().getId();
        data[2] = producer.getState();
        data[3] = producer.getStartDelay();
        data[4] = producer.getDelay();
        data[5] = producer.getCurrentCycle();
        if (producer.isLifeCicle()) {
            data[6] = producer.getTotalCycles();
        } else {
            data[6] = "NO LIMIT";
        }
        data[7] = producer.getProcessingTime();
        data[8] = formatter.format(producer.getStartTime());
        if (producer.getStopTime() != null) {
            data[9] = formatter.format(producer.getStopTime());
        } else {
            data[9] = "N/A";
        }
        return data;
    }

    private Object[] breakDownConsumer(Consumer consumer) {
        Object[] data = new Object[10];
        data[0] = consumer.getId();
        data[1] = consumer.getResourceType().getId();
        data[2] = consumer.getState();
        data[3] = consumer.getStartDelay();
        data[4] = consumer.getDelay();
        data[5] = consumer.getCurrentCycle();
        if (consumer.isLifeCicle()) {
            data[6] = consumer.getTotalCycles();
        } else {
            data[6] = "NO LIMIT";
        }
        data[7] = consumer.getProcessingTime();
        data[8] = formatter.format(consumer.getStartTime());
        if (consumer.getStopTime() != null) {
            data[9] = formatter.format(consumer.getStopTime());
        } else {
            data[9] = "N/A";
        }
        return data;
    }
}
