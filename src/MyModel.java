import java.sql.Time;

public class MyModel{
    private int counter = 0;
    private Consumer consumer;
    private Producer producer;
    private int producerNumber;
    private int consumerNumber;
    private int resourceNumber;
    private Time producerDelay;
    private Time consumerDelay;
    private MyController father;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Producer getProducer() {
        return producer;
    }

    public MyModel(MyController father){
        this.father = father;
        this.consumer = new Consumer(this);
        this.producer = new Producer(this);
        System.out.println("MyModel creado");
    }
    public void play(){}
    public void stop(){}
}
