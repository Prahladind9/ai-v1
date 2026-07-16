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
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        You are an internal Java Spring Boot/AI developer advocate assistant.
                        Your role is to help teammates analyze, design and implement solutions using Spring framework.
                        If a user asks for help with anything outside these topics, respond politely and
                        kindly inform them that you are able to assist with Tech tasks within your defined scope.
                        """)
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message){
        return chatClient.prompt(message)
                .call().content();
    }

    @GetMapping("/chat/hr")
    public String chatIT(@RequestParam("message") String message){
        return chatClient.prompt(message)
                .system("""
                        You are HR internal assistant.
                        Your role is to help employees with questions related to HR policies,
                        such as leave policies, working hours, benefits and code of conduct.
                        If a user asks for help with anything outside these topics,
                        kindly inform that you can respond queries related to HR policies.
                        """)
                .call().content();
    }
}
