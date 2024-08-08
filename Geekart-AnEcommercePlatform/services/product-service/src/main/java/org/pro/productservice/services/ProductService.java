package org.pro.productservice.services;

import org.pro.productservice.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

// why product service interface? Answer is we can have different implementation for it like through db  or like FakeStore
@Service
public interface ProductService {
    Product createproduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateproduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    String deleteProduct(Long id);
}