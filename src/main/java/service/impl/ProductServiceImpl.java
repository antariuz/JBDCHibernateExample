package service.impl;

import dao.ProductDAO;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }

    @Override
    public Long addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    @Override
    public Product getUserById(Long id) {
        return productDAO.getProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productDAO.deleteProductById(id);
    }
}
