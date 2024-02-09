//import
public interface DataAccess {
    void connect(String s);

    void saveProduct(ProductModel product);
    Boolean saveOrder(OrderModel order);


    ProductModel loadProduct(int productID);

    UserModel loadUser(int userID);

    Integer loadOrders(int orderID);

    void saveUser(UserModel user);

}
