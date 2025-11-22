package app;
import java.sql.*;
public class OrderDAO {
	public int createOrder(double total) {
        String q = "INSERT INTO Orders(order_id, total_amount, order_date) VALUES(?, ?, ?)";

        int orderId = (int)(Math.random() * 1000);
        Date sqlDate = new Date(System.currentTimeMillis());
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {

            ps.setInt(1, orderId);
            ps.setDouble(2, total);
            ps.setDate(3, sqlDate);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }

        return orderId;
    }

    public void addOrderItem(int orderId, CartItem item) {
        String q = "INSERT INTO Order_Items(item_id, order_id, product_id, quantity, price) VALUES(?, ?, ?, ?, ?)";

        int itemId = (int)(Math.random() * 10000);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {

            ps.setInt(1, itemId);
            ps.setInt(2, orderId);
            ps.setInt(3, item.product.id);
            ps.setInt(4, item.quantity);
            ps.setDouble(5, item.getTotal());

            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }
}
