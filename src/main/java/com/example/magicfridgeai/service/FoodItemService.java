package com.example.magicfridgeai.service;

import com.example.magicfridgeai.model.FoodItemModel;
import com.example.magicfridgeai.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemModel save(FoodItemModel foodItemModel) {
        return foodItemRepository.save(foodItemModel);
    }

    public List<FoodItemModel> findAll() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItemModel> findById(UUID id) {
        return foodItemRepository.findById(id);
    }

    public void deleteById(UUID id) {
        foodItemRepository.deleteById(id);
    }
}
