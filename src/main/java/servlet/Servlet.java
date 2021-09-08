package servlet;

import dao.impl.UserHibernateDAO;
import dao.impl.UserJDBCDAO;
import json.JSON;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;

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
            JSON<User> json = new JSON<>();
            resp.setContentType("text/html");
            printWriter.write(json.toJSON(userService.getAllUser()));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}