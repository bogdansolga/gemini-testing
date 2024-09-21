package com.gemini.testing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeminiTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeminiTestingApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(ChatClient client) {
		return args -> {
			ChatClient.CallPromptResponseSpec responseSpec = client.prompt(new Prompt(
																		   "Why is Gemini so difficult to configure?", getChatOptions()))
																   .call();
			System.out.println(responseSpec.content());
		};
	}

	private VertexAiGeminiChatOptions getChatOptions() {
		return VertexAiGeminiChatOptions.builder()
										.withTemperature(0.4f)
										.build();
	}
}
