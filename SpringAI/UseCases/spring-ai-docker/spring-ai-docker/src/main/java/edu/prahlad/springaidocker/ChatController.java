package edu.prahlad.springaidocker;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message){
        return chatClient.prompt(message)
                .system("""
                        You are an internal Java Spring Boot/AI developer advocate assistant.
                        Your role is to help teammates analyze, design and implement solutions using Spring framework.
                        If a user asks for help with anything outside these topics,
                        kindly inform them that you can only assist with queries related to Java technologies.
                        """)
                .call().content();
    }
}
