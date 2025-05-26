package com.future.productservice.FakeStoreServices;

import com.future.productservice.Dtos.ProductDto;
import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.CategoryModel;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.Models.RatingModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductServiceInterface {

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<ProductsModel> getProducts(int pageNumber,int pageSize) throws Exception{

        List<ProductsModel> products = new ArrayList<>();
        ProductDto[] productDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        for(ProductDto productDto : productDtos) {
            products.add(convertProductDtoToProduct(productDto));
        }
        if(products.isEmpty()) {
            throw new Exception("No products are available");
        }

        return new PageImpl<>(products);
    }

    @Override
    public ProductsModel getProductById(long id) throws CustomExceptions {

        ProductsModel product = (ProductsModel) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_"+id);

        if(product != null){
            return product;
        }

        ProductDto prductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                ProductDto.class
        );
        if(prductDto == null) {
            throw new CustomExceptions("Product with id "+id+ " does not exist.");
        }
        product = convertProductDtoToProduct(prductDto);
        //insert into redis if not availabe
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_"+id,product);

        return product;
    }

    @Override
    public ProductsModel createProduct(ProductsModel product){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductsModel updateProduct(long id, ProductsModel product){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductsModel replaceProduct(long id, ProductsModel product){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteProduct(long id){
        throw new UnsupportedOperationException("Not supported yet.");
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
