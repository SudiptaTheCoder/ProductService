package com.future.productservice.ProductInterfaces;

import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.ProductsModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductServiceInterface {
    List<ProductsModel> getProducts();
    ProductsModel getProductById(long id) throws CustomExceptions;
}
