package com.future.productservice.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="ratings")
public class RatingModel extends BaseModel {
    private float rate;
    private int count;
}
