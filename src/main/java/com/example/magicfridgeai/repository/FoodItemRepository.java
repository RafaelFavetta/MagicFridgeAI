package com.example.magicfridgeai.repository;

import com.example.magicfridgeai.model.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemModel, UUID> {
}
