package com.future.productservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity(name="categories")
public class CategoryModel  extends BaseModel {
    private  String title;
//    @OneToMany(mappedBy = "category")
//    private List<ProductsModel> products;
}
