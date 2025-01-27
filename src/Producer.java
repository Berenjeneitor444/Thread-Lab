public class Producer implements Runnable{
    private enum State{
        WAITING, PRODUCING, FINISHED
    }
    //private startTime;
    //private stopTime;
    private MyModel father;
    private String name;
    public Producer(MyModel father, String name){
        this.father = father;
        this.name = name;
        System.out.println("Producer creado");
        Thread producir = new Thread(this);
        producir.start();
    }
    public void run(){
        int contador = 0;
        while (contador < 1000) {
            try {
                father.setCounter(father.getCounter() + 1);
                Thread.sleep(100);
                contador++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void produce(Resource resource){

    }
}
