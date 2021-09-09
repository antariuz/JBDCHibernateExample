package dao.impl;

import connectivity.JDBC;
import dao.ProductDAO;
import factory.ProductFactory;
import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBCDAO implements ProductDAO {

    private final Logger LOGGER = LogManager.getLogger(ProductJDBCDAO.class.getName());

    @Override
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
            list = ProductFactory.getInstance().createVOProductList(resultSet);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addProduct(Product product) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO product (name, price) VALUES(?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            product = ProductFactory.getInstance().createProductVO(preparedStatement.executeQuery());
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE product SET name = ?, price = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setLong(4, product.getId());
            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

}
