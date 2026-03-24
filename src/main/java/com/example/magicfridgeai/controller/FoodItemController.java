package com.example.magicfridgeai.controller;

import com.example.magicfridgeai.model.FoodItemModel;
import com.example.magicfridgeai.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItemModel> save(@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel saved = foodItemService.save(foodItemModel);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemModel>> findAll() {
        List<FoodItemModel> foodItemModels = foodItemService.findAll();
        return ResponseEntity.ok(foodItemModels);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        foodItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemModel> update(@RequestBody FoodItemModel foodItemModel, @PathVariable UUID id) {
        return foodItemService.findById(id)
                .map(existing -> {
                    foodItemModel.setId(existing.getId());
                    FoodItemModel updated = foodItemService.update(foodItemModel);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
