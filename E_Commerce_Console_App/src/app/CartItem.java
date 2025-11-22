package app;

public class CartItem {
	Product product;
    int quantity;

    public double getTotal() {
        return product.price * quantity;
    }
}
