package com.example.angularjwtbe.service.product;

import com.example.angularjwtbe.model.Product;
import com.example.angularjwtbe.model.User;
import com.example.angularjwtbe.repository.IProductRepository;
import com.example.angularjwtbe.security.userprincal.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    UserDetailServices userDetailServices;
    @Autowired
    IProductRepository productRepository;
    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        User user = userDetailServices.getCurrentUser();
        product.setUser(user);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
