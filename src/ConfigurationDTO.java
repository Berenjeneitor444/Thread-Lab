public class ConfigurationDTO {
    private int producersNumbers;
    private int consumerNumbers;
    private int resourcesNumber;
    private int resourcesGeneralMin;
    private int resourcesGeneralMax;
    private int consumerDelay;
    private int producerDelay;

    public ConfigurationDTO(int producersNumbers, int consumerNumbers, int resourcesNumber, int resourcesGeneralMin,
                            int resourcesGeneralMax, int consumerDelay, int producerDelay){
        this.consumerDelay = consumerDelay;
        this.producerDelay = producerDelay;
        this.consumerNumbers = consumerNumbers;
        this.producersNumbers = producersNumbers;
        this.resourcesGeneralMax = resourcesGeneralMax;
        this.resourcesGeneralMin = resourcesGeneralMin;
        this.resourcesNumber = resourcesNumber;
    }

    public int getProducersNumbers() {
        return producersNumbers;
    }

    public void setProducersNumbers(int producersNumbers) {
        this.producersNumbers = producersNumbers;
    }

    public int getConsumerNumbers() {
        return consumerNumbers;
    }

    public void setConsumerNumbers(int consumerNumbers) {
        this.consumerNumbers = consumerNumbers;
    }

    public int getResourcesNumber() {
        return resourcesNumber;
    }

    public void setResourcesNumber(int resourcesNumber) {
        this.resourcesNumber = resourcesNumber;
    }

    public int getResourcesGeneralMin() {
        return resourcesGeneralMin;
    }

    public void setResourcesGeneralMin(int resourcesGeneralMin) {
        this.resourcesGeneralMin = resourcesGeneralMin;
    }

    public int getResourcesGeneralMax() {
        return resourcesGeneralMax;
    }

    public void setResourcesGeneralMax(int resourcesGeneralMax) {
        this.resourcesGeneralMax = resourcesGeneralMax;
    }

    public int getConsumerDelay() {
        return consumerDelay;
    }

    public void setConsumerDelay(int consumerDelay) {
        this.consumerDelay = consumerDelay;
    }

    public int getProducerDelay() {
        return producerDelay;
    }

    public void setProducerDelay(int producerDelay) {
        this.producerDelay = producerDelay;
    }
}
