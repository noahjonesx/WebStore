import com.hp.gagawa.java.elements.P;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect(String url) {
        try {
            // db parameters
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

            /* Test data!!!
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + product.productID + ","
                        + '\'' + product.name + '\'' + ","
                        + product.price + ","
                        + product.quantity + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + product.productID + ","
                        + "name = " + '\'' + product.name + '\'' + ","
                        + "price = " + product.price + ","
                        + "quantity = " + product.quantity +
                        " WHERE productID = " + product.productID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Boolean saveOrder(OrderModel order) {
        try {
            Statement stmt = conn.createStatement();

            if (loadOrders(order.orderID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO ORDERS(orderID, customerName, totalCost, totalTax, date) VALUES ("
                        + order.orderID + ","
                        + '\'' + order.customerName + '\'' + ","
                        + order.totalCost + ","
                        + order.totalTax + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE ORDERS SET "
                        + "orderID = " + order.orderID + ","
                        + "customerName = " + '\'' + order.customerName + '\'' + ","
                        + "totalCost = " + order.totalCost + ","
                        + "totalTax = " + order.totalTax + "," +
                        " WHERE orderID = " + order.orderID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE ProductID = " + productID);
            if (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public UserModel loadUser(int userID) {
        UserModel user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE UserID = " + userID);
            if (rs.next()) {
                user = new UserModel();
                user.userID = rs.getInt(1);
                user.userName = rs.getString(2);
                user.password = rs.getString(3);
                user.isManager = rs.getBoolean(4);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
    public Integer loadOrders(int orderID) {
        OrderModel order = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS WHERE OrderID = " + orderID);
            if (rs.next()) {
                order = new OrderModel();
                order.orderID = rs.getInt(1);
                order.date = rs.getDate(2);
                order.customerName = rs.getString(3);
                order.totalCost = rs.getDouble(4);
                order.totalTax = rs.getDouble(5);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //@Override
    public List<ProductModel> loadAllProducts() {
        List<ProductModel> list = new ArrayList<>();
        ProductModel product = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product ");
            while (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
                list.add(product);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<UserModel>loadAllUsers() {
        List<UserModel> list = new ArrayList<>();
        UserModel user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User ");
            while (rs.next()) {
                user = new UserModel();
                user.userID = rs.getInt(1);
                user.userName = rs.getString(2);
                user.password = rs.getString(3);
                user.isManager = rs.getBoolean(4);
                list.add(user);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public void saveUser(UserModel user) {
        try {
            Statement stmt = conn.createStatement();

            if (loadUser(user.userID) == null) {           // this is a new user!
                stmt.execute("INSERT INTO User(userID, UserName, password, isManager) VALUES ("
                        + user.userID + ","
                        + '\'' + user.userName + '\'' + ","
                        + user.password + ","
                        + user.isManager + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE User SET "
                        + "userID = " + user.userID + ","
                        + "UserName = " + '\'' + user.userName + '\'' + ","
                        + "password = " + user.password + ","
                        + "isManager = " + user.isManager +
                        " WHERE userID = " + user.userID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
