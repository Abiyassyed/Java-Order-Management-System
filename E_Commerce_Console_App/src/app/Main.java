package app;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        Cart cart = new Cart();

        while (true) {
            System.out.println("\n===== E-Commerce App =====");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    for (Product p : productDAO.getAllProducts()) {
                        System.out.println(p.id + " | " + p.name + " | " + p.price + " | Stock: " + p.stock);
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    Product p = productDAO.searchProduct(sc.nextInt());
                    if (p != null)
                        System.out.println(p.id + " | " + p.name + " | " + p.price);
                    else
                        System.out.println("Not Found!");
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    Product prod = productDAO.searchProduct(pid);

                    if (prod != null) {
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        cart.addToCart(prod, qty);
                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;

                case 4:
                    cart.viewCart();
                    break;

                case 5:
                    double total = cart.getGrandTotal();
                    int oid = orderDAO.createOrder(total);

                    for (CartItem ci : cart.cart) {
                        orderDAO.addOrderItem(oid, ci);
                        productDAO.updateStock(ci.product.id, ci.quantity);
                    }

                    System.out.println("Order Placed! Order ID: " + oid);
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
