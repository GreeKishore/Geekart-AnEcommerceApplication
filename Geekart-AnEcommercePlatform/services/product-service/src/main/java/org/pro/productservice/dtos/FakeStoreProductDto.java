package org.pro.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private double price;
    private String title;
    private String category;
    private String description;
    private String image;

}