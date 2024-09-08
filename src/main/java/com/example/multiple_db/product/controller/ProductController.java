package com.example.multiple_db.product.controller;

import com.example.multiple_db.order.entity.Order;
import com.example.multiple_db.order.repository.OrderRepo;
import com.example.multiple_db.product.Entity.Product;
import com.example.multiple_db.product.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product/api")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/save")
    public ResponseEntity<Product> saveorder(@RequestBody Product product)
    {
        Product savedProduct = productRepo.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

}
