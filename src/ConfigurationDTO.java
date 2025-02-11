public class ConfigurationDTO {
    private final int producersNumbers;
    private final int consumerNumbers;
    private final int resourcesNumber;
    private final int resourcesGeneralMin;
    private final int resourcesGeneralMax;
    private final int consumerDelayMin;
    private final int consumerDelayMax;
    private final int producerDelayMin;
    private final int producerDelayMax;
    private final int startDelayMin;
    private final int startDelayMax;
    private final int minCycles;
    private final int maxCycles;
    private final boolean lifeCycleEnabled;
    private final boolean guardedBlocksEnabled;
    private final boolean stockProtectionEnabled;



    // construyo el DTO en base a los valores de la tabla de configuracion
    public ConfigurationDTO(Object[] values){
        this.resourcesNumber = Integer.parseInt((String)values[RowIndexes.RESOURCE_TYPES_NUMBER]);
        this.resourcesGeneralMin = Integer.parseInt((String)values[RowIndexes.RESOURCES_GENERAL_MIN]);
        this.resourcesGeneralMax = Integer.parseInt((String)values[RowIndexes.RESOURCES_GENERAL_MAX]);
        this.producersNumbers = Integer.parseInt((String)values[RowIndexes.PRODUCER_NUMBER]);
        this.producerDelayMin = Integer.parseInt((String)values[RowIndexes.PRODUCER_DELAY_MIN]);
        this.producerDelayMax = Integer.parseInt((String)values[RowIndexes.PRODUCER_DELAY_MAX]);
        this.consumerNumbers = Integer.parseInt((String)values[RowIndexes.CONSUMER_NUMBER]);
        this.consumerDelayMin = Integer.parseInt((String)values[RowIndexes.CONSUMER_DELAY_MIN]);
        this.consumerDelayMax = Integer.parseInt((String)values[RowIndexes.CONSUMER_DELAY_MAX]);
        this.startDelayMin = Integer.parseInt((String)values[RowIndexes.START_DELAY_MIN]);
        this.startDelayMax = Integer.parseInt((String)values[RowIndexes.START_DELAY_MAX]);
        this.lifeCycleEnabled = Boolean.parseBoolean((String)values[RowIndexes.LIFECYCLE_ENABLED]);
        this.minCycles = Integer.parseInt((String)values[RowIndexes.MIN_CICLES]);
        this.maxCycles = Integer.parseInt((String)values[RowIndexes.MAX_CICLES]);
        this.guardedBlocksEnabled = Boolean.parseBoolean((String)values[RowIndexes.GUARDED_BLOCKS_ENABLED]);
        this.stockProtectionEnabled = Boolean.parseBoolean((String)values[RowIndexes.STOCK_PROTECTION_ENABLED]);
    }
    // valores predeterminados
    public ConfigurationDTO(){
        this.consumerDelayMin = 20;
        this.consumerDelayMax = 200;
        this.producerDelayMin = 20;
        this.producerDelayMax = 200;
        this.consumerNumbers = 10;
        this.producersNumbers = 10;
        this.resourcesGeneralMax = 500;
        this.resourcesGeneralMin = 0;
        this.resourcesNumber = 3;
        this.startDelayMin = 100;
        this.startDelayMax = 500;
        this.lifeCycleEnabled = false;
        this.minCycles = 0;
        this.maxCycles = 0;
        this.guardedBlocksEnabled = false;
        this.stockProtectionEnabled = false;
    }
    public int getProducersNumbers() {
        return producersNumbers;
    }

    public int getConsumerNumbers() {
        return consumerNumbers;
    }

    public int getResourcesNumber() {
        return resourcesNumber;
    }

    public int getResourcesGeneralMin() {
        return resourcesGeneralMin;
    }

    public int getResourcesGeneralMax() {
        return resourcesGeneralMax;
    }

    public int getStartDelayMin() {
        return startDelayMin;
    }

    public int getStartDelayMax() {
        return startDelayMax;
    }

    public int getConsumerDelayMin() {
        return consumerDelayMin;
    }


    public int getConsumerDelayMax() {
        return consumerDelayMax;
    }

    public int getProducerDelayMin() {
        return producerDelayMin;
    }

    public int getProducerDelayMax() {
        return producerDelayMax;
    }

    public int getMinCycles() {
        return minCycles;
    }

    public int getMaxCycles() {
        return maxCycles;
    }

    public boolean isLifeCycleEnabled() {
        return lifeCycleEnabled;
    }

    public boolean isGuardedBlocksEnabled() {
        return guardedBlocksEnabled;
    }

    public boolean isStockProtectionEnabled() {
        return stockProtectionEnabled;
    }
    // clase interna que almacena constantes de los índices de cada parámetro
    static final class RowIndexes{
        public static final int RESOURCE_TYPES_NUMBER = 0;
        public static final int RESOURCES_GENERAL_MIN = 1;
        public static final int RESOURCES_GENERAL_MAX = 2;
        public static final int PRODUCER_NUMBER = 3;
        public static final int PRODUCER_DELAY_MIN = 4;
        public static final int PRODUCER_DELAY_MAX = 5;
        public static final int CONSUMER_NUMBER = 6;
        public static final int CONSUMER_DELAY_MIN = 7;
        public static final int CONSUMER_DELAY_MAX = 8;
        public static final int START_DELAY_MIN = 9;
        public static final int START_DELAY_MAX = 10;
        public static final int LIFECYCLE_ENABLED = 11;
        public static final int MIN_CICLES = 12;
        public static final int MAX_CICLES = 13;
        public static final int GUARDED_BLOCKS_ENABLED = 14;
        public static final int STOCK_PROTECTION_ENABLED = 15;

    }
}
