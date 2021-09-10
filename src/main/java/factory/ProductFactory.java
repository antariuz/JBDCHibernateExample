package factory;

import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ProductFactory {
    INSTANCE;

    ProductFactory() {
    }

    public static ProductFactory getInstance() {
        return INSTANCE;
    }

    public Product createProductVO(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        if (resultSet.next()) {
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
        }
        return product;
    }

    public List<Product> createVOProductList(ResultSet resultSet) throws SQLException {
        List<Product> list = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            list.add(product);
        }
        return list;
    }

}
