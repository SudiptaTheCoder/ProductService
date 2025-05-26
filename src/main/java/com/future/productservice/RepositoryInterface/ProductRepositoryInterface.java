package com.future.productservice.RepositoryInterface;

import com.future.productservice.Models.ProductsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryInterface extends JpaRepository<ProductsModel,Long>{
    @Override
    Page<ProductsModel> findAll(Pageable pageable);

    @Override
    Optional<ProductsModel> findById(Long aLong);

    @Override
    ProductsModel save(ProductsModel productsModel);
}
