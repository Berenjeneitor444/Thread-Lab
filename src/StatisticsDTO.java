public class StatisticsDTO {
    // estadisticas generales
    private int totalResourceTypes = 0;
    private int totalProducers = 0;
    private int totalConsumers = 0;
    private int totalResourceQuantity = 0;
    private int totalConsumed = 0;
    private int totalProduced = 0;
    private int IDLEthreads = 0;
    private int activeThreads = 0;
    // listas de recursos, productores y consumidores
    private Object[][] producersData;

    private Object[][] consumersData;

    private Object[][] resourceTypesData;

    public void incrementTotalResourceTypes() {
        totalResourceTypes++;
    }
    public void incrementTotalProducers() {
        totalProducers++;
    }

    public void incrementTotalConsumers() {
        totalConsumers++;
    }
    public void incrementTotalResourceQuantity() {
        totalResourceQuantity++;
        totalProduced++;
    }
    public void decrementTotalResourceQuantity() {
        totalResourceQuantity--;
        totalConsumed++;
    }
    public void incrementIDLEthreads() {
        IDLEthreads++;
        activeThreads--;
    }
    public void decrementIDLEthreads() {
        IDLEthreads--;
        activeThreads++;
    }
    public void incrementActiveThreads() {
        activeThreads++;
    }
    public void decrementActiveThreads() {
        activeThreads--;
    }
    public int getTotalResourceTypes() {
        return totalResourceTypes;
    }

    public int getTotalProducers() {
        return totalProducers;
    }

    public int getTotalConsumers() {
        return totalConsumers;
    }

    public int getTotalResourceQuantity() {
        return totalResourceQuantity;
    }

    public int getTotalConsumed() {
        return totalConsumed;
    }

    public int getTotalProduced() {
        return totalProduced;
    }

    public int getIDLEthreads() {
        return IDLEthreads;
    }

    public int getActiveThreads() {
        return activeThreads;
    }

    public Object[][] getProducersData() {
        return producersData;
    }

    public Object[][] getConsumersData() {
        return consumersData;
    }

    public Object[][] getResourceTypesData() {
        return resourceTypesData;
    }

    public void setResourceTypesData(Object[][] resourceTypesData) {
        this.resourceTypesData = resourceTypesData;
    }

    public void setConsumersData(Object[][] consumersData) {
        this.consumersData = consumersData;
    }

    public void setProducersData(Object[][] producersData) {
        this.producersData = producersData;
    }
}
