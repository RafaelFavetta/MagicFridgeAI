package com.example.magicfridgeai.controller;

import com.example.magicfridgeai.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    private final ChatGptService chatGptService;

    @GetMapping
    public Mono<ResponseEntity<String>> generateRecipe() {
        return chatGptService.generateRecipe();
    }
}
