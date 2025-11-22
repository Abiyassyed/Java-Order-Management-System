package app;
import java.util.*;
import java.sql.*;
public class ProductDAO {
	public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Product p = new Product();
                p.id = rs.getInt("product_id");
                p.name = rs.getString("name");
                p.price = rs.getDouble("price");
                p.stock = rs.getInt("stock");
                p.category = rs.getString("category");
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public Product searchProduct(int id) {
        String q = "SELECT * FROM Products WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.id = rs.getInt("product_id");
                p.name = rs.getString("name");
                p.price = rs.getDouble("price");
                p.stock = rs.getInt("stock");
                p.category = rs.getString("category");
                return p;
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public void updateStock(int id, int qty) {
        String q = "UPDATE Products SET stock = stock - ? WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(q)) {

            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }
}
