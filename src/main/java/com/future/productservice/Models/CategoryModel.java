package com.future.productservice.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="categories")
public class CategoryModel  extends BaseModel {
    private  String title;
}
