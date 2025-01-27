public class Resource {
    private int quantity;
    private int maxQuantity;
    private int minQuantity;
    private String name;
    public Resource(int quantity, int maxQuantity, int minQuantity, String name){
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
        this.name = name;
    }
    public void addResource(){
        this.quantity++;
    }
    public void removeResource(){
        this.quantity--;
    }
}
