package com.future.productservice.FakeStoreServices;

import com.future.productservice.Configurations.RestTemplateConfig;
import com.future.productservice.Dtos.ProductDto;
import com.future.productservice.Models.CategoryModel;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.Models.RatingModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductServiceInterface {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductsModel> getProducts() {

        List<ProductsModel> products = new ArrayList<>();
        ProductDto[] productDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        for(ProductDto productDto : productDtos) {
            products.add(convertProductDtoToProduct(productDto));
        }

        return products;
    }

    @Override
    public ProductsModel getProductById(long id) {

        ProductDto prductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                ProductDto.class
        );
        return convertProductDtoToProduct(prductDto);
    }

    private ProductsModel convertProductDtoToProduct(ProductDto productDto) {
        ProductsModel productsModel = new ProductsModel();

        productsModel.setId(productDto.getId());
        productsModel.setTitle(productDto.getTitle());
        productsModel.setDescription(productDto.getDescription());
        productsModel.setPrice(productDto.getPrice());
        productsModel.setImage(productDto.getImage());
        productsModel.setCategory(new CategoryModel());
        productsModel.getCategory().setTitle(productDto.getCategory());
        productsModel.setRating(new RatingModel());
        productsModel.getRating().setRate(productDto.getRating().getRate());
        productsModel.getRating().setCount(productDto.getRating().getCount());

        return productsModel;
    }
}
