package com.scaler.fakestoreapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {

    private String title;
    private double price;
    @ManyToOne
    private Category category;
    private  String description;
    private String image;
}
