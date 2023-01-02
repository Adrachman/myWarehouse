package com.myWarehouse.controllers;

import com.myWarehouse.entities.Product;
import com.myWarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String homePage(){
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model model){
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop";
    }
    @GetMapping("/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") Long id){
        Product selectedProduct = productService.getProductById(id);
        model.addAttribute("selectedProducts", selectedProduct);
        return "details";
    }
    @GetMapping("/find_by_title")
    public String detailsPageByTitle(Model model, @RequestParam("title") String title){
        Product selectedProduct = productService.getProductByTitle(title);
        model.addAttribute("selectedProducts", selectedProduct);
        return "details";
    }
    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return "redirect:/shop";
    }
    @GetMapping("/create")
    public String createProductSchema(Model model){
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product){
        productService.createProduct(product);
        return "redirect:/shop";
    }
}
