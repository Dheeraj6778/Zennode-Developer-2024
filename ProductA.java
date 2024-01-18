public class ProductA extends Product{
    private int quantity;
    public ProductA(int quantity){
        super("Product A", 20);
        this.quantity=quantity;
    }
    public int getQuantity(){
        return quantity;
    }
}
