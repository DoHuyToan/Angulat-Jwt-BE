package com.example.angularjwtbe.controller;

import com.example.angularjwtbe.dto.respone.ResponeMessage;
import com.example.angularjwtbe.model.Product;
import com.example.angularjwtbe.model.User;
import com.example.angularjwtbe.security.userprincal.UserDetailServices;
import com.example.angularjwtbe.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private UserDetailServices userDetailServices;
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        User user = userDetailServices.getCurrentUser();
        if (user.getUsername().equals("Anonymous")) {
            return new ResponseEntity<>(new ResponeMessage("Please login!"), HttpStatus.OK);
        }
        if (productService.existsByName(product.getName())) {
            return new ResponseEntity<>(new ResponeMessage("name_product_exist"), HttpStatus.OK);
        }
        if (product.getAvatarProduct()==null) {
            return new ResponseEntity<>(new ResponeMessage("no_avatar_product"), HttpStatus.OK);
        }
        productService.save(product);
        return new ResponseEntity<>(new ResponeMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<?> showListProduct() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (productService.existsByName(product.getName())) {
            if (!product.getAvatarProduct().equals(product1.get().getAvatarProduct())) {
                product1.get().setAvatarProduct(product.getAvatarProduct());
                productService.save(product1.get());
                return new ResponseEntity<>(new ResponeMessage("yes"), HttpStatus.OK);
            }
            if (!product.getDateOfManufacture().equals(product1.get().getDateOfManufacture())) {
                product1.get().setDateOfManufacture(product.getDateOfManufacture());
                productService.save(product1.get());
                return new ResponseEntity<>(new ResponeMessage("yes"), HttpStatus.OK);
            }
            if (!product.getDescription().equals(product1.get().getDescription())) {
                product1.get().setDescription(product.getDescription());
                productService.save(product1.get());
                return new ResponseEntity<>(new ResponeMessage("yes"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponeMessage("no_name_product"), HttpStatus.OK);
        }
        product1.get().setName(product.getName());
        product1.get().setDateOfManufacture(product.getDateOfManufacture());
        product1.get().setAvatarProduct(product.getAvatarProduct());
        product1.get().setDescription(product.getDescription());
        productService.save(product1.get());
        return new ResponseEntity<>(new ResponeMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailsProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(new ResponeMessage("yes"), HttpStatus.OK);
    }
}
