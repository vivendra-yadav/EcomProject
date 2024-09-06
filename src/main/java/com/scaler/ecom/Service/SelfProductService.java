package com.scaler.ecom.Service;

import com.scaler.ecom.Expection.ProductNotFoundException;
import com.scaler.ecom.Modle.Category;
import com.scaler.ecom.Modle.Product;
import com.scaler.ecom.Repository.CategoryRepository;
import com.scaler.ecom.Repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProductById(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product createProduct(Product product) {
        //Before saving the Product object in the DB, save the category object.

        Category category = product.getCategory();

        if (category.getId() == null) {
            //we need to save the category
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
        } else {
            //we should check if the category id is valid or not.
        }

        Product savedProduct =  productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
        Category category1 = optionalCategory.get();
        savedProduct.setCategory(category1);
        return savedProduct;
    }

}
