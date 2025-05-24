package com.future.productservice.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsModel  extends BaseModel {
    private  String title;
    private  double price;
    private  String description;
    private  String image;
    private  CategoryModel category;
    private RatingModel rating;
}
