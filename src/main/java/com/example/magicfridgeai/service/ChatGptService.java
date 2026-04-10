package com.example.magicfridgeai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final String apiKey;
    private final String model;

    public ChatGptService(
            WebClient webClient,
            @Value("${api.key}") String apiKey,
            @Value("${chatgpt.model:gpt-4o-mini}") String model
    ) {
        this.webClient = webClient;
        this.apiKey = apiKey;
        this.model = model;
    }

    public Mono<ResponseEntity<String>> generateRecipe() {
        String prompt = "Agora você é um chef de cozinha e vai me sugerir receitas com base na minha alergia a glúten";
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um chef de cozinha experiente. Sugira receitas criativas."),
                        Map.of("role", "user", "content", prompt))
        );

        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(this::toRecipeResponse)
                .onErrorResume(WebClientResponseException.class, ex -> Mono.just(
                        ResponseEntity.status(ex.getStatusCode())
                                .body("Erro na API OpenAI: " + ex.getStatusCode().value() + " - " + ex.getResponseBodyAsString())
                ))
                .onErrorResume(ex -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Erro inesperado ao gerar receita")
                ));
    }

    private ResponseEntity<String> toRecipeResponse(Map<?, ?> response) {
        Object choicesObj = response.get("choices");
        if (!(choicesObj instanceof List<?> choices) || choices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Sem resposta do agente");
        }

        Object firstChoice = choices.get(0);
        if (!(firstChoice instanceof Map<?, ?> choiceMap)) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Formato de resposta invalido");
        }

        Object messageObj = choiceMap.get("message");
        if (!(messageObj instanceof Map<?, ?> messageMap)) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Formato de mensagem invalido");
        }

        Object contentObj = messageMap.get("content");
        if (contentObj instanceof String content && !content.isBlank()) {
            return ResponseEntity.ok(content);
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Conteudo vazio na resposta do agente");
    }
}
