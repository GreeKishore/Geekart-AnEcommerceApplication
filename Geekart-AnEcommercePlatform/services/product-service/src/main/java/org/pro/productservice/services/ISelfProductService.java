package org.pro.productservice.services;

import lombok.RequiredArgsConstructor;
import org.pro.productservice.commons.AuthenticationCommons;
import org.pro.productservice.entities.Category;
import org.pro.productservice.entities.Product;
import org.pro.productservice.repositories.CategoryRepository;
import org.pro.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iSelfProductService")
@RequiredArgsConstructor
public class ISelfProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticationCommons authCommons;

    @Override
    public Product createproduct(Product product) {

        // save the category in the DB since it is under the product
        Category category = product.getCategory();

        // Save the category regardless of whether it's new or not
        if (category.getId() == null) {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }

        //save the product in the DB
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        //fetch product with given id from DB
        return productRepository.findById(id).orElse(null); //Optional is used to avoid null pointer exception
    }

    @Override
    public List<Product> getAllProducts() {
        //fetch all products from DB
        return productRepository.findAll();
    }

    @Override
    public Product updateproduct(Long id, Product product) {
        //check if id is there or not
        if(!productRepository.existsById(id)){
            return product;
        }

        //update the product with given id in the DB
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        //replace the product with given id in the DB
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long id) {

        //check if id is there or not
        if(!productRepository.existsById(id)){
            return "Product with id "+id+" not found";
        }
        //delete the product with given id from the DB
        productRepository.deleteById(id);

        return "Product with id "+id+" deleted successfully";
    }
}