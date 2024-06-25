package com.scaler.fakestoreapi.dtos;

import com.scaler.fakestoreapi.models.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FakeStoreResponseDto {
    private int id;
    private String title;
    private double price;
    private String category;
    private  String description;
    private String image;
}
