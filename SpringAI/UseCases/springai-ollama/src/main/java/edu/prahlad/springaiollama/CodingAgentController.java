package edu.prahlad.springaiollama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodingAgentController {

    private final ChatClient chatClient;

    // Inject the auto-configured ChatClient builder from Spring AI
    public CodingAgentController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("You are an autonomous engineering sub-agent. Use your available tools to manage code.")
                .build();
    }

    @GetMapping("/ai/agent/run")
    public String executeAgentTask(@RequestParam String userPrompt) {
        return this.chatClient.prompt()
                .user(userPrompt)
                // Register the skill bean name we created above
                .tools("scanWorkspaceCode")
                .call()
                .content();
    }
}
