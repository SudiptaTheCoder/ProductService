package com.future.productservice.FakeStoreServices;

import com.future.productservice.Dtos.ProductDto;
import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.CategoryModel;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.Models.RatingModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductServiceInterface {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductsModel> getProducts() throws Exception{

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
    public ProductsModel getProductById(long id) throws CustomExceptions {

        ProductDto prductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                ProductDto.class
        );
        if(prductDto == null) {
            throw new CustomExceptions("Product with id "+id+ " does not exist.");
        }
        return convertProductDtoToProduct(prductDto);
    }

    @Override
    public ProductsModel createProduct(ProductsModel product){
        return  null;
    }

    @Override
    public ProductsModel updateProduct(long id, ProductsModel product){
        return null;
    }

    @Override
    public ProductsModel replaceProduct(long id, ProductsModel product){
        return  null;
    }

    @Override
    public void deleteProduct(long id){

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
