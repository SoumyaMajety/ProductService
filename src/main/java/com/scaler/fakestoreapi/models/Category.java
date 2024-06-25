package com.scaler.fakestoreapi.models;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String title;

}
