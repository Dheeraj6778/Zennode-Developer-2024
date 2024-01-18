import java.util.*;

public class Cart {
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private HashMap<String, Integer> catalogue;
    private int totalQuantity = 0;
    private int giftWrapFee = 0;

    public Cart(HashMap<String, Integer> catalogue) {
        this.catalogue = catalogue;
    }

    public void addToCart(String product, int quantity, boolean giftWrap) {
        hashMap.put(product, hashMap.getOrDefault(product, 0) + quantity);
        totalQuantity += quantity;
        if (giftWrap == true)
            giftWrapFee += quantity;
    }

    public int getCartTotal() {
        int subTotal = 0;
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            int price = catalogue.get(entry.getKey());
            subTotal += price * entry.getValue();
        }
        return subTotal;
    }

    public int getCartQuantity() {
        return totalQuantity;
    }

    public HashMap<String, Integer> getCartItems() {
        return hashMap;
    }

    public int getShippingFee() {
        int packages = Math.ceilDiv(totalQuantity, 10);
        return packages * 5;
    }

    public int getGiftWrapFee() {
        return giftWrapFee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            int price = catalogue.get(product);
            int totalPrice = price * quantity;
            sb.append("Product Name: " + product + " ");
            sb.append("Price: " + Integer.toString(price) + " ");
            sb.append("total amount: " + Integer.toString(totalPrice));
            sb.append("\n");
        }
        int subTotal = getCartTotal();
        sb.append("subtotal: " + Integer.toString(subTotal));
        return sb.toString();
    }

}
