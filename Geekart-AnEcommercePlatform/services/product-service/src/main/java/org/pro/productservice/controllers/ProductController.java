package org.pro.productservice.controllers;

import org.pro.productservice.commons.AuthenticationCommons;
import org.pro.productservice.dtos.UserDto;
import org.pro.productservice.entities.Product;
import org.pro.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products") //entities which we are creating should revolve around the particular entity
public class ProductController {

    private final AuthenticationCommons authenticationCommons;
    private final ProductService productService;

    //@Primary, @Secondary and @Qualifier can be used to resolve the ambiguity when there are multiple beans of the same type
    public ProductController(@Qualifier("iSelfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    //Create a product
    @PostMapping("/")
    public Product createproduct(@RequestBody Product product) {
        return productService.createproduct(product);
    }

    //get product by its id
    @GetMapping("/{id}/")
    public Product getProduct(@PathVariable("id") Long id) {
        //call the FakeStore API to get the product by given ID
        return productService.getProductById(id);
    }

    //get all products - part1
    //part2 - get all the products with authentication in user service
    // TODO versioning
    @GetMapping("/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token) {
        UserDto userDto = authenticationCommons.validateToken(token);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        //roles can also be checked here
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    //Partial update -> only the given values are updated
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product result = productService.updateproduct(id, product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //replace everything
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}