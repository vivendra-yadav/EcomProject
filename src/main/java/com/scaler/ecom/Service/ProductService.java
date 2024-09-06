package com.scaler.ecom.Service;

import com.scaler.ecom.Expection.ProductNotFoundException;
import com.scaler.ecom.Modle.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();
    Product replaceProductById(Long id , Product product);
    Product updateProduct(Long id , Product product);
    void deleteProduct(Long id);
    Product createProduct(Product product);
}
