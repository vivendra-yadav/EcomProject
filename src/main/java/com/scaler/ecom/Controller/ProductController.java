package com.scaler.ecom.Controller;

import com.scaler.ecom.Expection.ProductNotFoundException;
import com.scaler.ecom.Modle.Product;
import com.scaler.ecom.Service.ProductService;
import com.scaler.ecom.Service.SelfProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
        if(product==null) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PutMapping("/{id}")
    public Product repalceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProductById(id, product);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
