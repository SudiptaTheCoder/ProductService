package com.future.productservice.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingModel extends BaseModel {
    private float rate;
    private int count;
}
