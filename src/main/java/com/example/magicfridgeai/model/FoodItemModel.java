package com.example.magicfridgeai.model;

import com.example.magicfridgeai.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "food_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    private Integer quantity;
    private LocalDate expirationDate;
}
