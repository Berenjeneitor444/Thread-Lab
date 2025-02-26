import static java.lang.Thread.sleep;

public class ResourceType {
    private int quantity;
    private int maxQuantity;
    private int minQuantity;
    private int consumersNum;
    private int producersNum;
    private String id;
    private final MyModel father;
    private boolean stockProtectionEnabled;
    public ResourceType(MyModel father, int minQuantity, int maxQuantity, String name,
                        boolean stockProtectionEnabled){
        this.father = father;
        this.quantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
        this.id = name;
        this.stockProtectionEnabled = stockProtectionEnabled;
        father.incrementTotalResourceTypes();
    }
    public synchronized void wakeUpIDLEThreads(){
        notifyAll();
    }

    public void increment(Producer producer){
        if(stockProtectionEnabled) {
            // que siga esperando hasta que haya espacio
            while (quantity >= maxQuantity && !father.isMustStop()) {
                // condicional para que solo entre en el bloque de abajo una vez y luego se quede esperando
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (producer.getState() == MyController.State.IDLE){
                    continue;
                }
                producer.turnIDLE();
                father.incrementIDLEthreads();
            }
            // aqui ya sale y sigue
            if (producer.getState() == MyController.State.IDLE) {
                producer.turnRUNNING();
                father.decrementIDLEthreads();
            }
            // para que no se queden en bucle en idle si la quantity esta en maxquantity
            if (father.isMustStop()) {
                producer.stop();
                return;
            }
        }
        // entonces produce
        this.quantity++;
        father.incrementTotalResourceQuantity();

    }
    public void decrement(Consumer consumer) {
        if (stockProtectionEnabled) {
            // que siga esperando hasta que haya espacio
            while (quantity <= minQuantity && !father.isMustStop()) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // condicional para que solo entre en el bloque de abajo una vez y luego se quede esperando
                if (consumer.getState() == MyController.State.IDLE) {
                    continue;
                }
                consumer.turnIDLE();
                father.incrementIDLEthreads();
            }
            // aqui ya sale y sigue
            if (consumer.getState() == MyController.State.IDLE) {
                consumer.turnRUNNING();
                father.decrementIDLEthreads();
            }
            // para que no se queden en bucle en idle si la quantity esta en minquantity
            if (father.isMustStop()) {
                consumer.stop();
                return;
            }
        }
        // entonces consume
        this.quantity--;
        father.decrementTotalResourceQuantity();
    }


    public synchronized void incrementSync(Producer producer){
        if (stockProtectionEnabled) {
            // que siga esperando hasta que haya espacio
            while (quantity >= maxQuantity && !father.isMustStop()) {
                try {
                    producer.turnIDLE();
                    father.incrementIDLEthreads();
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    producer.turnRUNNING();
                    father.decrementIDLEthreads();
                }
            }
            // para que no se queden en bucle en idle si la quantity esta en maxquantity
            if (father.isMustStop()) {
                producer.stop();
                return;
            }
        }
        // entonces produce
        this.quantity++;
        father.incrementTotalResourceQuantity();

        if (stockProtectionEnabled) notifyAll();
    }
    public synchronized void decrementSync(Consumer consumer){
        if(stockProtectionEnabled) {
            // que siga esperando hasta que haya recursos
            while (quantity <= minQuantity && !father.isMustStop()) {
                try {
                    consumer.turnIDLE();
                    father.incrementIDLEthreads();
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    consumer.turnRUNNING();
                    father.decrementIDLEthreads();
                }

            }
            // para que no se queden en bucle en idle si la quantity esta en minquantity
            if (father.isMustStop()) {
                consumer.stop();
                return;
            }
        }
        // entonces consume
        this.quantity--;
        father.decrementTotalResourceQuantity();
        if(stockProtectionEnabled) notifyAll();
    }

    public String getId() {
        return id;
    }

    public int getConsumersNum() {
        return consumersNum;
    }

    public int getProducersNum() {
        return producersNum;
    }

    public void incrementConsumersNum(){
        consumersNum++;
    }
    public void incrementProducersNum(){
        producersNum++;
    }
    public int getQuantity() {
        return quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }
}