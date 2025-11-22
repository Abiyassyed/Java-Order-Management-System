package app;
import java.util.*;
public class Cart {
	 List<CartItem> cart = new ArrayList<>();

	    public void addToCart(Product p, int qty) {
	        CartItem item = new CartItem();
	        item.product = p;
	        item.quantity = qty;
	        cart.add(item);
	        System.out.println("Added to Cart Successfully!");
	    }

	    public void viewCart() {
	        for (CartItem i : cart) {
	            System.out.println(i.product.name + " | Qty: " + i.quantity + " | Total: " + i.getTotal());
	        }
	    }

	    public double getGrandTotal() {
	        double total = 0;
	        for (CartItem i : cart) total += i.getTotal();
	        return total;
	    }
}
