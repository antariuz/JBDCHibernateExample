package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProduct();

    Long addProduct(Product product);

    Product getProductById(Long id);

    void updateProduct(Product product);

    void deleteProductById(Long id);

}
