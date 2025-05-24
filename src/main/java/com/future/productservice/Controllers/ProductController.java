package com.future.productservice.Controllers;


import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ProductController {

    private ProductServiceInterface productService;

    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductsModel> GetProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductsModel GetProduct(@PathVariable("id") long id) throws CustomExceptions {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductsModel CreateProduct(@RequestBody ProductsModel productsModel) {
        return new ProductsModel();
    }

    @PatchMapping("/{id}")
    public ProductsModel UpdateProduct(@PathVariable("id") long id, @RequestBody ProductsModel productsModel) {
        return new ProductsModel();
    }

    @PutMapping("/{id}")
    public ProductsModel ReplaceProduct(@PathVariable("id") long id, @RequestBody ProductsModel productsModel) {
        return new ProductsModel();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) {

    }

//    @ExceptionHandler(CustomExceptions.class)
//    private ResponseEntity<String> productNotFoundExceptionFromController(CustomExceptions customExceptions) {
//        return new ResponseEntity<>(
//                customExceptions.getMessage(),
//                HttpStatus.BAD_GATEWAY
//        );
//    }
}
