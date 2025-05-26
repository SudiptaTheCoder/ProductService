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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<ProductsModel> getProducts(int pageNumber, int pageSize) throws Exception {
        Page<ProductsModel> products = productRepository.findAll(
                PageRequest.of(pageNumber, pageSize,
                        Sort.by(Sort.Direction.DESC, "title")
                        )
        );
        if(products.isEmpty()){
            throw new Exception("No products available right now.");
        }
        return products;
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
    public ProductsModel createProduct(ProductsModel product) throws Exception {
        if(product == null || product.getTitle() == null || product.getPrice() == 0){
            throw new RuntimeException("Product Details unAvailable");
        }
        CategoryModel category = product.getCategory();
        if(category != null){
            category = getCategoryByTitle(category);
        }
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public ProductsModel updateProduct(long id, ProductsModel product) throws Exception {
        throw  new UnsupportedOperationException("Not supported yet, insteed you can replace the product.");
    }

    @Override
    public ProductsModel replaceProduct(long id, ProductsModel product) throws Exception {
        Optional<ProductsModel> products = productRepository.findById(id);
        if(products.isEmpty()){
            throw new Exception("Product not found with id " + id);
        }
        if(product.getTitle() == null || product.getPrice() == 0){
            throw new RuntimeException("Product Details unAvailable");
        }

        CategoryModel category = product.getCategory();
        if(category != null){
            category = getCategoryByTitle(category);
        }
        product.setCategory(category);


        ProductsModel productFromDb =  products.get();
        productFromDb.setCategory(product.getCategory());
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setDescription(product.getDescription());

        productFromDb.setRating(product.getRating());
        productFromDb.setImage(product.getImage());

        return productRepository.save(productFromDb);
    }

    @Override
    public void deleteProduct(long id) throws Exception {
        Optional<ProductsModel> products = productRepository.findById(id);
        if(products.isEmpty()){
            throw new Exception("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    private CategoryModel getCategoryByTitle(CategoryModel title) throws CustomExceptions {
        CategoryModel category = null;
        if(title == null){
            throw new CustomExceptions("Category Details unAvailable");
        }
        Optional<CategoryModel> optionalCategory = categoryRepository.findByTitle(title.getTitle());
        if(optionalCategory.isEmpty()){
            category = categoryRepository.save(title);
        }
        else {
            category = optionalCategory.get();
        }
        return category;
    }
}
