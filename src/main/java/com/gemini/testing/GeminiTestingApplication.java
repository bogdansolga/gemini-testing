package com.gemini.testing;

import com.google.cloud.vertexai.VertexAI;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeminiTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeminiTestingApplication.class, args);
	}

	@Value("${spring.ai.vertex.ai.gemini.projectId}")
	private String projectId;

	@Value("${spring.ai.vertex.ai.gemini.location}")
	private String location;

	@Bean
	public ApplicationRunner runner() {
		return args -> {
			VertexAI vertexApi =  new VertexAI(projectId, location);
			var chatModel = new VertexAiGeminiChatModel(vertexApi, getChatOptions());
			ChatResponse response = chatModel.call(new Prompt("Why is Gemini so difficult to configure?"));
			System.out.println(response.getResult()
									   .getOutput()
									   .getContent());
		};
	}

	private VertexAiGeminiChatOptions getChatOptions() {
		return VertexAiGeminiChatOptions.builder()
										.withTemperature(0.4f)
										.withModel(VertexAiGeminiChatModel.ChatModel.GEMINI_1_5_PRO)
										.build();
	}
}
