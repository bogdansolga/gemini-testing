package com.gemini.testing.config;

import com.google.cloud.vertexai.VertexAI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    @Bean
    public VertexAiGeminiChatModel googleLanguageModel() {
        return new VertexAiGeminiChatModel(new VertexAI("ai-query-hub", "europe-west-3"));
    }

    @Bean
    public ChatClient googleChatClient() {
        return ChatClient.builder(googleLanguageModel())
                         .build();
    }
}