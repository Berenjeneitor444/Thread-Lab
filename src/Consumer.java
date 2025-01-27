public class Consumer implements Runnable{
    private enum State{
        WAITING, PRODUCING, FINISHED
    }
    //private startTime;
    //private stopTime;
    private MyModel father;
    private String name;

    public Consumer(MyModel father, String name){
        this.father = father;
        this.name = name;
        System.out.println("Consumer creado");
        Thread consumir = new Thread(this);
        consumir.start();
    }
    public void run(){
        int contador = 0;
        while (contador < 1000) {
            try {
                father.setCounter(father.getCounter() - 1);
                Thread.sleep(100);
                contador++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void consume(Resource resource){

    }
}
