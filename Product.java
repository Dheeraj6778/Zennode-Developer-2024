public class Product {
    private String productName;
    private int price;
    public Product(String productName,int price){
        this.price=price;
        this.productName=productName;
    }
    public int getPrice(){
        return price;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Product Name:"+this.productName+"\n");
        sb.append("Price: "+Integer.toString(price)+"\n");
        return sb.toString();
    }
}
