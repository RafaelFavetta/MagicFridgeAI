package com.example.magicfridgeai.controller;

import com.example.magicfridgeai.model.FoodItemModel;
import com.example.magicfridgeai.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    public ResponseEntity<FoodItemModel> save(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel saved = foodItemService.save(foodItemModel);
        return ResponseEntity.ok(saved);
    }
}
