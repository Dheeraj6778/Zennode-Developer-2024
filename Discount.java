import java.util.*;

public class Discount {
    private HashMap<String, Integer> catalogue;
    private Cart cart;

    public class Node {
        public String discountType;
        public int cartTotal;

        public Node(String discountType, int cartTotal) {
            this.discountType = discountType;
            this.cartTotal = cartTotal;
            //System.out.println("discount type :"+discountType+" cart total "+Integer.toString(cartTotal));
        }
    }

    public Discount(HashMap<String, Integer> catalogue, Cart cart) {
        this.catalogue = catalogue;
        this.cart = cart;
    }

    private int flat_10_discount() {
        int cartTotal = cart.getCartTotal();
        if (cartTotal >= 200) {
            cartTotal -= 10;
        }
        return cartTotal;
    }

    private int bulk_5_discount() {
        HashMap<String, Integer> hashMapCart = cart.getCartItems();
        int subTotal = 0;
        for (Map.Entry<String, Integer> entry : hashMapCart.entrySet()) {
            int quantity = entry.getValue();
            String product = entry.getKey();
            int price = catalogue.get(product);
            int tempCost = price * quantity;
            if (quantity > 10) {
                subTotal += ((double) tempCost - 0.05 * (double) tempCost);
            }
            else
                subTotal+=tempCost;
        }
        return subTotal;
    }

    private int bulk_10_discount() {
        int totalQuantity = cart.getCartQuantity();
        int cartTotal = cart.getCartTotal();
        if (totalQuantity > 20) {
            cartTotal = (int) ((double)cartTotal * 1.0 - (double) cartTotal * 0.10);
        }
        return cartTotal;
    }

    private int tiered_50_discount() {
        int totalQuantity = cart.getCartQuantity();
        if (totalQuantity > 30) {
            int subTotal = 0;
            HashMap<String, Integer> hashMapCart = cart.getCartItems();
            for (Map.Entry<String, Integer> entry : hashMapCart.entrySet()) {
                int quantity = entry.getValue();
                String product = entry.getKey();
                int price = catalogue.get(product);
                if (quantity > 15) {
                    int extra = quantity - 15;
                    subTotal += price * 15;
                    subTotal += (int) (extra * 1.0 * (price / 2.0));
                } else {
                    subTotal += price * quantity;
                }
            }
            return subTotal;
        }
        return cart.getCartTotal();
    }

    public String[] getBestDiscount() {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cartTotal - b.cartTotal);
        pq.add(new Node("flat_10_discount", flat_10_discount()));
        pq.add(new Node("bulk_5_discount", bulk_5_discount()));
        pq.add(new Node("bulk_10_discount", bulk_10_discount()));
        pq.add(new Node("tiered_50_discount", tiered_50_discount()));
        String[] ans = new String[2];
        ans[0] = pq.peek().discountType;
        ans[1]= Integer.toString(pq.peek().cartTotal);
        return ans;
    }
}
