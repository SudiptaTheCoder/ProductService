package com.future.productservice.DbServices;

import com.future.productservice.Exceptions.CustomExceptions;
import com.future.productservice.Models.CategoryModel;
import com.future.productservice.Models.ProductsModel;
import com.future.productservice.Models.RatingModel;
import com.future.productservice.ProductInterfaces.ProductServiceInterface;
import com.future.productservice.RepositoryInterface.CategoryRepositoryInterface;
import com.future.productservice.RepositoryInterface.ProductRepositoryInterface;
import com.future.productservice.RepositoryInterface.RatingRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Primary
@Service("dbProductService")
public class DbProductService implements ProductServiceInterface {

    private ProductRepositoryInterface  productRepository;
    private CategoryRepositoryInterface categoryRepository;
    private RatingRepositoryInterface ratingRepository;

    public DbProductService(ProductRepositoryInterface productRepository,
                            CategoryRepositoryInterface categoryRepository,
                            RatingRepositoryInterface ratingRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<ProductsModel> getProducts() {
        return null;
    }

    @Override
    public ProductsModel getProductById(long id) throws CustomExceptions {

        Optional<ProductsModel> products = productRepository.findById(id);

        if(products.isEmpty()){
            throw new CustomExceptions("Product not found with id " + id);
        }
        ProductsModel product = products.get();

        return product;
    }

    @Override
    public ProductsModel createProduct(ProductsModel product) {
        CategoryModel categoryModel = product.getCategory();
        Optional<CategoryModel> category = categoryRepository.findByTitle(categoryModel.getTitle());

        if(category.isEmpty()){
            categoryModel = categoryRepository.save(categoryModel);
        }

        product.setCategory(categoryModel);

        return productRepository.save(product);
    }

    @Override
    public ProductsModel updateProduct(long id, ProductsModel product) {
        return null;
    }

    @Override
    public ProductsModel replaceProduct(long id, ProductsModel product) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }
}
