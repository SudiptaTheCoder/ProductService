package com.future.productservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity(name = "products")
public class ProductsModel  extends BaseModel {
    private  String title;
    private  double price;
    private  String description;
    private  String image;
    @ManyToOne
    private  CategoryModel category;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private RatingModel rating;
}
