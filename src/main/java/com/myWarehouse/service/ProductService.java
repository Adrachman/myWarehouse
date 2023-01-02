package com.myWarehouse.service;

import com.myWarehouse.entities.Product;
import com.myWarehouse.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepositories;

    @Autowired
    public void setProductRepositories(ProductRepository productRepositories) {
        this.productRepositories = productRepositories;
    }

    public List<Product> getAllProducts(){
        return productRepositories.findAll();
    }
    public Product getProductById(Long id){
        return productRepositories.findById(id).get();
    }
    public Product getProductByTitle(String title){
        return productRepositories.findOneByTitle(title);
    }
    public void deleteProductById(Long id){
         productRepositories.deleteById(id);
    }
    public void createProduct(Product product){
         productRepositories.save(product);
    }


}
