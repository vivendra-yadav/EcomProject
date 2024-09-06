package com.scaler.ecom.Service;

import com.scaler.ecom.Dto.FackStoreProductDto;
import com.scaler.ecom.Expection.ProductNotFoundException;
import com.scaler.ecom.Modle.Category;
import com.scaler.ecom.Modle.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FackStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FackStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private Product convertFackStoreProductToProduct(FackStoreProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setDescreption(dto.getCategory());
        product.setCategory(category);

        return product;

    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FackStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FackStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException(id,"Product with id:"+id+" not found");
        }

        //Convert FakeStore DTO into Product object.
        return convertFackStoreProductToProduct(fakeStoreProductDto);

    }

    @Override
    public List<Product> getAllProducts() {
        FackStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FackStoreProductDto[].class);

        //convert List of FakeStoreProductDtos to List of Products
        List<Product> response = new ArrayList<>();
        for (FackStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            response.add(convertFackStoreProductToProduct(fakeStoreProductDto));
        }

        return response;
    }

    @Override
    public Product replaceProductById(Long id,Product product) {
        FackStoreProductDto fackStoreProductDto = new FackStoreProductDto();

        fackStoreProductDto.setTitle(product.getTitle());
        fackStoreProductDto.setPrice(product.getPrice());
        fackStoreProductDto.setDescription(product.getDescription());
        fackStoreProductDto.setImage(product.getImage());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fackStoreProductDto, FackStoreProductDto.class);
        HttpMessageConverterExtractor<FackStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FackStoreProductDto.class, restTemplate.getMessageConverters());
        FackStoreProductDto response=  restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFackStoreProductToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}