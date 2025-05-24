package com.future.productservice.Controllers;

import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dbItems")
public class DbProductController {
    private ProductServiceInterface productService;

    public DbProductController(@Qualifier("dbProductService") ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductsModel> GetProducts() throws Exception {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductsModel GetProduct(@PathVariable("id") long id) throws CustomExceptions {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductsModel CreateProduct(@RequestBody ProductsModel productsModel){
        return productService.createProduct(productsModel);
    }

    @PatchMapping("/{id}")
    public ProductsModel UpdateProduct(@PathVariable("id") long id, @RequestBody ProductsModel productsModel){
        return new ProductsModel();
    }

    @PutMapping("/{id}")
    public ProductsModel ReplaceProduct(@PathVariable("id") long id, @RequestBody ProductsModel productsModel){
        return new ProductsModel();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id){

    }
}
