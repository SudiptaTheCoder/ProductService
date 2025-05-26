package com.future.productservice.Controllers;

import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    public Page<ProductsModel> GetProducts(@RequestParam("pageNumber") int pageNumber,
                                           @RequestParam("pageSize") int pageSize) throws Exception {
        return productService.getProducts(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public ProductsModel GetProduct(@PathVariable("id") long id) throws CustomExceptions {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductsModel CreateProduct(@RequestBody ProductsModel productsModel) throws Exception {
        return productService.createProduct(productsModel);
    }

    @PatchMapping("/{id}")
    public ProductsModel UpdateProduct(@PathVariable("id") long id, @RequestBody ProductsModel productsModel) throws Exception {
        return productService.updateProduct(id,productsModel);
    }

    @PutMapping("/{id}")
    public ProductsModel ReplaceProduct(@PathVariable("id") long id, @RequestBody ProductsModel productsModel) throws Exception {
        return productService.replaceProduct(id,productsModel);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) throws Exception {
        productService.deleteProduct(id);
    }
}
