package org.pro.productservice.entities;

import com.common.entites.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    @ManyToOne //id of one side on many side
    private Category category;
    private String image;
    private int quantity;
}