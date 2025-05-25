package com.future.productservice.ProductInterfaces;

import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.ProductsModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductServiceInterface {
    List<ProductsModel> getProducts() throws Exception;
    ProductsModel getProductById(long id) throws CustomExceptions;
    ProductsModel createProduct(ProductsModel product) throws Exception;
    ProductsModel updateProduct(long id,ProductsModel product) throws Exception;
    ProductsModel replaceProduct(long id,ProductsModel product) throws Exception;
    void deleteProduct(long id) throws Exception;
}
