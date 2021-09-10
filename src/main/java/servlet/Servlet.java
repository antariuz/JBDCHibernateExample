package servlet;

import dao.impl.*;
import json.JSON;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import service.impl.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("")
public class Servlet extends HttpServlet {

    private final Logger LOGGER = LogManager.getLogger(JSON.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try (PrintWriter printWriter = resp.getWriter()) {
            DriverManager.registerDriver(new org.postgresql.Driver());
            UserService userService = new UserServiceImpl(new UserHibernateDAO());
            ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(new ShoppingCartHibernateDAO());
            ProductService productService = new ProductServiceImpl(new ProductHibernateDAO());
            OrderService orderService = new OrderServiceImpl(new OrderHibernateDAO());
            CartItemService cartItemService = new CartItemServiceImpl(new CartItemHibernateDAO());
            JSON<User> jsonUser = new JSON<>();
            JSON<ShoppingCart> jsonShoppingCart = new JSON<>();
            JSON<Product> jsonProduct = new JSON<>();
            JSON<Order> jsonOrder = new JSON<>();
            JSON<CartItem> jsonCartItem = new JSON<>();
            resp.setContentType("text/html");
            printWriter.write(jsonUser.toJSON(userService.getAllUser()) + "<p>");
//            printWriter.write(jsonShoppingCart.toJSON(shoppingCartService.getAllShoppingCart()) + "<p>");
//            printWriter.write(jsonProduct.toJSON(productService.getAllProduct()) + "<p>");
//            printWriter.write(jsonOrder.toJSON(orderService.getAllOrder()) + "<p>");
//            printWriter.write(jsonCartItem.toJSON(cartItemService.getAllCartItem()) + "<p>");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}