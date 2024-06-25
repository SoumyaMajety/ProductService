package com.scaler.fakestoreapi.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FakeStoreRequestDto {
    private String title;
    private double price;
    private String category;
    private  String description;
    private String image;
}
