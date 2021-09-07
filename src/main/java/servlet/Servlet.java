package servlet;

import json.JSON;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.Service;
import service.impl.UserService;

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
            Service<User> userService = new UserService();
            JSON<User> json = new JSON<>();
            resp.setContentType("text/html");
            printWriter.write(json.toJSON(UserService.getAllUser()));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}