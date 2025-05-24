package com.future.productservice.RepositoryInterface;

import com.future.productservice.Models.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepositoryInterface extends JpaRepository<RatingModel,Long> {

    @Override
    RatingModel save(RatingModel ratingModel);
}
