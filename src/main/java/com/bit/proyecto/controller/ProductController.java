package com.bit.proyecto.controller;

import com.bit.proyecto.dto.ShoeDTO;
import com.bit.proyecto.model.Shoe;
import com.bit.proyecto.rest.Response;
import com.bit.proyecto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("product/{code}")
    public ResponseEntity<Response> getProductByCode(@PathVariable String code){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(code));
    }
    @GetMapping("products")
    public ResponseEntity<Response> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("product")
    public ResponseEntity<Response> saveProduct(@RequestBody ShoeDTO shoe){
        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(shoe));
    }
    @PutMapping("product/up")
    public ResponseEntity<Response> updateProduct(@RequestBody ShoeDTO shoe){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(shoe));
    }
    @DeleteMapping("product/del/{code}")
    public ResponseEntity<Response> deleteProductByCode(@PathVariable String code){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProductById(code));
    }
}
