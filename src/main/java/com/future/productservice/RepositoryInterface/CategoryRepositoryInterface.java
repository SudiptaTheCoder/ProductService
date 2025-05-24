package com.future.productservice.RepositoryInterface;


import com.future.productservice.Models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepositoryInterface extends JpaRepository<CategoryModel,Long> {
    Optional<CategoryModel> findByTitle(String title);

    @Override
    CategoryModel save(CategoryModel categoryModel);
}
