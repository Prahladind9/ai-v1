package edu.prahlad.springaidocker.controller;


import edu.prahlad.springaidocker.service.RAGService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final RAGService ragService;

    public RAGController(RAGService ragService) {
        this.ragService = ragService;
    }

    @Value("classpath:/promptTemplates/systemPromptRandomDataTemplate.st")
    Resource promptTemplate;

    @GetMapping("random/chat")
    public ResponseEntity<String> randomChat(@RequestParam("message") String message) {
        String result = ragService.randomChat(message);
        return ResponseEntity.ok(result);
    }

    @GetMapping("random/withPrompt/chat")
    public ResponseEntity<String> randomChatWithSystemPrompt(@RequestParam("message") String message) {
        String result = ragService.randomChatWithSystemPrompt(message, promptTemplate);
        return ResponseEntity.ok(result);
    }

    @GetMapping("pdf/chat")
    public ResponseEntity<String> pdfChat(@RequestParam("message") String message) {
        String result = ragService.pdfChat(message, promptTemplate);
        return ResponseEntity.ok(result);
    }

}
