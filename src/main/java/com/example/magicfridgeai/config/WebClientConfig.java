package com.example.magicfridgeai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${chatgpt.url:https://api.openai.com/v1/chat/completions}")
    private String chatGptUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(chatGptUrl).build();
    }
}
