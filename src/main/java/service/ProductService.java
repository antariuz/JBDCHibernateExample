package service;

import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Long addProduct(Product product);

    Product getUserById(Long id);

    void updateProduct(Product product);

    void deleteProductById(Long id);

}
