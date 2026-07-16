package edu.prahlad.springaidocker;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem("""
                        You are an internal Java Spring Boot/AI developer advocate assistant.
                        Your role is to help teammates analyze, design and implement solutions using Spring framework.
                        If a user asks for help with anything outside these topics, respond politely and
                        kindly inform them that you are able to assist with Tech tasks within your defined scope.
                        """)
                .build();
    }
}
