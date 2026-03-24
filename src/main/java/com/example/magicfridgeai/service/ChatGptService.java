package com.example.magicfridgeai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void generateRecipe() {
        String prompt = "Agora você é um chef de cozinha e vai me sugerir receitas com base nos ingredientes que vou te passar";
    }
}
