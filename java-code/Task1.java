import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task1 {
    public static HashMap<String, Integer> catalogue;
    public static Cart cart;
    public static Scanner sc;
    static {
        sc=new Scanner(System.in);
        catalogue = new HashMap<>();
        catalogue.put("Product A", 20);
        catalogue.put("Product B", 40);
        catalogue.put("Product C", 50);
        cart = new Cart(catalogue);
    }

    public static void ask(String product) {
        
        System.out.println("How many units of " + product + " do you want ?");
        int quantity = Integer.valueOf(sc.nextLine());
        System.out.println("Do want to gift wrap (yes/no)");
        String resp=sc.nextLine();
        boolean flag = false;
        if(resp.toLowerCase().equals("yes"))
            flag=true;
        cart.addToCart(product, quantity, flag);
        
    }

    public static void main(String[] args) {

        for (Map.Entry<String, Integer> entry : catalogue.entrySet()) {
            String product = entry.getKey();
            ask(product);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cart.toString());
        sb.append("\n");

        int cartTotal = cart.getCartTotal();
        Discount discount = new Discount(catalogue, cart);
        String[] discountDetails = discount.getBestDiscount();
        String discountType = discountDetails[0];
        int cartTotalAfterDiscount = Integer.valueOf(discountDetails[1]);
        int discountAmount = cartTotal - cartTotalAfterDiscount;
        sb.append("Discount Applied is: " + discountType + " and discount amount  is "
                + Integer.toString(discountAmount));
        sb.append("\n");
        int shippingFee = cart.getShippingFee();
        int giftWrapFee = cart.getGiftWrapFee();
        sb.append("shipping fee: "+Integer.toString(shippingFee)+"\n");
        sb.append("giftwrap fee: "+Integer.toString(giftWrapFee)+"\n");
        int total=cartTotalAfterDiscount+shippingFee+giftWrapFee;
        sb.append("total: "+Integer.toString(total));
        System.out.println(sb.toString());
        sc.close();
    }
}