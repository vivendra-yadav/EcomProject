package com.scaler.ecom.Modle;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    String title;
    double price;
    @ManyToOne
    Category category;
    String description;
    String image;
}